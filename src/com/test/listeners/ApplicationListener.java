package com.test.listeners;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.test.dao.DBConnection;

public class ApplicationListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("In contextDestroyed method");
		Connection connection = (Connection) sce.getServletContext().getAttribute("DBConnection");
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("In contextInitialized method");
		Connection connection = DBConnection.getConnectionToDatabase();
		sce.getServletContext().setAttribute("DBConnection", connection);
	}

}
