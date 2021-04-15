package com.cg.trg.boot.salon.service;

import java.util.List;


import com.cg.trg.boot.salon.bean.Payment;

public interface IPaymentService {
	public Payment addPayment(Payment payment);
	public Payment removePayment(long paymentId);
	public Payment updatePayment(long paymentId, Payment payment);
	public Payment getPaymentDetails(long paymentId);
	public List<Payment> getAllPaymentDetails();
	public List<Payment> getPaymentByType(String type);
	public List<Payment> getPaymentByStatus(String status);
}
