package com.cg.trg.boot.salon.exceptions;

public class UserIdNotFoundException extends RuntimeException{
	
	public UserIdNotFoundException(String message) {
		super(message);
	}

	private String operation;
	public UserIdNotFoundException(String operation,String message) {
		super(message);
		this.operation = operation;
	}
	
	public String getOperation() {
		return operation;
	}

}
