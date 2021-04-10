package com.cg.trg.boot.salon.handler;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.trg.boot.salon.exceptions.NoDataException;
import com.cg.trg.boot.salon.exceptions.SalonServiceNotFoundException;



public class SalonHandler {

	@ExceptionHandler(NoDataException.class)
	public ResponseEntity<?> handleNoData(NoDataException ex) {
		Map<String, Object> errorBody = new LinkedHashMap<>();
		errorBody.put("error", "creation failed");
		
		return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(SalonServiceNotFoundException.class)
	public ResponseEntity<?> handleMissingSalonService(SalonServiceNotFoundException ex) {
		Map<String, Object> errorBody = new LinkedHashMap<>();
		errorBody.put("error", "creation failed");
	
		return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
	}

}

