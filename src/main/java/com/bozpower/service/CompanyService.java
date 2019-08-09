package com.bozpower.service;

import java.util.List;

import com.bozpower.entity.Company;
import com.bozpower.entity.PageData;

public interface CompanyService {

	public Company selectCompanyById(int id);
	
	public List<Company> selectCompanyList(PageData pageData);
	
	public Company selectCompanyByName(String companyName);
	
	public void insertCompany(Company company);
	
	public void deleteCompanyById(int id);
	
	public void updateCompany(Company company);
	
}
