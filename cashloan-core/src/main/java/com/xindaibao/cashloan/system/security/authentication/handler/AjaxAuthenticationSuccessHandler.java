package com.xindaibao.cashloan.system.security.authentication.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xindaibao.cashloan.core.common.context.Constant;

public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
 
    public AjaxAuthenticationSuccessHandler() {
    }
 
    @SuppressWarnings("deprecation")
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
 
        ObjectMapper objectMapper = new ObjectMapper();
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(),
                JsonEncoding.UTF8);
        try {
                          
        	Map<String, Object> context = new HashMap<String, Object>();
    		context.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            objectMapper.writeValue(jsonGenerator, context);
        } catch (Exception ex) {
            throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
        }
    }
}