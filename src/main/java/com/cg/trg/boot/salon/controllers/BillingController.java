package com.cg.trg.boot.salon.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.cg.trg.boot.salon.bean.Appointment;
import com.cg.trg.boot.salon.bean.Billing;
import com.cg.trg.boot.salon.exceptions.AppointmentNotFoundException;
import com.cg.trg.boot.salon.exceptions.BillNotFoundException;
import com.cg.trg.boot.salon.exceptions.EmptyDataException;
import com.cg.trg.boot.salon.exceptions.InvalidUserException;
import com.cg.trg.boot.salon.jwt.JwtTokenUtil;
import com.cg.trg.boot.salon.service.BillingServiceImpl;

import io.jsonwebtoken.SignatureException;

@RestController
@RequestMapping("bill")
@CrossOrigin(origins = "http://localhost:4200")
public class BillingController { 
	@Autowired
	BillingServiceImpl service1;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping
	public ResponseEntity<String>saveBill(@RequestBody Billing bill,HttpServletRequest request) {
		
		validateToken(request);
		Billing saveBill= service1.addBill(bill);
		if(saveBill != null) {
			return new ResponseEntity<String>("Bill successfully made", HttpStatus.OK);			
		}
		else
			return new ResponseEntity<String>("Failed to give Bill", HttpStatus.NOT_FOUND);
			
	}
	@DeleteMapping("{aid}")
	public ResponseEntity<String> removeBill(@PathVariable("aid") long id,HttpServletRequest request) {
		validateToken(request);
		Billing deleteBill = service1.removeBill(id);
		if(deleteBill != null) {
			return new ResponseEntity<String>("Bill successfully deleted", HttpStatus.OK);		
		}
		else
			return new ResponseEntity<String>("Bill failed to delete", HttpStatus.BAD_REQUEST);
			
	}
	@PutMapping("{bid}")
	public ResponseEntity<String> updateBill(@PathVariable("bid") long id,@RequestBody Billing bill,HttpServletRequest request) {
		validateToken(request);
		Billing updatedBill = service1.updateBill(id, bill);
		if(updatedBill != null) {
			return new ResponseEntity<String>("Bill succesfully updated", HttpStatus.OK);			
		}
		else
			return new ResponseEntity<String>("Bill failed to update", HttpStatus.BAD_REQUEST);
			
	}
	@PutMapping
	public String updateBill( @RequestBody Billing bill,HttpServletRequest request) {
		validateToken(request);
		if (service1.update(bill))
			return "Bill data successfully updated";
		else
			throw new AppointmentNotFoundException("Update", "Appointment with Id " + bill.getBillId() + " to update not found");
	}
	@GetMapping("{aid}")
	public ResponseEntity<?> getBill(@PathVariable("aid")long id,HttpServletRequest request){
		validateToken(request);
		Billing bill = service1.getBillDetails(id);
		if(bill == null) {
			throw new BillNotFoundException("Request", "Bill with Bill id:"+id+"not found");
		}
		return new ResponseEntity<Billing>(bill, HttpStatus.OK);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public ResponseEntity<List<Billing>> getAllBills(HttpServletRequest request){
		validateToken(request);
		List<Billing> bills = service1.getAllBills();
		if(bills.size() == 0) {
			throw new EmptyDataException("No Bill saved in database");
		}
		return new ResponseEntity<List<Billing>>(bills, HttpStatus.OK);
		
		
	}
	
	public void validateToken(HttpServletRequest request) {
		final String tokenHeader = request.getHeader("Authorization");

		String jwtToken = null;

		if (tokenHeader == null)
			throw new InvalidUserException("User Not Logged In or token not included");
		// JWT Token is in the form "Bearer token". Remove Bearer word
		if (!tokenHeader.startsWith("Bearer "))
			throw new InvalidUserException("Invalid Token");

		jwtToken = tokenHeader.substring(7);
		try {
			if (!(jwtTokenUtil.validateToken(jwtToken)))
				throw new InvalidUserException("Token Expired. Need Relogin");

		} catch (SignatureException ex) {
			throw new InvalidUserException("Invalid Token");
		}
	}
	
	
	
}


