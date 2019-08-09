package com.bozpower.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bozpower.entity.Company;
import com.bozpower.entity.PageData;
import com.bozpower.mapper.CompanyMapper;
import com.bozpower.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyMapper companyMapper;

	@Override
	public Company selectCompanyById(int id) {
		// TODO Auto-generated method stub
		return companyMapper.selectCompanyById(id);
	}

	@Override
	public List<Company> selectCompanyList(PageData pageData) {
		// TODO Auto-generated method stub
		return companyMapper.selectCompanyList(pageData);
	}

	@Override
	public Company selectCompanyByName(String companyName) {
		// TODO Auto-generated method stub
		return companyMapper.selectCompanyByName(companyName);
	}

	@Override
	public void insertCompany(Company company) {
		// TODO Auto-generated method stub
		companyMapper.insertCompany(company);
	}

	@Override
	public void deleteCompanyById(int id) {
		// TODO Auto-generated method stub
		companyMapper.deleteCompanyById(id);
	}

	@Override
	public void updateCompany(Company company) {
		// TODO Auto-generated method stub
		companyMapper.updateCompany(company);
	}

}
