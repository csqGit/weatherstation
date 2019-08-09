package com.bozpower.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bozpower.entity.Company;
import com.bozpower.entity.PageData;
import com.bozpower.service.CompanyService;

@Controller
@RequestMapping(value = "companyController")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;

	/**
	 * url:http://localhost:8080/companyController/selectCompanyById?id=1
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "selectCompanyById" , method = RequestMethod.GET)
	@ResponseBody
	public Company selectCompanyById(int id) {
		try {
			return companyService.selectCompanyById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * url:http://localhost:8080/companyController/selectCompanyList
	 * @param pageData
	 * @return
	 */
	@RequestMapping(value = "selectCompanyList", method = RequestMethod.GET)
//	@ResponseBody
	public String selectCompanyList(PageData pageData, Map<String, Object> map){
		try {
			List<Company> companyList = companyService.selectCompanyList(pageData);
			map.put("companyList", companyList);
			return "company";
		} catch (Exception e) {
			e.printStackTrace();
			
			return "error";
		}
	}
	
	/**
	 * url:http://localhost:8080/companyController/selectCompanyByName?username=
	 * @param companyName
	 * @return
	 */
	@RequestMapping(value = "selectCompanyByName", method = RequestMethod.GET)
	@ResponseBody
	public Company selectCompanyByName(String companyName) {
		try {
			return companyService.selectCompanyByName(companyName);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * url: http://localhost:8080/companyController/insertCompany
	 * @param company
	 * @return
	 */
	@RequestMapping(value = "insertCompany", method = RequestMethod.POST)
	public String insertCompany(Company company) {
		try {
			companyService.insertCompany(company);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	/**
	 * url:http://localhost:8080/companyController/deleteCompanyById?id=
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteCompanyById", method = RequestMethod.GET)
	@ResponseBody
	public String deleteCompanyById(int id) {
		try {
			companyService.deleteCompanyById(id);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping(value = "updateCompany", method = RequestMethod.POST)
	@ResponseBody
	public String updateCompany(Company company) {
		try {
			companyService.updateCompany(company);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
}
