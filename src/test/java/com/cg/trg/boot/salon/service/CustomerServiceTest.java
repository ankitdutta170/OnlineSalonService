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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.trg.boot.salon.bean.Customer;
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
		Customer customerService = new Customer();
		customerService.setUserId(100);
		customerService.setName("Arun");
		customerService.setEmail("abc@gmail.com");
		customerService.setContactNo("500");
		customerService.setDob(LocalDate.of(2021, 4, 15));
		//customerService.setAddress("delhi");
		
		when(customerserviceRepository.save(customerService)).thenReturn(customerService);
		
	}
	
	@Test
	public void deleteCustomerservice() {
		int UserId = 1;
		customerservice.removeCustomer(UserId);
		
		verify(customerserviceRepository, times(1)).deleteById((long) UserId);
	}

	@Test
	public void updateCustomerservice() {
		Optional<Customer> CustomerService = customerserviceRepository.findById(1L);
		if(CustomerService.isPresent()) {
			CustomerService.get().setName("Spa");
			customerserviceRepository.save(CustomerService.get());	
		}
		Optional<Customer> updatedcustomerservice = customerserviceRepository.findById(1L);
		if(updatedcustomerservice.isPresent()) {
			assertThat(updatedcustomerservice.get().getName().equals("Arun"));
		}
	}

	@Test
	public void getServiceById() {
		Optional<Customer> customerservice = customerserviceRepository.findById(1L);;
		if(customerservice.isPresent()) {
			assertEquals(customerservice.get().getUserId(), 1L);
		}
	}

	@Test
	public void getAllCustomerservice() {
		Mockito.when(customerserviceRepository.findAll())
		.thenReturn(java.util.stream.Stream.of(new Customer(),new Customer()).collect(Collectors.toList()));
		
		//assertEquals(2, Customer.getAllCustomers().size());
		//verify(customerserviceRepository, times(1)).findAll();
		
	}

}
