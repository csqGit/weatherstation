package com.bozpower.controller;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bozpower.entity.Company;
import com.bozpower.entity.User;
import com.bozpower.service.CompanyService;
import com.bozpower.service.UserService;

@Controller
@RequestMapping(value = "userController")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CompanyService companyService;
	

	
	
	/**
	 * 注册新用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "userRegister", method = RequestMethod.POST)
	public String register(User user) {
		try {
			userService.insertUser(user);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "../error/500";
		}
	}
	
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteUserById", method = RequestMethod.POST)
	public String deleteUserById(int id) {
		try {
			userService.deleteUserById(id);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "../error/500";
		}
	}
	
	
	/**
	 * 删除更新
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
//	@ResponseBody
	public String updateUser(User user, HttpServletRequest request) {
		try {
			userService.updateUser(user);
			request.setAttribute("user", user );
			Company company = companyService.selectCompanyById(user.getCompanyId().getId());
			request.setAttribute("company", company);
			return "user";
		} catch (Exception e) {
			e.printStackTrace();
			user = (User) request.getSession().getAttribute("user");
			request.setAttribute("user", user );
			Company company = user.getCompanyId();
			request.setAttribute("company", company);
			return "../error/500";
		}
	}
	
	
	
	
	@RequestMapping(value = "selectUser", method = RequestMethod.GET)
	public String selectUser(Map<String, Object> map, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			map.put("user", user);
			map.put("company", user.getCompanyId());
			return "user";
		} catch (Exception e) {
			e.printStackTrace();
			return "../error/500";
		}
	}
	
	
	@RequestMapping(value = "userLogout", method = RequestMethod.POST)
	public String userLogout(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			if(user!= null) {
				session.invalidate();
			}
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			return "../error/404";
		}
	}


}
