package com.xindaibao.cashloan.manage.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.util.StringUtil;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.system.constant.SystemConstant;
import com.xindaibao.cashloan.system.domain.SysRole;
import com.xindaibao.cashloan.system.domain.SysUser;
import com.xindaibao.cashloan.system.permission.annotation.RequiresPermission;
import com.xindaibao.cashloan.system.security.authentication.encoding.PasswordEncoder;
import com.xindaibao.cashloan.system.service.SysRoleService;
import com.xindaibao.cashloan.system.service.SysUserService;


/**
 * 用户action
 * @version 1.0
 * @author
 * @created 2014年9月23日 下午1:53:06
 */
@Scope("prototype")
@Controller
public class SysUserController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);
	
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private PasswordEncoder passwordEncoder;// 密码加密
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @param user
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/modules/system/modifyPassword.htm")
	@RequiresPermission(code = "modules:system:modifyPassword",name = "修改密码")
	public void modifyPassword(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user", required = false) String user) throws Exception{
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> userMap = JsonUtil.parse(user, Map.class);
		String oldPassword = passwordEncoder.encodePassword(String.valueOf(userMap.get("oldPassword")));
		String newPassword1 = passwordEncoder.encodePassword(String.valueOf(userMap.get("newPassword")));
		String newPassword2 = passwordEncoder.encodePassword(String.valueOf(userMap.get("newPassword2")));
		SysUser sysUser = this.getLoginUser(request);
		
		logger.debug("原始密码"+sysUser.getPassword());
		logger.debug("旧密码"+oldPassword);
		
		if (!sysUser.getPassword().equals(oldPassword)) {
			responseMap.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, "原密码输入不正确");
		} else if (!newPassword1.equals(newPassword2)) {
			responseMap.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, "两个新密码不一致");
		} else if (oldPassword.equals(newPassword1)) {
			responseMap.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, "新密码不能和旧密码相同");
		} else {
			sysUser.setPassword(newPassword1);// 密码加密
			sysUserService.editUserPassWord(sysUser);
			responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, "密码修改成功");
		}
		ServletUtils.writeToResponse(response, responseMap);
	}
	
	/**
	 * 增加用户
	 * @param request
	 * @param response
	 * @param user
	 * @param status
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/modules/manage/system/user/save.htm")
	@RequiresPermission(code = "modules:manage:system:user:save",name = "增加用户")
	public void save(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user", required = false) String user,
			@RequestParam(value = "status", required = false) String status) throws Exception{
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> userMap = JsonUtil.parse(user, Map.class);
		// 获取当前登录用户信息
		SysUser userinfo = this.getLoginUser(request);
		if(null!=userinfo){
			String loginUserName = userinfo.getName();
			Date curDate = new Date();
			if ("create".equalsIgnoreCase(status)) {// 新建
				SysUser sysUser = new SysUser();
				sysUser.setName(String.valueOf(userMap.get("name")));// 真实姓名
				sysUser.setJobNumber(String.valueOf(userMap.get("jobNumber")));// 工编号
				// 用户名验证
				String userName = String.valueOf(userMap.get("userName"));
				SysUser user2 = sysUserService.getUserByUserName(userName);
				if (null != user2) {
					responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
					responseMap.put(Constant.RESPONSE_CODE_MSG, "用户名已存在，不能重复");
				} else {
					sysUser.setUserName(userName); // 登录名
					Map<String, Object> temp = new HashMap<String, Object>();
					temp.put("parentId", 0);
					String email = String.valueOf(userMap.get("email"));
					if (StringUtil.isMail(email)) {
						sysUser.setEmail(email);
					}
					if(userMap.get("phone") != null){
						sysUser.setPhone(String.valueOf(userMap.get("phone")));
					}
					if(userMap.get("remark") != null){
						sysUser.setRemark(String.valueOf(userMap.get("remark")));
					}
					String mobile = String.valueOf(userMap.get("mobile"));
					if (StringUtil.isPhone(mobile)) {
						sysUser.setMobile(mobile);
					}
					sysUser.setAddTime(curDate);
					sysUser.setAddUser(loginUserName);
					sysUser.setUpdateTime(curDate);
					sysUser.setUpdateUser(loginUserName);
					sysUser.setPassword(passwordEncoder.encodePassword(SystemConstant.SYSTEM_PASSWORD_DEFAULT)); // 账号初始密码
					sysUser.setStatus(SystemConstant.USER_STATUS_NORMAL); // 用户状态：正常
					if (!StringUtil.isBlank((String)userMap.get("officeOver"))) {
						sysUser.setOfficeOver(String.valueOf(userMap.get("officeOver")));
					}
					
					userMap.put("position", 0);
					sysUserService.addUser(sysUser, String.valueOf(userMap.get("roleId")));// 增加用户
					responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
					responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
				}
			} else if ("update".equalsIgnoreCase(status)) {// 更新
				Map<String, Object> temp = new HashMap<String, Object>();
				temp.put("parentId", 0);
				if (StringUtil.isNotBlank((String)userMap.get("officeOver"))) {
					userMap.put("officeOver", String.valueOf(userMap.get("officeOver")));
				}
			
				if (StringUtil.isBlank(String.valueOf(userMap.get("position")))) {
					userMap.put("position", 0);
				}
				
				SysUser updateUser = getLoginUser(request);
				userMap.put("updateUser", updateUser.getUserName());
				boolean istrue = sysUserService.updateSysUserById(userMap);// 更新用户信息及对应的角色信息
				if (istrue) {
					responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
					responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
				}
			} else {
				responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
			}
		}else {
			responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG,"登录过期请重新登录");
		}
		ServletUtils.writeToResponse(response, responseMap);
	}
	
	/**
	 * 用户修改 -- 锁定及解锁 密码重置
	 * @param request
	 * @param response
	 * @param status
	 */
	@RequestMapping("/modules/manage/system/user/update.htm")
	@RequiresPermission(code = "modules:manage:system:user:update",name = "用户修改")
	public void update(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ids[]", required = false) String ids[],
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "password",required = false) String password) throws Exception{
		Map<String, Object> responseMap = new HashMap<String, Object>();
		int successcount = 0;
		for (String id : ids) {
			long userid = Long.parseLong(id);
			SysUser sysUser = sysUserService.getUserById(userid);
			if(sysUser != null){
				if ("lock".equals(status)) {
					sysUser.setStatus((byte)1);
				} else if ("unlock".equals(status)) {
					sysUser.setStatus((byte)0);
				} else if ("editpassword".equals(status)) {
					sysUser.setPassword(passwordEncoder.encodePassword(password));
				}
				sysUser.setUpdateTime(new Date());
				int count = sysUserService.userUpdate(sysUser);
				if(count > 0){
					successcount ++;
				}
			}
		}
		if (successcount == ids.length) {
			responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, "修改成功");
		} else {
			responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, "修改失败");
		}
		ServletUtils.writeToResponse(response, responseMap);
	}
	
	/**
	 * 获取用户列表
	 * @param request
	 * @param response
	 * @param user
	 * @param currentPage
	 * @param pageSize
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/modules/manage/system/user/list.htm")
	@RequiresPermission(code = "modules:manage:system:user:list",name = "获取用户列表")
	public void list(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user", required = false) String user,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception{
		Map<String, Object> reposedata = new HashMap<String, Object>();
		Map<String, Object> paramap = JsonUtil.parse(user, Map.class);
		Page<Map<String, Object>> pages = sysUserService.getUserPageList(current,pageSize,paramap);
		reposedata.put(Constant.RESPONSE_DATA, pages);
		reposedata.put(Constant.RESPONSE_DATA_PAGE, new RdPage(pages));
		reposedata.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		reposedata.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, reposedata);
	}
	
	/**
	 * 获取头部信息 可以js缓存优化 后期处理
	 * @param response
	 * @param request
	 */
	@RequestMapping("/modules/manage/system/user/find.htm")
	@RequiresPermission(code = "modules:manage:system:user:find",name = "登录用户查询")
	public void findUser(HttpServletResponse response, HttpServletRequest request) throws Exception{
		Map<String, Object> responsemap = new HashMap<String, Object>();
		SysUser sysUser = this.getLoginUser(request);
		if (null==sysUser) {
			response.sendRedirect("/dev/index.html");
			ServletUtils.writeToResponse(response, responsemap);
			return;
		}
		List<SysRole> roleList = sysRoleService.getRoleListByUserId(sysUser.getId());
		responsemap.put("name", sysUser.getName());
		responsemap.put("roleList", roleList);
		responsemap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		responsemap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, responsemap);
	}
	
	/**
	 * @description 根据角色Id查找用户
	 * @param response
	 * @param request
	 * @param session
	 * @param roleId
	 * @throws Exception
	 * @author
	 * @return void
	 * @since  1.0.0
	 */
	@RequestMapping("/modules/manage/system/user/info/find.htm")
	@RequiresPermission(code = "modules:manage:system:user:info:find",name = "查找用户")
	public void findUserInfo(
			HttpServletResponse response,
			HttpServletRequest request, 
			HttpSession session,
			@RequestParam(value = "roleName") String roleName)throws Exception {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleName", roleName);
		
		String officeOver = getLoginUser(request).getOfficeOver();
		params.put("officeOver", Arrays.asList(officeOver.split(",")));
		
		List<Map<String,Object>> users = sysUserService.getUserInfo(params);
		
		responseMap.put("data", users);
		responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, responseMap);
	}
}
