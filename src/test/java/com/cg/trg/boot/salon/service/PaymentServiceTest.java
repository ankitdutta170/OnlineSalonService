package com.cg.trg.boot.salon.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import com.cg.trg.boot.salon.dao.IAppointmentRepository;
import com.cg.trg.boot.salon.dao.IPaymentRepository;

@SpringBootTest
class PaymentServiceTest {
	@Autowired
	PaymentServiceImpl paymentService;

	@MockBean
	IPaymentRepository paymentRepository;
    private static final Long paymentId = 1110L;


//	@Test
//	@DisplayName("Test for Payment Retrival")
//	public Optional<Payment> getPaymentDetails() {
//		
//		Payment payment=new Payment();
//		payment.setPaymentId(1110);
//		payment.setType("card");
//		when(paymentRepository.findById(paymentId)).thenReturn(getPaymentDetails());
//		assertEquals(payment, paymentService.addPayment(payment));
//		
//	}
    @Test
	public void getAllPaymentDetails() {
		Mockito.when(paymentRepository.findAll())
		.thenReturn(Stream.of(new Payment(),new Payment()).collect(Collectors.toList()));
		
		assertEquals(2, paymentService.getAllPaymentDetails().size());
		
		
	}
}
