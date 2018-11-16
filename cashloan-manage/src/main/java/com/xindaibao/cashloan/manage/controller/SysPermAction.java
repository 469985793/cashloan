package com.xindaibao.cashloan.manage.controller;

import javax.annotation.Resource;

import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.system.service.SysPermService;
import com.xindaibao.cashloan.system.service.SysUserRoleService;

/** 
 * @author
 * @date 创建时间：2016-12-2 下午7:09:24 
 * @version 1.0 * @parameter  
 * @since  
 * @return  
 */
public class SysPermAction extends BaseController{

	@Resource
	private SysPermService sysPermService;
	@Resource
	private SysUserRoleService sysUserRoleService;
	
//	/**
//	 * 查询所有角色权限
//	 * @param request
//	 * @param response
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/modules/system/getPermList.htm")
//	public void getPermList(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		Map<String, Object> res = new HashMap<String, Object>();
//		List<Map<String, Object>> sysPermList = sysPermService.fetchAll();
//		res.put("data", sysPermList);
//		res.put("totalCount", sysPermList.size());
//		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
//		res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
//		ServletUtils.writeToResponse(response, res);
//	}
//	
//	/**
//	 * 查询用户已拥有权限
//	 * @param request
//	 * @param response
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/modules/system/getRolePermList.htm")
//	public void getRolePermList(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		Map<String, Object> res = new HashMap<String, Object>();
//		SysUser user = this.getLoginUser(request);
//		List<SysPerm> rolePermList = sysPermService.listByUserName(user.getUserName());
//		res.put("data", rolePermList);
//		res.put("totalCount", rolePermList.size());
//		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
//		res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
//		ServletUtils.writeToResponse(response, res);
//	}
}
