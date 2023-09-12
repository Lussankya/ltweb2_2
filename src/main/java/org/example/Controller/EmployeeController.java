package org.example.Controller;

import org.example.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.DatabaseConnection.getConnection;

public class EmployeeController {

    public static void addEmployee(Employee employee) {
        // Check if the department ID exists
        if (!isDepartmentIdExists(employee.getDeptId())) {
            System.out.println("Error: Department with ID " + employee.getDeptId() + " does not exist.");
            return ;
        }

        // Check if the salary is within the valid range
        if (!isSalaryInRange(employee.getSalary())) {
            System.out.println("Error: Employee salary is not within the valid range.");
            return ;
        }

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO EMPLOYEE (EMP_ID, DEPT_ID, EMP_NAME, EMP_NO, HIRE_DATE, IMAGE, JOB, MNG_ID, SALARY) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setInt(1, employee.getEmpId());
            preparedStatement.setInt(2, employee.getDeptId());
            preparedStatement.setString(3, employee.getEmpName());
            preparedStatement.setString(4, employee.getEmpNo());

            if (employee.getHireDate() != null) {
                preparedStatement.setDate(5, new java.sql.Date(employee.getHireDate().getTime()));
            } else {
                preparedStatement.setNull(5, Types.DATE);
            }

            preparedStatement.setBytes(6, employee.getImage());
            preparedStatement.setString(7, employee.getJob());
            preparedStatement.setBigDecimal(8, employee.getMngId());
            preparedStatement.setFloat(9, employee.getSalary());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee added successfully!");
            } else {
                System.out.println("Error: Failed to add Employee.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: SQL Exception occurred.");
        }
    }



    // Update an existing employee
    public static void updateEmployee(Employee employee) {
        // Check if the department ID exists
        if (!isDepartmentIdExists(employee.getDeptId())) {
            System.out.println("Error: Department with ID " + employee.getDeptId() + " does not exist.");
            return ;
        }

        // Check if the salary is within the valid range
        if (!isSalaryInRange(employee.getSalary())) {
            System.out.println("Error: Employee salary is not within the valid range.");
            return ;
        }

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE EMPLOYEE SET DEPT_ID=?, EMP_NAME=?, EMP_NO=?, HIRE_DATE=?, IMAGE=?, JOB=?, MNG_ID=?, SALARY=? " +
                             "WHERE EMP_ID=?")) {

            preparedStatement.setInt(1, employee.getDeptId());
            preparedStatement.setString(2, employee.getEmpName());
            preparedStatement.setString(3, employee.getEmpNo());

            if (employee.getHireDate() != null) {
                preparedStatement.setDate(4, new java.sql.Date(employee.getHireDate().getTime()));
            } else {
                preparedStatement.setNull(4, Types.DATE);
            }

            preparedStatement.setBytes(5, employee.getImage());
            preparedStatement.setString(6, employee.getJob());
            preparedStatement.setBigDecimal(7, employee.getMngId());
            preparedStatement.setFloat(8, employee.getSalary());
            preparedStatement.setInt(9, employee.getEmpId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee updated successfully!");
            } else {
                System.out.println("Error: Failed to update Employee.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: SQL Exception occurred.");
        }
    }

    // Delete an employee by ID
    public static void deleteEmployee(int empId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM EMPLOYEE WHERE EMP_ID=?")) {

            preparedStatement.setInt(1, empId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all employees
    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM EMPLOYEE");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmpId(resultSet.getInt("EMP_ID"));
                employee.setDeptId(resultSet.getInt("DEPT_ID"));
                employee.setEmpName(resultSet.getString("EMP_NAME"));
                employee.setEmpNo(resultSet.getString("EMP_NO"));
                employee.setHireDate(resultSet.getDate("HIRE_DATE"));
                employee.setImage(resultSet.getBytes("IMAGE"));
                employee.setJob(resultSet.getString("JOB"));
                employee.setMngId(resultSet.getBigDecimal("MNG_ID"));
                employee.setSalary(resultSet.getFloat("SALARY"));

                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // Get an employee by their ID
    public static Employee getEmployeeById(int empId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?")) {

            preparedStatement.setInt(1, empId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setEmpId(resultSet.getInt("EMP_ID"));
                    employee.setDeptId(resultSet.getInt("DEPT_ID"));
                    employee.setEmpName(resultSet.getString("EMP_NAME"));
                    employee.setEmpNo(resultSet.getString("EMP_NO"));
                    employee.setHireDate(resultSet.getDate("HIRE_DATE"));
                    employee.setImage(resultSet.getBytes("IMAGE"));
                    employee.setJob(resultSet.getString("JOB"));
                    employee.setMngId(resultSet.getBigDecimal("MNG_ID"));
                    employee.setSalary(resultSet.getFloat("SALARY"));
                    return employee;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Employee with the specified ID was not found
    }
    // Check if a department ID exists
    private static boolean isDepartmentIdExists(int deptId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM DEPARTMENT WHERE DEPT_ID = ?")) {

            preparedStatement.setInt(1, deptId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Check if the salary is within the valid range
    private static boolean isSalaryInRange(float salary) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM SALARY_GRADE WHERE ? BETWEEN LOW_SALARY AND HIGH_SALARY")) {

            preparedStatement.setFloat(1, salary);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

