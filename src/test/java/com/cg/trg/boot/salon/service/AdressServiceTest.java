package com.cg.trg.boot.salon.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.trg.boot.salon.bean.Address;
import com.cg.trg.boot.salon.bean.Customer;
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
		//Appointment appointment = new Appointment();		
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
		int addressId = 1;
		addressService.removeAddress(addressId);
		
		verify(addressRepository, times(1)).deleteById((long) addressId);
	}
	@Test
	public void getAdressById() {
		Optional<Address> adress = addressRepository.findById(1L);
		if(adress.isPresent()) {
			assertEquals(adress.get().getAddressId(), 1L);
		}
	}
	@Test
	public void updateAdressTest() {
		Optional<Address> address = addressRepository.findById(1L);
		if(address.isPresent()) {
			
			addressRepository.save(address.get());
			
		}
		Optional<Address> updatedAdress = addressRepository.findById(1L);
		if(updatedAdress.isPresent()) {
			
		}
	}

}
