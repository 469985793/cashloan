package com.xindaibao.cashloan.core.common.exception;


public class ObjectNotFoundException extends ErongBaseException{
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException() {
	}
	
	public ObjectNotFoundException(String message) {
		super(message);
	}
	
	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
