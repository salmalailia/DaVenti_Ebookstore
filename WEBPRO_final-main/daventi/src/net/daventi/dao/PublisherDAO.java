package net.daventi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.daventi.model.Publisher;

public class PublisherDAO {
	private static final String INSERT_PUBLISHER_SQL = "INSERT INTO publisher" + "(id_publisher, name_publisher, email_publisher) VALUES" + "(?, ?, ?);";
	
	private static final String SELECT_PUBLISHER_BY_ID = "select id_publisher, name_publisher, email_publisher from publisher where id_publisher=?";
	private static final String SELECT_ALL_PUBLISHER = "select * from publisher";
	private static final String DELETE_PUBLISHER_SQL = "delete from publisher where id_publisher=?;";
	private static final String UPDATE_PUBLISHER_SQL = "update publisher set name_publisher=?, email_publisher=? where id_publisher=?;";
	
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

	
	// insert Publisher
	public void insertPublisher (Publisher publisher) throws SQLException {
		System.out.println(INSERT_PUBLISHER_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PUBLISHER_SQL)) {
			preparedStatement.setString(1, publisher.getId_publisher());
			preparedStatement.setString(2, publisher.getName_publisher());
			preparedStatement.setString(3, publisher.getEmail_publisher());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	// update publisher
	public boolean updatePublisher (Publisher publisher) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PUBLISHER_SQL);) {
			System.out.println("updated Publisher: "+ statement);
			statement.setString(1, publisher.getName_publisher());
			statement.setString(2, publisher.getEmail_publisher());
			statement.setString(3, publisher.getId_publisher());
			
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	// select all publisher
	public List<Publisher> selectAllPublisher() {
		List<Publisher> publishers = new ArrayList<Publisher>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PUBLISHER);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
					
			while (rs.next()) {
				String id_publisher = rs.getString("id_publisher");
				String name_publisher = rs.getString("name_publisher");
				String email_publisher = rs.getString("email_publisher");
				publishers.add(new Publisher(id_publisher, name_publisher, email_publisher));
			}
		} catch (SQLException e) {
			printSQLException(e);;
		}
		return publishers;
	}
	
	// delete publisher
	public boolean deletePublisher(String id_publisher) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PUBLISHER_SQL);) {
			statement.setString(1, id_publisher);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	// select publisher by id
	public Publisher selectPublisher(String id_publisher) {
		Publisher publisher = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PUBLISHER_BY_ID);) {
			preparedStatement.setString(1, id_publisher);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				String name_publisher = rs.getString("name_publisher");
				String email_publisher = rs.getString("email_publisher");
				publisher = new Publisher(id_publisher, name_publisher, email_publisher);
			}
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		return publisher;
	}


	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Casue: " + t);
					t = t.getCause();
				}
			}
		}
		
	} 

}
