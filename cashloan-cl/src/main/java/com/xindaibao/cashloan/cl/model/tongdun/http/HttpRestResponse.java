package com.xindaibao.cashloan.cl.model.tongdun.http;


/**
 * Created by syq on 2015/10/13.
 */
public abstract class HttpRestResponse {


    private Integer code;

    private String message;

    private String body;

    private HttpRestRequest<?> httpRestRequest;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public HttpRestRequest<?> getHttpRestRequest() {
        return httpRestRequest;
    }

    public void setHttpRestRequest(HttpRestRequest<?> httpRestRequest) {
        this.httpRestRequest = httpRestRequest;
    }

    /**
     * 当请求成功之后的后续处理
     * @return
     */
    public abstract String postResponseToJsonStr();

}
