package com.cg.trg.boot.salon.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
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
import com.cg.trg.boot.salon.bean.Customer;
import com.cg.trg.boot.salon.dao.IAddressRepository;

@SpringBootTest
class AdressServiceTest {
	@Autowired
	AddressServiceImpl addressService;
	@MockBean
	IAddressRepository adressRepository;
	@Test
	@DisplayName("Test for adding Adress")
	public void addAddress() {
		Address adress = new Address();
		Customer customer = new Customer(100,"12345","Customer",false);
		Appointment appointment = new Appointment();		
		adress.setDoorNo("15");
		adress.setStreet("khaman");
		adress.setArea("sankhachila");		
		adress.setCustomer(customer);
		adress.setCity("jajpur");
		adress.setState("Odisha");
		adress.setPincode(755015);
		adress.setAddressId("om");
		adress.setAppointment(appointment);
		
	
	}
	@Test
	public void getAllAddressTest() {
		Mockito.when(adressRepository.findAll())
		.thenReturn(java.util.stream.Stream.of(new Address(),new Address()).collect(Collectors.toList()));
		
		assertEquals(2, addressService.getAllAddress().size());
		verify(adressRepository, times(1)).findAll();
		
	}

	@Test
	public void deleteAdressTest() {
		int addressId = 1;
		addressService.removeAddress(addressId);
		
		verify(adressRepository, times(1)).deleteById((long) addressId);
	}
	@Test
	public void getAdressById() {
		Optional<Address> adress = adressRepository.findById(1L);
		if(adress.isPresent()) {
			//assertEquals(adress.get().getAdressId(), 1L);
		}
	}
	@Test
	public void updateAdressTest() {
		Optional<Address> address = adressRepository.findById(1L);
		if(address.isPresent()) {
			//address.get().setVisitType("Home");
			adressRepository.save(address.get());
			
		}
		Optional<Address> updatedAdress = adressRepository.findById(1L);
		if(updatedAdress.isPresent()) {
			//assertThat(updatedAdress.get().getVisitType().equals("Home"));
		}
	}

}
