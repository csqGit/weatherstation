package com.bozpower.entity;

import java.io.Serializable;

public class User  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
	private Company companyId;
	private int userPermission;//管理员权限  	0为普通用户	1为管理员
	private String remarks;
	
	public User() {
	}
	public User(int id, String username, String password, String remarks) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.remarks = remarks;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Company getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Company companyId) {
		this.companyId = companyId;
	}
	public int getUserPermission() {
		return userPermission;
	}
	public void setUserPermission(int userPermission) {
		this.userPermission = userPermission;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", companyId=" + companyId
				+ ", userPermission=" + userPermission + ", remarks=" + remarks + "]";
	}
	

}
