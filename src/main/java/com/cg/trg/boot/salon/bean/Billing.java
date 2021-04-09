package com.cg.trg.boot.salon.bean;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Bill")
public class Billing {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long billId;
	private double amount;
	private LocalDate billingDate;
	@ManyToOne
	@JoinColumn(name="customerId", referencedColumnName = "userId")
	private Customer customer;
	
	@OneToOne(mappedBy = "billing")
	private Appointment appointment;
	@OneToOne
	@JoinColumn(name="paymentId",referencedColumnName = "paymentId")
	private Payment payment;
	
	public Billing() {
		
	}

	
	


	public Billing(long billId, double amount, LocalDate billingDate, Customer customer, Appointment appointment,
			Payment payment) {
		super();
		this.billId = billId;
		this.amount = amount;
		this.billingDate = billingDate;
		this.customer = customer;
		this.appointment = appointment;
		this.payment = payment;
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
	
	

	public Appointment getAppointment() {
		return appointment;
	}


	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}


	public Payment getPayment() {
		return payment;
	}


	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	

	@Override
	public String toString() {
		return "Billing [billId=" + billId + ", amount=" + amount + ", billingDate=" + billingDate + ", customer="
				+ customer + ", appointment=" + appointment + ", payment=" + payment + "]";
	}


	
	
	

}
