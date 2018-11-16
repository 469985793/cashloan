package com.xindaibao.cashloan.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tool.util.StringUtil;

import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.exception.BussinessException;
import com.xindaibao.cashloan.core.common.exception.PersistentDataException;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.ListUtil;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.util.Tree;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.system.domain.SysMenu;
import com.xindaibao.cashloan.system.domain.SysPerm;
import com.xindaibao.cashloan.system.domain.SysRole;
import com.xindaibao.cashloan.system.domain.SysUser;
import com.xindaibao.cashloan.system.model.SysMenuCheck;
import com.xindaibao.cashloan.system.permission.annotation.RequiresPermission;
import com.xindaibao.cashloan.system.service.SysMenuService;
import com.xindaibao.cashloan.system.service.SysPermService;
import com.xindaibao.cashloan.system.service.SysRolePermService;

@Scope("prototype")
@Controller
public class SysMenuController extends BaseController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SysMenuController.class);

	private static final int CHOISETREENOCHECKED = 2;

	private static final int CHOISOTTHER = 3;

	private List<Map<String, Object>> roleIdMenuList;

	@Resource
	private SysMenuService sysMenuService;
	@Resource
	private SysPermService sysPermService;
	@Resource
	private SysRolePermService sysRolePermService;

	/**
	 * 修改菜单时加载出原有菜单数据
	 * 
	 * @param request
	 * @param response
	 * @author
	 * @created 2015年12月30日
	 */
//	@RequestMapping(value = "/modules/system/getMenuInfoById.htm")
	@RequestMapping(value = "/modules/manage/system/menu/find.htm")
	@RequiresPermission(code = "modules:manage:system:menu:find", name = "加载出原有菜单数据")
	public void find(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true) Long id) throws Exception {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		long parentId = sysMenuService.menuFind(id).getParentId();// 父节点
		if (parentId == 0) {// 无父节点
			responseMap.put("data", sysMenuService.menuFind(id));
		} else {
			responseMap.put("data", null);
		}
		responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, responseMap);
	}

	/**
	 * 根据角色查询菜单
	 * @param request
	 * @param response
	 * @param sysType
	 * @throws Exception
	 */
//	@RequestMapping("/modules/system/sconfig/fetchRoleMenu.htm")
	@RequestMapping("/modules/manage/system/roleMenu/find.htm")
	@RequiresPermission(code = "modules:manage:system:roleMenu:find", name = "根据角色查询菜单")
	public void fetchRoleMenu(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "sysType", required = true) String sysType) throws Exception {
		SysRole sysRole = getRoleForLoginUser(request);
	
		if (sysType == null) {
			throw new BussinessException("请指定登录的系统类型");
		} else if (!sysType.matches("[\\d,]+")) {
			throw new BussinessException("参数错误");
		}
		if (null != sysRole && null != sysRole.getId() && 0 < sysRole.getId()) {
			List<Map<String, Object>> menus = sysMenuService.fetchRoleMenus(
					sysType, sysRole.getId());
			menus = ListUtil.treeForExt(menus, null, null, true);
			Map<String, Object> res = new HashMap<String, Object>();

			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_DATA, menus);
			ServletUtils.writeToResponse(response, res);

		} else {
			throw new BussinessException("请先登录");
		}
	
	}

	/**
	 * 修改用户权限
	 * @param request
	 * @param response
	 * @param checkedkey
	 * @param menus
	 * @param roleId
	 * @throws Exception
	 */
//	@RequestMapping("/modules/system/sconfig/saveOrUpdateRoleMenus.htm")
	@RequestMapping("/modules/manage/system/menu/save.htm")
	@RequiresPermission(code = "modules:manage:system:menu:save", name = "修改用户权限")
	public void saveOrUpdate(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "checkedkey") String checkedkey, @RequestParam(value = "menus") String menus,
			@RequestParam(value = "roleId") Integer roleId) throws Exception {
		List<Integer> permIds = JSONObject.parseArray(checkedkey, Integer.class);
		List<Long> menuIds = JSONObject.parseArray(menus, Long.class);
		SysUser sysUser = this.getLoginUser(request);
		String userName = sysUser.getUserName();
		sysRolePermService.updatePerms(roleId, permIds, userName);
		sysMenuService.saveOrUpdateMenuss(roleId, menuIds);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, res);
	}

	/**
	 * 分配权限时得到当前用户权限
	 * 
	 * @param request
	 * @param response
	 * @param roleId
	 * @throws Exception
	 */
