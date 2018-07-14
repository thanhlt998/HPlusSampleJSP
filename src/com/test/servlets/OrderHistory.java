package com.test.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.beans.Order;
import com.test.dao.ApplicationDao;

@WebServlet("/orderHistory")
public class OrderHistory extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get connection and username from session
		String username = (String) req.getSession().getAttribute("username");
		Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
		
		//call dao and get order history
		ApplicationDao dao = new ApplicationDao();
		List<Order> orders = dao.getOrders(username, connection);
		
		//set order data in request
		req.setAttribute("orders", orders);
		
		//forward to home.jsp
		req.getRequestDispatcher("/html/home.jsp").forward(req, resp);
		
	}
}
