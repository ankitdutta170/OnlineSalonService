package com.cg.trg.boot.salon.bean;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitilizer","handler"})
@Entity
@Table(name = "Appointment")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="appointment_id")
	private long appointmentId;
	
	private String location;
	
	private String visitType;
	@JsonIgnore
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="salon_service_id_fk")
	private SalonService preferredService;
	
	
	private LocalDate preferredDate;
	
	private LocalTime preferredTime;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id_fk")
	private Customer customer;
	@JsonIgnore
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="address_id_fk")
	private Address address;
	
	@OneToOne(mappedBy = "appointment", targetEntity = Billing.class,fetch = FetchType.LAZY, orphanRemoval = true)
	private Billing billing;
	
	public Appointment() {
		
	}

	

	public Appointment(long appointmentId, String location, String visitType, SalonService preferredService,
			LocalDate preferredDate, LocalTime preferredTime, Customer customer, Address address, Billing billing) {
		super();
		this.appointmentId = appointmentId;
		this.location = location;
		this.visitType = visitType;
		this.preferredService = preferredService;
		this.preferredDate = preferredDate;
		this.preferredTime = preferredTime;
		this.customer = customer;
		this.address = address;
		this.billing = billing;
	}
	public Appointment(String location, String visitType, SalonService preferredService,
			LocalDate preferredDate, LocalTime preferredTime, Customer customer, Address address, Billing billing) {
		super();
		
		this.location = location;
		this.visitType = visitType;
		this.preferredService = preferredService;
		this.preferredDate = preferredDate;
		this.preferredTime = preferredTime;
		this.customer = customer;
		this.address = address;
		this.billing = billing;
	}



	public long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public SalonService getPreferredService() {
		return preferredService;
	}

	public void setPreferredService(SalonService preferredService) {
		this.preferredService = preferredService;
	}

	public LocalDate getPreferredDate() {
		return preferredDate;
	}

	public void setPreferredDate(LocalDate preferredDate) {
		this.preferredDate = preferredDate;
	}

	public LocalTime getPreferredTime() {
		return preferredTime;
	}

	public void setPreferredTime(LocalTime preferredTime) {
		this.preferredTime = preferredTime;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Billing getBilling() {
		return billing;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}
	
	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", location=" + location + ", visitType=" + visitType
				+ ", preferredService=" + preferredService + ", preferredDate=" + preferredDate + ", preferredTime="
				+ preferredTime + ", customer=" + customer + ", address=" + address + " ]";
	}
	

}
