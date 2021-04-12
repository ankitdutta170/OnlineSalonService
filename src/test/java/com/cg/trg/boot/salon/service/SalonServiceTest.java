package com.cg.trg.boot.salon.service;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
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
import com.cg.trg.boot.salon.bean.SalonService;
import com.cg.trg.boot.salon.dao.ISalonRepository;
import com.cg.trg.boot.salon.service.ISalonService;
import com.cg.trg.boot.salon.service.ISalonServiceImpl;
import com.google.common.util.concurrent.Service;

@SpringBootTest
public class SalonServiceTest {

	@Autowired
	ISalonServiceImpl salonservice;
	
	@MockBean
	ISalonRepository salonserviceRepository;
	@Test
	void contextLoads() {
	}
	
	//1
	@Test
	@DisplayName("Test to add service")
	public void addSalonservice() {
		SalonService salonService = new SalonService();
		salonService.setServiceId(100);
		salonService.setServiceName("hair");
		salonService.setPrice(500);
		salonService.setDiscount(0);
		salonService.setDuration("30");
		
		when(salonserviceRepository.save(salonService)).thenReturn(salonService);
		assertEquals(salonService, salonservice.addService(salonService));
	}
	
	//2
	@Test
	public void deleteSalonservice() {
		int ServiceId = 1;
		salonservice.removeService(ServiceId);
		
		verify(salonserviceRepository, times(1)).deleteById((long) ServiceId);
	}
	
	//3
	@Test
	public void updateSalonservice() {
		Optional<SalonService> salonService = salonserviceRepository.findById(1L);
		if(salonService.isPresent()) {
			salonService.get().setServiceName("Spa");
			salonserviceRepository.save(salonService.get());	
		}
		Optional<SalonService> updatedsalonservice = salonserviceRepository.findById(1L);
		if(updatedsalonservice.isPresent()) {
			assertThat(updatedsalonservice.get().getServiceName().equals("Spa"));
		}
	}
	
	//4
	@Test
	public void getServiceById() {
		Optional<SalonService> salonservice = salonserviceRepository.findById(1L);;
		if(salonservice.isPresent()) {
			assertEquals(salonservice.get().getServiceId(), 1L);
		}
	}
	
	//5
	@Test
	public void getAllSalonservice() {
		Mockito.when(salonserviceRepository.findAll())
		.thenReturn(java.util.stream.Stream.of(new SalonService(),new SalonService()).collect(Collectors.toList()));
		
		assertEquals(2, salonservice.getAllServices().size());
		verify(salonserviceRepository, times(1)).findAll();
		
	}

}

