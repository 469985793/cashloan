package com.xindaibao.cashloan.manage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tool.util.StringUtil;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.system.domain.SysRole;
import com.xindaibao.cashloan.system.domain.SysUser;
import com.xindaibao.cashloan.system.permission.annotation.RequiresPermission;
import com.xindaibao.cashloan.system.service.SysRoleService;
import com.xindaibao.cashloan.system.service.SysUserService;

/**
 * 角色Action
 * @version 1.0
 * @author
 * @created 2014年9月23日 下午1:45:50
 */
@Scope("prototype")
@Controller
public class SysRoleController extends BaseController {
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysUserService sysUserService;

	/**
	 * 获取所有角色列表
	 * @param request
	 * @param response
	 * @version 1.0
	 * @author
	 * @created 2014年10月15日
	 */
//	@RequestMapping(value = "/modules/system/getRoleList.htm")
	@RequestMapping(value = "/modules/manage/system/role/list.htm")
	@RequiresPermission(code = "modules:manage:system:role:list", name = "获取所有角色列表")
	public void list(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> res = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<>();
//		param.put("isDelete", "0");
		List<SysRole> sysRoleList = sysRoleService.getList(param);
		res.put(Constant.RESPONSE_DATA, sysRoleList);
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, res);
	}
	
	/**
	 * 角色删除（逻辑删除）
	 * @throws ServiceException
	 * @throws Exception
	 *             异常
	 */
//	@RequestMapping(value = "/modules/system/general/roleDelete.htm")
	@RequestMapping(value = "/modules/manage/system/role/delete.htm")
	@RequiresPermission(code = "modules:manage:system:role:delete", name = "角色删除")
	public void roleDelete(@RequestParam("key") Long id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 判断用户是否还在使用这个角色
		Map<String, Object> res = new HashMap<String, Object>();
		if (id == null) {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "角色不能为空");
			ServletUtils.writeToResponse(response, res);
			return;
		}
		Map<String, Object> param = new HashMap<>();
		param.put("roleId", id);
		param.put("delete", "0");
		int roleNum = sysUserService.queryRoleUserIsUse(param);
		if (roleNum >= 1) {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "角色有用户在使用，删除失败");
			ServletUtils.writeToResponse(response, res);
			return;
		}
		int result = sysRoleService.deleteRole(id);
		if(result > 0){
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除成功");
		}else{
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除失败");
		}
		ServletUtils.writeToResponse(response, res);
	}

	/**
	 * 获取系统用户列表
	 * @param response
	 * @param currentPage
	 * @param pageSize
	 * @throws Exception
	 */
//	@RequestMapping("/modules/system/getSysRoleList.htm")
	@RequestMapping(value = "/modules/manage/system/sysUsers/page.htm")
	@RequiresPermission(code = "modules:manage:system:sysUsers:page", name = "获取系统用户列表")
	public void pageSysUsers(HttpServletResponse response,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception{
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> reposedata = new HashMap<String, Object>();
		Page<SysRole> page = (Page<SysRole>) sysRoleService.getRolePageList(current,pageSize,data);
		reposedata.put(Constant.RESPONSE_DATA, page);
		reposedata.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		
		ServletUtils.writeToResponse(response, reposedata);
	}

	/**
	 * 添加或更新用户
	 * @param request
	 * @param response
	 * @param data
	 * @param status
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/system/role/save.htm")
	@RequiresPermission(code = "modules:manage:system:role:save", name = "添加或更新用户")
	public void saveOrUpdate(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "form") String data,
			@RequestParam(value = "status") String status) throws Exception{
		HashMap<String, Object> dataMap = JsonUtil.parse(data, HashMap.class);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		if ("create".equalsIgnoreCase(status)) {
			SysUser loginUser = this.getLoginUser(request);
			SysRole role = new SysRole();
			role.setAddTime(new Date());
			role.setAddUser(loginUser.getUserName());
			role.setUpdateTime(new Date());
			role.setUpdateUser(loginUser.getUserName());
			role.setName(dataMap.get("name") != null ? String.valueOf(dataMap.get("name")) :"");
			role.setNid(dataMap.get("nid") != null ? String.valueOf(dataMap.get("nid")) :"");
			role.setRemark(dataMap.get("remark") != null ? String.valueOf(dataMap.get("remark")) :"");
			int d=0;
			if(StringUtil.isNotBlank(dataMap.get("isDelete"))){
				d=(int) dataMap.get("isDelete");
			}
			role.setIsDelete((byte)d);
			long n = sysRoleService.addRole(role);
			if (n > 0) {
				responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
			} else {
				responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, "保存失败");
			}
		} else if ("update".equals(status)) {
			int total = sysRoleService.updateRole(dataMap);
			if (total > 0) {
				responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, "更新成功");
			} else {
				responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, "更新失败");
			}
		}
		ServletUtils.writeToResponse(response, responseMap);
	}
	
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "/modules/system/insertByMap.htm")
//	public void insertByMap(HttpServletRequest request, HttpServletResponse response,
//			@RequestParam(value = "data") String data) throws Exception{
//		Map<String, Object> returnMap = new HashMap<>();
//		HashMap<String, Object> dataMap = JsonUtil.parse(data, HashMap.class);
//		String[] keys = new String[dataMap.size()]; 
//        Set<String> sset = dataMap.keySet(); 
//        int i = 0; 
//        for (String os : sset) { 
//            keys[i++] = os; 
//        } 
//        Map<String, Object> role = new HashMap<String, Object>(); 
//        role.put("keys", keys); 
//        role.put("params", dataMap); 
//		long n = sysRoleService.insertByMap(role);
//		if (n > 0) {
//			returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
//			returnMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
//		} else {
//			returnMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
//			returnMap.put(Constant.RESPONSE_CODE_MSG, "保存失败");
//		}
//		ServletUtils.writeToResponse(response, returnMap);
//	}
	
	/**
	 * 查询用户所属角色
	 * @description
	 * @param response
	 * @param username
	 * @param password
	 * @param request
	 * @param session
	 * @throws Exception
	 * @author
	 * @return void
	 * @since  1.0.0
	 */
//	@RequestMapping(value = "/getByUserPassRolesList.htm")
	@RequestMapping(value = "/modules/manage/system/userRole/list.htm")
	@RequiresPermission(code = "modules:manage:system:userRole:list", name = "查询用户所属角色")
	public void listUserRoles(HttpServletResponse response,
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			HttpServletRequest request, HttpSession session
			)
			throws Exception {
		if (username != null && password != null) {
			
			List<Map<String, Object>> roles = sysRoleService
					.getByUserPassRolesList(username, password);

			Map<String, Object> rec = new HashMap<String, Object>();

			if (roles.size() > 0) {
				rec.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				rec.put(Constant.RESPONSE_CODE_MSG, roles);
				ServletUtils.writeToResponse(response, rec);
			} else {
				rec.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				rec.put(Constant.RESPONSE_CODE_MSG, "对不起，角色信息不对");
				ServletUtils.writeToResponse(response, rec);
			}
		}
	}

}
