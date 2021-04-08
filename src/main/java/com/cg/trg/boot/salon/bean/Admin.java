package com.cg.trg.boot.salon.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Admin")

public class Admin extends User {
	
	public Admin(String role, String userId, String password) {
		super(role, userId, password);
		// TODO Auto-generated constructor stub
	}

	private String userId;
	final String role = "admin";
	
	public Admin(String role, String userId, String password, String userId2) {
		super(role, userId, password);
		userId = userId2;
		
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "Admin [userId=" + userId + ", role=" + role + "]";
	}
	
	
	
	
	
	

}
