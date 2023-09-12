package org.example.View;


import org.example.model.Employee;
import org.example.Controller.EmployeeController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EmployeeView {

    private static final EmployeeController employeeController = new EmployeeController();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    viewAllEmployees();
                    break;
                case 2:
                    addEmployee(scanner);
                    break;
                case 3:
                    editEmployee(scanner);
                    break;
                case 4:
                    deleteEmployee(scanner);
                    break;
                case 5:
                    MainView.main(args);
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nEmployee Management System");
        System.out.println("1. View All Employees");
        System.out.println("2. Add Employee");
        System.out.println("3. Update Employee");
        System.out.println("4. Delete Employee");
        System.out.println("5. Return to Home Page");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void viewAllEmployees() {
        List<Employee> employees = employeeController.getAllEmployees();
        System.out.println("\nList of Employees:");
        for (Employee employee : employees) {
            System.out.println(employeeToString(employee));
        }
    }

    private static void addEmployee(Scanner scanner) {
        Employee employee = new Employee();
        System.out.println("\nEnter Employee Details:");

        System.out.print("Employee ID: ");
        employee.setEmpId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Department ID: ");
        employee.setDeptId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Employee Name: ");
        employee.setEmpName(scanner.nextLine());

        System.out.print("Employee Number: ");
        employee.setEmpNo(scanner.nextLine());

        System.out.print("Hire Date (YYYY-MM-DD): ");
        String hireDateStr = scanner.nextLine();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date hireDate = dateFormat.parse(hireDateStr);
            employee.setHireDate(hireDate);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }


        employee.setImage(null);

        System.out.print("Job: ");
        employee.setJob(scanner.nextLine());

        System.out.print("Manager ID: ");
        BigDecimal mngId = scanner.nextBigDecimal();
        scanner.nextLine();
        employee.setMngId(mngId);

        System.out.print("Salary: ");
        employee.setSalary(scanner.nextFloat());
        scanner.nextLine();
        employeeController.addEmployee(employee);



    }

    private static void editEmployee(Scanner scanner) {
        System.out.print("\nEnter Employee ID to edit: ");
        int empId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Employee existingEmployee = employeeController.getEmployeeById(empId);
        if (existingEmployee == null) {
            System.out.println("Employee not found!");
            return;
        }

        System.out.println("Editing Employee ID " + empId + ":");

        System.out.print("New Department ID: ");
        int newDeptId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        existingEmployee.setDeptId(newDeptId);

        System.out.print("New Employee Name: ");
        String newEmpName = scanner.nextLine();
        existingEmployee.setEmpName(newEmpName);

        System.out.print("New Employee Number: ");
        String newEmpNo = scanner.nextLine();
        existingEmployee.setEmpNo(newEmpNo);

        System.out.print("New Hire Date (YYYY-MM-DD): ");
        String newHireDateStr = scanner.nextLine();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date newHireDate = dateFormat.parse(newHireDateStr);
            existingEmployee.setHireDate(newHireDate);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        // Set the image to null for now (you can add functionality to update it as well)
        existingEmployee.setImage(null);

        System.out.print("New Job: ");
        String newJob = scanner.nextLine();
        existingEmployee.setJob(newJob);

        System.out.print("New Manager ID: ");
        BigDecimal newMngId = scanner.nextBigDecimal();
        scanner.nextLine(); // Consume the newline character
        existingEmployee.setMngId(newMngId);

        System.out.print("New Salary: ");
        float newSalary = scanner.nextFloat();
        scanner.nextLine(); // Consume the newline character
        existingEmployee.setSalary(newSalary);
        employeeController.updateEmployee(existingEmployee);



    }

    private static void deleteEmployee(Scanner scanner) {
        System.out.print("\nEnter Employee ID to delete: ");
        int empId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Employee existingEmployee = employeeController.getEmployeeById(empId);
        if (existingEmployee == null) {
            System.out.println("Employee not found!");
            return;
        }

        employeeController.deleteEmployee(empId);
        System.out.println("Employee deleted successfully!");
    }

    private static String employeeToString(Employee employee) {
        StringBuilder builder = new StringBuilder();
        builder.append("Employee ID: ").append(employee.getEmpId()).append("\n");
        builder.append("Department ID: ").append(employee.getDeptId()).append("\n");
        builder.append("Employee Name: ").append(employee.getEmpName()).append("\n");
        builder.append("Employee Number: ").append(employee.getEmpNo()).append("\n");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        builder.append("Hire Date: ").append(dateFormat.format(employee.getHireDate())).append("\n");
        builder.append("Image: NULL\n");
        builder.append("Job: ").append(employee.getJob()).append("\n");
        builder.append("Manager ID: ").append(employee.getMngId()).append("\n");
        builder.append("Salary: ").append(employee.getSalary()).append("\n");

        return builder.toString();
    }
}
