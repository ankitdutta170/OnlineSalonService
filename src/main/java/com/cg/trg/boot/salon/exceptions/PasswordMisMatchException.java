package com.cg.trg.boot.salon.exceptions;

public class PasswordMisMatchException extends RuntimeException {

	public PasswordMisMatchException(String message) {
		super(message);
	}

	private String operation;
	public PasswordMisMatchException(String operation,String message) {
		super(message);
		this.operation = operation;
	}
	
	public String getOperation() {
		return operation;
	}
	
}
