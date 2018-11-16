package com.xindaibao.cashloan.cl.model.tongdun.sdk;

import java.util.Arrays;
import java.util.Map;

import com.xindaibao.cashloan.cl.model.tongdun.http.HttpRestException;
import com.xindaibao.cashloan.cl.model.tongdun.http.HttpRestRequest;
import com.xindaibao.cashloan.cl.model.tongdun.util.EncryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSON;

/**
 * Created by syq on 2016/8/5.
 */
@SuppressWarnings("unchecked")
public class PreloanRequest extends HttpRestRequest<PreloanResponse> {
	
 
    
	private String urlParam;
	private String httpMethod;
	private String serverHost;
	
	@Override
    public Object getBody() throws Exception {
        Map<String, Object> bodyMap = (Map<String, Object>) JSON.parse(this.getRequestBody());
        MultiValueMap<String, Object> encodeMap = EncryptUtil.postFormAndEncoder(bodyMap, false);
        return encodeMap;
    }

    @Override
    public HttpHeaders getHeaderMap() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Accept-Charset", "utf-8");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }

    @Override
    public void check() throws HttpRestException {
        if (StringUtils.isBlank(requestBody)) {
            throw new HttpRestException("requestBody 参数不能为空！");
        }
    }
 
   
	public void setUrlParam(String urlParam) {
		this.urlParam = urlParam;
	}
 
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
 
	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}


	public String getUrlParam() {
		return urlParam;
	}


	public String getHttpMethod() {
		return httpMethod;
	}


	public String getServerHost() {
		return serverHost;
	}
	/*
	@Override
    public String getUrlParam() {
        String partnerKey = InterfaceConfig.partnerKey;
        String partnerCode = InterfaceConfig.partnerCode;
        String appName = InterfaceConfig.appName;
        String urlParam = "partner_key=" + partnerKey + "&partner_code=" + partnerCode + "&app_name=" + appName;
        return urlParam;
    }

    @Override
    public String getHttpMethod() {
        return POST;
    }
 
    @Override
    public String getServerHost() {
        return InterfaceConfig.preloanUrl();
    }
    */
}
