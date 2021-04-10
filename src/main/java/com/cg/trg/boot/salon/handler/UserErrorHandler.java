package com.cg.trg.boot.salon.handler;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.trg.boot.salon.exceptions.EmptyDataException;
import com.cg.trg.boot.salon.exceptions.NullPointException;
import com.cg.trg.boot.salon.exceptions.PasswordChangeException;
import com.cg.trg.boot.salon.exceptions.PasswordMisMatchException;
import com.cg.trg.boot.salon.exceptions.SignOutException;
import com.cg.trg.boot.salon.exceptions.UserIdNotFoundException;

public class UserErrorHandler {
	
	@ControllerAdvice
	public class ApplicationErrorHandler {
		

		
		@ExceptionHandler(NullPointException.class)
		public ResponseEntity<?> handleEmptyData(NullPointException ex) {
			Map<String, Object> errorBody = new LinkedHashMap<>();
			errorBody.put("error", "creation failed");
			errorBody.put("timestamp", LocalDate.now());
			errorBody.put("details", ex.getMessage());
			
			return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(SignOutException.class)
		public ResponseEntity<?> handleMissingAppointment(SignOutException ex) {
			Map<String, Object> errorBody = new LinkedHashMap<>();
			errorBody.put("error", "creation failed");
			errorBody.put("timestamp", LocalDate.now());
			errorBody.put("details", ex.getMessage());
			
			return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
		}
		

		@ExceptionHandler(UserIdNotFoundException.class)
		public ResponseEntity<?> handleMissingAppointment(UserIdNotFoundException ex) {
			Map<String, Object> errorBody = new LinkedHashMap<>();
			errorBody.put("error", "creation failed");
			errorBody.put("timestamp", LocalDate.now());
			errorBody.put("details", ex.getMessage());
			
			return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(PasswordMisMatchException.class)
		public ResponseEntity<?> handleMissingAppointment(PasswordMisMatchException ex) {
			Map<String, Object> errorBody = new LinkedHashMap<>();
			errorBody.put("error", "creation failed");
			errorBody.put("timestamp", LocalDate.now());
			errorBody.put("details", ex.getMessage());
			
			return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(PasswordChangeException.class)
		public ResponseEntity<?> handleMissingAppointment(PasswordChangeException ex) {
			Map<String, Object> errorBody = new LinkedHashMap<>();
			errorBody.put("error", "creation failed");
			errorBody.put("timestamp", LocalDate.now());
			errorBody.put("details", ex.getMessage());
			
			return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
		}
		
		}
	}
