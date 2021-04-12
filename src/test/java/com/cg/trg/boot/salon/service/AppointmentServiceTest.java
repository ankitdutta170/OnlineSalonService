package com.cg.trg.boot.salon.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;

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

@SpringBootTest
class AppointmentServiceTest {
	@Autowired
	AppointmentServiceImpl appointmentService;
	
	@MockBean
	IAppointmentRepository appointmentRepository;
	
	
	@Test
	@DisplayName("Test for adding appointment")
	public void addAppointment() {
		Appointment appointment = new Appointment();
		SalonService salonService = new SalonService(100,"Spa",500,0,"20");
		Customer customer = new Customer(100,"12345","Customer",false);
		Address address = new Address("address100","NW004","Lane1","Area1","Bangalore","Karnataka",101245,customer);
		Card card = new Card(100,"Visa","123456",LocalDate.of(2026, 8, 25),356);
		Payment payment = new Payment(100,"Card","Paid",card);
		Billing billing = new Billing(100,500,LocalDate.now(),customer,payment);
		
		appointment.setAppointmentId(100);
		appointment.setLocation("Whitefield");
		appointment.setVisitType("Salon");
		appointment.setPreferredService(salonService);
		appointment.setPreferredDate(LocalDate.of(2021, 4, 15));
		appointment.setPreferredTime(LocalTime.of(16, 0));
		appointment.setCustomer(customer);
		appointment.setAddress(address);
		appointment.setBilling(billing);
		
		when(appointmentRepository.save(appointment)).thenReturn(appointment);
		assertEquals(appointment, appointmentService.addAppointment(appointment));
	}
	
	
	

}
