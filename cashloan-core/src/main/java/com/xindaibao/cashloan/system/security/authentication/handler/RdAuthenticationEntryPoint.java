package com.xindaibao.cashloan.system.security.authentication.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.xindaibao.cashloan.core.common.util.ServletUtils;

@SuppressWarnings({"deprecation","unused"})
public class RdAuthenticationEntryPoint extends
		LoginUrlAuthenticationEntryPoint {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private static final Log logger = LogFactory
			.getLog(RdAuthenticationEntryPoint.class);

	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String url = request.getRequestURI();
		logger.info("commence------------");
		if (logger.isDebugEnabled()) {
			logger.debug("url:" + url);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("ajax request or post");
		}

		if (request.getHeader("x-requested-with") != null
				&& "XMLHttpRequest".equalsIgnoreCase( // ajax超时处理
						request.getHeader("x-requested-with"))) {
			response.addHeader("sessionstatus", "timeout");

			Map<String, Object> error = new HashMap<String, Object>();
			error.put("code", "800");
			
			error.put("msg", "与服务器的会话已经超时");
			error.put("data", ""); // 兼容extjs form loading
			response.setCharacterEncoding("UTF-8");
			response.addHeader("__timeout", "true");
			ServletUtils.writeToResponse(response, error);
			logger.info("------- test=====================");
		} else {

			super.commence(httpRequest, httpResponse, authException);
		}

    }

	}

