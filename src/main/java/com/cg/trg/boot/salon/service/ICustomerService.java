package com.cg.trg.boot.salon.service;

import java.util.List;

import com.cg.trg.boot.salon.bean.Appointment;
import com.cg.trg.boot.salon.bean.Billing;
import com.cg.trg.boot.salon.bean.Customer;

public interface ICustomerService {
	public Customer addCustomer(Customer customer);
	public Customer removeCustomer(long custId);
	public Customer updateCustomer(long custId, Customer customer);
	public boolean update(Customer customer);
	public Customer getCustomer(long custId);
	public List<Customer> getAllCustomers(); 
	public List<Appointment> getAllAppointmentsForCustomer(long userId);
	public List<Billing> getAllBillingForCustomer(long userId);
}
