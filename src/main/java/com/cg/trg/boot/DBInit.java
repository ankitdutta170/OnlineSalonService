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
import com.cg.trg.boot.salon.bean.Customer;
import com.cg.trg.boot.salon.dao.ICustomerRepository;

import ch.qos.logback.classic.Logger;


@Component
public class DBInit implements CommandLineRunner{
	@Autowired
	ICustomerRepository customerRepository;
		
	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(DBInit.class);
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("Data  Entry process initiated for Customer table");
		customerRepository.save(new Customer("Ankit","abc@gmail.com","7903083839",LocalDate.of(1999, 4, 8),new ArrayList<Billing>(),new HashSet<Address>(),new ArrayList<Appointment>()));
		customerRepository.save(new Customer("Sai","abcd@gmail.com","7903083838",LocalDate.of(1999, 4, 7),new ArrayList<Billing>(),new HashSet<Address>(),new ArrayList<Appointment>()));
		customerRepository.save(new Customer("Om","abcde@gmail.com","7903083837",LocalDate.of(1999, 4, 6),new ArrayList<Billing>(),new HashSet<Address>(),new ArrayList<Appointment>()));
		customerRepository.save(new Customer("Sulabh","abcdef@gmail.com","7903083836",LocalDate.of(1999, 4, 5),new ArrayList<Billing>(),new HashSet<Address>(),new ArrayList<Appointment>()));
		customerRepository.save(new Customer("Siddharth","abcdefg@gmail.com","7903083835",LocalDate.of(1999, 4, 4),new ArrayList<Billing>(),new HashSet<Address>(),new ArrayList<Appointment>()));
		customerRepository.save(new Customer("Kurshed","abcdefgh@gmail.com","7903083834",LocalDate.of(1999, 4, 3),new ArrayList<Billing>(),new HashSet<Address>(),new ArrayList<Appointment>()));

		logger.info("6 rows inserted in customer table");

		

	}

}
