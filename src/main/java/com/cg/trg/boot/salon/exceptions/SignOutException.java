package com.cg.trg.boot.salon.exceptions;

public class SignOutException extends RuntimeException{

	public SignOutException(String message) {
		super(message);
	}

	private String operation;
	public SignOutException(String operation,String message) {
		super(message);
		this.operation = operation;
	}
	
	public String getOperation() {
		return operation;
	}
}
