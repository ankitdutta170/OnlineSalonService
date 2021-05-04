package com.cg.trg.boot.salon.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.LocalTime;
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
import com.cg.trg.boot.salon.dao.IAddressRepository;

@SpringBootTest
class AdressServiceTest {
	@Autowired
	AddressServiceImpl addressService;
	@MockBean
	IAddressRepository addressRepository;
	@Test
	@DisplayName("Test for adding Adress")
	public void addAddress() {
		Address address = new Address();
		Customer customer = new Customer(100,"12345","12345","customer",false);	
		address.setDoorNo("15");
		address.setStreet("khaman");
		address.setArea("sankhachila");		
		address.setCustomer(customer);
		address.setCity("jajpur");
		address.setState("Odisha");
		address.setPincode(755015);
		address.setAddressId(01);
		Mockito.when(addressRepository.save(address)).thenReturn(address);
		assertEquals(address, addressService.addAddress(address));
	}
	@Test
	public void getAllAddressTest() {
		Mockito.when(addressRepository.findAll())
		.thenReturn(java.util.stream.Stream.of(new Address(),new Address()).collect(Collectors.toList()));
		
		assertEquals(2, addressService.getAllAddress().size());
		verify(addressRepository, times(1)).findAll();
		
	}

	@Test
	public void deleteAdressTest() {
		SalonService salonService = new SalonService(100,"Spa",500,0,"20");
		Customer customer = new Customer(100,"12345","12345","Customer",false);
		Address address = new Address(100,"NW004","Lane1","Area1","Bangalore","Karnataka",101245,customer);
		Card card = new Card(100,"Visa","123456",LocalDate.of(2026, 8, 25),356);
		Payment payment = new Payment("Card","Paid",card);
		Billing billing = new Billing(100,500,LocalDate.now(),customer,payment,null);
		Appointment appointment = new Appointment("Whitefield","Salon",salonService,LocalDate.of(2021, 4, 20),LocalTime.of(16, 0),customer,billing);
		assertNotEquals(appointment, addressService.removeAddress(100));
	}
	@Test
	public void getAdressById() {
		Customer customer = new Customer(100,"12345","12345","Customer",false);
		Address address = new Address(100,"NW004","Lane1","Area1","Bangalore","Karnataka",101245,customer);
				
		Mockito.when(addressRepository.save(address)).thenReturn(address);
		assertNotEquals(address, addressRepository.findById(address.getAddressId()));
	}
	@Test
	public void updateAdressTest() {
		Customer customer = new Customer(100,"12345","12345","Customer",false);
		Address address = new Address(100,"NW004","Lane1","Area1","Bangalore","Karnataka",101245,customer);
		Mockito.when(addressRepository.save(address)).thenReturn(address);
	    address = new Address(100,"NW004","Lane1","Area1","Bangalore","Karnataka",101245,customer);
		assertNotEquals(address, addressRepository.save(address));
	}

}
