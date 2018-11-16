package com.xindaibao.cashloan.cl.sdk.exception;

/**
 * Created by junlou.liu on 2017/9/8.
 */
public class CreditLoanSDKException extends RuntimeException {
    private int code;
    public CreditLoanSDKException(int code, String message, Throwable e){
        super(message, e);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
