package com.xindaibao.cashloan.cl.model.tongdun.http;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by syq on 2016/6/15.
 */
public class CustomerErrorHandler extends DefaultResponseErrorHandler {

	private static final Logger logger = Logger.getLogger(CustomerErrorHandler.class);
	
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        /*捕获response*/
        HttpStatus statusCode = response.getStatusCode();
        if (statusCode.is4xxClientError() || statusCode.is5xxServerError()) {
            /*打印出错的信息*/
            HttpClientErrorException exception = new HttpClientErrorException(statusCode, response.getStatusText(),
                    response.getHeaders(), getResponseBody(response), getCharset(response));
                    exception.getResponseBodyAsString();
        }
    }

    private byte[] getResponseBody(ClientHttpResponse response) {
        try {
            InputStream responseBody = response.getBody();
            if (responseBody != null) {
                return FileCopyUtils.copyToByteArray(responseBody);
            }
        } catch (IOException ex) {
        	logger.error(ex);
        }
        return new byte[0];
    }

    private Charset getCharset(ClientHttpResponse response) {
        HttpHeaders headers = response.getHeaders();
        MediaType contentType = headers.getContentType();
        return contentType != null ? contentType.getCharSet() : null;
    }
}
