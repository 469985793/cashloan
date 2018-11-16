package com.xindaibao.cashloan.core.common.web.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.xindaibao.cashloan.core.common.model.TimeProfiler;

/**
 * 性能监听器:将访问时间以100毫秒为单位，
 * 超过100毫秒的，将记录下访问信息，以日志形式输出
 * @author
 */
public class PerformanceMonitorFilter implements Filter {

	/**
	 * 缺省监测值为100毫秒，超过这个值的request请求将被记录
	 */
	private int threshold = 100;

	public void init(FilterConfig config) throws ServletException {
		String s = config.getInitParameter("threshold");

		if (s == null) {
			return;
		}
		threshold = Integer.valueOf(s);
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (TimeProfiler.isProfileEnable()) {
			doFilterWithProfile(request, response, chain);
		} else {
			chain.doFilter(request, response);
		}
	}

	private void doFilterWithProfile(ServletRequest request,
			ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest hs = (HttpServletRequest) request;
		hs.setCharacterEncoding("UTF-8");
		String profilerId = getProfilerId(hs);
		TimeProfiler profiler = TimeProfiler.start(profilerId, threshold);
		try {
			chain.doFilter(request, response);
		} finally {
			profiler.release(getProfilerName(hs));
		}
	}

	private String getProfilerId(HttpServletRequest hs) {
		StringBuffer id = hs.getRequestURL();
		return id.toString();
	}

	@SuppressWarnings("rawtypes")
	private String getProfilerName(HttpServletRequest hs) {
		StringBuffer id = hs.getRequestURL();
		id.append(' ');
		Enumeration enumer = hs.getParameterNames();
		while (enumer.hasMoreElements()) {
			String name = enumer.nextElement().toString();
			String value = hs.getParameter(name);
			id.append('[').append(name).append('=').append(value).append("] ");
		}
		return id.toString();
	}
}
