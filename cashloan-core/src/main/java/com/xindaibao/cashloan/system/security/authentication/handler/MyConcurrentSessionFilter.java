package com.xindaibao.cashloan.system.security.authentication.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xindaibao.cashloan.core.common.util.ServletUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import com.xindaibao.cashloan.core.common.context.Constant;

@SuppressWarnings("unused")
public class MyConcurrentSessionFilter  extends GenericFilterBean{
	
    private SessionRegistry sessionRegistry;
    private String expiredUrl;
    private LogoutHandler[] handlers = new LogoutHandler[] {new SecurityContextLogoutHandler()};
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    //~ Methods ========================================================================================================

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(sessionRegistry, "SessionRegistry required");
        Assert.isTrue(expiredUrl == null || UrlUtils.isValidRedirectUrl(expiredUrl),
                expiredUrl + " isn't a valid redirect URL");
    }

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		 HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;

	        HttpSession session = request.getSession(false);
	        if (session != null) {
	        	if("/j_spring_security_logout".equals(request.getRequestURI())){//退出系统时，清除这三个自定义的session. 退出方式和原来一样。added by wgc on 2015-6
	        		session.removeAttribute("SysUser");
	        		session.removeAttribute(Constant.ROLEID);
	        		session.removeAttribute("SPRING_SECURITY_CONTEXT");
	        	    session.invalidate();
	        	    SecurityUtils.getSubject().logout();
	        	}
	            SessionInformation info = sessionRegistry.getSessionInformation(session.getId());

	            if (info != null) {
	                if (info.isExpired()) {
	                	  doLogout(request, response);
	                	
	                	//  session.setMaxInactiveInterval(0);
	                      String targetUrl = determineExpiredUrl(request, info);
	                      response.setHeader("Location",request.getContextPath()+targetUrl);        
	                      //response.setHeader("sessionstatus", "timeout");
	                    // Expired - abort processing
	                  

	                   

//	                    if (targetUrl != null) {
//	                        redirectStrategy.sendRedirect(request, response, targetUrl);
//
//	                        return;
//	                    } else {
//	                        response.getWriter().print("This session has been expired (possibly due to multiple concurrent " +
//	                                "logins being attempted as the same user).");
//	                        response.flushBuffer();
//	                    }
	                      
	                      Map<String, Object> error = new HashMap<String, Object>();
	                      error.put("success", false);
	                      error.put("errCode", "0x0001");
	                      error.put("message", "与服务dadads的会话已经超时");
	                      error.put("data", "");  // 兼容extjs form loading
	                      response.setHeader("Location",request.getContextPath()+targetUrl);  
	                      response.setCharacterEncoding("UTF-8");
	                      response.addHeader("__timeout","true");  
//	                      PrintWriter printWriter=response.getWriter();
//	                      printWriter.write(JSON.toJSONString(error));
//	                      printWriter.flush();
//	                      printWriter.close();
	                      
	                      //response.sendRedirect(redirectUrl);
	                      
	                    ServletUtils.writeToResponse(response, error);

	                   // return;
	                } else {
	                    // Non-expired - update last request date/time
	                    info.refreshLastRequest();
	                }
	            }
	        }else{
	        	response.addHeader("sessionstatus", "timeout");

				Map<String, Object> error = new HashMap<String, Object>();
				error.put("code", "800");
				
				error.put("msg", "与服务器的会话已经超时");
				error.put("data", ""); // 兼容extjs form loading
				response.setCharacterEncoding("UTF-8");
				response.addHeader("__timeout", "true");
				ServletUtils.writeToResponse(response, error);
				//response.sendRedirect("/dev/index.html");
				return;
	        }
//	            else {
////				  
//	        	   Map<String, Object> error = new HashMap<String, Object>();
//	        response.setHeader("Location",request.getContextPath()+targetUrl);  
//                   error.put("success", false);
//                   error.put("errCode", "0x0001");
//                   error.put("message", "与服务dadads的会话已经超时");
//                   error.put("data", "");  // 兼容extjs form loading
//                   response.setCharacterEncoding("UTF-8");
//                   response.addHeader("__timeout","true");  
////                   PrintWriter printWriter=response.getWriter();
////                   printWriter.write(JSON.toJSONString(error));
////                   printWriter.flush();
////                   printWriter.close();
//                   
//                   //response.sendRedirect(redirectUrl);
//                   
//                 ServletUtils.writeToResponse(response, error);
//			}
//              
	        
				
	        	chain.doFilter(request, response);
			
	}
	
	   protected String determineExpiredUrl(HttpServletRequest request, SessionInformation info) {
	        return expiredUrl;
	    }

	    private void doLogout(HttpServletRequest request, HttpServletResponse response) {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	        for (int i = 0; i < handlers.length; i++) {
	            handlers[i].logout(request, response, auth);
	        }
	    }

	    public void setExpiredUrl(String expiredUrl) {
	        this.expiredUrl = expiredUrl;
	    }

	    public void setSessionRegistry(SessionRegistry sessionRegistry) {
	        this.sessionRegistry = sessionRegistry;
	    }

	    public void setLogoutHandlers(LogoutHandler[] handlers) {
	        Assert.notNull(handlers);
	        this.handlers = handlers;
	    }

	    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
	        this.redirectStrategy = redirectStrategy;
	    }

}
