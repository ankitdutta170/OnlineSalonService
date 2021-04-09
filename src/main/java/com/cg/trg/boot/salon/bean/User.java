package com.cg.trg.boot.salon.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String userId;
	private String password;
	private String role;
	private boolean isLoggedIn;
	
	public User(String userId, String password, String role, boolean isLoggedIn) {
		super();
		this.userId = userId;
		this.password = password;
		this.role = role;
		this.isLoggedIn = isLoggedIn;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", role=" + role + ", isLoggedIn=" + isLoggedIn
				+ "]";
	}
	
	
	
}
