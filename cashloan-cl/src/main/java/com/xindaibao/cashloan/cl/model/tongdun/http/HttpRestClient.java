package com.xindaibao.cashloan.cl.model.tongdun.http;

import com.xindaibao.cashloan.cl.model.tongdun.RestTemplateConfig;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


import java.net.URI;

/**
 * Created by syq on 2015/10/13.
 */
public class HttpRestClient {

	private static final Logger logger = Logger.getLogger(HttpRestClient.class);
	
    private static HttpRestClient instance = new HttpRestClient();


    private static SimpleClientHttpRequestFactory requestFactory;

    private HttpRestClient() {
        requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(30000);

    }
    public static HttpRestClient create() {
        return instance;
    }
    public <T extends HttpRestResponse> T executeThenGetJsonResponse(HttpRestRequest<T> request) {
        return this._execute(request, new ResponseDataToJsonParser<>(request.getResponseClass()));
    }

    private <T extends HttpRestResponse> T _execute(HttpRestRequest<T> request, HttpResponseParser<T> parser) {
    	
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.setErrorHandler(new CustomerErrorHandler());

        T response = null;
        try{
	       // request.check();
	        String httpMethod = request.getHttpMethod();
	        Object body = request.getBody();
	        String host = request.getServerHost();
	        String urlParam = request.getUrlParam();
	        if (urlParam != null && !urlParam.isEmpty()) {
	            host = host + "?" + urlParam;
	        }
	        HttpHeaders header = request.getHeaderMap();
	        URI u = new URI(host);
	        HttpEntity<?> httpEntity = new HttpEntity<>(body, header);
	        ResponseEntity<String> result = null;

            //logger.warn("++++++++++++httpMethod+++++++++++++++++ " + httpMethod);
	        switch (httpMethod) {
	            case "POST":
	                restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
	                result = restTemplate.exchange(u, HttpMethod.POST, httpEntity, String.class);
	                break;
	            case "GET":
	                result = restTemplate.exchange(u, HttpMethod.GET, httpEntity, String.class);
	                break;
	            case "PUT":
	                result = restTemplate.exchange(u, HttpMethod.PUT, httpEntity, String.class);
	                break;
	            case "DELETE":
	                result = restTemplate.exchange(u, HttpMethod.DELETE, httpEntity, String.class);
	                break;
	        }
	        //logger.warn("+++++++++++++result++++++++++++++++ " + result);
	        if (result != null) {
	            response = parser.handle(result);
	            response.setHttpRestRequest(request);
	        }
    	} catch (HttpRestException e) {
            logger.error("========error = ======", e);
            try{
				response = request.getResponseClass().newInstance();
				response.setCode(400);
	            response.setMessage(e.getErrMsg());
             }catch (Exception e1) {
            	 logger.error(e1);
			}
        } catch (Exception e) {
            logger.error("========error ... = ======", e);
        	try {
        		response.setCode(200);
                response.setMessage("请求失败");
        	} catch (Exception e1) {
        		logger.error(e1);
        	}
        }
        return response;
    }

    /**
     *
     *
     * https://gist.github.com/jonashackt/820642aff1da17a89ee2
     * @param request
     * @param <T>
     * @return
     */
    public <T extends HttpRestResponse> T executeThenGetJsonResponse2(HttpRestRequest<T> request) {
        return this.execute2(request, new ResponseDataToJsonParser<>(request.getResponseClass()));
    }

    public <T extends HttpRestResponse> T execute2(HttpRestRequest<T> request, HttpResponseParser<T> parser) {

        RestTemplate restTemplate = RestTemplateConfig.initRestTemplateForPdfAsByteArrayAndSelfSignedHttps();
        restTemplate.setErrorHandler(new CustomerErrorHandler());

        T response = null;
        try{
            // request.check();
            String httpMethod = request.getHttpMethod();
            Object body = request.getBody();
            String host = request.getServerHost();
            String urlParam = request.getUrlParam();
            if (urlParam != null && !urlParam.isEmpty()) {
                host = host + "?" + urlParam;
            }
            HttpHeaders header = request.getHeaderMap();
            URI u = new URI(host);
            HttpEntity<?> httpEntity = new HttpEntity<>(body, header);
            ResponseEntity<String> result = null;

            logger.warn("++++++++++++httpMethod+++++++++++++++++ " + httpMethod);
            switch (httpMethod) {
                case "POST":
                    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
                    result = restTemplate.exchange(u, HttpMethod.POST, httpEntity, String.class);
                    break;
                case "GET":
                    result = restTemplate.exchange(u, HttpMethod.GET, httpEntity, String.class);
                    break;
                case "PUT":
                    result = restTemplate.exchange(u, HttpMethod.PUT, httpEntity, String.class);
                    break;
                case "DELETE":
                    result = restTemplate.exchange(u, HttpMethod.DELETE, httpEntity, String.class);
                    break;
            }
//            logger.warn("+++++++++++++result++++++++++++++++ " + result);
            if (result != null) {
                response = parser.handle(result);
                response.setHttpRestRequest(request);
            }
        } catch (HttpRestException e) {
            logger.error("========error you = ======", e);
            try{
                response = request.getResponseClass().newInstance();
                response.setCode(400);
                response.setMessage(e.getErrMsg());
            }catch (Exception e1) {
                logger.error(e1);
            }
        } catch (Exception e) {
            logger.error("========error = ======", e);
            try {
                response.setCode(200);
                response.setMessage("请求失败");
            } catch (Exception e1) {
                logger.error(e1);
            }
        }
        return response;
    }

}