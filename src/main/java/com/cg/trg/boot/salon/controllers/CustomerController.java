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

import com.cg.trg.boot.salon.bean.Appointment;
import com.cg.trg.boot.salon.bean.Billing;
import com.cg.trg.boot.salon.bean.Customer;
import com.cg.trg.boot.salon.exceptions.AppointmentNotFoundException;
import com.cg.trg.boot.salon.exceptions.BillNotFoundException;
import com.cg.trg.boot.salon.exceptions.CustomerNotFoundException;
import com.cg.trg.boot.salon.exceptions.EmptyDataException;
import com.cg.trg.boot.salon.exceptions.InvalidUserException;
import com.cg.trg.boot.salon.jwt.JwtTokenUtil;
import com.cg.trg.boot.salon.service.ICustomerServiceImpl;
import com.cg.trg.boot.salon.service.ValidateToken;

import io.jsonwebtoken.SignatureException;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	ICustomerServiceImpl service;
	
	@Autowired
	ValidateToken login;
	
	@PostMapping
	public ResponseEntity<String> saveCustomer(@RequestBody Customer customer, HttpServletRequest request) {
		
		Customer saveCustomer = service.addCustomer(customer);
		if(saveCustomer != null) {
			return new ResponseEntity<String>("Customer saved successfully", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Failed to save customer", HttpStatus.BAD_REQUEST);
	}
	@DeleteMapping("{aid}")
	public ResponseEntity<String> removeCustomer(@PathVariable("aid") long custId, HttpServletRequest request) {
		login.validateToken(request,"admin");
		Customer deleteCustomer = service.removeCustomer(custId);
		if(deleteCustomer != null) {
			return new ResponseEntity<String>("Customer deleted successfully", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Failed to delete customer", HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateCustomer(@PathVariable("id")long custId, Customer customer, HttpServletRequest request) {
		login.validateToken(request,"admin");
		Customer updatedCustomer = service.updateCustomer(custId, customer);
		if(updatedCustomer != null) {
			return new ResponseEntity<String>("Customer updated successfully", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Customer failed to update", HttpStatus.NOT_FOUND);
	}
	@PutMapping
	public String updatemployee( @RequestBody Customer customer,HttpServletRequest request) {
		login.validateToken(request,"admin");
		if (service.update(customer))
			return "Customer data successfully updated";
		else
			throw new AppointmentNotFoundException("Update", "Customer with Id " + customer.getUserId() + " to update not found");
	}
	@GetMapping("{aid}")
	public ResponseEntity<?> getCustomer(@PathVariable("aid")long custId, HttpServletRequest request){
		login.validateToken(request,"admin");
		Customer customer = service.getCustomer(custId);
		if(customer == null) {
			throw new CustomerNotFoundException("Request", "Customer with customer custId:"+custId+"not found");
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers(HttpServletRequest request){
		//login.validateToken(request,"admin");
		List<Customer> customers = service.getAllCustomers();
		if(customers.size() == 0) {
			throw new EmptyDataException("No Customers saved in database");
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
	@GetMapping("/getAppointments/{cid}")
	public ResponseEntity<List<Appointment>> getAllAppointmentForCustomer(@PathVariable("cid")long id, HttpServletRequest request){
		login.validateToken(request,"admin");
		List<Appointment> appointments = service.getAllAppointmentsForCustomer(id);
		if(appointments.size() == 0) {
			throw new AppointmentNotFoundException("Appointment not found for customer "+id);
		}
		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
	}
	
	
	@GetMapping("/getBills/{cid}")
	public ResponseEntity<List<Billing>> getAllBillsForCustomer(@PathVariable("cid") long id, HttpServletRequest request){
		login.validateToken(request,"admin");
		List<Billing> bills = service.getAllBillingForCustomer(id);
		if(bills.size()== 0) {
			throw new BillNotFoundException("Bills for the customer not found");
		}
		return new ResponseEntity<List<Billing>>(bills, HttpStatus.OK);
	}
	
	

}
