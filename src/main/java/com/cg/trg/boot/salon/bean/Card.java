package com.cg.trg.boot.salon.bean;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Card")
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="card_id")
	private long cardId;
	private String cardName;
	private String cardNumber;
    private LocalDate cardExpiry;
    private int cvv;

    @OneToOne(mappedBy = "card", targetEntity = Payment.class,cascade = CascadeType.ALL)
    @JsonIgnore

    private Payment payment;
	public Card() {
		
	}
	
	

	public Card(long cardId, String cardName, String cardNumber, LocalDate cardExpiry, int cvv, Payment payment) {
		super();
		this.cardId = cardId;
		this.cardName = cardName;
		this.cardNumber = cardNumber;
		this.cardExpiry = cardExpiry;
		this.cvv = cvv;
		this.payment = payment;
	}
	public Card(long cardId, String cardName, String cardNumber, LocalDate cardExpiry, int cvv) {
		super();
		this.cardId = cardId;
		this.cardName = cardName;
		this.cardNumber = cardNumber;
		this.cardExpiry = cardExpiry;
		this.cvv = cvv;
		
	}
	
	public Card(String cardName, String cardNumber, LocalDate cardExpiry, int cvv, Payment payment) {
		super();
		
		this.cardName = cardName;
		this.cardNumber = cardNumber;
		this.cardExpiry = cardExpiry;
		this.cvv = cvv;
		this.payment = payment;
	}


	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public LocalDate getCardExpiry() {
		return cardExpiry;
	}
	public void setCardExpiry(LocalDate cardExpiry) {
		this.cardExpiry = cardExpiry;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	
	
	public Payment getPayment() {
		return payment;
	}



	public void setPayment(Payment payment) {
		this.payment = payment;
	}



	@Override
	public String toString() {
		return "Card [cardId=" + cardId + ", cardName=" + cardName + ", cardNumber=" + cardNumber + ", cardExpiry="
				+ cardExpiry + ", cvv=" + cvv + ", payment=" + payment + "]";
	}



	

	
	
    
}
