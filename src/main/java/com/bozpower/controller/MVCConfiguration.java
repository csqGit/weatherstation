package com.bozpower.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bozpower.entity.Company;
import com.bozpower.entity.User;
import com.bozpower.service.UserService;


@Controller
public class MVCConfiguration implements WebMvcConfigurer {
	
//	@Autowired
//	private UserLoginIntegcepter userLoginIntercepter;
	@Autowired
	private UserService userService;

	
	@RequestMapping("/")
	public String view(HttpServletRequest request) {
		
		return "login";
	}
	
	
	/**
	 * 用户登录
	 * @return
	 */
	@RequestMapping(value = "login")
	public String login(HttpServletRequest request, HttpServletResponse response,  User user) {
		User userSuccess = null;
		HttpSession session = request.getSession();
		try {
			String username = user.getUsername();
			request.setAttribute("username", username);
//			if(username != null && !"".equals(username)) {
				userSuccess = userService.selectUserByUsername(user.getUsername());
				if(userSuccess != null) {//用户名存在
					userSuccess = userService.userLogin(user);
					if(userSuccess != null) {//登录成功
						session.setAttribute("user", userSuccess);
						Company c = userSuccess.getCompanyId();
						request.setAttribute("c", c);
//						System.out.println("user:" + userSuccess);
						return "index";
					}else {//登录失败
						String password = user.getPassword();
						if(password == null || "".equals(password))
							request.setAttribute("passwordErr", "密码不能为空");
						else
							request.setAttribute("passwordErr", "密码错误");
						return "login";
					}
				}else {//用户名不存在
					if(username == null || "".equals(username))
						request.setAttribute("usernameErr", "用户名不能为空");
					else 
						request.setAttribute("usernameErr", "用户名不存在");
					return "login";
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("systemErr", "系统错误，请刷新后登录");
			return "redirect:/";
		}
	}
}
