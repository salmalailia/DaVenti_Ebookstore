package net.daventi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.daventi.model.Employee;

public class EmployeeDAO {
    public int registerEmployee(Employee employee) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO employee" +
            "  (id_employee, name_employee, email_employee, password_employee, gender, phone_num_employee, role) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?);";

        int result = 0;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection(DaVentiConstants.URL, DaVentiConstants.USER, DaVentiConstants.PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, employee.getId_employee());
            preparedStatement.setString(2, employee.getName_employee());
            preparedStatement.setString(3, employee.getEmail_employee());
            preparedStatement.setString(4, employee.getPassword_employee());
            preparedStatement.setString(5, employee.getGender());
            preparedStatement.setString(6, employee.getPhone_num_employee());
            preparedStatement.setString(7, employee.getRole());

            System.out.println(preparedStatement);

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
