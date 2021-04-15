package com.cg.trg.boot.salon.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@RestController
public class PaymentController {
	@Autowired
	private PaymentServiceImpl repo;


	@PostMapping(value ="/addpayment", consumes = {org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addPayment(@RequestBody Payment payment) {
		Payment pay = repo.addPayment(payment);
		if (pay != null) {
			return new ResponseEntity<String>("Payment successfull",HttpStatus.OK);
		} else
			return new ResponseEntity<String>("Payment Failed",HttpStatus.BAD_REQUEST);
	}


	@DeleteMapping("/payment/delete/{id}")
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
	public ResponseEntity<String> updatePayment(@PathVariable(value = "id") long paymentId,@RequestBody Payment payment) {
		Payment check = repo.getPaymentDetails(paymentId);
		if(check==null) {
			throw new PaymentNotFound("Request", "Payment with paymentId id:" + paymentId + "not found");
		}
		else {
			repo.updatePayment(paymentId, payment);
			return new ResponseEntity<String>("Payment Updated Successfully",HttpStatus.OK);
			
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
	public ResponseEntity<List<Payment>> getAllPaymentDetails() {
		List<Payment> payment = repo.getAllPaymentDetails();
		if (payment.size() == 0) {
			throw new EmptyDataException("No Appointments saved in database");
		}
		return new ResponseEntity<List<Payment>>(payment, HttpStatus.OK);
	}
	@GetMapping("/payment/type/{type}")
	public ResponseEntity<List<Payment>> getPaymentByType(@PathVariable("type") String type){
		List<Payment> payment=repo.getPaymentByType(type);
		if(payment.size()==0)
			throw new PaymentNotFound("Request", "Payment not found");
		else
			return new ResponseEntity<List<Payment>>(payment, HttpStatus.OK);
	}
	
	@GetMapping("/payment/status/{status}")
	public ResponseEntity<List<Payment>> getPaymentByStatus(@PathVariable("status") String status){
		List<Payment> payment=repo.getPaymentByStatus(status);
		if(payment.size()==0)
			throw new PaymentNotFound("Request", "Payment not found");
		else
			return new ResponseEntity<List<Payment>>(payment, HttpStatus.OK);
	}
	
	}
	
	
