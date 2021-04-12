package com.cg.trg.boot.salon.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.trg.boot.salon.bean.Payment;
import com.cg.trg.boot.salon.bean.PaymentCash;
import com.cg.trg.boot.salon.dao.IPaymentCashRepository;
import com.cg.trg.boot.salon.dao.IPaymentRepository;

@Service
public class IPaymentCashImpl implements IPaymentCashService{
	 	@Autowired
	 	IPaymentCashRepository repository;
	 	
	 
		@Override
		public PaymentCash addCashPayment(PaymentCash payment) {
			repository.save(payment);
			return payment;
		}
		

		@Override
		public PaymentCash removeCashPayment(long paymentId) {
			java.util.Optional<PaymentCash> PaymentToBeDeleted =  repository.findById(paymentId);
			repository.deleteById(paymentId);
			
			if(PaymentToBeDeleted.isPresent()) {
				return PaymentToBeDeleted.get();
			}
			else {
				return null;
			}
		}

		

		@Override
		public PaymentCash updateCashPayment(long paymentId, PaymentCash payment) {
			if(repository.existsById(paymentId)) {
				PaymentCash paymentToBeUpdated = repository.findById(paymentId).get();
				repository.save(payment);
				return paymentToBeUpdated;
			}
			return null;
		}

		@Override
		public PaymentCash getCashPaymentDetails(long paymentId) {
			java.util.Optional<PaymentCash> pay= repository.findById(paymentId);
			if(pay.isPresent()) {
				return pay.get();
			}
			else {
			return null;
			}
		}

		@Override
		public List<PaymentCash> getAllCashPaymentDetails() {
			
			List<PaymentCash> pay = repository.findAll();
			return pay;
		}
	
	
}
