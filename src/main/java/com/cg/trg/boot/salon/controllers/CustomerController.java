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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.trg.boot.salon.bean.Customer;
import com.cg.trg.boot.salon.exceptions.CustomerNotFoundException;
import com.cg.trg.boot.salon.exceptions.EmptyDataException;
import com.cg.trg.boot.salon.service.ICustomerServiceImpl;

@RestController
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	ICustomerServiceImpl service;
	
	@PostMapping
	public String saveCustomer(Customer customer) {
		Customer saveCustomer = service.addCustomer(customer);
		if(saveCustomer != null) {
			return "Customer successfully made";
		}
		else
			return "Failed to give Customer";
	}
	@DeleteMapping("{aid}")
	public String removeCustomer(@PathVariable("aid") long custId) {
		Customer deleteCustomer = service.removeCustomer(custId);
		if(deleteCustomer != null) {
			return "Customer successfully deleted";
		}
		else
			return "Customer failed to delete";
	}
	
	@PutMapping
	public String updateCustomer(long custId, Customer customer) {
		Customer updatedCustomer = service.updateCustomer(custId, customer);
		if(updatedCustomer != null) {
			return "Customer succesfully updated";
		}
		else
			return "Customer failed to update";
	}
	@GetMapping("{aid}")
	public ResponseEntity<?> getCustomer(@PathVariable("aid")long custId){
		Customer customer = service.getCustomer(custId);
		if(customer == null) {
			throw new CustomerNotFoundException("Request", "Customer with customer custId:"+custId+"not found");
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	@GetMapping
	public List<Customer> getAllCustomers(){
		List<Customer> customers = service.getAllCustomers();
		if(customers.size() == 0) {
			throw new EmptyDataException("No Customers saved in database");
		}
		return customers;
	}

}
