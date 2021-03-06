package com.cg.trg.boot.salon.controllers;

import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.trg.boot.salon.bean.Appointment;
import com.cg.trg.boot.salon.exceptions.AppointmentNotFoundException;
import com.cg.trg.boot.salon.exceptions.EmptyDataException;
import com.cg.trg.boot.salon.exceptions.InvalidUserException;
import com.cg.trg.boot.salon.jwt.JwtTokenUtil;
import com.cg.trg.boot.salon.service.AppointmentServiceImpl;
import com.cg.trg.boot.salon.service.ValidateToken;

import io.jsonwebtoken.SignatureException;

@RestController
@RequestMapping("appointments")
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentController {
	
	@Autowired
	AppointmentServiceImpl service;
	
	@Autowired
	ValidateToken login;
	
	@PostMapping
	public ResponseEntity<String> saveAppointment(@RequestBody Appointment appointment,HttpServletRequest request) {
		login.validateToken(request,"customer");
		Appointment saveAppointment = service.addAppointment(appointment);
		if(saveAppointment != null) {
			return new ResponseEntity<String>("Appointment saved successfully", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Appointment failed to save", HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("{aid}")
	public ResponseEntity<String> removeAppointment(@PathVariable("aid") long id,HttpServletRequest request) {
		login.validateToken(request,"customer");
		Appointment deleteAppointment = service.removeAppointment(id);
		if(deleteAppointment != null) {
			return new ResponseEntity<String>("Appointment successfully deleted", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Appoitment failed to delete", HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateAppointment(@PathVariable("id")long id, @RequestBody Appointment appointment,HttpServletRequest request) {
		login.validateToken(request,"customer");
		Appointment updatedAppointment = service.updateAppointment(id, appointment);
		if(updatedAppointment != null) {
			return new ResponseEntity<String>("Appointment successfully updated", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Appointment failed to delete", HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping
	public String update( @RequestBody Appointment appointment,HttpServletRequest request) {
		login.validateToken(request,"customer");
		if (service.update(appointment))
			return "Appointment data successfully updated";
		else
			throw new AppointmentNotFoundException("Update", "Appointment with Id " + appointment.getAppointmentId() + " to update not found");
	}
	
	@GetMapping("{aid}")
	public Appointment getAppointment(@PathVariable("aid")long id,HttpServletRequest request){
		
		login.validateToken(request,"customer");
		Appointment appointment = service.getAppointment(id);
		if(appointment == null) {
			throw new AppointmentNotFoundException("Request", "Appointment with appointment id:"+id+"not found");
		}
		return appointment;
	}
	@GetMapping
	public List<Appointment> getAllAppointments(HttpServletRequest request){
		login.validateToken(request,"customer");
		List<Appointment> appointments = service.getAllAppointments();
		if(appointments.size() == 0) {
			throw new EmptyDataException("No Appointments saved in database");
		}
		return appointments;
		
	}
	@GetMapping("/date/{date}")
	public List<Appointment> getAllAppointmentsForCustomer(@PathVariable("date") String date,HttpServletRequest request){
		login.validateToken(request, "admin");
		LocalDate dateToFind = LocalDate.parse(date);
		List<Appointment> appointments = service.getAppointmentByDate(dateToFind);
		if(appointments.size() == 0) {
			throw new EmptyDataException("No Appointments saved in database for the date");
		}
		return appointments;
	}
	@GetMapping("{year}/{month}/{day}")
	public ResponseEntity<List<Appointment>> getAppointmentByDate(@PathVariable("year")int year, @PathVariable("month")int month,@PathVariable("day")int day,HttpServletRequest request){
		login.validateToken(request,"customer");
		List<Appointment> appointments = service.getAppointmentByDate(LocalDate.of(year, month, day));
		if(appointments.size() == 0) {
			throw new EmptyDataException("No Appointments saved in database");
		}
		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
	}
	
	
	
	
}
