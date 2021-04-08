package com.cg.trg.boot.salon.bean;

import java.time.LocalDate;

import javax.persistence.OneToMany;

public class Customer {
	
	private String userId;
	private String name;
	private String email;
	private String contactNo;
	private  LocalDate dob;
	@OneToMany(mappedBy = "customer")
	private Address address;

}
