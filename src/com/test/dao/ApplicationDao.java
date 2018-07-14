package com.test.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.test.beans.Order;
import com.test.beans.Product;
import com.test.beans.User;

public class ApplicationDao {

	public List<Product> searchProducts(String searchString, Connection connection) {
		Product product = null;
		List<Product> products = new ArrayList<>();

		try {

			String sql = "select * from products where product_name like '%" + searchString + "%'";

			Statement statement = connection.createStatement();

			ResultSet set = statement.executeQuery(sql);

			while (set.next()) {
				product = new Product();
				product.setProductId(set.getInt("product_id"));
				product.setProductImgPath(set.getString("image_path"));
				product.setProductName(set.getString("product_name"));
				products.add(product);

			}

		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return products;
	}

	public int registerUser(User user, Connection connection) {
		int rowsAffected = 0;

		try {

			// write the insert query
			String insertQuery = "insert into users values(?,?,?,?,?,?)";

			// set parameters with PreparedStatement
			java.sql.PreparedStatement statement = connection.prepareStatement(insertQuery);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setInt(5, user.getAge());
			statement.setString(6, user.getActivity());

			// execute the statement
			rowsAffected = statement.executeUpdate();

		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return rowsAffected;
	}

	public boolean validateUser(String username, String password, Connection connection) {
		boolean isValidUser = false;
		try {
			/*
			 * // get the connection for the database Connection connection =
			 * DBConnection.getConnectionToDatabase();
			 */

			// write the select query
			String sql = "select * from users where username=? and password=?";

			// set parameters with PreparedStatement
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);

			// execute the statement and check whether user exists
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				isValidUser = true;
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}

		return isValidUser;
	}

	public User getProfileDetails(String username, Connection connection) {

		User user = null;
		try {
			// get the connection to the database

			// write the select query to get profile details
			String sql = "select * from users where username=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);

			// execute query
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				user = new User();
				user.setUsername(set.getString("username"));
				user.setPassword(set.getString("password"));
				user.setFirstName(set.getString("firstName"));
				user.setLastName(set.getString("lastName"));
				user.setAge(set.getInt("age"));
				user.setActivity(set.getString("activity"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;

	}

	public List<Order> getOrders(String username, Connection connection) {
		Order order = null;
		List<Order> orders = new ArrayList<>();
		try {
			// write the select query to get order details
			String sql = "select * from orders where username=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);

			// execute the query, get the resultSet and return the Order information
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				order = new Order();
				order.setOrderId(set.getString("order_id"));
				order.setUsername(set.getString("username"));
				order.setProductImgPath(set.getString("image_path"));
				order.setProductName(set.getString("product_name"));
				order.setOrderDate(set.getString("order_date"));
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
	}

	public int insertOrderHistory(Order order, Connection connection) {
		int rowsAffected = 0;
		// prepare the insert query to insert order information into orders
		String insertQuery = "insert into orders (username, product_name, image_path, order_date)"
				+ " values (?,?,?,?)";
		try {
			PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, order.getUsername());
			insertStatement.setString(2, order.getProductName());
			insertStatement.setString(3, order.getProductImgPath());
			insertStatement.setString(4, order.getOrderDate());
			
			rowsAffected = insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected;

	}

	public Product getProduct(String productName, Connection connection) {
		Product product = null;
		// prepare the select query to get the product
		String selectSql = "select * from products where product_name=?";
		try {
			PreparedStatement selectStatement = connection.prepareStatement(selectSql);
			selectStatement.setString(1, productName);
			ResultSet set = selectStatement.executeQuery();
			while (set.next()) {
				product = new Product();
				product.setProductId(set.getInt("product_id"));
				product.setProductName(productName);
				product.setProductImgPath(set.getString("image_path"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return product;
	}

}
