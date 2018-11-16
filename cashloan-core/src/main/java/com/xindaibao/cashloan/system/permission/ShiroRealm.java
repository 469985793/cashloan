package com.xindaibao.cashloan.system.permission;


import java.util.List;

import javax.annotation.Resource;

import com.xindaibao.cashloan.system.domain.SysUser;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.system.domain.SysPerm;
import com.xindaibao.cashloan.system.service.SysPermService;
import com.xindaibao.cashloan.system.service.SysUserService;

public class ShiroRealm extends AuthorizingRealm {

	private static final Logger logger = Logger.getLogger(ShiroRealm.class);
	
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysPermService sysPermService;
	
	
    /** 
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用. 
     */  
	@Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
  
		logger.info("授权认证：" + principals.getRealmNames());  
		String userName = (String)principals.getPrimaryPrincipal();
       List<SysPerm> perms = sysPermService.listByUserName(userName);
  
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
       for (SysPerm perm : perms) {
       	//基于Permission的权限信息  
        	info.addStringPermission(perm.getCode());  
		}
        return info;  
    }  

	/**
	 * 验证当前登录的Subject 用户登录验证源（shiro调用此方法执行认证）
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

		// 获取基于用户名和密码的令牌
		// user.login(token) 间接调用
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		// 从数据库中查询用户用信息
		SysUser user;
		try {
			user = sysUserService.getUserByUserName(token.getUsername());
			
			if (user == null) {
				throw new UnknownAccountException();// 没找到帐号
			} else if (user.getStatus() != 0) {
				throw new LockedAccountException(); // 帐号锁定
			} else {
//				String password = String.valueOf((char[]) token.getCredentials());//得到密码  
//				password = 
//				if(user.getPassword().equals(password)){
//					throw new IncorrectCredentialsException(); //密码错误
//				}else{
					Subject subject = SecurityUtils.getSubject();
					Session session = subject.getSession();
					session.setAttribute("SysUser", user);
					return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
//				}
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			throw new ExpiredCredentialsException();// 没找到帐号
		}

	}

}
