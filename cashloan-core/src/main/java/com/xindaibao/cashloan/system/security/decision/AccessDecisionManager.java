package com.xindaibao.cashloan.system.security.decision;

import java.util.Collection;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 访问决策管理器
 * @author
 */
public class AccessDecisionManager implements org.springframework.security.access.AccessDecisionManager{

	public void decide(Authentication auth, Object obj, Collection<ConfigAttribute> configs) throws AccessDeniedException,
			InsufficientAuthenticationException {
		if (configs == null) {
			return;
		}
		for (ConfigAttribute ca : configs) {
            String needRole = ((SecurityConfig) ca).getAttribute();
            for (GrantedAuthority ga : auth.getAuthorities()) {
                if (needRole.equals(ga.getAuthority())) { //ga is user's role.
                    return;
                }
            }
        }
        throw new AccessDeniedException("no right");
	}

	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

}
