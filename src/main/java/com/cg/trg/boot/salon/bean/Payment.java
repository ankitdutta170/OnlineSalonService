package com.cg.trg.boot.salon.bean;

import javax.persistence.*;
public class Payment {
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long paymentId;
	private String type;
	private String status;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "paymentId" , referencedColumnName="cardId")
	private Card card;
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

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", type=" + type + ", status=" + status + ", card=" + card + "]";
	}

}
