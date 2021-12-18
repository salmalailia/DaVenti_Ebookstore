package net.daventi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import net.daventi.model.Category;

public class CategoryDAO {
	private static final String INSERT_CATEGORY_SQL = "INSERT INTO category" + "  (id_category, name_category) VALUES "
			+ " (?, ?);";
	
	private static final String SELECT_ALL_CATEGORY = "select * from category";
	private static final String DELETE_CATEGORY_SQL = "delete from category where id_category = ?;";
	private static final String UPDATE_CATEGORY_SQL = "update category set name_category = ? where id_category = ?;";
	private static final String SELECT_CATEGORY_BY_ID = "select id_category,name_category from category where id_category =?";
	
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

	
	// create category
	public void insertCategory(Category category) throws SQLException{
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY_SQL);){
			preparedStatement.setString(1, category.getId_category());
			preparedStatement.setString(2, category.getName_category());
			preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// update category
	public boolean updateCategory(Category category) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY_SQL);){
			statement.setString(1, category.getName_category());
			statement.setString(2, category.getId_category());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	// select category
	public List<Category> selectAllCategory() {
		List<Category> category = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String id_category = rs.getString("id_category");
				String name_category = rs.getString("name_category");
				category.add(new Category(id_category, name_category));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return category;
	}

	// delete category
	public boolean deleteCategory(String id_category) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY_SQL);) {
			statement.setString(1, id_category);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	// select category by id
	public Category selectCategory(String id_category) {
		Category category = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID);) {
			preparedStatement.setString(1, id_category);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name_category = rs.getString("name_category");
				category = new Category(id_category, name_category);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return category;
	}


	private void printSQLException(SQLException e) {
		// TODO Auto-generated method stub
		
	}

}
