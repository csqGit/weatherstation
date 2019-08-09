package com.bozpower;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bozpower.entity.Company;
import com.bozpower.entity.User;
import com.bozpower.mapper.CompanyMapper;
import com.bozpower.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CompanyMapper companyMapper;
	
	@Test
	public void selectUserTest() {
		
	}
	
//	@Test
	public void insertDeviceTest() {
		User user = new User();
//		user.setId(2);
		user.setUsername("testusername");
		user.setPassword("s22");
		Company c = companyMapper.selectCompanyById(1);
		user.setCompanyId(c);
		userMapper.insertUser(user);
	}
	
//	@Test
	public void selectUserById() {
		User user = userMapper.selectUserById(1);
		System.out.println(user);
	}
	
	
//	@Test
	public void deleteUserById() {
		userMapper.deleteUserById(1);
	}
	
	
	@Test
	public void updateUser() {
		User user = new User();
		user.setPassword("222");
		user.setUsername("222");
		Company c = companyMapper.selectCompanyById(1);
		user.setCompanyId(c);
		user.setId(3);
		userMapper.updateUser(user);
	}


}
