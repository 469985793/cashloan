package com.xindaibao.cashloan.core.common.exception;


public class ServiceException extends ErongBaseException {

	private static final long serialVersionUID = 2517215637491201756L;

	protected static final int SERVICE_EXCEPTION__CODE = 400;
	protected static final String SERVICE_EXCEPTION__MSG = "ServiceFailed";

	public ServiceException(String businessMessage,int code) {
		this(businessMessage);
	}

	public ServiceException(String businessMessage, Throwable cause, int code) {
		this(businessMessage, cause);
	}

	public ServiceException(String businessMessage, Throwable cause) {
		super(businessMessage, cause);
		this.code = SERVICE_EXCEPTION__CODE;
	}

	public ServiceException(String message) {
		super(message);
		this.code = SERVICE_EXCEPTION__CODE;

	}

	public ServiceException(Throwable t) {
		this(t.getMessage(), t);
	}

}
