package com.cg.trg.boot.salon.service;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.trg.boot.salon.bean.Address;
import com.cg.trg.boot.salon.bean.Appointment;
import com.cg.trg.boot.salon.bean.Billing;
import com.cg.trg.boot.salon.bean.Card;
import com.cg.trg.boot.salon.bean.Customer;
import com.cg.trg.boot.salon.bean.Payment;
import com.cg.trg.boot.salon.bean.SalonService;
import com.cg.trg.boot.salon.dao.ICustomerRepository;

import com.cg.trg.boot.salon.service.ICustomerServiceImpl;

import com.google.common.util.concurrent.Service;

@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	ICustomerServiceImpl customerservice;
	
	@MockBean
	ICustomerRepository customerserviceRepository;
	@Test
	void contextLoads() {
	}
	

	@Test
	@DisplayName("Test to ")
	public void addCustomerService() {
		Appointment appointment = new Appointment();
		SalonService salonService = new SalonService(100,"Spa",500,0,"20");
		Customer customerService1 = new Customer("ankit","12345","customer");
		Address address = new Address(100,"NW004","Lane1","Area1","Bangalore","Karnataka",101245,customerService1);
		Card card = new Card(100,"Visa","123456",LocalDate.of(2026, 8, 25),356);
		Payment payment = new Payment("Card","Paid",card);
		Billing billing = new Billing(100,500,LocalDate.now(),customerService1,payment,appointment);
		
		
		customerService1.setName("Arun");
		customerService1.setEmail("abc@gmail.com");
		customerService1.setContactNo("500");
		customerService1.setDob(LocalDate.of(2021, 4, 15));
		
		Mockito.when(customerserviceRepository.save(customerService1)).thenReturn(customerService1);
		assertEquals(customerService1,customerservice.addCustomer(customerService1));
		
	}
	
	@Test
	public void deleteCustomerservice() {
		SalonService salonService = new SalonService(100,"Spa",500,0,"20");
		Customer customer = new Customer("ankit","12345","customer");
		Address address = new Address(100,"NW004","Lane1","Area1","Bangalore","Karnataka",101245,customer);
		Card card = new Card(100,"Visa","123456",LocalDate.of(2026, 8, 25),356);
		Payment payment = new Payment("Card","Paid",card);
		Billing billing = new Billing(100,500,LocalDate.now(),customer,payment,null);
		
		Appointment appointment = new Appointment("Whitefield","Salon",salonService,LocalDate.of(2021, 4, 20),LocalTime.of(16, 0),customer,billing);

		assertNotEquals(customer, customerservice.removeCustomer(31));
	}

	@Test
	public void updateCustomerservice() {
		Customer customer = new Customer("ankit","12345","customer");
		Card card = new Card(100,"Visa","123456",LocalDate.of(2026, 8, 25),356);
		Payment payment = new Payment("Card","Paid",card);
		Billing billing = new Billing(100,500,LocalDate.now(),customer,payment,null);
		Mockito.when(customerserviceRepository.save(customer)).thenReturn(customer);
		 customer = new Customer("ankit","12345","customer");

		assertNotEquals(customer, customerserviceRepository.save(customer));
	}

	@Test
	public void getServiceById() {
		Customer customer = new Customer("ankit","12345","customer");
		Card card = new Card(100,"Visa","123456",LocalDate.of(2026, 8, 25),356);
		Payment payment = new Payment("Card","Paid",card);
		Billing billing = new Billing(100,500,LocalDate.now(),customer,payment,null);
		Mockito.when(customerserviceRepository.save(customer)).thenReturn(customer);
		assertNotEquals(customer, customerserviceRepository.findById(customer.getUserId()));
	}

	@Test
	public void getAllCustomerservice() {
		Mockito.when(customerserviceRepository.findAll())
		.thenReturn(java.util.stream.Stream.of(new Customer(),new Customer()).collect(Collectors.toList()));
		
		assertEquals(2, customerservice.getAllCustomers().size());
	}

}
