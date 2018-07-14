package com.test.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.beans.Order;
import com.test.beans.Product;
import com.test.dao.ApplicationDao;

@WebServlet("/addProducts")
public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get the HTTPSession object
		HttpSession session = request.getSession();

		// create a cart as arraylist for the user
		List<String> cart = (ArrayList<String>) session.getAttribute("cart");

		if (cart == null) {
			cart = new ArrayList<>();
		}

		// add the selected product to the cart
		if (request.getParameter("product") != null) {
			cart.add(request.getParameter("product"));
		}
		
		session.setAttribute("cart", cart);

		// get search criteria from search servlet

		String search = (String) session.getAttribute("search");
		Connection connection = (Connection) getServletContext().getAttribute("DBConnection");

		// get the search results from dao
		ApplicationDao dao = new ApplicationDao();
		List<Product> products = dao.searchProducts(search, connection);
		Product product = dao.getProduct(request.getParameter("product"), connection);
		
		//prepare order to insert into orders
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-YYYY hh:mm:ss");
		Date date = Calendar.getInstance().getTime();
		
		Order order = new Order(dateFormat.format(date).toString(), product.getProductImgPath(), product.getProductName(), (String) session.getAttribute("username"));
		dao.insertOrderHistory(order, connection);
		// set the search results in request scope
		request.setAttribute("products", products);

		// forward to searchResults.jsp
		request.getRequestDispatcher("/html/searchResults.jsp").forward(request, response);

	}

}
