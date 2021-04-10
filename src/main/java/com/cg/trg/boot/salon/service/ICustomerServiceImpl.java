package com.cg.trg.boot.salon.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.cg.trg.boot.salon.bean.Appointment;
import com.cg.trg.boot.salon.bean.Customer;
import com.cg.trg.boot.salon.dao.ICustomerRepository;
import com.cg.trg.boot.salon.exceptions.DuplicateAppointmentException;
import com.cg.trg.boot.salon.exceptions.DuplicateCustomerException;

public class ICustomerServiceImpl implements ICustomerService{
    @Autowired
    ICustomerRepository repository;

	@Override
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
	
			Customer savedCustomer = repository.save(customer);
			return savedCustomer;
			
		
		
	}

	

	@Override
	public Customer removeCustomer(long custId) {
		// TODO Auto-generated method stub
		
		Optional<Customer> customerToBeDeleted = repository.findById(custId);
		repository.deleteById(custId);
		
		if(customerToBeDeleted.isPresent()) {
			return customerToBeDeleted.get();
		}
		else {
		
		return null;
		}
	}

	@Override
	public Customer updateCustomer(long custId, Customer customer) {
		// TODO Auto-generated method stub
		if(repository.existsById(custId)) {
			Customer customerToBeUpdated = repository.findById(custId).get();
			repository.save(customer);
			return customerToBeUpdated;
		}
		return null;
	}

	@Override
	public Customer getCustomer(long custId) {
		// TODO Auto-generated method stub
		Optional<Customer> customer = repository.findById(custId);
		if(customer.isPresent()) {
			return customer.get();
		}
		else
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		List<Customer> customers = repository.findAll();
		return customers;
		
	}

}