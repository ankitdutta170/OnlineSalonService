package com.cg.trg.boot.salon.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
	private long appointmentId;
	private String location;
	private String visitType;
	private String preferredService;
	private LocalDate preferredDate;
	private LocalTime preferredTime;
	private Customer customer;
	private Payment payment;

}
