package org.example.View;

import org.example.Controller.DepartmentController;
import org.example.model.Department;

import java.util.List;
import java.util.Scanner;

public class DepartmentView {
    private static DepartmentController departmentController = new DepartmentController();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nDepartment Management Menu:");
            System.out.println("1. List All Departments");
            System.out.println("2. Add Department");
            System.out.println("3. Edit Department");
            System.out.println("4. Delete Department");
            System.out.println("5. Return to Home Page");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    listAllDepartments();
                    break;
                case 2:
                    addDepartment(scanner);
                    break;
                case 3:
                    editDepartment(scanner);
                    break;
                case 4:
                    deleteDepartment(scanner);
                    break;
                case 5:
                    MainView.main(args);
                    break;
                case 6:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }


    private static void listAllDepartments() {
        List<Department> departments = departmentController.getAllDepartments();
        System.out.println("\nList of All Departments:");
        for (Department department : departments) {
            System.out.println("Department ID: " + department.getDeptId());
            System.out.println("Department Name: " + department.getDeptName());
            System.out.println("Department Number: " + department.getDeptNo());
            System.out.println("Location: " + department.getLocation());
            System.out.println("-------------");
        }
    }

    private static void addDepartment(Scanner scanner) {
        Department department = new Department();
        System.out.println("\nEnter Department Details:");

        System.out.print("Department ID: ");
        department.setDeptId(scanner.nextInt());
        scanner.nextLine(); // Consume the newline character

        System.out.print("Department Name: ");
        department.setDeptName(scanner.nextLine());

        System.out.print("Department Number: ");
        department.setDeptNo(scanner.nextLine());

        System.out.print("Location: ");
        department.setLocation(scanner.nextLine());

        departmentController.addDepartment(department);

    }

    private static void editDepartment(Scanner scanner) {
        System.out.print("\nEnter Department ID to edit: ");
        int deptId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Department existingDepartment = departmentController.getDepartmentById(deptId);
        if (existingDepartment == null) {
            System.out.println("Department not found!");
            return;
        }

        System.out.println("Editing Department ID " + deptId + ":");
        System.out.print("New Department Name: ");
        String newDeptName = scanner.nextLine();
        existingDepartment.setDeptName(newDeptName);

        System.out.print("New Department Number: ");
        String newDeptNo = scanner.nextLine();
        existingDepartment.setDeptNo(newDeptNo);

        System.out.print("New Location: ");
        String newLocation = scanner.nextLine();
        existingDepartment.setLocation(newLocation);

        departmentController.updateDepartment(existingDepartment);

    }

    private static void deleteDepartment(Scanner scanner) {
        System.out.print("\nEnter Department ID to delete: ");
        int deptId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Department existingDepartment = departmentController.getDepartmentById(deptId);
        if (existingDepartment == null) {
            System.out.println("Department not found!");
            return;
        }

        departmentController.deleteDepartment(deptId);
        System.out.println("Department deleted successfully!");
    }
}
