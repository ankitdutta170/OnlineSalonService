package com.cg.trg.boot.salon.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.cg.trg.boot.salon.service.AppointmentServiceImpl;
@CrossOrigin
@RestController
@RequestMapping("appointments")
public class AppointmentController {
	
	@Autowired
	AppointmentServiceImpl service;
	
	@PostMapping
	public ResponseEntity<String> saveAppointment(@RequestBody Appointment appointment,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		Appointment saveAppointment = service.addAppointment(appointment);
		if(saveAppointment != null) {
			return new ResponseEntity<String>("Appointment saved successfully", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Appointment failed to save", HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("{aid}")
	public ResponseEntity<String> removeAppointment(@PathVariable("aid") long id,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		Appointment deleteAppointment = service.removeAppointment(id);
		if(deleteAppointment != null) {
			return new ResponseEntity<String>("Appointment successfully deleted", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Appoitment failed to delete", HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateAppointment(@PathVariable("id")long id, @RequestBody Appointment appointment,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		Appointment updatedAppointment = service.updateAppointment(id, appointment);
		if(updatedAppointment != null) {
			return new ResponseEntity<String>("Appointment successfully updated", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Appointment failed to delete", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("{aid}")
	public ResponseEntity<?> getAppointment(@PathVariable("aid")long id,HttpServletRequest request){
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		
		Appointment appointment = service.getAppointment(id);
		if(appointment == null) {
			throw new AppointmentNotFoundException("Request", "Appointment with appointment id:"+id+"not found");
		}
		return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<Appointment>> getAllAppointments(HttpServletRequest request){
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		
		
		List<Appointment> appointments = service.getAllAppointments();
		if(appointments.size() == 0) {
			throw new EmptyDataException("No Appointments saved in database");
		}
		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
		
	}
	@GetMapping("{year}/{month}/{day}")
	public ResponseEntity<List<Appointment>> getAppointmentByDate(@PathVariable("year")int year, @PathVariable("month")int month,@PathVariable("day")int day){
		
		List<Appointment> appointments = service.getAppointmentByDate(LocalDate.of(year, month, day));
		if(appointments.size() == 0) {
			throw new EmptyDataException("No Appointments saved in database");
		}
		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
	}
	
	
}
