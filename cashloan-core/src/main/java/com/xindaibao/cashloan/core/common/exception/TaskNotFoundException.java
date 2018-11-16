package com.xindaibao.cashloan.core.common.exception;


public class TaskNotFoundException extends ErongBaseException{

	
	
	private static final long serialVersionUID = 1L;

	public TaskNotFoundException() {
	}

	public TaskNotFoundException(String message) {
		super(message);
	}

	public TaskNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
