package com.xindaibao.cashloan.core.common.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 
 * 用来获取客户端实际IP的过滤器。
 * @author
 * 
 */
public class RemoteAddressFilter implements Filter {

	private static final String FORWARD_PARAMTER_NAME = "forwardParameter";
	
	//http头中用来存放真实IP的属性名，
	private String forwardParameter = "X-Forwarded-For";

	public void init(FilterConfig fc) throws ServletException {
		String get = fc.getInitParameter(FORWARD_PARAMTER_NAME);
		if (get != null) {
			forwardParameter = get.trim();
		}
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain fc) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 非http servlet request ,不处理
		if (!(request instanceof HttpServletRequest)) {			
			fc.doFilter(request, response);
			return;
		}
		
		// 已经处理过,不处理
		if (request instanceof RemoteIpRequestWrapper) {		
			fc.doFilter(request, response);
			return;
		}
		
		HttpServletRequest hsr = (HttpServletRequest) request;
		final String forward = hsr.getHeader(forwardParameter);
		if (forward == null) {
			fc.doFilter(request, response);
			return;
		}
		fc.doFilter(new RemoteIpRequestWrapper(hsr, forward), response);
	}

	private static class RemoteIpRequestWrapper extends
			HttpServletRequestWrapper {
		private String remoteIp;

		public RemoteIpRequestWrapper(HttpServletRequest request, String ip) {
			super(request);
			this.remoteIp = ip;
		}

		@Override
		public String getRemoteAddr() {
			return remoteIp;
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
