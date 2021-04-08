package com.cg.trg.boot.salon.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Admin")

public class Admin extends User {
	@Id
	private String userId;
	private String role;
	
	public Admin(String role, String userId, String password) {
		super(role, userId, password);
		// TODO Auto-generated constructor stub
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
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Admin [userId=" + userId + ", role=" + role + "]";
	}
	
	
	
	
	
	

}
