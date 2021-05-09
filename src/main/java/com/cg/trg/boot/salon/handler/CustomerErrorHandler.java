package com.cg.trg.boot.salon.handler;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.trg.boot.salon.exceptions.CustomerNotFoundException;
import com.cg.trg.boot.salon.exceptions.DuplicateCustomerException;
import com.cg.trg.boot.salon.exceptions.EmptyDataException;
@ControllerAdvice
public class CustomerErrorHandler {
	public ResponseEntity<?> handleDuplicateCustomerData(DuplicateCustomerException ex) {
		Map<String, Object> errorBody = new LinkedHashMap<>();

		errorBody.put("error", "creation failed");
		errorBody.put("timestamp", LocalDate.now());
		errorBody.put("errorMessage", ex.getMessage());
		
		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(EmptyDataException.class)
	public ResponseEntity<?> handleEmptyData(EmptyDataException ex) {
		Map<String, Object> errorBody = new LinkedHashMap<>();
		errorBody.put("error", "creation failed");
		errorBody.put("timestamp", LocalDate.now());
		errorBody.put("errorMessage", ex.getMessage());
		
		return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<?> handleMissingCustomer(CustomerNotFoundException ex) {
		Map<String, Object> errorBody = new LinkedHashMap<>();
		errorBody.put("error", "creation failed");
		errorBody.put("timestamp", LocalDate.now());
		errorBody.put("errorMessage", ex.getMessage());
		
		return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
		
	}
}
