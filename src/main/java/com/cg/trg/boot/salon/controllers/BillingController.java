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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.trg.boot.salon.bean.Billing;
import com.cg.trg.boot.salon.exceptions.BillNotFoundException;
import com.cg.trg.boot.salon.exceptions.EmptyDataException;
import com.cg.trg.boot.salon.service.BillingServiceImpl;
@CrossOrigin
@RestController
@RequestMapping("bill")
public class BillingController { 
	@Autowired
	BillingServiceImpl service1;
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String>saveBill(Billing bill,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		
		Billing saveBill= service1.addBill(bill);
		if(saveBill != null) {
			return new ResponseEntity<String>("Bill successfully made", HttpStatus.OK);
			
		}
		else
			return new ResponseEntity<String>("Failed to give Bill", HttpStatus.NOT_FOUND);
			
	}
	@DeleteMapping("{aid}")
	public ResponseEntity<String> removeBill(@PathVariable("aid") long id,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		Billing deleteBill = service1.removeBill(id);
		if(deleteBill != null) {
			return new ResponseEntity<String>("Bill successfully deleted", HttpStatus.OK);
		
		}
		else
			return new ResponseEntity<String>("Bill failed to delete", HttpStatus.BAD_REQUEST);
			
	}
	@PutMapping
	public ResponseEntity<String> updateBill(long id, Billing bill,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		Billing updatedBill = service1.updateBill(id, bill);
		if(updatedBill != null) {
			return new ResponseEntity<String>("Bill succesfully updated", HttpStatus.OK);
			
		}
		else
			return new ResponseEntity<String>("Bill failed to update", HttpStatus.BAD_REQUEST);
			
	}
	@GetMapping("{aid}")
	public ResponseEntity<?> getBill(@PathVariable("aid")long id,HttpServletRequest request){
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		Billing bill = service1.getBillDetails(id);
		if(bill == null) {
			throw new BillNotFoundException("Request", "Bill with Bill id:"+id+"not found");
		}
		return new ResponseEntity<Billing>(bill, HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<Billing>> getAllBills(HttpServletRequest request){
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		
		List<Billing> bills = service1.getAllBills();
		if(bills.size() == 0) {
			throw new EmptyDataException("No Bill saved in database");
		}
		return new ResponseEntity<List<Billing>>(bills, HttpStatus.OK);
		
		
	}
	
	
	
}


