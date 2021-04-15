package com.cg.trg.boot.salon.service;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.cg.trg.boot.salon.bean.SalonService;
import com.cg.trg.boot.salon.dao.ISalonRepository;


@SpringBootTest
public class SalonServiceTest {

	@Autowired
	ISalonServiceImpl salonservice;
	
	@MockBean
	ISalonRepository salonserviceRepository;
	
	//1
	@Test
	@DisplayName("Test to add service")
	public void addSalonserviceTest() {
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
	public void getAllSalonserviceTest() {
		Mockito.when(salonserviceRepository.findAll())
		.thenReturn(java.util.stream.Stream.of(new SalonService(),new SalonService()).collect(Collectors.toList()));
		assertEquals(2, salonservice.getAllServices().size());
		verify(salonserviceRepository, times(1)).findAll();	
	}

	
	//3
	@Test
	public void getSalonserviceById() {
		SalonService salonservice = new SalonService(100,"Spa",500,0,"20");
		when(salonserviceRepository.save(salonservice)).thenReturn(salonservice);
		assertNotEquals(salonservice, salonserviceRepository.findById(salonservice.getServiceId()));
	}
	
	//4
		@Test
		public void deleteSalonserviceTest() {
			SalonService salonService = new SalonService(100,"Spa",500,0,"20");
			assertNotEquals(salonservice, salonservice.removeService(31));
		}
	
	//5
	@Test
	public void updateSalonserviceTest() {
		SalonService salonService = new SalonService(100,"Spa",500,0,"20");
		when(salonserviceRepository.save(salonService)).thenReturn(salonService);
		salonService = new SalonService(100,"Spa",500,0,"20");
		 assertNotEquals(salonService, salonserviceRepository.save(salonService));
	}
}

