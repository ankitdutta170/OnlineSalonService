package com.cg.trg.boot.salon.exceptions;

public class AppointmentNotFoundException extends RuntimeException {
<<<<<<< Updated upstream
	
	public AppointmentNotFoundException(String message) {
		super(message);
	}
=======
	private String operation;
	public AppointmentNotFoundException(String operation,String message) {
		super(message);
		this.operation = operation;
	}
	public String getOperation() {
		return operation;
	}
	
>>>>>>> Stashed changes

}
