package com.cg.trg.boot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;

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
import com.cg.trg.boot.salon.dao.ICustomerRepository;
import com.cg.trg.boot.salon.dao.ISalonRepository;
import com.cg.trg.boot.salon.dao.IUserRepository;
import com.cg.trg.boot.salon.dao.IAppointmentRepository;
import com.cg.trg.boot.salon.service.ICustomerServiceImpl;
import com.cg.trg.boot.salon.service.ISalonServiceImpl;
import com.cg.trg.boot.salon.bean.Payment;
import com.cg.trg.boot.salon.dao.ICardRepository;
import com.cg.trg.boot.salon.dao.IPaymentRepository;
import com.cg.trg.boot.salon.service.AddressServiceImpl;
import com.cg.trg.boot.salon.service.AppointmentServiceImpl;
import com.cg.trg.boot.salon.service.BillingServiceImpl;
import com.cg.trg.boot.salon.service.CardImpl;
import com.cg.trg.boot.salon.service.PaymentServiceImpl;

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
	IBillingRepository billingRepository;
	@Autowired
	PaymentServiceImpl paymentService;
	@Autowired
	CardImpl cardimpl;
	@Autowired
	ICustomerServiceImpl customerService;
	@Autowired
	BillingServiceImpl billingService;
	@Autowired
	ISalonServiceImpl salonService;
	@Autowired
	AppointmentServiceImpl appointmentService;
	@Autowired
	IUserRepository userRepository;
	@Autowired
	AddressServiceImpl addressService;

	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(DBInit.class);

	@Override
	public void run(String... args) throws Exception {

		// ############################################################################################

		logger.info("Data  Entry process initiated for Salon_Service table");

		salonRepository.save(new SalonService("Hair", 200.0, 0, "30 Minutes"));
		salonRepository.save(new SalonService("Facial", 250.0, 0, "25 Minutes"));
		salonRepository.save(new SalonService("Hair Spa", 300.0, 0, "45 Minutes"));
		salonRepository.save(new SalonService("Trimming", 100.0, 0, "20 Minutes"));
		salonRepository.save(new SalonService("Groom Makeup", 1500.0, 0, "1.5 Hours"));
		salonRepository.save(new SalonService("Bride Makeup", 2000.0, 0, "2 Hours"));

		logger.info("6 rows inserted in Salon_Service table");

		// #############################################################################################

		logger.info("Data  Entry process initiated for Card table");

		cardRepository.save(new Card("Visa", "123456789", LocalDate.of(2025, 5, 20), 420));
		cardRepository.save(new Card("MasterCard", "1601654314", LocalDate.of(2027, 7, 21), 425));
		cardRepository.save(new Card("RuPay", "434524532453", LocalDate.of(2018, 6, 20), 424));
		cardRepository.save(new Card("Visa", "43434873", LocalDate.of(2023, 4, 20), 423));
		cardRepository.save(new Card("MasterCard", "12453439", LocalDate.of(2021, 12, 14), 422));
		cardRepository.save(new Card("RuPay", "345345345", LocalDate.of(2022, 11, 2), 421));

		logger.info("6 rows inserted in Card table");

		// ############################################################################################

		logger.info("Data  Entry process initiated for Payment table");

		Card card1 = cardimpl.getCardDetails(1);
		Card card2 = cardimpl.getCardDetails(20);
		Card card3 = cardimpl.getCardDetails(21);
		Card card4 = cardimpl.getCardDetails(22);
		Card card5 = cardimpl.getCardDetails(23);
		Card card6 = cardimpl.getCardDetails(24);

		paymentRepository.save(new Payment("card", "success", card1));
		paymentRepository.save(new Payment("cash", "pending", card2));
		paymentRepository.save(new Payment("card", "pending", card3));
		paymentRepository.save(new Payment("cash", "success", card4));
		paymentRepository.save(new Payment("card", "success", card5));
		paymentRepository.save(new Payment("cash", "pending", card6));

		logger.info("6 rows inserted in Payment table");

		// ############################################################################################

		logger.info("Data  Entry process initiated for Customer table");

		customerRepository.save(new Customer("ankitdutta170","ankitdutta","customer","Ankit", "abc@gmail.com", "7903083839", LocalDate.of(1999, 4, 8),
				new ArrayList<Billing>(), new HashSet<Address>(), new ArrayList<Appointment>()));
		customerRepository.save(new Customer("saipraveen","saipraveen","customer","Sai", "abcd@gmail.com", "7903083838", LocalDate.of(1999, 4, 7),
				new ArrayList<Billing>(), new HashSet<Address>(), new ArrayList<Appointment>()));
		customerRepository.save(new Customer("omsahoo","omsahoo","customer","Om", "abcde@gmail.com", "7903083837", LocalDate.of(1999, 4, 6),
				new ArrayList<Billing>(), new HashSet<Address>(), new ArrayList<Appointment>()));
		customerRepository.save(new Customer("sulabhmajumder","sulabhmajumder","customer","Sulabh", "abcdef@gmail.com", "7903083836", LocalDate.of(1999, 4, 5),
				new ArrayList<Billing>(), new HashSet<Address>(), new ArrayList<Appointment>()));
		customerRepository.save(new Customer("siddharth","siddharth","customer","Siddharth", "abcdefg@gmail.com", "7903083835", LocalDate.of(1999, 4, 4),
				new ArrayList<Billing>(), new HashSet<Address>(), new ArrayList<Appointment>()));
		customerRepository.save(new Customer("kurshed","kurshed","customer","Kurshed", "abcdefgh@gmail.com", "7903083834", LocalDate.of(1999, 4, 3),
				new ArrayList<Billing>(), new HashSet<Address>(), new ArrayList<Appointment>()));

		logger.info("6 rows inserted in Customer table");

		// ############################################################################################

		logger.info("Data  Entry process initiated for Address table");

		Customer customer1 = customerService.getCustomer(19);
		Customer customer2 = customerService.getCustomer(2);
		Customer customer3 = customerService.getCustomer(3);
		Customer customer4 = customerService.getCustomer(4);
		Customer customer5 = customerService.getCustomer(5);
		Customer customer6 = customerService.getCustomer(6);

		address.save(new Address("House No: 4", "near Hanuman temple", "Bhubaneswar", "Khorda", "Odisha", 755001,
				customer1));
		address.save(new Address("plat no:34", "near bus stand", "sankhachila", "jajpur", "odisha", 755015, customer2));
		address.save(new Address("room no:4", "Arakere signal", "BG road", "Bangalore", "Kanataka", 560076, customer3));
		address.save(
				new Address("plot no:89", "lay out office", "arakere", "bengalurur", "Kanataka", 560076, customer4));
		address.save(new Address("348", "near temple", "sankhachila", "jajpur", "odisha", 755015, customer5));
		address.save(new Address("907", "laxminagar", "sankhachila", "jajpur", "odisha", 755015, customer6));

		logger.info("6 rows inserted in Address table");

		// ############################################################################################

		logger.info("Inserting data for Appointment");

		SalonService salonService1 = salonService.getService(1);
		SalonService salonService2 = salonService.getService(2);
		SalonService salonService3 = salonService.getService(3);
		SalonService salonService4 = salonService.getService(4);
		SalonService salonService5 = salonService.getService(4);
		SalonService salonService6 = salonService.getService(5);

		Address address1 = addressService.getAddressDetails(25);
		Address address2 = addressService.getAddressDetails(26);
		Address address3 = addressService.getAddressDetails(27);
		Address address4 = addressService.getAddressDetails(28);
		Address address5 = addressService.getAddressDetails(29);
		Address address6 = addressService.getAddressDetails(30);

		appointmentRepository.save(new Appointment("Bangalore", "Salon", salonService1, LocalDate.of(2021, 4, 18),
				LocalTime.of(14, 0), customer1,  null));
		appointmentRepository.save(new Appointment("Kolkata", "Home", salonService2, LocalDate.of(2022, 4, 19),
				LocalTime.of(15, 0), customer2,  null));
		appointmentRepository.save(new Appointment("Mumbai", "Salon", salonService3, LocalDate.of(2021, 5, 17),
				LocalTime.of(16, 0), customer3,  null));
		appointmentRepository.save(new Appointment("Chennai", "Home", salonService4, LocalDate.of(2021, 7, 20),
				LocalTime.of(17, 0), customer4,  null));
		appointmentRepository.save(new Appointment("Pune", "Salon", salonService5, LocalDate.of(2021, 8, 15),
				LocalTime.of(18, 0), customer5,  null));
		appointmentRepository.save(new Appointment("Delhi", "Home", salonService6, LocalDate.of(2021, 7, 10),
				LocalTime.of(19, 0), customer6,  null));

		logger.info("6 rows inserted in Appoinment table");

		// ############################################################################################

		logger.info("Data  Entry process initiated for Bill table");

		Payment payment1 = paymentService.getPaymentDetails(13);
		Payment payment2 = paymentService.getPaymentDetails(26);
		Payment payment3 = paymentService.getPaymentDetails(27);
		Payment payment4 = paymentService.getPaymentDetails(28);
		Payment payment5 = paymentService.getPaymentDetails(29);
		Payment payment6 = paymentService.getPaymentDetails(30);
		
		Appointment appointment1=appointmentService.getAppointment(31);
		Appointment appointment2=appointmentService.getAppointment(0);
		Appointment appointment3=appointmentService.getAppointment(0);
		Appointment appointment4=appointmentService.getAppointment(0);
		Appointment appointment5=appointmentService.getAppointment(0);
		Appointment appointment6=appointmentService.getAppointment(0);

		bill.save(new Billing(500.00, LocalDate.of(1999, 4, 8), customer1, payment1, appointment1));
		bill.save(new Billing(100.00, LocalDate.of(1999, 4, 7), customer2, payment2, appointment2));
		bill.save(new Billing(700.00, LocalDate.of(1999, 4, 6), customer3, payment3, appointment3));
		bill.save(new Billing(300.00, LocalDate.of(1999, 4, 5), customer4, payment4, appointment4));
		bill.save(new Billing(250.00, LocalDate.of(1999, 4, 4), customer5, payment5, appointment5));
		bill.save(new Billing(500.00, LocalDate.of(1999, 4, 3), customer6, payment6, appointment6));

		logger.info("6 rows inserted in Bill table");

		// ############################################################################################

	}
}
