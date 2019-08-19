package com.bozpower.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bozpower.entity.Company;
import com.bozpower.entity.User;

public class RequestUtils {
	
	public int getCompanyId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Company company = user.getCompanyId();
		int id = company.getId();
		return id;
	}

}
