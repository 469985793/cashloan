package com.xindaibao.cashloan.system.security.userdetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;

public class UserFunction implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -260181556678341867L;
	
	private Long id;
	
    private Collection<ConfigAttribute> roles;
    
    public UserFunction(Long id) {
        this.id = id;
        roles = new ArrayList<ConfigAttribute>();
    }

    public UserFunction(Long id, Collection<ConfigAttribute> roles) {
        this.id = id;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<ConfigAttribute> getRoles() {
        return roles;
    }

    public void setRoles(Collection<ConfigAttribute> roles) {
        this.roles = roles;
    }

    public void add(ConfigAttribute role) {
        roles.add(role);
    }
}
