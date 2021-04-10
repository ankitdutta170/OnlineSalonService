package com.cg.trg.boot.salon.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.trg.boot.salon.bean.Customer;


public interface ICustomerRepository extends JpaRepository<Customer, Long> {
	/*
	 * public Customer addCustomer(Customer customer); public Customer
	 * removeCustomer(long custId); public Customer updateCustomer(long custId,
	 * Customer customer); public Customer getCustomer(long custId); public
	 * List<Customer> getAllCustomers();
	 */
}
