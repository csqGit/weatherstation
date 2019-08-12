package com.bozpower.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bozpower.entity.Company;
import com.bozpower.entity.User;
import com.bozpower.service.UserService;

@Controller
@RequestMapping(value = "userController")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 用户登录
	 * @return
	 */
	@RequestMapping(value = "login")
	public String login(HttpServletRequest request,  User user) {
		User userSuccess = null;
		HttpSession session = request.getSession();
		try {
			userSuccess = userService.selectUserByUsername(user.getUsername());
			if(userSuccess != null) {//用户名存在
				userSuccess = userService.userLogin(user);
				if(userSuccess != null) {//登录成功
					session.setAttribute("user", userSuccess);
					Company c = userSuccess.getCompanyId();
					request.setAttribute("c", c);
					System.out.println("user:" + userSuccess);
					return "index";
				}else {//登录失败
					request.setAttribute("passwordErr", "密码错误");
					return "login";
				}
			}else {//用户名不存在
//				map.put("usernameErr", "用户名不存在");
				request.setAttribute("usernameErr", "用户名不存在");
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("systemErr", "系统错误，请刷新后登录");
			return "login";
		}
	}
	
	
	
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
			return "error";
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
			return "error";
		}
	}
	
	
	/**
	 * 删除更新
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	@ResponseBody
	public String updateUser(User user) {
		try {
			userService.updateUser(user);
			
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	
	/**
	 * 查询用户集合
	 * @param map
	 * @param pageData
	 * @return
	 */
//	@RequestMapping(value = "selectUserList", method = RequestMethod.GET)
//	public String selectUserList(Map<Object, Object> map, HttpServletRequest request,  PageData pageData) {
//		List<User> list = userService.selectUserList(pageData);
//		try {
//			HttpSession session = request.getSession();
//			User user = (User) session.getAttribute("user");
//			map.put("company", user.getCompanyId());
//			map.put("userList", list);
//			map.put("user", user);
//			return "user";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "error";
//		}
//	}
	
	
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
			return "forward:../";
		} catch (Exception e) {
			e.printStackTrace();
			return "../error/404";
		}
	}


}
