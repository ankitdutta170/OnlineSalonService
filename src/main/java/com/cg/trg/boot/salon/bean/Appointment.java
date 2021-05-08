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

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
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
	//@JsonIgnore
	@JsonProperty(access = Access.READ_WRITE)
	@ManyToOne
	@JoinColumn(name = "salon_service_id_fk")
	private SalonService preferredService;
	
	
	private LocalDate preferredDate;
	
	private LocalTime preferredTime;
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name="user_id_fk")
	private Customer customer;
	@JsonIgnore

	@OneToOne(mappedBy = "appointment", targetEntity = Billing.class,fetch = FetchType.LAZY, orphanRemoval = true)
	private Billing billing;
	
	public Appointment() {
		
	}

	

	public Appointment(long appointmentId, String location, String visitType, SalonService preferredService,
			LocalDate preferredDate, LocalTime preferredTime, Customer customer, Billing billing) {
		super();
		this.appointmentId = appointmentId;
		this.location = location;
		this.visitType = visitType;
		this.preferredService = preferredService;
		this.preferredDate = preferredDate;
		this.preferredTime = preferredTime;
		this.customer = customer;
		
		this.billing = billing;
	}
	public Appointment(String location, String visitType, SalonService preferredService,
			LocalDate preferredDate, LocalTime preferredTime, Customer customer, Billing billing) {
		super();
		
		this.location = location;
		this.visitType = visitType;
		this.preferredService = preferredService;
		this.preferredDate = preferredDate;
		this.preferredTime = preferredTime;
		this.customer = customer;
		
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
				+ preferredTime + ", customer=" + customer +  " ]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (appointmentId ^ (appointmentId >>> 32));
		result = prime * result + ((billing == null) ? 0 : billing.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((preferredDate == null) ? 0 : preferredDate.hashCode());
		result = prime * result + ((preferredService == null) ? 0 : preferredService.hashCode());
		result = prime * result + ((preferredTime == null) ? 0 : preferredTime.hashCode());
		result = prime * result + ((visitType == null) ? 0 : visitType.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		if (appointmentId != other.appointmentId)
			return false;
		if (billing == null) {
			if (other.billing != null)
				return false;
		} else if (!billing.equals(other.billing))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (preferredDate == null) {
			if (other.preferredDate != null)
				return false;
		} else if (!preferredDate.equals(other.preferredDate))
			return false;
		if (preferredService == null) {
			if (other.preferredService != null)
				return false;
		} else if (!preferredService.equals(other.preferredService))
			return false;
		if (preferredTime == null) {
			if (other.preferredTime != null)
				return false;
		} else if (!preferredTime.equals(other.preferredTime))
			return false;
		if (visitType == null) {
			if (other.visitType != null)
				return false;
		} else if (!visitType.equals(other.visitType))
			return false;
		return true;
	}
	

}
