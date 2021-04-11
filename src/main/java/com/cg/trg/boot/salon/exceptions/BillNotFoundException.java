package com.cg.trg.boot.salon.exceptions;

public class BillNotFoundException extends RuntimeException{
	
	public BillNotFoundException(String message) {
		super(message);
	}

	private String operation;
	public BillNotFoundException(String operation,String message) {
		super(message);
		this.operation = operation;
	}
	
	public String getOperation() {
		return operation;
	}

}
