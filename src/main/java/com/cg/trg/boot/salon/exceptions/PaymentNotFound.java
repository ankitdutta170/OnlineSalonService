package com.cg.trg.boot.salon.exceptions;

public class PaymentNotFound extends RuntimeException{
	private String operation;
	private String message;
	public PaymentNotFound(String opertaion) {
		super(opertaion);
	}
	public PaymentNotFound(String operation,String message ) {
		super(message);
		this.operation=operation;
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
}
