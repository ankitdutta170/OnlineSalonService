package com.cg.trg.boot.salon.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cg.trg.boot.salon.bean.Appointment;
import com.cg.trg.boot.salon.bean.Billing;
import com.cg.trg.boot.salon.bean.Customer;
import com.cg.trg.boot.salon.dao.IAppointmentRepository;
import com.cg.trg.boot.salon.dao.IBillingRepository;
import com.cg.trg.boot.salon.dao.ICustomerRepository;

@Service
public class ICustomerServiceImpl implements ICustomerService{
    @Autowired
    ICustomerRepository repository;
    @Autowired
    IAppointmentRepository appointmentRepository;
    @Autowired
    IBillingRepository billingRepository;

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



	@Override
	public List<Appointment> getAllAppointmentsForCustomer(long userId) {
		
		return appointmentRepository.getAppointmentByCustomer(userId);
	}



	@Override
	public List<Billing> getAllBillingForCustomer(long userId) {
		
		return billingRepository.getAllBillsForCustomer(userId);
	}



	@Override
	public boolean update(Customer customer) {
		if (repository.existsById(customer.getUserId())) {
			repository.save(customer);
			return true;
		}
		return false;
	}

}