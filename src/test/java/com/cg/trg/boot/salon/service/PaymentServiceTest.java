package com.cg.trg.boot.salon.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.cg.trg.boot.salon.bean.Card;
import com.cg.trg.boot.salon.bean.Payment;
import com.cg.trg.boot.salon.dao.IPaymentRepository;

@SpringBootTest
class PaymentServiceTest {
	@Autowired
	PaymentServiceImpl paymentService;

	@MockBean
	IPaymentRepository paymentRepository;
	
	Card card = new Card(100,"Visa","123456",LocalDate.of(2026, 8, 25),356);
	Payment payment = new Payment("Card","Paid",card);
	
	 @Test
	 @DisplayName("Test for adding Payment")
		public void addPaymentTest() {
	    	Mockito.when(paymentRepository.save(payment)).thenReturn(payment);
			assertEquals(payment, paymentService.addPayment(payment));
			}
	 
	 @Test
	 @DisplayName("Test for Deleteing Payment by Id")
 	public void deletePaymentTest() {
 		int appointmentId = 1;
 		paymentService.removePayment(appointmentId);
 		
 		Mockito.verify(paymentRepository, times(1)).deleteById((long) appointmentId);
 	}
	 @Test
	 @DisplayName("Test for Updating Payment")
 	public void updatePaymentTest() {
 		Optional<Payment> payment = paymentRepository.findById(1L);
 		if(payment.isPresent()) {
 			payment.get().setType("Cash");
 			Mockito.doReturn(paymentRepository.save(payment.get()));
 			
 		}
 		Optional<Payment> updatedpayment = paymentRepository.findById(1L);
		if(updatedpayment.isPresent()) {
			assertThat(updatedpayment.get().getType().equals("Home"));
		}
	 }
	 
	 @Test
	 @DisplayName("Test for retrive Payement Details By id")
		public void getPaymentDetailsTest() {
	    
	    	
			Mockito.when(paymentRepository.findById(100L))
			.thenReturn(java.util.Optional.ofNullable(payment));
			
		}

	 
    @Test
    @DisplayName("Test for retriving all Payement Details")
	public void getAllPaymentDetails() {
		Mockito.when(paymentRepository.findAll())
		.thenReturn(Stream.of(new Payment(),new Payment()).collect(Collectors.toList()));
		
		assertEquals(2, paymentService.getAllPaymentDetails().size());
		verify(paymentRepository, times(1)).findAll();
		
	}

 
    
}
