package com.cg.trg.boot.salon.handler;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.trg.boot.salon.exceptions.BillNotFoundException;
import com.cg.trg.boot.salon.exceptions.EmptyDataException;

@ControllerAdvice
public class BillErrorHandler {
	@ExceptionHandler(EmptyDataException.class)
	public ResponseEntity<?> handleEmptyData(EmptyDataException ex) {
		Map<String, Object> errorBody = new LinkedHashMap<>();
		errorBody.put("error", "creation failed");
		errorBody.put("timestamp", LocalDate.now());
		errorBody.put("errorMessage", ex.getMessage());
		
		return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(BillNotFoundException.class)
	public ResponseEntity<?> handleMissingAppointment(BillNotFoundException ex) {
		Map<String, Object> errorBody = new LinkedHashMap<>();
		errorBody.put("error", "creation failed");
		errorBody.put("timestamp", LocalDate.now());
		errorBody.put("errorMessage", ex.getMessage());
		
		return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
	}

}
