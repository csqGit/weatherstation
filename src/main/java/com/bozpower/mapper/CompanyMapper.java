package com.bozpower.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bozpower.entity.Company;
import com.bozpower.entity.PageData;

@Mapper
public interface CompanyMapper {

	public Company selectCompanyById(int id);
	
	public List<Company> selectCompanyList(PageData pageData);
	
	public Company selectCompanyByName(String companyName);
	
	public void insertCompany(Company company);
	
	public void deleteCompanyById(int id);
	
	public void updateCompany(Company company);
	
	
	
	
}
