package net.daventi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.daventi.model.Promo;

public class PromoDAO {
	private String url = "jdbc:mysql://localhost:3306/DaVentiDB";
	private String user = "root";
	private String pass = "";
	
	private static final String INSERT_PROMO_SQL = "INSERT INTO promo" + "  (id_promo, name_promo, discount, start_discount, end_discount) VALUES "
			+ " (?, ?, ?, ?, ?);";
	
	private static final String SELECT_ALL_PROMO = "select * from promo";
	private static final String DELETE_PROMO_SQL = "delete from promo where id_promo = ?;";
	private static final String UPDATE_PROMO_SQL = "update promo set name_promo = ?, discount = ?, start_discount = ?, end_discount = ? where id_promo = ?;";
	private static final String SELECT_PROMO_BY_ID = "select id_promo,name_promo, discount, start_discount, end_discount from promo where id_promo =?";
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	
	// create promo
	public void insertPromo(Promo promo) throws SQLException{
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROMO_SQL);){
			preparedStatement.setString(1, promo.getId_promo());
			preparedStatement.setString(2, promo.getName_promo());
			preparedStatement.setFloat(3, promo.getDiscount());
			preparedStatement.setString(4, promo.getStart_discount());
			preparedStatement.setString(5, promo.getEnd_discount());

			preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// update promo
	public boolean updatePromo(Promo promo) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PROMO_SQL);){
			statement.setString(1, promo.getName_promo());
			statement.setFloat(2, promo.getDiscount());
			statement.setString(3, promo.getStart_discount());
			statement.setString(4, promo.getEnd_discount());
			statement.setString(5, promo.getId_promo());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	// select promo
	public List<Promo> selectAllPromo() {
		List<Promo> promo = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROMO);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String id_promo = rs.getString("id_promo");
				String name_promo = rs.getString("name_promo");
				Float discount = rs.getFloat("discount");
				String start_discount = rs.getString("start_discount");
				String end_discount = rs.getString("end_discount");
				promo.add(new Promo(id_promo, name_promo, discount, start_discount, end_discount));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return promo;
	}

	// delete promo
	public boolean deletePromo(String id_promo) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PROMO_SQL);) {
			statement.setString(1, id_promo);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	// select promo by id
	public Promo selectPromo(String id_promo) {
		Promo promo = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROMO_BY_ID);) {
			preparedStatement.setString(1, id_promo);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name_promo = rs.getString("name_promo");
				Float discount = rs.getFloat("discount");
				String start_discount = rs.getString("start_discount");
				String end_discount = rs.getString("end_discount");
				promo = new Promo(id_promo, name_promo, discount, start_discount, end_discount);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return promo;
	}


	private void printSQLException(SQLException e) {
		// TODO Auto-generated method stub
		
	}
}
