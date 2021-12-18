package net.daventi.dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.daventi.model.Employee;

public class LoginDAO {
    public Employee checkLogin(String email, String password) throws SQLException,
    ClassNotFoundException {
    	String LOGIN_SQL = "SELECT * FROM employee WHERE email_employee = ? and password_employee = ?";
	
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Employee employee = null;

		Connection connection = DriverManager.getConnection(DaVentiConstants.URL, DaVentiConstants.USER, DaVentiConstants.PASS);
		PreparedStatement statement = connection.prepareStatement(LOGIN_SQL);
		statement.setString(1, email);
		statement.setString(2, password);
		
		ResultSet result = statement.executeQuery();
						
		if (result.next()) {
		    employee = new Employee();
		    employee.setName_employee(result.getString("name_employee"));
		    employee.setEmail_employee(email);
		}
		
		connection.close();
		
		return employee;
    }
}
