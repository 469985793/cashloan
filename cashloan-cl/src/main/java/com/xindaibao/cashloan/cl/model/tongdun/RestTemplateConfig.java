package com.xindaibao.cashloan.cl.model.tongdun;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestTemplateConfig {

    public static RestTemplate initRestTemplateForPdfAsByteArrayAndSelfSignedHttps() {
        RestTemplate restTemplate = new RestTemplate(useApacheHttpClientWithSelfSignedSupport());
        restTemplate.getMessageConverters().add(generateByteArrayHttpMessageConverter());
        return restTemplate;
    }

    private static HttpComponentsClientHttpRequestFactory useApacheHttpClientWithSelfSignedSupport() {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        HttpComponentsClientHttpRequestFactory useApacheHttpClient = new HttpComponentsClientHttpRequestFactory();
        useApacheHttpClient.setHttpClient(httpClient);
        return useApacheHttpClient;
    }

    private static ByteArrayHttpMessageConverter generateByteArrayHttpMessageConverter() {
        ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();

        List<MediaType> supportedApplicationTypes = new ArrayList<MediaType>();
        supportedApplicationTypes.add(new MediaType("application","pdf"));
        byteArrayHttpMessageConverter.setSupportedMediaTypes(supportedApplicationTypes);
        return byteArrayHttpMessageConverter;
    }
}