package com.cg.trg.boot.salon.bean;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
@Table(name = "Payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_id")
	private long paymentId;
	private String type;
	private String status;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "card_id_fk")
	private Card card;
	
	@OneToOne(mappedBy = "payment", targetEntity = Billing.class)
	private Billing billing;
	
	public Payment() {
		
	}
	

	

	public Payment(long paymentId, String type, String status, Card card, Billing billing) {
		super();
		this.paymentId = paymentId;
		this.type = type;
		this.status = status;
		this.card = card;
		this.billing = billing;
	}
	public Payment(long paymentId, String type, String status, Card card) {
		super();
		this.paymentId = paymentId;
		this.type = type;
		this.status = status;
		this.card = card;
		
	}

	public Payment( String type, String status, Card card, Billing billing) {
		super();
		
		this.type = type;
		this.status = status;
		this.card = card;
		this.billing = billing;
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

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
	
	
	
	public Billing getBilling() {
		return billing;
	}




	public void setBilling(Billing billing) {
		this.billing = billing;
	}




	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", type=" + type + ", status=" + status + ", card=" + card
				+ ", billing=" + billing + "]";
	}




	

	

}
