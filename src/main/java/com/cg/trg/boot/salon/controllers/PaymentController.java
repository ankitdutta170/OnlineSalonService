package com.cg.trg.boot.salon.controllers;

import java.util.List;
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
import com.cg.trg.boot.salon.bean.PaymentCash;
import com.cg.trg.boot.salon.exceptions.EmptyDataException;
import com.cg.trg.boot.salon.exceptions.PaymentNotFound;
import com.cg.trg.boot.salon.service.IPaymentCashImpl;
import com.cg.trg.boot.salon.service.PaymentServiceImpl;

@RestController
public class PaymentController {
	@Autowired
	private PaymentServiceImpl repo;
	@Autowired
	private IPaymentCashImpl repository;

	@PostMapping("/addpayment/card")
	public String addPayment(@RequestBody Payment payment) {
		Payment pay = repo.addPayment(payment);
		if (pay != null) {
			return "Payment successfull";
		} else
			return "Payment Failed";
	}


	@DeleteMapping("/payment/card/{id}")
	public ResponseEntity<?> removePayment(@PathVariable(value = "id") long paymentId) {

		Payment payemntDetails = repo.getPaymentDetails(paymentId);
		if (payemntDetails == null) {
			throw new PaymentNotFound("Request", "Payment with paymentId id:" + paymentId + "not found");
		} else {
			repo.removePayment(paymentId);
			return new ResponseEntity<Payment>(payemntDetails, HttpStatus.OK);
		}
	}

	@PutMapping("/payment/card/update/{id}")
	public String updatePayment(@PathVariable(value = "id") long paymentId, Payment payment) {
		Payment check = repo.getPaymentDetails(paymentId);
		if(check==null) {
			throw new PaymentNotFound("Request", "Payment with paymentId id:" + paymentId + "not found");
		}
		else {
			repo.updatePayment(paymentId, payment);
			return "Payment Successfully Updated";
			
		}
		
	}

	@GetMapping("/payment/card/details/{id}")
	public ResponseEntity<?> getPaymentDetails(@PathVariable(value = "id") long paymentId) {
		Payment pay = repo.getPaymentDetails(paymentId);
		if (pay == null) {
			throw new PaymentNotFound("Request", "Payment with payment id:" + paymentId + "not found");
		}
		return new ResponseEntity<Payment>(pay, HttpStatus.OK);
	}

	@GetMapping("/payment/card/all")
	public List<Payment> getAllPaymentDetails() {
		List<Payment> payment = repo.getAllPaymentDetails();
		if (payment.size() == 0) {
			throw new EmptyDataException("No Appointments saved in database");
		}
		return payment;
	}
	
	//****************************************************************************************
	//Payment Using cash
	//****************************************************************************************
	@PostMapping("/addPayment/cash")
	public String addCashPayment(@RequestBody PaymentCash payment) {
		PaymentCash pay = repository.addCashPayment(payment);
		if (pay != null) {
			return "Payment successfull";
		} else
			return "Payment Failed";
	}
	
	@DeleteMapping("/payment/cash/{id}")
	public ResponseEntity<?> removeCashPayment(@PathVariable(value = "id") long paymentId) {

		PaymentCash payemntDetails = repository.getCashPaymentDetails(paymentId);
		if (payemntDetails == null) {
			throw new PaymentNotFound("Request", "Payment with paymentId id:" + paymentId + "not found");
		} else {
			repo.removePayment(paymentId);
			return new ResponseEntity<PaymentCash>(payemntDetails, HttpStatus.OK);
		}
	}
	@PutMapping("/payment/card/update/{id}")
	public String updateCashPayment(@PathVariable(value = "id") long paymentId, PaymentCash payment) {
		PaymentCash check = repository.getCashPaymentDetails(paymentId);
		if(check==null) {
			throw new PaymentNotFound("Request", "Payment with paymentId id:" + paymentId + "not found");
		}
		else {
			repository.updateCashPayment(paymentId, payment);
			return "Payment Successfully Updated";
			
		}
		
	}

	@GetMapping("/payment/card/details/{id}")
	public ResponseEntity<?> getCashPaymentDetails(@PathVariable(value = "id") long paymentId) {
		PaymentCash pay = repository.getCashPaymentDetails(paymentId);
		if (pay == null) {
			throw new PaymentNotFound("Request", "Payment with payment id:" + paymentId + "not found");
		}
		return new ResponseEntity<PaymentCash>(pay, HttpStatus.OK);
	}

	@GetMapping("/payment/card/all")
	public List<PaymentCash> getAllCashPaymentDetails() {
		List<PaymentCash> payment = repository.getAllCashPaymentDetails();
		if (payment.size() == 0) {
			throw new EmptyDataException("No Appointments saved in database");
		}
		return payment;
	}
}
