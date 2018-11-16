package com.xindaibao.cashloan.cl.model.tongdun.http;

import org.springframework.http.HttpHeaders;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * SDK请求总接口
 * Created by syq on 2015/10/13.
 */
public abstract class HttpRestRequest<T extends HttpRestResponse> {

    public static final String GET = "GET";

    public static final String POST = "POST";

    public static final String DELETE = "DELETE";

    public static final String PUT = "PUT";

    public static final String HEAD = "HEAD";

    public static final String PATCH = "PATCH";

    private Map<String, Object> paramMap;

    protected String requestBody;

  

    private Class<T> clazz;

    @SuppressWarnings("unchecked")
	public HttpRestRequest() {
        Type type = getClass().getGenericSuperclass();
        this.clazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
    }


    public String getRequestBody() {
        return requestBody;
    }
    
    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    /**
     * 初始化参数
     *
     * @param map
     */
    public void setParamMap(Map<String, Object> map) {
        this.paramMap = map;
        this.requestBody = (String) this.paramMap.get("body");
    }

    /**
     * 参数加密
     *
     * @return
     */
    public abstract String getUrlParam();


    /**
     * 获取本次请求http方法
     *
     * @return
     */
    public abstract String getHttpMethod();


    /**
     * 获取参数体
     * 多用于post等
     *
     * @return
     */
    public abstract Object getBody() throws Exception;


    /**
     * 获取本次请求的服务地址
     *
     * @return
     */
    public abstract String getServerHost();

    /**
     * 获取request对应的response
     *
     * @return
     */
    public Class<T> getResponseClass() {
        return this.clazz;
    }

    /**
     * 设置http header
     *
     * @return
     */
    public abstract HttpHeaders getHeaderMap();

    /**
     * 检验一些必须提交的参数
     *
     * @throws Exception
     */
    public abstract void check() throws HttpRestException;



}
