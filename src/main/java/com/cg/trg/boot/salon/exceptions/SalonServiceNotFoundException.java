package com.cg.trg.boot.salon.exceptions;

public class SalonServiceNotFoundException extends RuntimeException {

	
	public SalonServiceNotFoundException(String message) {
		super(message);
	}

	private String operation;
	public SalonServiceNotFoundException(String operation,String message) {
		super(message);
		this.operation = operation;
	}
	
	public String getOperation() {
		return operation;
	}
	


}
