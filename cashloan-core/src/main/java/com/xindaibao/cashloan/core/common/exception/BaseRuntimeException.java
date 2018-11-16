package com.xindaibao.cashloan.core.common.exception;

public class BaseRuntimeException extends RuntimeException {
	
	private static final long serialVersionUID = 538922474277376456L;

	public static final int TYPE_JSON = 1;
	private String url;
	protected int type =500;

	public BaseRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BaseRuntimeException() {
		super();
	}

	public BaseRuntimeException(String message) {
		super(message);
	}

	public BaseRuntimeException(String message, String url) {
		super(message);
		this.url = url;
	}

	public BaseRuntimeException(String message, int type) {
		super(message);
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
