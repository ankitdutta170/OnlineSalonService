package com.cg.trg.boot.salon.exceptions;





public class DuplicateAppointmentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateAppointmentException(String message) {
		super(message);
	}

}
