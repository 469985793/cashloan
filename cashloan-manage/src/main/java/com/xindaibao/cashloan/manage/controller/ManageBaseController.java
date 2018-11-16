package com.xindaibao.cashloan.manage.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import tool.util.NumberUtil;
import tool.util.StringUtil;

import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.exception.BussinessException;
import com.xindaibao.cashloan.core.common.exception.ParamValideException;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.model.URLConfig;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.util.ValidateCode;
import com.xindaibao.cashloan.core.common.web.controller.AbstractController;
import com.xindaibao.cashloan.system.domain.SysRole;
import com.xindaibao.cashloan.system.domain.SysUser;
import com.xindaibao.cashloan.system.security.authentication.handler.SaveLoginInfoAuthenticationSuccessHandler;
import com.xindaibao.cashloan.system.security.userdetails.UserFunction;
import com.xindaibao.cashloan.system.security.userdetails.UserRole;
import com.xindaibao.cashloan.system.service.SysRoleService;
import com.xindaibao.cashloan.system.service.SysUserService;

/**
 * 
 * 基类action
 * @version 1.0
 * @author
 * @created 2014年9月23日 下午1:48:28
 */
@Controller
public abstract class ManageBaseController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(ManageBaseController.class);
	
	protected  HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;  

	@Autowired
	protected URLConfig mlmsAppServerConfig;
	@Resource
	protected SysUserService sysUserService;
	@Resource
    protected SysRoleService roleService;

    @ModelAttribute  
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;  
        this.response = response;  
        this.session = request.getSession();  
    }  

	/**
	 * 初始化绑定
	 * 
	 * @param binder
	 */
	@InitBinder
	protected final void initBinderInternal(WebDataBinder binder) {
		registerDefaultCustomDateEditor(binder);
		registerDefaultCustomNumberEditor(binder);
		initBinder(binder);
	}

	private void registerDefaultCustomNumberEditor(WebDataBinder binder) {
		// 注册双精度数字格式化类型: #0.00
		NumberFormat numberFormat = new DecimalFormat("#0.00");
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(
				Double.class, numberFormat, true));
	}

	protected void registerDefaultCustomDateEditor(WebDataBinder binder) {
		// 注册默认的日期格式化类型: yyyy-MM-dd
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	/**
	 * 提供子类初始化表单, 子类如果要调用请重写该方法
	 * 
	 * @param binder
	 */
	protected void initBinder(WebDataBinder binder) {
	}

	/**
	 * 获取当前登录用户的用户
	 * 
	 * @param request
	 * @return
	 * @see SaveLoginInfoAuthenticationSuccessHandler
	 */
	protected SysUser getLoginUser(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute("SysUser");
		if(obj != null){
			return (SysUser) obj;
		}
		return null;
	}

	/**
	 * 获得当前登录用户信息
	 * 
	 * @return SystemUser
	 * @throws ServiceException
	 */
	protected SysUser getSysUser() throws ServiceException {
		// 增加用户登录判断
		UserDetails user = (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		SysUser sysUser = sysUserService.getUserByUserName(user.getUsername());
		return sysUser;
	}

	/**
	 * 设置一个Token 。 放置重复提交<br/>
	 * 转向页面的时候根据指定的tokenName名字 在session中存储一个值字符的"true",提交页面时在处理接受的方法中调用
	 * isTonten方法判断session中的值
	 * 
	 * @param tokenName
	 *            指定的Token名称
	 * @param request
	 */
	protected void setToken(String tokenName, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			session = request.getSession();
		}
		session.setAttribute(tokenName, "true");
	}

	/**
	 * 判断session中Token的值<br/>
	 * 根据指定的tokenName获取session中对应的值，如果该值符合条件则改变session中的值并返会"" <br/>
	 * 如果不符合条件则返回 提示信息
	 * 
	 * @param tokenName
	 *            request
	 * @param request
	 * @return 符合条件返回true， 否则返回false
	 */
	protected String isToken(String tokenName, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			session = request.getSession();
		}
		String tokenValue = (String) session.getAttribute(tokenName);
		String paramValue = (String) request.getParameter(tokenName);

		// 参数、session中都没用token值提示错误
		if (StringUtil.isBlank(paramValue) && StringUtil.isBlank(tokenValue)) {
			return "会话Token未设定！";
		} else if (StringUtil.isBlank(paramValue)
				&& !StringUtil.isBlank(tokenValue)) {
			return "表单Token未设定！";
		} else if (paramValue.equals(tokenValue)
				&& !StringUtil.isBlank(tokenValue) && "true".equals(tokenValue)) { // session中有token,防止重复提交检查
			session.setAttribute(tokenName, "false");
			return "";
		} else {
			return "请勿重复提交！";
		}
	}

	protected void message(HttpServletResponse response) throws IOException {
		this.message(response, "", true);
	}

	/**
	 * 消息处理方法
	 * 
	 * @param msg
	 *            消息
	 */
	protected void message(HttpServletResponse response, String msg)
			throws IOException {
		this.message(response, msg, true);
	}

	/**
	 * 消息处理方法重载
	 * 
	 * @param msg
	 *            消息
	 */
	protected void message(HttpServletResponse response, String msg,
			boolean result) throws IOException {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("success", result);
		data.put("msg", msg);
		this.jsonResponse(response, data);
	}

	protected Integer paramInt(HttpServletRequest request, String str) {
		return NumberUtil.getInt(request.getParameter(str));
	}

	protected Long paramLong(HttpServletRequest request, String str) {
		return NumberUtil.getLong(request.getParameter(str));
	}

	protected String paramString(HttpServletRequest request, String str) {
		return StringUtil.isNull(request.getParameter(str));
	}

	protected String redirect(String url) {
		return "redirect:" + mlmsAppServerConfig + url;
	}

	protected String redirectLogin() {
		return redirect("/modules/login.htm");
	}

	protected String success() {
		return redirect("/success.htm");
	}

	protected String error() {
		return redirect("/error.htm");
	}

	protected String success(ModelMap model) {
		return "success";
	}

	protected String error(ModelMap model) {
		return "error";
	}

	/**
	 * 根据URL地址判断是否有访问的权限
	 * 
	 * @param url
	 *            访问的URL
	 * @return 是否通过
	 */
	protected boolean isAllowAccess(String url) {
		Map<String, UserFunction> functionMap = ((UserRole) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal())
				.getFunctionMap();
		if (functionMap.containsKey(url)) {
			return true;
		}
		return false;
	}
	
	/**
	 * shiro 异常处理
	 * @param e
	 * @param response
	 */
	@ExceptionHandler({AuthorizationException.class})
	public void authorizationExceptionHandler(AuthorizationException e, HttpServletResponse response) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.PERM_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, "对不起，您没有该权限");
		ServletUtils.writeToResponse(response, res);
	}
	
	
	@ExceptionHandler({Exception.class})
	public void exceptionHandler(Exception e, HttpServletResponse response) {
		 Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, "400");
		res.put(Constant.RESPONSE_CODE_MSG, "系统出错了，请检查参数是否正确");
		logger.error("Exception:", e);
		ServletUtils.writeToResponse(response, res);
	}
	
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public void notNullException(MethodArgumentNotValidException e, HttpServletResponse response) {
        Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, "400");
		BindingResult bindingResult = e.getBindingResult();
		if (bindingResult.hasErrors()) {
			String msg = bindingResult.getFieldError().getDefaultMessage();
			res.put(Constant.RESPONSE_CODE_MSG, msg);
		} else {
			res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
		}
		logger.error("MethodArgumentNotValidException:", e);
		ServletUtils.writeToResponse(response, res);
	}
	
	@ExceptionHandler({ParamValideException.class})
	public void paramValideException(ParamValideException e, HttpServletResponse response) {
        Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, "404");
		res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
		logger.error("MethodArgumentNotValidException:", e);
		ServletUtils.writeToResponse(response, res);
	}
	
	@ExceptionHandler({ ServiceException.class })
	public void excptionDispose(ServiceException e, HttpServletResponse response) {
        Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, e.getCode());
		res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());

		logger.error("ServiceException:", e);

		ServletUtils.writeToResponse(response, res);
	}

    @ExceptionHandler({RuntimeException.class})
    public void runtimeExcptionDispose(RuntimeException e, HttpServletResponse response) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
        res.put(Constant.RESPONSE_CODE_MSG, "系统出错了");

		logger.error("RuntimeException", e);

        ServletUtils.writeToResponse(response, res);
    }
    
    
    @ExceptionHandler({BussinessException.class})
    public void bussinessException(BussinessException e, HttpServletResponse response) {
        Map<String, Object> res = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(e.getCode())) {
        	res.put(Constant.RESPONSE_CODE, e.getCode());
        } else {
        	 res.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
        }
        res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
	
		logger.error("BussinessException", e);

        ServletUtils.writeToResponse(response, res);
    }
    
    @ExceptionHandler({BindException.class})
    public void bindException(BindException e, HttpServletResponse response) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
        res.put(Constant.RESPONSE_CODE_MSG, "数据保存失败，请稍后重试");
    
		logger.error("参数校验失败"+ e.getFieldError().getDefaultMessage(), e);

        ServletUtils.writeToResponse(response, res);
    }
    
	/**
	 * 从自定义session 中获取角色
	 * 
	 * @param request
	 * @return
	 */
	public List<Long> getRole(HttpServletRequest request) {

		List<Long> roles = new ArrayList<Long>();
		HttpSession session = request.getSession();
		Long role = (Long) session.getAttribute(Constant.ROLEID);
		roles.add(role);

		return roles;

	}

    public SysRole getRoleForLoginUser(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        Long roleId = (Long) session.getAttribute(Constant.ROLEID);
        if (null==roleId) {
			return new SysRole();
		}
        SysRole role = roleService.getRoleById(roleId);

        return role;

    }
    
    public String getLoginUserName(HttpServletRequest request) throws ServiceException {
    	SysUser loginUser = getLoginUser(request);

        return loginUser.getUserName();

    }
    public List<String> getCoverdOffices(SysUser loginUser) {
        ArrayList<String> list = new ArrayList<String>();
        String coverdOfficeStr = loginUser.getOfficeOver();
        if(!org.springframework.util.StringUtils.hasLength(coverdOfficeStr)){
            coverdOfficeStr="null";
        }
        StringTokenizer stringTokenizer = new StringTokenizer(coverdOfficeStr, ",");
        while(stringTokenizer.hasMoreElements()) {
            list.add(stringTokenizer.nextToken());
        }
        return list;
    }

	public Map<String, Object> getRequestFormMap(HttpServletRequest request) throws UnsupportedEncodingException{
        String str = getRequestParams(request);
		Map<String, Object> params = new HashMap<String, Object>();
		Enumeration<String> paramNames = request.getParameterNames();  
        while (paramNames.hasMoreElements()) {  
            String paramName = (String) paramNames.nextElement();  
  
            String[] paramValues = request.getParameterValues(paramName);  
            if (paramValues.length == 1) {  
                String paramValue = paramValues[0];  
                if (paramValue.length() != 0) {  
                	params.put(paramName, paramValue);  
                }  
            }  
        }  
		if(StringUtil.isNotBlank(str) && params.size()==0){
			String str1 = java.net.URLDecoder.decode(str, "UTF-8");
			String[] strs = str.split("name=");
			String[] strs1 = str1.split("&");
			for (int i = 1; i < strs.length; i++) {
				String temp = strs[i].substring(0, strs[i].indexOf("------"));
				int index = temp.indexOf("\"", 1);
				index = index + 1;
				String key = temp.substring(0, index);
				String value = temp.substring(index, temp.length());
				params.put(key, value);
			}
			for (int i = 0; i < strs1.length; i++) {
				String[] temp = strs1[i].split("=");
				params.put(temp[0], temp[1]);
			}
		}
        return params;
    }
    
	/**
	 * 使用 request.getInputStream()读取回调数据流
	 * 
	 * @param request
	 * @return
	 */
	public String getRequestParams(HttpServletRequest request) {
		String params = "";
		try {
			request.setCharacterEncoding("UTF-8");
			InputStream in = request.getInputStream();
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			params = sb.toString();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return params;
	}
	
	/**
	 * 生成图片验证码
	 * @throws Exception
	 */
	protected void generateImgCode() throws Exception{
	    // 设置响应的类型格式为图片格式  
	    response.setContentType("image/jpeg");
	    //禁止图像缓存。  
	    response.setHeader("Pragma", "no-cache");
	    response.setHeader("Cache-Control", "no-cache");
	    response.setDateHeader("Expires", 0);
	    
	    HttpSession session = request.getSession();
	  
	    ValidateCode vCode = new ValidateCode(120,40,4,50);
	    session.setAttribute("code", vCode.getCode());
	    vCode.write(response.getOutputStream());
	    response.getOutputStream().flush();
	}
}
