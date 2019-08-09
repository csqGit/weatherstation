package com.bozpower.service;

import java.util.List;

import com.bozpower.entity.PageData;
import com.bozpower.entity.User;

public interface UserService {
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	User userLogin(User user);
	
/**
 * 查询用户列表
 * @return 
 */
	User selectUser();
	
	User selectUserByUsername(String username);
	
	User selectUserByUserAndPassword(String username, String password);
	
	User selectUserById(int id);
	
	List<User> selectUserList(PageData pageData);
	
	void insertUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(int id);
}
