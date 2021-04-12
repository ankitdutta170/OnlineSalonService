package com.cg.trg.boot.salon.exceptions;

public class CardNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String operation;
	private String message;
	public CardNotFoundException(String message) {
		super(message);
	}

	
	public CardNotFoundException(String operation,String message) {
		super(message);
		this.operation = operation;
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}

	public String getOperation() {
		return operation;
	}


}
