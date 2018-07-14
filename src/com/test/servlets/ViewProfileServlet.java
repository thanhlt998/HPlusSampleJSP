package com.test.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.beans.User;
import com.test.dao.ApplicationDao;

@WebServlet("/getProfileDetails")
public class ViewProfileServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get the username from the session
		System.out.println("User name in profile servlet: " + request.getSession().getAttribute("username"));
		String username = (String) request.getSession().getAttribute("username");
		Connection connection = (Connection) getServletContext().getAttribute("DBConnection");

		// call dao and get the profile details
		ApplicationDao dao = new ApplicationDao();
		User user = dao.getProfileDetails(username, connection);
		if(user != null) System.out.println("get Profile Successfully!");
		Map<String, Double> weightSummary = new HashMap<>();
		weightSummary.put("January", 59.1);
		weightSummary.put("Febrary", 58.1);
		weightSummary.put("March", 58.5);
		weightSummary.put("April", 59.4);
		weightSummary.put("May", 59.1);
		weightSummary.put("June", 59.6);
		weightSummary.put("July", 57.1);
		weightSummary.put("August", 58.1);
		weightSummary.put("September", 59.1);
		weightSummary.put("October", 60.5);
		weightSummary.put("November", 58.7);
		weightSummary.put("December", 59.1);
		
		// store all information in request object
		request.setAttribute("user", user);
		request.setAttribute("weightSummary", weightSummary);
		
		// forward control to profile jsp
		request.getRequestDispatcher("/html/profile.jsp").forward(request, response);

	}

}
