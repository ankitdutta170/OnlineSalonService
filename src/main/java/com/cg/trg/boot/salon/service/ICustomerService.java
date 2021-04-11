package com.cg.trg.boot.salon.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.trg.boot.salon.bean.Customer;

public interface ICustomerService {
	public Customer addCustomer(Customer customer);
	public Customer removeCustomer(long custId);
	public Customer updateCustomer(long custId, Customer customer);
	public Customer getCustomer(long custId);
	public List<Customer> getAllCustomers(); 
}
