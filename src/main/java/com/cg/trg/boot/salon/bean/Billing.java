package com.cg.trg.boot.salon.bean;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@Table(name = "Bill")
public class Billing {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long billId;
	private double amount;
	private LocalDate billingDate;
	@ManyToOne
	@JoinColumn(name="user_id_fk")
	private Customer customer;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="payment_id_fk")
	private Payment payment;
	@JsonIgnore
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="appointment_id_fk")
	private Appointment appointment;
	public Billing() {
		
	}

	
	



	public Billing(long billId, double amount, LocalDate billingDate, Customer customer, Payment payment,
			Appointment appointment) {
		super();
		this.billId = billId;
		this.amount = amount;
		this.billingDate = billingDate;
		this.customer = customer;
		this.payment = payment;
		this.appointment = appointment;
	}
	
	public Billing(double amount, LocalDate billingDate, Customer customer, Payment payment,
			Appointment appointment) {
		super();
		
		this.amount = amount;
		this.billingDate = billingDate;
		this.customer = customer;
		this.payment = payment;
		this.appointment = appointment;
	}






	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(LocalDate billingDate) {
		this.billingDate = billingDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
		

	public Payment getPayment() {
		return payment;
	}


	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	

	public Appointment getAppointment() {
		return appointment;
	}






	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}






	@Override
	public String toString() {
		return "Billing [billId=" + billId + ", amount=" + amount + ", billingDate=" + billingDate + ", customer="
				+ customer + ", payment=" + payment + ", appointment=" + appointment + "]";
	}






	


	
	
	

}
