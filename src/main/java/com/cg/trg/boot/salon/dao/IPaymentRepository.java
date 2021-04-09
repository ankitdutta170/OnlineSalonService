package com.cg.trg.boot.salon.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.trg.boot.salon.bean.Payment;


public interface IPaymentRepository extends JpaRepository<Payment, Long> {
	public Payment addPayment(Payment payment);
	public Payment removePayment(long id);
	public Payment updatePayment(long id, Payment payment);
	public Payment getPaymentDetails(long id);
	public List<Payment> getAllPaymentDetails();

}
	/*
	 * public Payment addPayment(Payment payment); public Payment removePayment(long
	 * id); public Payment updatePayment(long id, Payment payment); public Payment
	 * getPaymentDetails(long id); public List<Payment> getAllPaymentDetails();
	 */


