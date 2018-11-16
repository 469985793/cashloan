package com.xindaibao.cashloan.core.common.exception;

public class BaseException extends RuntimeException {
	
	private static final long serialVersionUID = 538922474277376456L;

	public static final int TYPE_JSON = 1;
	private String url;
	protected int type =500;

	public BaseException(String msg, RuntimeException ex) {
		super(msg, ex);
	}

	public BaseException() {
		super();
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String message, String url) {
		super(message);
		this.url = url;
	}

	public BaseException(String message, int type) {
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
