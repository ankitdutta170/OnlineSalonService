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
import com.cg.trg.boot.salon.exceptions.EmptyDataException;
import com.cg.trg.boot.salon.exceptions.PaymentNotFound;
import com.cg.trg.boot.salon.service.PaymentServiceImpl;

@RestController
public class PaymentController {
	@Autowired
	private PaymentServiceImpl repo;

	@PostMapping("/addPayment")
	public String addPayment(@RequestBody Payment payment) {
		Payment pay = repo.addPayment(payment);
		if (pay != null) {
			return "Payment successfull";
		} else
			return "Payment Failed";
	}

	@DeleteMapping("/payment/{id}")
	public ResponseEntity<?> removePayment(@PathVariable(value = "id") long paymentId) {

		Payment payemntDetails = repo.getPaymentDetails(paymentId);
		if (payemntDetails == null) {
			throw new PaymentNotFound("Request", "Payment with paymentId id:" + paymentId + "not found");
		} else {
			repo.removePayment(paymentId);
			return new ResponseEntity<Payment>(payemntDetails, HttpStatus.OK);
		}
	}

	@PutMapping("/payment/update/{id}")
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

	@GetMapping("/payment/details/{id}")
	public ResponseEntity<?> getPaymentDetails(@PathVariable(value = "id") long paymentId) {
		Payment pay = repo.getPaymentDetails(paymentId);
		if (pay == null) {
			throw new PaymentNotFound("Request", "Payment with payment id:" + paymentId + "not found");
		}
		return new ResponseEntity<Payment>(pay, HttpStatus.OK);
	}

	@GetMapping("/payment/all")
	public List<Payment> getAllPaymentDetails() {
		List<Payment> payment = repo.getAllPaymentDetails();
		if (payment.size() == 0) {
			throw new EmptyDataException("No Appointments saved in database");
		}
		return payment;
	}
}
