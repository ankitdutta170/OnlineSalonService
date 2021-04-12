package com.cg.trg.boot.salon.bean;

import javax.persistence.*;




@Entity
@Table(name = "Payment")
public class PaymentCash {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_id")
	private long paymentId;
	private String type;
	private String status;
	//final String type="Card";
	
	
	
	
	public PaymentCash() {
		
	}
	

	public PaymentCash(long paymentId, String type, String status) {
		super();
		this.paymentId = paymentId;
		this.type = type;
		this.status = status;
		
		
	}

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", type=" + type + ", status=" + status + ", card=]";
	}

	

}
