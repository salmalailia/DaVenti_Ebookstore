package net.daventi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.daventi.model.Status;

public class StatusDAO {
	private static final String INSERT_STATUS_SQL = "INSERT INTO order_status" + "  (id_status, name_status) VALUES "
			+ " (?, ?);";
	
	private static final String SELECT_ALL_STATUS = "select * from order_status";
	private static final String DELETE_STATUS_SQL = "delete from order_status where id_status = ?;";
	private static final String UPDATE_STATUS_SQL = "update order_status set name_status = ? where id_status = ?;";
	private static final String SELECT_STATUS_BY_ID = "select id_status,name_status from order_status where id_status =?";
	
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

	public void insertStatus(Status status) throws SQLException{
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATUS_SQL);){
			preparedStatement.setString(1, status.getId_status());
			preparedStatement.setString(2, status.getName_status());
			preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean updateStatus(Status status) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS_SQL);){
			statement.setString(1, status.getName_status());
			statement.setString(2, status.getId_status());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public List<Status> selectAllStatus() {
		List<Status> status = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STATUS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String id_status = rs.getString("id_status");
				String name_status = rs.getString("name_status");
				status.add(new Status(id_status, name_status));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return status;
	}

	public boolean deleteStatus(String id_status) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_STATUS_SQL);) {
			statement.setString(1, id_status);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public Status selectStatus(String id_status) {
		Status status = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STATUS_BY_ID);) {
			preparedStatement.setString(1, id_status);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name_status = rs.getString("name_status");
				status = new Status(id_status, name_status);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return status;
	}


	private void printSQLException(SQLException e) {
		// TODO Auto-generated method stub
		
	}
}
