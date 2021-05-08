package com.cg.trg.boot.salon.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.trg.boot.salon.bean.Payment;
import com.cg.trg.boot.salon.exceptions.EmptyDataException;
import com.cg.trg.boot.salon.exceptions.PaymentNotFound;
import com.cg.trg.boot.salon.service.PaymentServiceImpl;

@RestController
@RequestMapping("payments")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {
	@Autowired
	private PaymentServiceImpl repo;

	@PostMapping
	public ResponseEntity<String> addPayment(@RequestBody Payment payment, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		Payment pay = repo.addPayment(payment);
		if (pay != null) {
			return new ResponseEntity<String>("Payment successfull", HttpStatus.OK);
		} else
			return new ResponseEntity<String>("Payment Failed", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removePayment(@PathVariable(value = "id") long paymentId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");

		Payment payemntDetails = repo.getPaymentDetails(paymentId);
		if (payemntDetails == null) {
			throw new PaymentNotFound("Request", "Payment with paymentId id:" + paymentId + "not found");
		} else {
			repo.removePayment(paymentId);
			return new ResponseEntity<Payment>(payemntDetails, HttpStatus.OK);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updatePayment(@PathVariable(value = "id") long paymentId,
			@RequestBody Payment payment, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		Payment check = repo.getPaymentDetails(paymentId);
		if (check == null) {
			throw new PaymentNotFound("Request", "Payment with paymentId id:" + paymentId + "not found");
		} else {
			repo.updatePayment(paymentId, payment);
			return new ResponseEntity<String>("Payment Updated Successfully", HttpStatus.OK);

		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPaymentDetails(@PathVariable(value = "id") long paymentId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		Payment pay = repo.getPaymentDetails(paymentId);
		if (pay == null) {
			throw new PaymentNotFound("Request", "Payment with payment id:" + paymentId + "not found");
		}
		return new ResponseEntity<Payment>(pay, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Payment>> getAllPayments(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		List<Payment> payment = repo.getAllPaymentDetails();
		if (payment.size() == 0) {
			throw new EmptyDataException("No Appointments saved in database");
		}
		return new ResponseEntity<List<Payment>>(payment, HttpStatus.OK);
	}

//	@GetMapping("/{type}")
//	public ResponseEntity<List<Payment>> getPaymentByType(@PathVariable("type") String type,
//			HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		String userId = (String) session.getAttribute("userId");
//		String userName = (String) session.getAttribute("username");
//		System.out.println("*******************" + userName + "*************************");
//		System.out.println("*******************" + userId + "*************************");
//		List<Payment> payment = repo.getPaymentByType(type);
//		if (payment.size() == 0)
//			throw new PaymentNotFound("Request", "Payment not found");
//		else
//			return new ResponseEntity<List<Payment>>(payment, HttpStatus.OK);
//	}
//
//	@GetMapping("/{status}")
//	public ResponseEntity<List<Payment>> getPaymentByStatus(@PathVariable("status") String status,
//			HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		String userId = (String) session.getAttribute("userId");
//		String userName = (String) session.getAttribute("username");
//		System.out.println("*******************" + userName + "*************************");
//		System.out.println("*******************" + userId + "*************************");
//		List<Payment> payment = repo.getPaymentByStatus(status);
//		if (payment.size() == 0)
//			throw new PaymentNotFound("Request", "Payment not found");
//		else
//			return new ResponseEntity<List<Payment>>(payment, HttpStatus.OK);
//	}

}
