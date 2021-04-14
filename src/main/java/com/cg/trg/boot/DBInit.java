package com.cg.trg.boot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cg.trg.boot.salon.bean.Address;
import com.cg.trg.boot.salon.bean.Appointment;
import com.cg.trg.boot.salon.bean.Billing;
import com.cg.trg.boot.salon.bean.Card;
import com.cg.trg.boot.salon.bean.Customer;

import com.cg.trg.boot.salon.dao.IAppointmentRepository;
import com.cg.trg.boot.salon.dao.ICustomerRepository;
import com.cg.trg.boot.salon.service.ICustomerServiceImpl;

import com.cg.trg.boot.salon.bean.Payment;
import com.cg.trg.boot.salon.dao.ICardRepository;
import com.cg.trg.boot.salon.dao.ICustomerRepository;
import com.cg.trg.boot.salon.dao.IPaymentRepository;
import com.cg.trg.boot.salon.service.CardImpl;
import com.cg.trg.boot.salon.service.ICustomerServiceImpl;
import com.cg.trg.boot.salon.service.PaymentServiceImpl;


import ch.qos.logback.classic.Logger;


@Component
public class DBInit implements CommandLineRunner{
	@Autowired
	ICustomerRepository customerRepository;

	
	@Autowired
	IAppointmentRepository appointmentRepository;
	
	@Autowired
	ICustomerServiceImpl customerServiceImpl;

	@Autowired
	IPaymentRepository paymentRepository;
	@Autowired
	ICardRepository cardRepository;
	@Autowired
	PaymentServiceImpl paymentService;
	@Autowired
	CardImpl cardimpl;
	@Autowired 
	ICustomerServiceImpl customerService;
	

	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(DBInit.class);
	
	@Override
	public void run(String... args) throws Exception {
		
		
		logger.info("Data  Entry process initiated for Customer table");

		

		
		
	
		logger.info("6 rows inserted in payment table");
		Payment payment=paymentService.getPaymentDetails(1);
		Customer customer=customerService.getCustomer(1);
		logger.info("Data  Entry process initiated for Card table");
		cardRepository.save(new Card("Visa","123456789",LocalDate.of(2025, 5, 20),420,payment,customer));
		logger.info("Data  Entry process initiated for Payment table");
		Card card=cardimpl.getCardDetails(3);
		paymentRepository.save(new Payment("card","Successfull",card,new Billing()));
		

		logger.info("6 rows inserted in card table");
		

		
		Customer customer = customerServiceImpl.getCustomer(1);
		logger.info("Inserting data for Appointment");
		appointmentRepository.save(new Appointment("Whitefield", "Salon", null, LocalDate.of(2021, 4, 18), LocalTime.of(15, 0), customer, null, null));

	}

}