@SuppressWarnings("unchecked")
	//	@RequestMapping("/modules/system/sconfig/fetchRoleMenuHas.htm")
	@RequestMapping("/modules/manage/system/roleMenu/fetch.htm")
	@RequiresPermission(code = "modules:manage:system:roleMenu:fetch", name = "当前用户权限")
	public void findRoleMenuHas(HttpServletRequest request, HttpServletResponse response, long roleId)
			throws Exception {

		List<Map<String, Object>> menus = sysMenuService.fetchRoleMenuHas(roleId);
		List<Map<String, Object>> list = sysPermService.fetchAll();
		List<SysPerm> perms = sysPermService.listByRoleId(roleId);
		for (Map<String, Object> perm : list) {
			for (Map<String, Object> menu : menus) {
				if (StringUtil.isNull(perm.get("menuId")).equals(StringUtil.isNull(menu.get("value")))) {
					perm.put("menuName", menu.get("label"));
					perm.put("menuParentId", menu.get("parentId"));
				}
			}
			if (perms.size() != 0) {
				for (SysPerm sysPerm : perms) {
					if ((sysPerm.getId().toString()).equals(perm.get("id").toString())) {
						perm.put("isPerm", true);
						break;
					} else {
						perm.put("isPerm", false);
					}
				}
			} else {
				perm.put("isPerm", false);
			}
		}

		menus = ListUtil.list2Tree(menus, "value", "parentId");
		menus = ListUtil.treeForExt(menus, null, null, true);
		for (Map<String, Object> parentMenu : menus) {
			List<Map<String, Object>> menuChilds = ((List<Map<String, Object>>) parentMenu.get("children"));
			if (null != menuChilds) {
				for (Map<String, Object> menuChild : menuChilds) {
					List<Map<String, Object>> menuPerms = null;
					int i=0;
					for (Map<String, Object> perm : list) {
						if ((StringUtil.isNull(perm.get("menuId")).equals(StringUtil.isNull(menuChild.get("value"))))) {
							if (null == menuPerms) {
								menuPerms = new ArrayList<Map<String, Object>>();
							}
							if((perm.get("isPerm").toString()).equals("false")){
								i=i+1;
							}
							menuPerms.add(perm);
							
						} 
					}
					if (null != menuPerms) {
						menuChild.put("children", menuPerms);
						
					}
					if(i>0){
						menuChild.put("checked", 0);
					}
				}
			}
		}
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_DATA, menus);
		ServletUtils.writeToResponse(response, res);
	}

	/**
	 * 查询用户权限
	 * @param request
	 * @param response
	 * @throws Exception
	 */
//	@RequestMapping("/modules/system/sconfig/fetchAllMenu.htm")
	@RequestMapping("/modules/manage/system/perm/find.htm")
	@RequiresPermission(code = "modules:manage:system:perm:find",name = "查询用户权限")
	public void findAllMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Map<String, Object>> list = sysMenuService.fetchAllMenu();
		list = ListUtil.list2Tree(list, "value", "parentId");
		list = ListUtil.treeForExt(list, null, null, true);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_DATA, list);
		ServletUtils.writeToResponse(response, res);
	}

	/**
	 * 获取用户某个菜单面板项集合
	 * @param response
	 * @param request
	 * @param id
	 * @throws Exception
	 */
