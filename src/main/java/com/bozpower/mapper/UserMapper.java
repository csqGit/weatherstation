package com.bozpower.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bozpower.entity.PageData;
import com.bozpower.entity.User;

@Mapper
public interface UserMapper {
	
	User userLogin(User user);

	
	User selectUserByUsername(String username);
	
	
	
	User selectUser();
	
	User selectUserById(int id);
	
	List<User> selectUserList(PageData pageData);
	
	void insertUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(int id);
}
