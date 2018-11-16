package com.xindaibao.cashloan.cl.model.zmxy.authorize;

/**
 * Created by syq on 2016/9/13.
 */
public class AuthCallBackResp {

    private boolean success;

    private String openId;

    private String key;

    private String errorCode;

    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getOpenId() {
        return openId;
    }

    public String getKey() {
        return key;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
