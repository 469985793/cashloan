package com.xindaibao.cashloan.core.common.exception;


public class PersistentDataException extends ErongBaseException {

	private static final long serialVersionUID = -4715158650768072340L;

	public PersistentDataException() {
		super();
	}

	public PersistentDataException(int code, String msg) {
		super(code, msg);
	}

	public PersistentDataException(int code) {
		super(code);
	}

	public PersistentDataException(String message, Throwable cause, int code) {
		super(message, cause, code);
	}

	public PersistentDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistentDataException(String message) {
		super(message);
	}

	public PersistentDataException(Throwable cause) {
		super(cause);
	}

	// private static final int PERSISTENEXCEPTION = 409;
	//
	// public PersistentDataException(String message, Throwable cause) {
	// super(message, cause, PERSISTENEXCEPTION);
	//
	// }
	//
	// public PersistentDataException(String msg) {
	// super(PERSISTENEXCEPTION, msg);
	// }

}
