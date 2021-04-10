package com.cg.trg.boot.salon.exceptions;

public class AddressNotFoundException extends RuntimeException {
	public AddressNotFoundException(String message) {
		super(message);
	}

	private String operation;
	public AddressNotFoundException(String operation,String message) {
		super(message);
		this.operation = operation;
	}
	
	public String getOperation() {
		return operation;
	}

}
