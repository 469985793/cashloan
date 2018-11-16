package com.xindaibao.cashloan.core.common.web.interceptor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.multiaction.InternalPathMethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;

import com.xindaibao.cashloan.system.service.SysMenuService;

import tool.util.StringUtil;


@Service
public class HandInterceptor extends HandlerInterceptorAdapter{

	/** 日志 */
	private static final Logger log = Logger.getLogger(HandInterceptor.class);

	
	@Autowired@Qualifier("sysMenuServiceImpl")
	private SysMenuService sysMenuService;
	
	/** request */
	private HttpServletRequest request;
	
	private MethodNameResolver methodNameResolver = new InternalPathMethodNameResolver();

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
		this.request = request;
		String methodName = methodNameResolver.getHandlerMethodName(request);
		this.saveOperatorLog(methodName);
		if(e != null){
			log.error("Exception message   :", e);
			this.saveExceptionLog(methodName, e);
		}
	}

	/**
	 * 系统操作信息保存
	 * @param className 类命
	 * @param methodName 方法名
	 */
	public void saveOperatorLog(String methodName) {
		/*String path = request.getServletPath();
		String ip = IpUtil.getRemortIP(request);
		boolean result = this.isOperatorLog(path);
		if (result) {
			sysMenuService.getMenuByHref(path);
			SysLog log = new SysLog();
			log.setMethod(methodName);
			if (menu != null) {
				log.setException(menu.getRemark());
			}
			log.setParams(this.getAllParams(true));
			log.setRemoteAddr(ip);
			log.setRequestUri(path);
			log.setType(SystemConstant.TYPE_OPERATOR);
			UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (user != null) {
				log.setAddUser(user.getUsername());
				log.setException(log.getException() + ",系统操作员：" + user.getUsername());
			}
			try {
				this.sysLogService.addLog(log);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}*/	
	}
	
	/**
	 * 异常信息保存
	 * @param className 类命
	 * @param methodName 方法名
	 * @param e 异常信息
	 */
	public void saveExceptionLog(String methodName, Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw, true));
		/*SysLog log = new SysLog();
		log.setMethod(methodName);
		log.setException(sw.toString());
		log.setParams(this.getAllParams(true));
		String path = request.getServletPath();
		String ip = IpUtil.getRemortIP(request);
		log.setRemoteAddr(ip);
		log.setRequestUri(path);
		log.setType(SystemConstant.TYPE_EXCEPTION);
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user != null) {
			log.setAddUser(user.getUsername());
		}
		try {
			this.sysLogService.addLog(log);
		} catch (ServiceException e1) {
			e1.printStackTrace();
		}*/
	}
	
	/**
	 * 此次请求，是否保存操作日志信息
	 *  
	 * @param path 路径
	 * @return 结果
	 */
	public boolean isOperatorLog(String path) {
		String[] urlArr = {"Add.htm", "Edit.htm", "Delete.htm"};
		for (int i = 0; i < urlArr.length; i++) {
			String url = urlArr[i];
			int size = path.lastIndexOf(url);
			if (size > 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 提取参数
	 * @param safety 是否对密码过滤
	 * @return 参数信息
	 */
	protected String getAllParams(boolean safety) {
		StringBuilder ps = new StringBuilder();
		Enumeration<?> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameter = (String) parameterNames.nextElement();
			String value = request.getParameter(parameter);
			if (StringUtil.isNotBlank(value)) {
				if (safety || (!parameter.contains("password") && !parameter.contains("pwd"))) { //安全性
					ps.append(parameter + "=" + value);
					if (parameterNames.hasMoreElements()) {
						ps.append("&");
					}
				}
			}
		}
		return ps.toString();
	}
}
