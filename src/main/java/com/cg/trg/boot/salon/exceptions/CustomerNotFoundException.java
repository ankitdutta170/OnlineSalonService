package com.cg.trg.boot.salon.exceptions;

public class CustomerNotFoundException  extends RuntimeException{
	

	private String operation;
	public CustomerNotFoundException(String operation,String message) {
		super(message);
		this.operation = operation;
	}
	
	public String getOperation() {
		return operation;
	}

}
