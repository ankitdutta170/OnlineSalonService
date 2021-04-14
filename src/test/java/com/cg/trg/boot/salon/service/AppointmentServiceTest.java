package com.cg.trg.boot.salon.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
import com.cg.trg.boot.salon.dao.IAppointmentRepository;
import com.sun.el.stream.Stream;

@SpringBootTest
class AppointmentServiceTest {
	@Autowired
	AppointmentServiceImpl appointmentService;
	
	@MockBean
	IAppointmentRepository appointmentRepository;
	
	
	@Test
	@DisplayName("Test for adding appointment")
	public void addAppointmentTest() {
		Appointment appointment = new Appointment();
		SalonService salonService = new SalonService(100,"Spa",500,0,"20",appointment);
		Customer customer = new Customer(100,"12345","Customer",false);
		Address address = new Address(100,"NW004","Lane1","Area1","Bangalore","Karnataka",101245,customer);
		Card card = new Card(100,"Visa","123456",LocalDate.of(2026, 8, 25),356);
		Payment payment = new Payment("Card","Paid",card);
		Billing billing = new Billing(100,500,LocalDate.now(),customer,payment,appointment);
		
		appointment.setAppointmentId(100);
		appointment.setLocation("Whitefield");
		appointment.setVisitType("Salon");
		appointment.setPreferredService(salonService);
		appointment.setPreferredDate(LocalDate.of(2021, 4, 15));
		appointment.setPreferredTime(LocalTime.of(16, 0));
		appointment.setCustomer(customer);
		appointment.setAddress(address);
		appointment.setBilling(billing);
		
		Mockito.when(appointmentRepository.save(appointment)).thenReturn(appointment);
		assertEquals(appointment, appointmentService.addAppointment(appointment));
	}
	
	@Test
	public void getAllAppointmentTest() {
		Mockito.when(appointmentRepository.findAll())
		.thenReturn(java.util.stream.Stream.of(new Appointment(),new Appointment()).collect(Collectors.toList()));
		
		assertEquals(2, appointmentService.getAllAppointments().size());
		verify(appointmentRepository, times(1)).findAll();
		
	}
	
	@Test
	public void deleteAppointmentTest() {
		int appointmentId = 1;
		appointmentService.removeAppointment(appointmentId);
		
		verify(appointmentRepository, times(1)).deleteById((long) appointmentId);
	}
	
	
	@Test
	public void getAppointmentById() {
		Optional<Appointment> appointment = appointmentRepository.findById(1L);
		if(appointment.isPresent()) {
			assertEquals(appointment.get().getAppointmentId(), 1L);
		}
	}
	
	@Test
	public void updateAppointmentTest() {
		Optional<Appointment> appointment = appointmentRepository.findById(1L);
		if(appointment.isPresent()) {
			appointment.get().setVisitType("Home");
			appointmentRepository.save(appointment.get());
			
		}
		Optional<Appointment> updatedAppointment = appointmentRepository.findById(1L);
		if(updatedAppointment.isPresent()) {
			assertThat(updatedAppointment.get().getVisitType().equals("Home"));
		}
	}
	
	
	
	

}
