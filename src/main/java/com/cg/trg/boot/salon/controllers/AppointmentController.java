package com.cg.trg.boot.salon.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.trg.boot.salon.bean.Appointment;
import com.cg.trg.boot.salon.exceptions.AppointmentNotFoundException;
import com.cg.trg.boot.salon.exceptions.EmptyDataException;
import com.cg.trg.boot.salon.service.AppointmentServiceImpl;

@RestController
@RequestMapping("appointments")
public class AppointmentController {
	
	@Autowired
	AppointmentServiceImpl service;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveAppointment(Appointment appointment) {
		Appointment saveAppointment = service.addAppointment(appointment);
		if(saveAppointment != null) {
			return "Appointment successfully made";
		}
		else
			return "Failed to give appointment";
	}
	@DeleteMapping("{aid}")
	public String removeAppointment(@PathVariable("aid") long id) {
		Appointment deleteAppointment = service.removeAppointment(id);
		if(deleteAppointment != null) {
			return "Appointment successfully deleted";
		}
		else
			return "Appointment failed to delete";
	}
	
	@PutMapping
	public String updateAppointment(long id, Appointment appointment) {
		Appointment updatedAppointment = service.updateAppointment(id, appointment);
		if(updatedAppointment != null) {
			return "Appointment succesfully updated";
		}
		else
			return "Appointment failed to update";
	}
	
	@GetMapping("{aid}")
	public ResponseEntity<?> getAppointment(@PathVariable("aid")long id){
		Appointment appointment = service.getAppointment(id);
		if(appointment == null) {
			throw new AppointmentNotFoundException("Request", "Appointment with appointment id:"+id+"not found");
		}
		return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
	}
	@GetMapping
	public List<Appointment> getAllAppointments(){
		List<Appointment> appointments = service.getAllAppointments();
		if(appointments.size() == 0) {
			throw new EmptyDataException("No Appointments saved in database");
		}
		return appointments;
		
	}
	
	
}
