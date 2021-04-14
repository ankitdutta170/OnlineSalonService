package com.cg.trg.boot.salon.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.trg.boot.salon.bean.Payment;
import com.cg.trg.boot.salon.exceptions.EmptyDataException;
import com.cg.trg.boot.salon.exceptions.PaymentNotFound;
import com.cg.trg.boot.salon.service.PaymentServiceImpl;

@RestController
public class PaymentController {
	@Autowired
	private PaymentServiceImpl repo;
	Logger log = LoggerFactory.getLogger(PaymentController.class);

	@PostMapping("/addpayment")
	public String addPayment(@RequestBody Payment payment) {
		log.info("addPayment() has been invoked");
		Payment pay = repo.addPayment(payment);
		if (pay != null) {
			log.info("Payment added");
			return "Payment successfull";
		} else {
			log.info("Payment Failed");
			return "Payment Failed";}
	}


	@DeleteMapping("/payment/{id}")
	public ResponseEntity<?> removePayment(@PathVariable(value = "id") long paymentId) {
		log.info("removePayment() has been invoked");
		Payment payemntDetails = repo.getPaymentDetails(paymentId);
		if (payemntDetails == null) {
			log.info("payment with "+paymentId+" Not found");
			throw new PaymentNotFound("Request", "Payment with paymentId id:" + paymentId + "not found");
		} else {
			repo.removePayment(paymentId);
			log.info("payment with "+paymentId+" removed");
			return new ResponseEntity<Payment>(payemntDetails, HttpStatus.OK);
		}
	}

	@PutMapping("/payment/update/{id}")
	public String updatePayment(@PathVariable(value = "id") long paymentId, Payment payment) {
		log.info("updatePayment() has been invoked");
		Payment check = repo.getPaymentDetails(paymentId);
		if(check==null) {
			log.info("Payment with paymentId id:" + paymentId + "not found");
			throw new PaymentNotFound("Request", "Payment with paymentId id:" + paymentId + "not found");
		}
		else {
			log.info("Payment with paymentId id:" + paymentId + " Updated");
			repo.updatePayment(paymentId, payment);
			return "Payment Successfully Updated";
			
		}
		
	}

	@GetMapping("/payment/details/{id}")
	public ResponseEntity<?> getPaymentDetails(@PathVariable(value = "id") long paymentId) {
		log.info("getPaymentDetails() has been invoked");
		Payment pay = repo.getPaymentDetails(paymentId);
		if (pay == null) {
			log.info("Payment with paymentId id:" + paymentId + "not found");
			throw new PaymentNotFound("Request", "Payment with payment id:" + paymentId + "not found");
		}
		else {
			log.info("Payment with paymentId id:" + paymentId);
		return new ResponseEntity<Payment>(pay, HttpStatus.OK);
		}
	}

	@GetMapping("/payment/all")
	public List<Payment> getAllPaymentDetails() {
		log.info("getAllPaymentDetails() has been invoked");
		List<Payment> payment = repo.getAllPaymentDetails();
		if (payment.size() == 0) {
			log.info("No payment details");
			throw new EmptyDataException("No Appointments saved in database");
		}
		else {
			log.info("All payment details");		
		return payment;}
	}
	}
	
	
