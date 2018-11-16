package com.xindaibao.cashloan.system.security.authentication.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.ServletUtils;

public class MyAccessDeniedHandlerImpl implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
		Map<String, Object> context = new HashMap<String, Object>();
		context.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
		context.put(Constant.RESPONSE_CODE_MSG, "登录失败");
		ServletUtils.writeToResponse(response, context);

	}

}
