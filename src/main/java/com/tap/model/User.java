package com.tap.model;

import java.util.Date;

public class User {
	 private int uesrId;
	 private String name;
	 private String username;
	 private String password;
	 private String email;
	 private String phone;
	 private String address;
	 private String role;
	 private Date createdDate;
	 private Date lastLoginDate;
	 
	 public User() {
		
	}

	public int getUesrId() {
		return uesrId;
	}

	public void setUserId(int uesrId) {
		this.uesrId = uesrId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public User(int uesrId, String name, String username, String password, String email, String phone, String address,
			String role, Date createdDate, Date lastLoginDate) {
		super();
		this.uesrId = uesrId;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.createdDate = createdDate;
		this.lastLoginDate = lastLoginDate;
	}
	

}
