package com.bozpower.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bozpower.entity.PageData;
import com.bozpower.entity.User;
import com.bozpower.mapper.UserMapper;
import com.bozpower.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	/**
	 * 查询用户列表集合
	 */
	@Override
	public List<User> selectUserList(PageData pageData) {
		return userMapper.selectUserList(pageData);
	}


	@Override
	public User selectUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
		
	}

	@Override
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public User selectUser() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User userLogin(User user) {
		
		return userMapper.userLogin(user);
	}


	@Override
	public User selectUserByUsername(String username) {
		
		return userMapper.selectUserByUsername(username);
	}


	@Override
	public User selectUserByUserAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
