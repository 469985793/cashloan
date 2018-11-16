package com.xindaibao.cashloan.cl.model.tongdun.http;

import org.springframework.http.ResponseEntity;

/**
 * Created by syq on 2016/5/30.
 */
public class ResponseDataToJsonParser<T extends HttpRestResponse> implements HttpResponseParser<T>{

    private Class<T> clazz;

    public ResponseDataToJsonParser(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T handle(ResponseEntity<String> responseBody) throws HttpRestException {
        T response = null;
        int status = responseBody.getStatusCode().value();
        String body = responseBody.getBody();

        try {
            response = JsonFieldAutoPickGenerator.autoSetter(body, this.clazz);
            response.setBody(body);
            response.setCode(status);
            response.setMessage("");
        } catch (Exception e) {
            throw new HttpRestException(e.getMessage(),e);
        }
        return response;
    }
}
