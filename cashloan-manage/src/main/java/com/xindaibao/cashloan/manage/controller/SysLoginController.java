package com.xindaibao.cashloan.manage.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.exception.ImgCodeException;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.exception.SysAccessCodeException;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.util.StringUtil;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.system.domain.SysAccessCode;
import com.xindaibao.cashloan.system.domain.SysRole;
import com.xindaibao.cashloan.system.domain.SysUser;
import com.xindaibao.cashloan.system.domain.SysUserRole;
import com.xindaibao.cashloan.system.security.authentication.encoding.PasswordEncoder;
import com.xindaibao.cashloan.system.service.SysAccessCodeService;
import com.xindaibao.cashloan.system.service.SysRoleService;
import com.xindaibao.cashloan.system.service.SysUserRoleService;
import com.xindaibao.cashloan.system.service.SysUserService;

/**
 * 
 * 登陆处理Action, 实际登陆处理交由Spring Security框架, 该Action的作用仅仅为辅助
 * 
 * @version 1.0
 * @author
 * @created 2014年9月23日 下午2:15:42
 */
@Scope("prototype")
@Controller
public class SysLoginController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(SysLoginController.class);

	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private AuthenticationManager authenticationManager;
	@Resource
	private PasswordEncoder passwordEncoder;// 密码加密
	@Resource
	private SysUserRoleService sysUserRoleService;
	@Resource
	private SysAccessCodeService sysAccessCodeService;

	/**
	 * 登陆处理
	 * 
	 * @param error
	 * @param model
	 */
	@RequestMapping("/login.htm")
	public void login(HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.sendRedirect("/dev/index.html");
	}

	@RequestMapping("/index.htm")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/system/user/login.htm")
	public void loginAjax(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "accessCode", required = false) String accessCode,
			HttpServletResponse response,
			HttpServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
//			Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
//			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			//shiro
			Subject user = SecurityUtils.getSubject();
			password = passwordEncoder.encodePassword(String.valueOf(password));
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			token.setRememberMe(true);
			user.login(token);
//			SecurityContextHolder.getContext().setAuthentication(authentication);
			session.setAttribute("SPRING_SECURITY_CONTEXT",SecurityContextHolder.getContext());
			SysUser sysUser = (SysUser) user.getSession().getAttribute("SysUser");
			String accessCodeAble = Global.getValue("access_code_able");// 是否启用访问码，10启用，20关闭
			if(accessCodeAble.equals("10")){
				validateAccessCode(sysUser,accessCode);
				session.setAttribute(Constant.ACCESSCODE,accessCode);	
			}
			//图片验证码校验
			checkImgCode(request.getParameter("code"),session.getAttribute("code"));
			
			session.setAttribute("SysUser", sysUser);
			
			List<SysUserRole> list = sysUserRoleService.getSysUserRoleList(sysUser.getId());
			if(list != null && list.size() > 0) {
				session.setAttribute(Constant.ROLEID, list.get(0).getRoleId());
			} else {
				throw new UnknownAccountException("未找到该账号对应的角色");
			}
			
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		} catch (SysAccessCodeException ex){
			logger.error("访问码无效", ex);
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "登录失败，访问码无效");
		} catch (IncorrectCredentialsException ex){
			logger.error("密码错误", ex);
			res.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "密码错误请重新输入");
		} catch (LockedAccountException ex) {
			logger.error("该用户已锁定", ex);
			res.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "该用户已锁定，请联系管理员！");
		} catch (AuthenticationException ex) {
			logger.error("登录失败", ex);
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "登录失败");
		} catch (ExpiredCredentialsException ex) {
			logger.error(ex.getMessage(), ex);
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, ex.getMessage());
		} catch (UnknownAccountException ex){
			logger.error(ex.getMessage(), ex);
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "账号不存在请核对后重新输入");
		} catch (ImgCodeException ex){
			logger.error(ex.getMessage(), ex);
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "图片验证码错误");
		}  
		
		ServletUtils.writeToResponse(response, res);

	}
	
	private void checkImgCode(String code, Object sessionCode) {
		if (StringUtil.isBlank(code)||code.length()!=4||!code.equals(sessionCode)) {
			throw new ImgCodeException("图片验证码错误");
		}
	}

	/**
	 * 生成图片验证码
	 * @param search
	 * @throws Exception
	 */
	@RequestMapping(value = "/system/user/imgCode/generate.htm")
	public void generate() throws Exception {
		super.generateImgCode();
	}
	
	private void validateAccessCode(SysUser user, String code) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sysUserId",user.getId());
		map.put("code",code);
		SysAccessCode sysAccessCode = sysAccessCodeService.findSysAccessCode(map);
		if(sysAccessCode == null 
				|| sysAccessCode.getExceedTime().getTime() < new Date().getTime()
				|| sysAccessCode.getState().equals("20")) {
			throw new SysAccessCodeException("访问码无效");
		}
				
	}
	

	/**
	 * 切换角色
	 * @description
	 * @param role
	 * @param response
	 * @param request
	 * @author
	 * @return void
	 * @throws IOException
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/system/user/switch/role.htm")
	public void changeLoginajax(@RequestParam(value = "role", required = false) String role,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		HttpSession session = request.getSession(true);
		session.setAttribute(Constant.ROLEID, Long.valueOf(role.trim()));
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		ServletUtils.writeToResponse(response, res);
	}

	public void validateRole(SysUser user, Long roleid) throws ServiceException {
		List<SysUserRole> list = sysUserRoleService.getSysUserRoleList(user.getId());
		for (SysUserRole role : list) {
			if (role.getRoleId().equals(roleid))
				return;
		}
		SysRole role = sysRoleService.getRoleById(roleid);
		throw new ServiceException(user.getName() + "不包含[" + role.getName() + "]这个角色");
	}

	@RequestMapping(value = "/login2.htm")
	public void sessionout(HttpServletResponse response) {

		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, "登录失败");

		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping(value = "/system/user/logout.htm")
	public void logout() {
	/*	
	 * session.removeAttribute("SysUser");
		session.removeAttribute(Constant.ROLEID);
		session.removeAttribute("SPRING_SECURITY_CONTEXT");
	    session.invalidate();
	    */
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, "成功");
		ServletUtils.writeToResponse(response, res);
	}
	
	
}
