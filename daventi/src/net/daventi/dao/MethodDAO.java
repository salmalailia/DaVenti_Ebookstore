package net.daventi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.daventi.model.Method;

public class MethodDAO {
	private static final String INSERT_METHOD_SQL = "INSERT INTO payment_method" + "  (id_method, name_method, dest_bank) VALUES "
			+ " (?, ?, ?);";
	
	private static final String SELECT_ALL_METHOD = "select * from payment_method";
	private static final String DELETE_METHOD_SQL = "delete from payment_method where id_method = ?;";
	private static final String UPDATE_METHOD_SQL = "update payment_method set name_method = ?, dest_bank = ? where id_method = ?;";
	private static final String SELECT_METHOD_BY_ID = "select id_method,name_method,dest_bank from payment_method where id_method =?";
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DaVentiConstants.URL, DaVentiConstants.USER, DaVentiConstants.PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	
	// create method
	public void insertMethod(Method method) throws SQLException{
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_METHOD_SQL);){
			preparedStatement.setString(1, method.getId_method());
			preparedStatement.setString(2, method.getName_method());
			preparedStatement.setString(3, method.getDest_bank());
			preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// update method
	public boolean updateMethod(Method method) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_METHOD_SQL);){
			statement.setString(1, method.getName_method());
			statement.setString(2, method.getDest_bank());
			statement.setString(3, method.getId_method());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	// select method
	public List<Method> selectAllMethod() {
		List<Method> method = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_METHOD);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String id_method = rs.getString("id_method");
				String name_method = rs.getString("name_method");
				String dest_bank = rs.getString("dest_bank");

				method.add(new Method(id_method, name_method, dest_bank));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return method;
	}

	// delete method
	public boolean deleteMethod(String id_method) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_METHOD_SQL);) {
			statement.setString(1, id_method);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	// select method by id
	public Method selectMethod(String id_method) {
		Method method = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_METHOD_BY_ID);) {
			preparedStatement.setString(1, id_method);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name_method = rs.getString("name_method");
				String dest_bank = rs.getString("dest_bank");

				method = new Method(id_method, name_method, dest_bank);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return method;
	}


	private void printSQLException(SQLException e) {
		// TODO Auto-generated method stub
		
	}

}
