package com.xindaibao.cashloan.core.common.exception;


public class RDRuntimeException extends BaseRuntimeException{

	private static final long serialVersionUID = 1L;

	public RDRuntimeException() {
	}
	
	public RDRuntimeException(String message) {
		super(message);
	}
	
	public RDRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
