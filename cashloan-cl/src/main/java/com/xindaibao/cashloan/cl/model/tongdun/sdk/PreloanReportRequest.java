package com.xindaibao.cashloan.cl.model.tongdun.sdk;

import java.util.Arrays;

import com.xindaibao.cashloan.cl.model.tongdun.http.HttpRestRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.xindaibao.cashloan.cl.model.tongdun.http.HttpRestException;

/**
 * Created by syq on 2016/8/5.
 */
public class PreloanReportRequest extends HttpRestRequest<PreloanReportResponse> {
	
	private String urlParam;
	private String httpMethod;
	private String serverHost;
/*    @Override
    public String getUrlParam() {
        String partnerKey = InterfaceConfig.partnerKey;
        String partnerCode = InterfaceConfig.partnerCode;
        String appName = InterfaceConfig.appName;
        String urlParam = "partner_key=" + partnerKey + "&partner_code=" + partnerCode + "&app_name=" + appName;

        Map bodyMap = JSON.parseObject(this.getRequestBody(), Map.class);
        String reportId = (String) bodyMap.get("report_id");
        urlParam = urlParam + "&report_id=" + reportId;
        return urlParam;
    }
    @Override
    public String getServerHost() {
        return InterfaceConfig.getReportUrl();
    }
    
    @Override
    public String getHttpMethod() {
        return GET;
    }
*/
    @Override
    public Object getBody() throws Exception {
        return null;
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

	public String getUrlParam() {
		return urlParam;
	}

	public void setUrlParam(String urlParam) {
		this.urlParam = urlParam;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}
    
    
    
    
    
    
}
