package com.cg.trg.boot;

import java.time.LocalDate;

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
		customerRepository.save(new Customer("Ankit","abc@gmail.com","7903083839",LocalDate.of(1999, 4, 8),new ArrayList<Billing>(),new HashSet<Address>(),new ArrayList<Appointment>(),null));
//		customerRepository.save(new Customer("Sai","abcd@gmail.com","7903083838",LocalDate.of(1999, 4, 7),new ArrayList<Billing>(),new HashSet<Address>(),new ArrayList<Appointment>()));
//		customerRepository.save(new Customer("Om","abcde@gmail.com","7903083837",LocalDate.of(1999, 4, 6),new ArrayList<Billing>(),new HashSet<Address>(),new ArrayList<Appointment>()));
//		customerRepository.save(new Customer("Sulabh","abcdef@gmail.com","7903083836",LocalDate.of(1999, 4, 5),new ArrayList<Billing>(),new HashSet<Address>(),new ArrayList<Appointment>()));
//		customerRepository.save(new Customer("Siddharth","abcdefg@gmail.com","7903083835",LocalDate.of(1999, 4, 4),new ArrayList<Billing>(),new HashSet<Address>(),new ArrayList<Appointment>()));
//		customerRepository.save(new Customer("Kurshed","abcdefgh@gmail.com","7903083834",LocalDate.of(1999, 4, 3),new ArrayList<Billing>(),new HashSet<Address>(),new ArrayList<Appointment>()));

		logger.info("6 rows inserted in customer table");
		
//		
//		logger.info("Data  Entry process initiated for Payment table");
//		Card card=cardimpl.getCardDetails(1);
//		paymentRepository.save(new Payment("card","Successfull",card,new Billing()));
//		paymentRepository.save(new Payment("card","pending",new Card(),new Billing()));
//		paymentRepository.save(new Payment("Cash","Successfull",null,new Billing()));
//		paymentRepository.save(new Payment("Cash","Successfull",null,new Billing()));
//		paymentRepository.save(new Payment("card","pending",new Card(),new Billing()));
//		paymentRepository.save(new Payment("card","Successfull",new Card(),new Billing()));
//		
		logger.info("6 rows inserted in payment table");
		Payment payment=paymentService.getPaymentDetails(1);
		Customer customer=customerService.getCustomer(1);
		logger.info("Data  Entry process initiated for Card table");
		cardRepository.save(new Card("Visa","123456789",LocalDate.of(2025, 5, 20),420,payment,customer));
		logger.info("Data  Entry process initiated for Payment table");
		Card card=cardimpl.getCardDetails(3);
		paymentRepository.save(new Payment("card","Successfull",card,new Billing()));
		
//		cardRepository.save(new Card("MasterCard","6468486465",LocalDate.of(2023, 10, 21),425,payment));
//		cardRepository.save(new Card("Rupday","5165156262",LocalDate.of(2024, 9, 15),424,payment));
//		cardRepository.save(new Card("Visa","48945615151",LocalDate.of(2022, 8, 10),423,payment));
//		cardRepository.save(new Card("MasterCard","6184166486",LocalDate.of(2027, 1, 20),421,payment));
//		cardRepository.save(new Card("Rupday","5485130305",LocalDate.of(2025, 6, 22),422,new Payment()));
		logger.info("6 rows inserted in card table");
		

		

	}

}
