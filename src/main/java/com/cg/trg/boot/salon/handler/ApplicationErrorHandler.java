package com.cg.trg.boot.salon.handler;

import java.time.LocalDate;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.cg.trg.boot.salon.exceptions.DuplicateAppointmentException;

import com.cg.trg.boot.salon.exceptions.AppointmentNotFoundException;
import com.cg.trg.boot.salon.exceptions.EmptyDataException;
import com.cg.trg.boot.salon.exceptions.InvalidUserException;


@ControllerAdvice
public class ApplicationErrorHandler {
	

	public ResponseEntity<?> handleDuplicateAppointmentData(DuplicateAppointmentException ex) {
		Map<String, Object> errorBody = new LinkedHashMap<>();

		errorBody.put("error", "Duplicate data");
		errorBody.put("timestamp", LocalDate.now());
		errorBody.put("errorMessage", ex.getMessage());
		
		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(EmptyDataException.class)
	public ResponseEntity<?> handleEmptyData(EmptyDataException ex) {
		Map<String, Object> errorBody = new LinkedHashMap<>();
		errorBody.put("error", "No Data Found");
		errorBody.put("timestamp", LocalDate.now());
		errorBody.put("errorMessage", ex.getMessage());
		
		return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<?> handleInvalidUser(InvalidUserException ex) {
		Map<String, Object> errorBody = new LinkedHashMap<>();
	
		errorBody.put("errorMessage", ex.getMessage());
		
		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AppointmentNotFoundException.class)
	public ResponseEntity<?> handleMissingAppointment(AppointmentNotFoundException ex) {
		Map<String, Object> errorBody = new LinkedHashMap<>();
		errorBody.put("error", "Appointment Not Found");
		errorBody.put("timestamp", LocalDate.now());
		errorBody.put("errorMessage", ex.getMessage());
		
		return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
	}
	
	


}
