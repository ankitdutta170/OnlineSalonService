package com.cg.trg.boot.salon.bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitilizer","handler"})
@Entity
public class Customer extends User {

	private String name;
	private String email;
	private String contactNo;
	private LocalDate dob;

	//@OneToMany(mappedBy = "customer", targetEntity = Card.class)
	//private List<Card> cards=new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "customer", targetEntity = Billing.class,fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Billing> bills = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "customer", targetEntity = Address.class,fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Address> addresses = new HashSet<>();
	@JsonIgnore
	@OneToMany(mappedBy = "customer", targetEntity = Appointment.class,fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Appointment> appointments= new ArrayList<>();
	public Customer() {
		super();
	}

	

	

	public Customer(String userName, String password, String role) {
		super(userName, password, role);
		
	}


	


	public Customer(String userName, String password, String role, String name, String email,
			String contactNo, LocalDate dob, List<Billing> bills, Set<Address> addresses,
			List<Appointment> appointments) {
		super(userName, password, role);
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
		this.dob = dob;
		this.bills = bills;
		this.addresses = addresses;
		this.appointments = appointments;
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

	

	/*public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}*/



	@Override
	public String toString() {
		return "Customer [name=" + name + ", email=" + email + ", contactNo=" + contactNo + ", dob=" + dob + ", bills="
				+ bills + ", addresses=" + addresses + ", appointments=" + appointments + "]";

	}

	

	

}
