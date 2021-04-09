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
	private  LocalDate dob;
	@OneToMany(mappedBy = "customer")
	private Set<Address> addresses;
	@OneToMany(mappedBy = "customer")
	private List<Appointment> appointment;
	@OneToMany(mappedBy = "customer")
	private List<Billing> bills;
	
	
	
	
	

	public Customer(String userId, String password, String role, boolean isLoggedIn) {
		super(userId, password, role, isLoggedIn);
		// TODO Auto-generated constructor stub
	}



	public Customer(String userId, String password, String role, boolean isLoggedIn, String name, String email,
			String contactNo, LocalDate dob, Set<Address> addresses, List<Appointment> appointment,
			List<Billing> bills) {
		super(userId, password, role, isLoggedIn);
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
		this.dob = dob;
		this.addresses = addresses;
		this.appointment = appointment;
		this.bills = bills;
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
	

	public List<Appointment> getAppointment() {
		return appointment;
	}



	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}



	public List<Billing> getBills() {
		return bills;
	}



	public void setBills(List<Billing> bills) {
		this.bills = bills;
	}



	@Override
	public String toString() {
		return "Customer [name=" + name + ", email=" + email + ", contactNo=" + contactNo + ", dob=" + dob
				+ ", addresses=" + addresses + ", appointment=" + appointment + ", bills=" + bills + "]";
	}



	
	

}
