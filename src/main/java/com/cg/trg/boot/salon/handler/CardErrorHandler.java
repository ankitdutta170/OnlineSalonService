package com.cg.trg.boot.salon.handler;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.cg.trg.boot.salon.exceptions.CardNotFoundException;


@ControllerAdvice
public class CardErrorHandler {
	@ExceptionHandler(CardNotFoundException.class)
	public ResponseEntity<?> CardNotFoundException(CardNotFoundException nu){
		Map<String, Object> errorBody = new LinkedHashMap<>();

		errorBody.put("error", "No Card found");
		errorBody.put("timestamp", LocalDate.now());
		errorBody.put("errorMessage", nu.getMessage());
		
		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
	}

}
