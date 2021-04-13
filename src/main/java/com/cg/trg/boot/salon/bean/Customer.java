package com.cg.trg.boot.salon.bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
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
	
	@OneToMany(mappedBy = "customer", targetEntity = Billing.class)
	private List<Billing> bills = new ArrayList<>();
	@OneToMany(mappedBy = "customer", targetEntity = Address.class)
	private Set<Address> addresses = new HashSet<>();

	@OneToMany(mappedBy = "customer", targetEntity = Appointment.class)
	private List<Appointment> appointments= new ArrayList<>();
	public Customer() {
		super();
	}

	public Customer(long userId, String password, String role, boolean isLoggedIn) {
		super(userId, password, role, isLoggedIn);
		// TODO Auto-generated constructor stub
	}

	

	public Customer(String name, String email, String contactNo, LocalDate dob, List<Billing> bills, Set<Address> addresses,
			List<Appointment> appointments) {
		super();
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
		this.dob = dob;
		this.bills = bills;
		this.addresses = addresses;
		this.appointments = appointments;
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
	
	public List<Billing> getBills() {
		return bills;
	}

	public void setBills(List<Billing> bills) {
		this.bills = bills;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", email=" + email + ", contactNo=" + contactNo + ", dob=" + dob + ", bills="
				+ bills + ", addresses=" + addresses + ", appointments=" + appointments + "]";
	}

	

	

}
