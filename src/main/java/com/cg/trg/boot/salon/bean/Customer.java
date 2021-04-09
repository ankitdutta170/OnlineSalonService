package com.cg.trg.boot.salon.bean;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name = "Customer")
public class Customer extends User {
	


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String userId;
	private String name;
	private String email;
	private String contactNo;
	private  LocalDate dob;
	@ManyToMany
	@JoinTable(
			name = "customer_address",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "address_id")
			)
	private Set<Address> addresses;
	
	
	public Customer(String userId, String password, String role) {
		super(userId, password, role);
		// TODO Auto-generated constructor stub
	}
	
	


	public Customer(String userId, String password, String role, String userId2, String name, String email,
			String contactNo, LocalDate dob, Set<Address> addresses) {
		super(userId, password, role);
		userId = userId2;
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
		this.dob = dob;
		this.addresses = addresses;
	}




	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	

	public Set<Address> getAddresses() {
		return addresses;
	}


	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}


	@Override
	public String toString() {
		return "Customer [userId=" + userId + ", name=" + name + ", email=" + email + ", contactNo=" + contactNo
				+ ", dob=" + dob + ", address=" + addresses+ "]";
	}
	
	

}
