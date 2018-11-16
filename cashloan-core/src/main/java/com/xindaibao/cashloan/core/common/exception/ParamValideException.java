package com.xindaibao.cashloan.core.common.exception;

/**
 *  参数校验异常
 * @author
 *
 */
public class ParamValideException extends ErongBaseException {
	
	private static final long serialVersionUID = 1L;

	public ParamValideException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParamValideException(int code, String msg) {
		super(code, msg);
		// TODO Auto-generated constructor stub
	}

	public ParamValideException(int code) {
		super(code);
		// TODO Auto-generated constructor stub
	}

	public ParamValideException(String message, Throwable cause, int code) {
		super(message, cause, code);
		// TODO Auto-generated constructor stub
	}

	public ParamValideException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ParamValideException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ParamValideException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
