package com.xindaibao.cashloan.cl.model.tongdun.http;

import org.springframework.http.ResponseEntity;

/**
 * 接口响应数据解析 接口
 * Created by syq on 2015/10/13.
 */
public interface HttpResponseParser<T extends HttpRestResponse> {


    T handle(ResponseEntity<String> responseBody) throws HttpRestException;


}
