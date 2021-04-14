package com.cg.trg.boot.salon.exceptions;

public class AppointmentNotFoundException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AppointmentNotFoundException(String message) {
		super(message);
	}

	private String operation;
	public AppointmentNotFoundException(String operation,String message) {
		super(message);
		this.operation = operation;
	}
	
	public String getOperation() {
		return operation;
	}
	


}
