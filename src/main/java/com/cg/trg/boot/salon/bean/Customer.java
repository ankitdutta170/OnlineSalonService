package com.cg.trg.boot.salon.bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Customer extends User {
	
	private String name;
	private String email;
	private String contactNo;
	private LocalDate dob;
	
	
	public Customer() {
		super();
	}

	public Customer(long userId, String password, String role, boolean isLoggedIn) {
		super(userId, password, role, isLoggedIn);
		// TODO Auto-generated constructor stub
	}



	public Customer(long userId, String password, String role, boolean isLoggedIn, String name, String email,
			String contactNo, LocalDate dob) {
		super(userId, password, role, isLoggedIn);
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
		this.dob = dob;
		
		
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	

		

		@Override
	public String toString() {
		return "Customer [name=" + name + ", email=" + email + ", contactNo=" + contactNo + ", dob=" + dob
				+  "]";
	}



	
	

}
