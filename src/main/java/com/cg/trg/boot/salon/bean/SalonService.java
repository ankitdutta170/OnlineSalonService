package com.cg.trg.boot.salon.bean;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

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
	
	@OneToMany(orphanRemoval = true,mappedBy = "preferredService",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Appointment> appointments = new ArrayList<>();
	public SalonService() {
		super();
	}
	
	public SalonService(long serviceId, String serviceName, double price, int discount, String duration) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.price = price;
		this.discount = discount;
		this.duration = duration;
		//this.appointment = appointment;
	}
	
	public SalonService(String serviceName, double price, int discount, String duration) {
		super();
		
		this.serviceName = serviceName;
		this.price = price;
		this.discount = discount;
		this.duration = duration;
		//this.appointment = appointment;
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
	/*public Appointment getAppointment() {
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
	
	*/
	@Override
	public String toString() {
		return "SalonService [serviceId=" + serviceId + ", serviceName=" + serviceName + ", price=" + price
				+ ", discount=" + discount + ", duration=" + duration + "]";
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments.clear();
		this.appointments.addAll(appointments);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appointments == null) ? 0 : appointments.hashCode());
		result = prime * result + discount;
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (serviceId ^ (serviceId >>> 32));
		result = prime * result + ((serviceName == null) ? 0 : serviceName.hashCode());
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
		SalonService other = (SalonService) obj;
		if (appointments == null) {
			if (other.appointments != null)
				return false;
		} else if (!appointments.equals(other.appointments))
			return false;
		if (discount != other.discount)
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (serviceId != other.serviceId)
			return false;
		if (serviceName == null) {
			if (other.serviceName != null)
				return false;
		} else if (!serviceName.equals(other.serviceName))
			return false;
		return true;
	}
	
	
}
