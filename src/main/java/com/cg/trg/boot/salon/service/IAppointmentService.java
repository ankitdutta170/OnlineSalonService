package com.cg.trg.boot.salon.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.trg.boot.salon.bean.Appointment;


public interface IAppointmentService {

	public Appointment addAppointment(Appointment appointment);
	public Appointment removeAppointment(long id); 
	public Appointment updateAppointment(long id,Appointment appointment);
	public Appointment getAppointment(long id);
	public List<Appointment> getAllAppointments(); 
	public List<Appointment> getAppointmentByDate(LocalDate date);
}
