package com.bozpower.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bozpower.entity.User;

//@Component
public class UserLoginIntegcepter implements HandlerInterceptor {
	protected org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		if(user != null)//表示已登录
			return true;
		else {
			System.out.println("path:" + request.getContextPath());
		}
			response.sendRedirect("/");
			return false;
		}
}
