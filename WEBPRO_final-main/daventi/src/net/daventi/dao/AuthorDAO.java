package net.daventi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.daventi.model.Author;

public class AuthorDAO {
	private static final String INSERT_AUTHOR_SQL = "INSERT INTO author" + "(id_author, name_author) VALUES" + "(?, ?);";
	
	private static final String SELECT_AUTHOR_BY_ID = "select id_author, name_author from author where id_author=?";
	private static final String SELECT_ALL_AUTHOR = "select * from author";
	private static final String DELETE_AUTHOR_SQL = "delete from author where id_author=?;";
	private static final String UPDATE_AUTHOR_SQL = "update author set name_author=? where id_author=?;";
	
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

	
	// insert Author
	public void insertAuthor (Author author) throws SQLException {
		System.out.println(INSERT_AUTHOR_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUTHOR_SQL)) {
			preparedStatement.setString(1, author.getId_author());
			preparedStatement.setString(2, author.getName_author());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	// update author
	public boolean updateAuthor (Author author) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_AUTHOR_SQL);) {
			System.out.println("updated Author: "+ statement);
			statement.setString(1, author.getName_author());
			statement.setString(2, author.getId_author());
			
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	// select all author
	public List<Author> selectAllAuthor() {
		List<Author> authors = new ArrayList<Author>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_AUTHOR);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
					
			while (rs.next()) {
				String id_author = rs.getString("id_author");
				String name_author = rs.getString("name_author");
				authors.add(new Author(id_author, name_author));
			}
		} catch (SQLException e) {
			printSQLException(e);;
		}
		return authors;
	}
	
	// delete author
	public boolean deleteAuthor(String id_author) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_AUTHOR_SQL);) {
			statement.setString(1, id_author);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	// select Author by id
	public Author selectAuthor(String id_author) {
		Author author = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUTHOR_BY_ID);) {
			preparedStatement.setString(1, id_author);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				String name_author = rs.getString("name_author");
				author = new Author(id_author, name_author);
			}
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		return author;
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
