package com.cg.trg.boot.salon.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.trg.boot.salon.bean.Payment;
import com.cg.trg.boot.salon.dao.IPaymentRepository;

@Service
public class PaymentServiceImpl implements IPaymentService{
	 	@Autowired
	    IPaymentRepository repository;
	 	
	 
		@Override
		public Payment addPayment(Payment payment) {
			repository.save(payment);
			return payment;
		}
		

		@Override
		public Payment removePayment(long paymentId) {
			java.util.Optional<Payment> PaymentToBeDeleted =  repository.findById(paymentId);
			repository.deleteById(paymentId);
			
			if(PaymentToBeDeleted.isPresent()) {
				return PaymentToBeDeleted.get();
			}
			else {
				return null;
			}
		}

		

		@Override
		public Payment updatePayment(long paymentId, Payment payment) {
			if(repository.existsById(paymentId)) {
				Payment paymentToBeUpdated = repository.findById(paymentId).get();
				repository.save(payment);
				return paymentToBeUpdated;
			}
			return null;
		}

		@Override
		public Payment getPaymentDetails(long paymentId) {
			java.util.Optional<Payment> pay= repository.findById(paymentId);
			if(pay.isPresent()) {
				return pay.get();
			}
			else {
			return null;
			}
		}

		@Override
		public List<Payment> getAllPaymentDetails() {
			
			List<Payment> pay = repository.findAll();
			return pay;
		}
	
	
}
