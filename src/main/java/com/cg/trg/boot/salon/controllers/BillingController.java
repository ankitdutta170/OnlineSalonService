package com.cg.trg.boot.salon.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cg.trg.boot.salon.bean.Billing;
import com.cg.trg.boot.salon.exceptions.AppointmentNotFoundException;
import com.cg.trg.boot.salon.exceptions.EmptyDataException;
import com.cg.trg.boot.salon.service.BillingServiceImpl;

@RestController
@RequestMapping("bill")
public class BillingController { 
	@Autowired
	BillingServiceImpl service1;
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveBill(Billing bill) {
		Billing saveBill = service1.addBill(bill);
		if(saveBill != null) {
			return "Bill successfully made";
		}
		else
			return "Failed to give Bill";
	}
	@DeleteMapping("{aid}")
	public String removeBill(@PathVariable("aid") long id) {
		Billing deleteBill = service1.removeBill(id);
		if(deleteBill != null) {
			return "Bill successfully deleted";
		}
		else
			return "Bill failed to delete";
	}
	@PutMapping
	public String updateBill(long id, Billing bill) {
		Billing updatedBill = service1.updateBill(id, bill);
		if(updatedBill != null) {
			return "Bill succesfully updated";
		}
		else
			return "Bill failed to update";
	}
	@GetMapping("{aid}")
	public ResponseEntity<?> getBill(@PathVariable("aid")long id){
		Billing bill = service1.getBillDetails(id);
		if(bill == null) {
			throw new AppointmentNotFoundException("Request", "Bill with Bill id:"+id+"not found");
		}
		return new ResponseEntity<Billing>(bill, HttpStatus.OK);
	}
	@GetMapping
	public List<Billing> getAllBills(){
		List<Billing> bills = service1.getAllBills();
		if(bills.size() == 0) {
			throw new EmptyDataException("No Bill saved in database");
		}
		return bills;
		
	}
	
	
	
}


