package com.cg.trg.boot.salon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.trg.boot.salon.bean.Appointment;
import com.cg.trg.boot.salon.dao.IAppointmentRepository;

@Service
public class AppointmentServiceImpl implements IAppointmentService {
	
	@Autowired
	IAppointmentRepository repository;
	@Override
	public Appointment addAppointment(Appointment appointment) {
		repository.save(appointment);
		return appointment;
	}

	@Override
	public Appointment removeAppointment(long id) {
		Optional<Appointment> appointmentToBeDeleted = repository.findById(id);
		repository.deleteById(id);
		
		if(appointmentToBeDeleted.isPresent()) {
			return appointmentToBeDeleted.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Appointment updateAppointment(long id, Appointment appointment) {
		if(repository.existsById(id)) {
			Appointment appointmentToBeUpdated = repository.findById(id).get();
			repository.save(appointment);
			return appointmentToBeUpdated;
		}
		return null;
	}

	@Override
	public Appointment getAppointment(long id) {
		Optional<Appointment> appointment = repository.findById(id);
		if(appointment.isPresent()) {
			return appointment.get();
		}
		else {
			return null;
		}
	}

	@Override
	public List<Appointment> getAllAppointments() {
		List<Appointment> appointments = repository.findAll();
		return appointments;
	}

	@Override
	public List<Appointment> getOpenAppointments() {
		List<Appointment> appointments = repository.findAll();
		return appointments;
		
	}

}