package com.xindaibao.cashloan.system.security.datesource;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import com.xindaibao.cashloan.system.security.userdetails.UserFunction;
import com.xindaibao.cashloan.system.security.userdetails.UserRole;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * 访问决策器：角色与功能的URL访问决策。
 * @author
 */
public class SecurityContextMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	//private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	
	 
	public Collection<ConfigAttribute> getAttributes(Object obj) throws IllegalArgumentException {
        String url = ((FilterInvocation) obj).getRequestUrl();
        RequestMatcher urlMatcher = new AntPathRequestMatcher(url);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserRole) {
            UserRole userRole = (UserRole) principal;
            Map<String, UserFunction> resouceMap = userRole.getFunctionMap();
            if (resouceMap != null && !resouceMap.isEmpty()) {
                for (Entry<String, UserFunction> res : resouceMap.entrySet()) {
                    if (urlMatcher.matches(((FilterInvocation) obj).getHttpRequest())) {
                        return res.getValue().getRoles();
                    }
                }
            }
        }
        return null;
    }

	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

}
