package com.bozpower.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.bozpower.entity.User;

//@Component
//@WebFilter(urlPatterns = "/*/select*", filterName = "authFilter")
public class UserLoginFilter implements  Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String urlString = req.getRequestURI();
		if(urlString.endsWith("login.html") || "/".equals(urlString)) {
			chain.doFilter(req, res);
			return ;
		}else if(user == null)
			res.sendRedirect("http://localhost:8080/");
		else {
			chain.doFilter(req, res);
		}
		
		
	}

}
