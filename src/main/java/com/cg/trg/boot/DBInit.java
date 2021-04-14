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
import com.cg.trg.boot.salon.dao.IAddressRepository;
import com.cg.trg.boot.salon.dao.IBillingRepository;
import com.cg.trg.boot.salon.dao.ICustomerRepository;

import ch.qos.logback.classic.Logger;


@Component
public class DBInit implements CommandLineRunner{
	@Autowired
	ICustomerRepository customerRepository;
	
	
	@Autowired
	IBillingRepository bill;
	@Autowired
	IAddressRepository address;
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
		
		
		logger.info("Data  Entry process initiated for Bill table");
		bill.save(new Billing(500.00,LocalDate.of(1999, 4, 8),null,null,null));
		bill.save(new Billing(100.00,LocalDate.of(1999, 4, 7),null,null,null));
		bill.save(new Billing(700.00,LocalDate.of(1999, 4, 6),null,null,null));
		bill.save(new Billing(300.00,LocalDate.of(1999, 4, 5),null,null,null));
		bill.save(new Billing(250.00,LocalDate.of(1999, 4, 4),null,null,null));
		bill.save(new Billing(500.00,LocalDate.of(1999, 4, 3),null,null,null));

		logger.info("6 rows inserted in bill table");
		
		logger.info("Data  Entry process initiated for Address table");
		address.save(new Address("House No: 4","near Hanuman temple","Bhubaneswar","Khorda","Odisha",755001,null,null));
		address.save(new Address("plat no:34","near bus stand","sankhachila","jajpur","odisha",755015,null,null));
		address.save(new Address("room no:4","Arakere signal","BG road","Bangalore","Kanataka",560076,null,null));
		address.save(new Address("plot no:89","lay out office","arakere","bengalurur","Kanataka",560076,null,null));
		address.save(new Address("348","near temple","sankhachila","jajpur","odisha",755015,null,null));
		address.save(new Address("907","laxminagar","sankhachila","jajpur","odisha",755015,null,null));
		logger.info("6 rows inserted in bill table");
		

		

	}

}
