package com.cg.trg.boot.salon.dao;

import java.util.List;

import com.cg.trg.boot.salon.bean.Customer;

public interface ICustomerRepository {
	public Customer addCustomer(Customer customer);
	public Customer removeCustomer(long custId);
	public Customer updateCustomer(long custId, Customer customer);
	public Customer getCustomer(long custId);
	public List<Customer> getAllCustomers(); 
}
