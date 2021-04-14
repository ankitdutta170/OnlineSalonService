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

import com.cg.trg.boot.salon.dao.IAddressRepository;
import com.cg.trg.boot.salon.dao.IBillingRepository;
import com.cg.trg.boot.salon.bean.SalonService;
import com.cg.trg.boot.salon.bean.User;
import com.cg.trg.boot.salon.dao.ICustomerRepository;
import com.cg.trg.boot.salon.dao.ISalonRepository;
import com.cg.trg.boot.salon.dao.IUserRepository;
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
public class DBInit implements CommandLineRunner {
	@Autowired
	ICustomerRepository customerRepository;

	@Autowired
	IBillingRepository bill;
	@Autowired
	IAddressRepository address;
	@Autowired
	ISalonRepository salonRepository;

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

	@Autowired
	IUserRepository userRepository;

	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(DBInit.class);

	@Override
	public void run(String... args) throws Exception {

		logger.info("Data  Entry process initiated for Bill table");
		// bill.save(new Billing(500.00,LocalDate.of(1999, 4, 8),null,null,null));
		// bill.save(new Billing(100.00,LocalDate.of(1999, 4, 7),null,null,null));
		// bill.save(new Billing(700.00,LocalDate.of(1999, 4, 6),null,null,null));
		// bill.save(new Billing(300.00,LocalDate.of(1999, 4, 5),null,null,null));
		// bill.save(new Billing(250.00,LocalDate.of(1999, 4, 4),null,null,null));
		// bill.save(new Billing(500.00,LocalDate.of(1999, 4, 3),null,null,null));

		logger.info("6 rows inserted in bill table");

		logger.info("Data  Entry process initiated for Address table");
		// address.save(new Address("House No: 4","near Hanuman
		// temple","Bhubaneswar","Khorda","Odisha",755001,null,null));
		// address.save(new Address("plat no:34","near bus
		// stand","sankhachila","jajpur","odisha",755015,null,null));
		// address.save(new Address("room no:4","Arakere signal","BG
		// road","Bangalore","Kanataka",560076,null,null));
		// address.save(new Address("plot no:89","lay out
		// office","arakere","bengalurur","Kanataka",560076,null,null));
		// address.save(new Address("348","near
		// temple","sankhachila","jajpur","odisha",755015,null,null));
		// address.save(new
		// Address("907","laxminagar","sankhachila","jajpur","odisha",755015,null,null));
		logger.info("6 rows inserted in address table");

		logger.info("Data  Entry process initiated for Card table");
		cardRepository.save(new Card("Visa", "123456789", LocalDate.of(2025, 5, 20), 420, null));
		cardRepository.save(new Card("MasterCard", "1601654314", LocalDate.of(2027, 7, 21), 425, null));
		cardRepository.save(new Card("RuPay", "434524532453", LocalDate.of(2024, 6, 20), 424, null));
		cardRepository.save(new Card("Visa", "43434873", LocalDate.of(2023, 4, 20), 423, null));
		cardRepository.save(new Card("MasterCard", "12453439", LocalDate.of(2021, 12, 14), 422, null));
		cardRepository.save(new Card("RuPday", "345345345", LocalDate.of(2022, 11, 2), 421, null));
		logger.info("6 rows inserted in card table");

		logger.info("Data  Entry process initiated for Payment table");
		paymentRepository.save(new Payment("card", "Successfull", null, null));
		paymentRepository.save(new Payment("cash", "pending", null, null));
		paymentRepository.save(new Payment("card", "pending", null, null));
		paymentRepository.save(new Payment("cash", "Successfull", null, null));
		paymentRepository.save(new Payment("card", "Successfull", null, null));
		paymentRepository.save(new Payment("cash", "pending", null, null));
		logger.info("6 rows inserted in payment table");

		logger.info("Inserting data for Appointment");
		Customer customer = customerService.getCustomer(1);
		appointmentRepository.save(new Appointment("Whitefield", "Salon", null, LocalDate.of(2021, 4, 18),
				LocalTime.of(15, 0), customer, null, null));

		logger.info("Data  Entry process initiated for Salon_Service table");
		salonRepository.save(new SalonService("Hair", 200.0, 0, "30 Minutes", null));
		salonRepository.save(new SalonService("Facial", 250.0, 0, "25 Minutes", null));
		salonRepository.save(new SalonService("Hair Spa", 300.0, 0, "45 Minutes", null));
		salonRepository.save(new SalonService("Trimming", 100.0, 0, "20 Minutes", null));
		salonRepository.save(new SalonService("Groom Makeup", 1500.0, 0, "1.5 Hours", null));
		salonRepository.save(new SalonService("Bride Makeup", 2000.0, 0, "2 Hours", null));

		logger.info("6 rows inserted in salon_service table");

		logger.info("Inserting data for userId and Password");
		userRepository.save(new User(1, "sid", "password", false));
		userRepository.save(new User(2, "sai", "pass", false));
		userRepository.save(new User(3, "ankit", "word", false));
		userRepository.save(new User(4, "om", "omcapg", false));
		userRepository.save(new User(5, "alam", "k.alam", false));
		userRepository.save(new User(6, "sulabh", "s@password", false));

		logger.info("6 rows inserted in user_service table");
	}
}
