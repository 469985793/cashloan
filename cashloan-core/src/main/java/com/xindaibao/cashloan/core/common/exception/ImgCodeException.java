package com.xindaibao.cashloan.core.common.exception;

/**
 * 图片验证码异常
 */
public class ImgCodeException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	protected static final int IMGCODE_EXCEPTION__CODE = 400;
	
	protected int code;
	
	public ImgCodeException(int code, String businessMessage) {
		this(businessMessage);
	}

	public ImgCodeException(String businessMessage, Throwable cause, int code) {
		this(businessMessage, cause);
	}

	public ImgCodeException(String businessMessage, Throwable cause) {
		super(businessMessage, cause);
		this.code = IMGCODE_EXCEPTION__CODE;
	}

	public ImgCodeException(String message) {
		super(message);
		this.code = IMGCODE_EXCEPTION__CODE;

	}

	public ImgCodeException(Throwable t) {
		this(t.getMessage(), t);
	}
	
	
	
}