//	@SuppressWarnings("unchecked")
//	@RequestMapping("/modules/system/sconfig/westone.htm")
//	@RequiresPermission(code = "modules:system:sconfig:westone",name = "获取用户某个菜单面板项集合")
//	public void getleftMenuPanelList(HttpServletResponse response, HttpServletRequest request,
//			@RequestParam(value = "parentId", required = false) String id) throws Exception {
//		List<SysMenu> menuList = null;
//
//		if (Integer.valueOf(id) == 0) {
//			logger.info("------------------SysMenuAction-getleftMenuPanelList()---parentId=" + id + " userName="
//					+ this.getLoginUserName(request) + " role=" + this.getRole(request));
//
//			menuList = (List<SysMenu>) sysMenuService.getMenuPanelByParentId(this.getLoginUserName(request), id,
//					CHOISETREENOCHECKED, this.getRole(request));
//			// menuList = (List<SysMenu>)
//			// sysMenuService.getMenuPanelByParentId("kf_lsk","0",2,this.getRole(request));
//		}
//
//		Map<Object, Object> result = new HashMap<Object, Object>();
//
//		if (menuList.size() > 0 && menuList != null) {
//			result.put("result", true);
//
//			result.put("datas", menuList);
//		} else {
//			result.put("result", false);
//
//		}
//		ServletUtils.writeToResponse(response, result);
//
//	}
//
//	@SuppressWarnings("unchecked")
//	@RequestMapping("/modules/system/sconfig/tree.htm")
//	@RequiresPermission(code = "modules:system:sconfig:tree",name = "获取菜单树状图")
//	public void getleftMenuTreeList(HttpServletResponse response, HttpServletRequest request,
//			@RequestParam(value = "parentId", required = false) String id,
//			@RequestParam(value = "node", required = false) String node) {
//		logger.info(
//				"###########################SysMenuAction---getleftMenuTreeList(): parentId=" + id + "  node=" + node);
//		List<SysMenu> menuList = null;
//		SysUser sysUser = this.getLoginUser(request);
//		try {
//
//			if (id != null && "root".equalsIgnoreCase(node)) {
//
//				menuList = (List<SysMenu>) sysMenuService.getMenuPanelByParentId(sysUser.getUserName(), id,
//						CHOISETREENOCHECKED, this.getRole(request));
//			} else if (!"NaN".equalsIgnoreCase(node)) {
//				menuList = (List<SysMenu>) sysMenuService.getMenuPanelByParentId(sysUser.getUserName(), node,
//						CHOISETREENOCHECKED, this.getRole(request));
//
//				setLevelVaule(menuList);
//			}
//			ServletUtils.writeToResponselist(response, menuList);
//		} catch (Exception e) {
//
//		}
//
//	}

	/**
	 * 
	 * 配置菜单
	 * 
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
//	@RequestMapping("/modules/system/sconfig/menutree.htm")
	@RequestMapping("/modules/manage/system/menu/findMenuTrees.htm")
	@RequiresPermission(code = "modules:manage:system:menu:findMenuTrees",name = "配置菜单")
	public void findMenuTrees(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "parentId", required = false) String id,
			@RequestParam(value = "node", required = false) String node) throws Exception {
		List<SysMenu> menuLists ;
		if ("root".equalsIgnoreCase(node)) {
			menuLists = (List<SysMenu>) sysMenuService.getMenuPanelByParentId(this.getSysUser().getUserName(), id,
					CHOISETREENOCHECKED, this.getRole(request));

		} else {
			// menuLists = (List<SysMenu>)
			// sysMenuService.getMenuPanelByParentId(
			// this.getSysUser().getUserName(), node);
			menuLists = (List<SysMenu>) sysMenuService.getMenuPanelByParentId("system", node, CHOISETREENOCHECKED,
					this.getRole(request));
		}

		ServletUtils.writeToResponselist(response, menuLists);

	}

	/**
	 * 新增修改菜单
	 * @param response
	 * @param data
	 * @param status
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
//	@RequestMapping("/menu/update.htm")
	@RequestMapping("/modules/manage/system/menu/update.htm")
	@RequiresPermission(code = "modules:manage:system:menu:update",name = "新增修改菜单")
	public void update(HttpServletResponse response, @RequestParam(value = "menu", required = false) String data,
			@RequestParam(value = "status", required = false) String status) throws Exception {
		Map<String, Object> dataMap = JsonUtil.parse(data, Map.class);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		if ("create".equalsIgnoreCase(status)) {
			int n = sysMenuService.addMenu(dataMap);
			if (n > 0) {
				responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
			} else {
				responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, "保存失败");
			}
		} else if ("update".equals(status)) {
			int total = sysMenuService.updateMenu(dataMap);
			if (total > 0) {
				responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
			} else {
				responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, "保存失败");
			}
		}
		ServletUtils.writeToResponse(response, responseMap);
	}

//	/**
//	 * 删除菜单
//	 * @param response
//	 * @param id
//	 * @throws Exception
//	 */
//	@RequestMapping("/menu/delete.htm")
//	@RequiresPermission(code = "menu:delete",name = "删除菜单 ")
//	public void delete(HttpServletResponse response, @RequestParam(value = "id", required = false) Long id)
//			throws Exception {
//		Map<String, Object> responseMap = new HashMap<String, Object>();
//		Map<String, Object> dataMap = new HashMap<String, Object>();
//		dataMap.put("id", id);
//		dataMap.put("isDelete", 1);
//		sysMenuService.updateMenu(dataMap);
//		responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
//		responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
//		ServletUtils.writeToResponse(response, responseMap);
//	}
//
//	/**
//	 * 分配菜单
//	 * @param response
//	 * @param ids
//	 * @param roleId
//	 * @param res
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/modules/system/sconfig/rolemenu.htm")
//	@RequiresPermission(code = "modules:system:sconfig:rolemenu",name = "分配菜单 ")
//	public void saveOrUpdateRoleMenu(HttpServletResponse response,
//			@RequestParam(value = "form", required = false) String ids,
//			@RequestParam(value = "roleId", required = false) String roleId, Map<String, Object> res)
//			throws IOException {
//
//		try {
//			if (ids != null && roleId != null) {
//				sysMenuService.saveOrUpdate(roleId, ids);
//				res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
//				res.put(Constant.RESPONSE_CODE_MSG, "角色 分配成功");
//
//			} else {
//
//				return;
//
//			}
//
//		} catch (ServiceException e) {
//
//			res.put(Constant.RESPONSE_CODE, e.getCode());
//			res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
//
//			e.printStackTrace();
//
//		}
//		ServletUtils.writeToResponse(response, res);
//	}

	@SuppressWarnings("unchecked")
	public List<SysMenuCheck> getMenuList(String id, HttpServletRequest request) throws ServiceException, PersistentDataException {

		List<SysMenuCheck> menuLists = (List<SysMenuCheck>) sysMenuService.getMenuPanelByParentId("system", id,
				CHOISOTTHER, this.getRole(request));

		for (SysMenuCheck sysMenu : menuLists) {

			sysMenu.setChildren(this.getMenuList(String.valueOf(sysMenu.getId()), request));
			// 重构標記
			for (Map<String, Object> sysMenuCheck : roleIdMenuList) {

				if (sysMenuCheck.containsValue(Integer.parseInt(String.valueOf(sysMenu.getId())))) {
					sysMenu.setChecked(true);

				}

			}
		}
		return menuLists;

	}

	public List<Map<String, Object>> getRoleIds(String roleId) {
		if (roleId != null) {
			return sysMenuService.getRoleIdMenuList(Integer.valueOf(roleId));

		}

		return null;
	}

	/**
	 * 菜单下拉框实现
	 * 
	 * @param request
	 * @param response
	 * @version 1.0
	 * @author
	 * @throws ServiceException
	 * @created 2014年12月19日
	 */
//	@RequestMapping(value = "/modules/system/sconfig/menucombo.htm")
	@RequestMapping(value = "/modules/manage/system/menu/combo/find.htm")
	@RequiresPermission(code = "modules:manage:system:menu:combo:find",name = "菜单下拉框")
	public void findMenucombo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("isDelete", 0);
		List<SysMenu> sysMenus = sysMenuService.getMenuList(param);
		Map<String, Object> res = new HashMap<String, Object>();

		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_DATA, Tree.TreeList(sysMenus, "id", "text", "parentId"));
		// 将树返回到页面
		ServletUtils.writeToResponse(response, res);
	}

//	private void setLevelVaule(List<? extends SysMenu> menuList) {
//
//		if (menuList != null) {
//
//			Iterator<? extends SysMenu> sysMenus = menuList.iterator();
//
//			while (sysMenus.hasNext()) {
//				SysMenu sysMenu = sysMenus.next();
//
//				if (sysMenu.getLevel() == 3) {
//					sysMenu.setLeaf(true);
//				}
//
//			}
//
//		}
//
//	}

}
