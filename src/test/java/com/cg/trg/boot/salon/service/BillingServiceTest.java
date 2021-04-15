package com.cg.trg.boot.salon.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
import com.cg.trg.boot.salon.dao.IBillingRepository;
@SpringBootTest
class BillingServiceTest {
	@Autowired
	BillingServiceImpl billService;
	
	@MockBean
	IBillingRepository billRepository;
	@Test
	@DisplayName("Test for adding Billing")
	
	public void addBilling() {
		Billing bill = new Billing();
		Customer customer = new Customer(100,"12345","12345","Customer",false);
		Card card = new Card(100,"Visa","123456",LocalDate.of(2026, 8, 25),356);
		Payment payment = new Payment("Card","Paid",card);
				
		bill.setBillId(100);
		bill.setAmount(500.00);
		bill.setBillingDate(LocalDate.of(2021, 4, 15));		
		bill.setCustomer(customer);
		bill.setPayment(payment);
		Mockito.when(billRepository.save(bill)).thenReturn(bill);
		assertEquals(bill, billService.addBill(bill));
	
	}
	@Test
	public void getAllBillTest() {
		Mockito.when(billRepository.findAll())
		.thenReturn(java.util.stream.Stream.of(new Billing(),new Billing()).collect(Collectors.toList()));
		
		assertEquals(2, billService.getAllBills().size());
		verify(billRepository, times(1)).findAll();
		
	}
	
	@Test
	public void deleteBillTest() {
		SalonService salonService = new SalonService(100,"Spa",500,0,"20");
		Customer customer = new Customer(100,"12345","12345","Customer",false);
		Address address = new Address(100,"NW004","Lane1","Area1","Bangalore","Karnataka",101245,customer);
		Card card = new Card(100,"Visa","123456",LocalDate.of(2026, 8, 25),356);
		Payment payment = new Payment("Card","Paid",card);
		Billing billing = new Billing(100,500,LocalDate.now(),customer,payment,null);
		
		Appointment appointment = new Appointment("Whitefield","Salon",salonService,LocalDate.of(2021, 4, 20),LocalTime.of(16, 0),customer, address,billing);

		assertNotEquals(billing, billService.removeBill(100));
	}
	
	
	@Test
	public void getBillingById() {
		SalonService salonService = new SalonService(100,"Spa",500,0,"20");
		Customer customer = new Customer(100,"12345","12345","Customer",false);
		Address address = new Address(100,"NW004","Lane1","Area1","Bangalore","Karnataka",101245,customer);
		Card card = new Card(100,"Visa","123456",LocalDate.of(2026, 8, 25),356);
		Payment payment = new Payment("Card","Paid",card);
		Billing billing = new Billing(100,500,LocalDate.now(),customer,payment,null);
		
		Appointment appointment = new Appointment("Whitefield","Salon",salonService,LocalDate.of(2021, 4, 20),LocalTime.of(16, 0),customer, address,billing);
		Mockito.when(billRepository.save(billing)).thenReturn(billing);
		assertNotEquals(billing, billRepository.findById(billing.getBillId()));
		}
	
	
	@Test
	public void updateBillingTest() {
		SalonService salonService = new SalonService(100,"Spa",500,0,"20");
		Customer customer = new Customer(100,"12345","12345","Customer",false);
		Address address = new Address(100,"NW004","Lane1","Area1","Bangalore","Karnataka",101245,customer);
		Card card = new Card(100,"Visa","123456",LocalDate.of(2026, 8, 25),356);
		Payment payment = new Payment("Card","Paid",card);
		Billing billing = new Billing(100,500,LocalDate.now(),customer,payment,null);
		
		Appointment appointment = new Appointment("Whitefield","Salon",salonService,LocalDate.of(2021, 4, 20),LocalTime.of(16, 0),customer, address,billing);
		Mockito.when(billRepository.save(billing)).thenReturn(billing);
		billing = new Billing(100,500,LocalDate.now(),customer,payment,null);
		 assertNotEquals(billing, billRepository.save(billing));
	}
	

}
