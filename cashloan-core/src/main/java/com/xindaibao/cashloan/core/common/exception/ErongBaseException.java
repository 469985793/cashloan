package com.xindaibao.cashloan.core.common.exception;

public class ErongBaseException extends Exception {

	private static final long serialVersionUID = 2762206975774689502L;

	protected int code = 500;

	public ErongBaseException(int code) {
		this.code = code;
	}

	public ErongBaseException() {
		super();
	}

	public ErongBaseException(String message) {
		super(message);
	}

	public ErongBaseException(int code, String msg) {
		super(msg);
		this.code = code;
	}

	public ErongBaseException(Throwable cause) {
		super(cause);
	}

	public ErongBaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErongBaseException(String message, Throwable cause, int code) {
		super(message, cause);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
