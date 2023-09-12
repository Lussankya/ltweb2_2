package org.example.Controller;

import org.example.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.DatabaseConnection.getConnection;

public class DepartmentController {


    // Add a new department
    public static void addDepartment(Department department) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO DEPARTMENT (DEPT_ID, DEPT_NAME, DEPT_NO, LOCATION) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setInt(1, department.getDeptId());
            preparedStatement.setString(2, department.getDeptName());
            preparedStatement.setString(3, department.getDeptNo());
            preparedStatement.setString(4, department.getLocation());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Department added successfully!");
            } else {
                System.out.println("Error: Failed to add Department.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: SQL Exception occurred.");
        }
    }

    // Update an existing department
    public static void updateDepartment(Department department) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE DEPARTMENT SET DEPT_NAME = ?, DEPT_NO = ?, LOCATION = ? WHERE DEPT_ID = ?")) {

            preparedStatement.setString(1, department.getDeptName());
            preparedStatement.setString(2, department.getDeptNo());
            preparedStatement.setString(3, department.getLocation());
            preparedStatement.setInt(4, department.getDeptId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Department updated successfully!");
            } else {
                System.out.println("Error: Failed to update Department.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: SQL Exception occurred.");
        }
    }

    // Delete a department by ID
    public static void deleteDepartment(int deptId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM DEPARTMENT WHERE DEPT_ID = ?")) {

            preparedStatement.setInt(1, deptId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get a department by ID
    public static Department getDepartmentById(int deptId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM DEPARTMENT WHERE DEPT_ID = ?")) {

            preparedStatement.setInt(1, deptId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Department department = new Department();
                    department.setDeptId(resultSet.getInt("DEPT_ID"));
                    department.setDeptName(resultSet.getString("DEPT_NAME"));
                    department.setDeptNo(resultSet.getString("DEPT_NO"));
                    department.setLocation(resultSet.getString("LOCATION"));
                    return department;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Department with the specified ID was not found
    }

    // Get a list of all departments
    public static List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM DEPARTMENT")) {

            while (resultSet.next()) {
                Department department = new Department();
                department.setDeptId(resultSet.getInt("DEPT_ID"));
                department.setDeptName(resultSet.getString("DEPT_NAME"));
                department.setDeptNo(resultSet.getString("DEPT_NO"));
                department.setLocation(resultSet.getString("LOCATION"));
                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }
}

