package com.xindaibao.cashloan.system.security.authentication.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.system.constant.SystemConstant;
import com.xindaibao.cashloan.system.domain.SysRole;
import com.xindaibao.cashloan.system.domain.SysUser;
import com.xindaibao.cashloan.system.model.MenuModel;
import com.xindaibao.cashloan.system.security.authentication.encoding.PasswordEncoder;
import com.xindaibao.cashloan.system.security.userdetails.UserFunction;
import com.xindaibao.cashloan.system.security.userdetails.UserRole;
import com.xindaibao.cashloan.system.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import tool.util.StringUtil;

import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.system.service.SysMenuService;
import com.xindaibao.cashloan.system.service.SysUserService;

/**
 * 认证管理器: 用户角色信息Provider
 * @author
 */
@SuppressWarnings("deprecation")
@Service
public class UserRoleDetailProvider implements UserDetailsService {
	
	public static final Logger logger = LoggerFactory.getLogger(UserRoleDetailProvider.class);

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysMenuService sysMenuService;
	
	private PasswordEncoder passwordEncoder;
	
	private String systemPasswordInitialization;
	
	/**
     * 根据用户名加载用户和角色信息
     * 
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     * @throws DataAccessException
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException ,DataAccessException{
		try {    
			// 根据登陆用户名获得用户信息
			SysUser op = sysUserService.getUserByUserName(userName);
			// 用户信息为空则抛出默认异常
			if (op == null) {
				throw new UsernameNotFoundException(userName + " is not exists");
			}
			// 根据用户Id获得用户角色列表
			List<SysRole> roleList = sysRoleService.getRoleListByUserId(op.getId());
			 // 用户授权集合
	        Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
	        // 设置默认权限
	        auths.add(new GrantedAuthorityImpl(SystemConstant.ROLE_DEFAULT));
	        // 用户资源映射<资源名称, 资源属性集合>
	        Map<String, UserFunction> functionMap = null;
	        if (roleList != null && !roleList.isEmpty()) {
	    	    List<Long> roleIdList = new ArrayList<Long>();
	            // 转换用户的角色为用户授权, 并记录用户角色Id列表
	        	for (SysRole role : roleList) {
	        		GrantedAuthorityImpl auth = new GrantedAuthorityImpl(role.getId().toString());
	                auths.add(auth);
	                roleIdList.add(role.getId());
	        	}
	        	// 根据用户角色Id列表获得该角色拥有的系统功能列表
	        	List<MenuModel> menuList = sysMenuService.getMenuListByRoleIds(roleIdList);
	        	if (menuList != null && !menuList.isEmpty()) {
	        		functionMap = new HashMap<String, UserFunction>();
	                // 转换系统功能为用户资源
	        		for (MenuModel menu : menuList) {
	        			String href = menu.getHref();
	        			if (StringUtil.isNotBlank(href)) {
	        				String[] urls = StringUtils.commaDelimitedListToStringArray(href);
	        				for (String url : urls) {
	                            url = StringUtil.trim(url);
	                            if (StringUtil.isNotBlank(url)) {
		                            // 设置用户资源映射, key为资源对应的名称 (即系统功能url), value为 资源属性集合 (即拥有该系统功能的角色)
	                        		if (!functionMap.containsKey(url)) {
		                                UserFunction userFunction = new UserFunction(menu.getId());
		                                ConfigAttribute ca = new SecurityConfig(menu.getRoleId().toString());
		                                userFunction.add(ca);
		                                functionMap.put(url, userFunction);
		                            } else {
		                            	functionMap.get(url).add(new SecurityConfig(menu.getRoleId().toString()));
		                            }
	                            }
	                        }
	        			}
					}
	        	}
	        	 // 创建UserDetail的实现并返回该实现
	            UserRole userRole = new UserRole(userName, this.getPassword(op), true,
	            		true, true, true, auths, functionMap);
	            return userRole;
	        }
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	
	/**
     * 获取用户密码, 如果用户为mlms并且为首次登陆, 则采用初始化密码
     * 
     * @param op 用户
     * @return String
	 * @throws ServiceException 
     */
    private String getPassword(SysUser op) throws ServiceException {
        String password = op.getPassword();
        if (SystemConstant.SYSTEM_LOGIN_NAME.equals(op.getUserName()) && op.getLoginTime() == null) {
            if (StringUtil.isNotBlank(systemPasswordInitialization)) {
                password = passwordEncoder.encodePassword(systemPasswordInitialization);
            } else {
                password = passwordEncoder.encodePassword(SystemConstant.SYSTEM_PASSWORD_DEFAULT);
            }
            op.setPassword(password);
			sysUserService.editUserPassWord(op);
        }
        return password;
    }


	public SysUserService getSysUserService() {
		return sysUserService;
	}


	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}


	public SysRoleService getSysRoleService() {
		return sysRoleService;
	}


	public void setSysRoleService(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}


	public SysMenuService getSysMenuService() {
		return sysMenuService;
	}


	public void setSysMenuService(SysMenuService sysMenuService) {
		this.sysMenuService = sysMenuService;
	}


	public String getSystemPasswordInitialization() {
		return systemPasswordInitialization;
	}


	public void setSystemPasswordInitialization(String systemPasswordInitialization) {
		this.systemPasswordInitialization = systemPasswordInitialization;
	}
	
}
