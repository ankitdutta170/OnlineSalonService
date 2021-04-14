package com.cg.trg.boot.salon.bean;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "SalonService")
public class SalonService {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long serviceId;
	private String serviceName;
	private double price;
	private int discount;
	private String duration;
	@JsonIgnore
	@OneToOne(mappedBy = "preferredService",targetEntity = Appointment.class)
	private Appointment appointment;
	public SalonService() {
		super();
	}
	public SalonService(long serviceId, String serviceName, double price, int discount, String duration,Appointment appointment) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.price = price;
		this.discount = discount;
		this.duration = duration;
		this.appointment = appointment;
	}
	public SalonService(String serviceName, double price, int discount, String duration,Appointment appointment) {
		super();
		
		this.serviceName = serviceName;
		this.price = price;
		this.discount = discount;
		this.duration = duration;
		this.appointment = appointment;
	}
	public long getServiceId() {
		return serviceId;
	}
	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	@Override
	public String toString() {
		return "SalonService [serviceId=" + serviceId + ", serviceName=" + serviceName + ", price=" + price
				+ ", discount=" + discount + ", duration=" + duration + ", appointment=" + appointment + "]";
	}
	
	
	
}
