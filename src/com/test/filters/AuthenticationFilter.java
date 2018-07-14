package com.test.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		if (request.getRequestURI().startsWith("/HPlusSample/orderHistory")
				|| request.getRequestURI().startsWith("/HPlusSample/getProfileDetails")) {
			HttpSession session = request.getSession();
			if (session.getAttribute("username") == null) {
				request.getRequestDispatcher("/html/login.jsp").forward(request, arg1);
			}
		}

		arg2.doFilter(request, arg1);
	}

}
