package com.cg.trg.boot.salon.handler;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

<<<<<<< Updated upstream
import com.cg.trg.boot.salon.exceptions.DuplicateAppointmentException;
=======
import com.cg.trg.boot.salon.exceptions.AppointmentNotFoundException;
import com.cg.trg.boot.salon.exceptions.DuplicateAppointmentException;
import com.cg.trg.boot.salon.exceptions.EmptyDataException;
>>>>>>> Stashed changes

@ControllerAdvice
public class ApplicationErrorHandler {
	@ExceptionHandler(DuplicateAppointmentException.class)
<<<<<<< Updated upstream
	public ResponseEntity<?> handleDuplicateData(DuplicateAppointmentException ex) {
		Map<String, Object> errorBody = new LinkedHashMap()<>();
=======
	public ResponseEntity<?> handleDuplicateAppointmentData(DuplicateAppointmentException ex) {
		Map<String, Object> errorBody = new LinkedHashMap<>();
>>>>>>> Stashed changes
		errorBody.put("error", "creation failed");
		errorBody.put("timestamp", LocalDate.now());
		errorBody.put("details", ex.getMessage());
		
		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
	}
<<<<<<< Updated upstream
=======
	
	@ExceptionHandler(EmptyDataException.class)
	public ResponseEntity<?> handleEmptyData(EmptyDataException ex) {
		Map<String, Object> errorBody = new LinkedHashMap<>();
		errorBody.put("error", "creation failed");
		errorBody.put("timestamp", LocalDate.now());
		errorBody.put("details", ex.getMessage());
		
		return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AppointmentNotFoundException.class)
	public ResponseEntity<?> handleMissingAppointment(AppointmentNotFoundException ex) {
		Map<String, Object> errorBody = new LinkedHashMap<>();
		errorBody.put("error", "creation failed");
		errorBody.put("timestamp", LocalDate.now());
		errorBody.put("details", ex.getMessage());
		
		return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
	}
	
	
>>>>>>> Stashed changes

}
