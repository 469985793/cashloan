package com.xindaibao.cashloan.core.common.exception;


public class TaskExecutionException extends ErongBaseException{

	
	private static final long serialVersionUID = 1L;

	public TaskExecutionException() {
	}

	public TaskExecutionException(String message) {
		super(message);
	}

	public TaskExecutionException(String message, Throwable cause) {
		super(message, cause);
	}
}
