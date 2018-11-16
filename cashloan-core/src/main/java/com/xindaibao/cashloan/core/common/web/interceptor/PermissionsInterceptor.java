package com.xindaibao.cashloan.core.common.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xindaibao.cashloan.system.service.SysMenuService;


@Service
public class PermissionsInterceptor extends HandlerInterceptorAdapter {

	@Autowired@Qualifier("sysMenuServiceImpl")
	private SysMenuService sysMenuService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		if(SecurityContextHolder.getContext().getAuthentication() == null){
			return true;
		}
		
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  	if(user == null || user.getUsername() == null){
	  		response.setContentType("text/html;charset=utf-8");
	  		response.getWriter().print("您未登录，请登录后继续此操作!");
	  		return false;
	  	}
	  	String url =	request.getServletPath();
	  	if(url == null || url.length() <= 0){
	  		return true;
	  	}
//	  	boolean flag = sysMenuService.getMenuPermission(user.getUsername(), url);
//	  	if (!flag) {
//			try {
//				response.setContentType("text/html;charset=utf-8");
//				response.getWriter().print(MessageUtil.getMessage("W10002"));
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				response.getWriter().close();
//			}
//			return false;
//		}
		return true;
	}
}
