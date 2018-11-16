package com.xindaibao.cashloan.cl.model.tongdun.http;

/**
 * Created by syq on 2015/10/13.
 */
@SuppressWarnings("serial")
public class HttpRestException extends RuntimeException {

    private String errCode;
    private String errMsg;

    public HttpRestException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

	public HttpRestException() {
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public HttpRestException(Throwable cause) {
        super(cause);
    }

    public HttpRestException(String message, Throwable cause) {
        super(message, cause);
    }


    public HttpRestException(String message) {
        super(message);
    }



}
