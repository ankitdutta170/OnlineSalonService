package com.cg.trg.boot.salon.exceptions;

public class PasswordChangeException extends RuntimeException {
	
	public PasswordChangeException(String message) {
		super(message);
	}

	private String operation;
	public PasswordChangeException(String operation,String message) {
		super(message);
		this.operation = operation;
	}
	
	public String getOperation() {
		return operation;
	}

}
