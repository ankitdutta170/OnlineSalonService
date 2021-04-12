package com.cg.trg.boot.salon.service;

import java.util.List;



import com.cg.trg.boot.salon.bean.Payment;
import com.cg.trg.boot.salon.bean.PaymentCash;

public interface IPaymentCashService {
	public PaymentCash addCashPayment(PaymentCash payment);
	public PaymentCash removeCashPayment(long paymentId);
	public PaymentCash updateCashPayment(long paymentId, PaymentCash payment);
	public PaymentCash getCashPaymentDetails(long paymentId);
	public List<PaymentCash> getAllCashPaymentDetails();
}
