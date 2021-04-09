package com.cg.trg.boot.salon.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.trg.boot.salon.bean.Payment;

public interface IPaymentService {
	public Payment addPayment(Payment payment);
	public Payment removePayment(long paymentId);
	public Payment updatePayment(long paymentId, Payment payment);
	public Payment getPaymentDetails(long paymentId);
	public List<Payment> getAllPaymentDetails();
}
