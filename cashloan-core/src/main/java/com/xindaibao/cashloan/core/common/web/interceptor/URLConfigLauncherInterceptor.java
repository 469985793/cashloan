package com.xindaibao.cashloan.core.common.web.interceptor;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xindaibao.cashloan.core.common.model.URLConfig;

/**
 * 缺省的url映射处理
 * @author
 */
public class URLConfigLauncherInterceptor extends HandlerInterceptorAdapter {
	
	private Map<String, URLConfig> urlConfigs;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
		HttpServletResponse response, Object handler) throws Exception {
		for (Entry<String, URLConfig> entry : urlConfigs.entrySet()) {
			request.setAttribute(entry.getKey(), entry.getValue());
		}
		return super.preHandle(request, response, handler);
	}

	public Map<String, URLConfig> getUrlConfigs() {
		return urlConfigs;
	}

	public void setUrlConfigs(Map<String, URLConfig> urlConfigs) {
		this.urlConfigs = urlConfigs;
	}
}
