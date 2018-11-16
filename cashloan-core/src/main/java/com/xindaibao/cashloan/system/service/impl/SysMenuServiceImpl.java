package com.xindaibao.cashloan.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xindaibao.cashloan.core.common.exception.PersistentDataException;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.ListUtil;
import com.xindaibao.cashloan.core.common.util.MapUtil;
import com.xindaibao.cashloan.core.common.util.StringUtil;
import com.xindaibao.cashloan.system.domain.SysMenu;
import com.xindaibao.cashloan.system.domain.SysRoleMenu;
import com.xindaibao.cashloan.system.mapper.SysMenuMapper;
import com.xindaibao.cashloan.system.mapper.SysRoleMenuMapper;
import com.xindaibao.cashloan.system.model.MenuModel;
import com.xindaibao.cashloan.system.service.SysMenuService;

@Service(value = "sysMenuServiceImpl")
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu,Long> implements SysMenuService {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SysMenuServiceImpl.class);
	
	@Resource
	private SysMenuMapper sysMenuMapper;
	
	@Resource
	private SysRoleMenuMapper sysRoleMenuMapper;
	
	protected String getLoginName() {
		// 增加用户登录判断
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getUsername();
	}

	@Transactional(rollbackFor = Exception.class)
	public SysMenu menuAdd(SysMenu menu) throws ServiceException {
		menu.setAddUser(this.getLoginName());
		this.sysMenuMapper.save(menu);
		return menu;
	}

	@Transactional(rollbackFor = Exception.class)
	public SysMenu menuUpdate(SysMenu menu) throws ServiceException {
		menu.setUpdateUser(this.getLoginName());
		this.sysMenuMapper.update(menu);
		return menu;
	}


	public List<SysMenu> menuUseList(long userId, byte isMenu) {
		return this.sysMenuMapper.menuUseList(userId, isMenu);
	}

	public boolean getMenuPermission(String userName, String href)
			throws ServiceException {
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("href", href);
		// SysMenu Menu;
		// try {
		// Menu = sysMenuMapper.getItemByMap(map, "SysMenu");
		//
		// SysMenu perm = this.sysMenuMapper.getMenuPermission(userName, href);
		// if ((Menu == null && perm == null) || (Menu != null && perm != null))
		// {
		// return true;
		// }
		// } catch (PersistentDataException e) {
		// throw new ServiceException(Constant.FAIL_CODE_VALUE,
		// "get data fail");
		// }
		return true;
	}

	public SysMenu getMenuByHref(String href) {
		return this.sysMenuMapper.getMenuByHref(href);
	}

	public List<MenuModel> getMenuListByRoleIds(List<Long> roleIds) throws ServiceException {
		return this.sysMenuMapper.getMenuListByRoleIds(roleIds);
	}

	public SysMenuMapper getSysMenuMapper() {
		return sysMenuMapper;
	}

	public void setSysMenuMapper(SysMenuMapper sysMenuMapper) {
		this.sysMenuMapper = sysMenuMapper;
	}

	@Override
	public List<? extends SysMenu> getMenuPanelByParentId(String useName,
			String parentId, int code, List<Long> roles)
			throws ServiceException, PersistentDataException {
		return sysMenuMapper.getMenuPanelByParentId(useName, parentId, code,
				roles);

	}
	@Override
	@Transactional
	public int updateMenu(Map<String, Object> menuMap) {

		return sysMenuMapper.updateMenu(menuMap);

	}

	@Override
	public int addMenu(Map<String, Object> menuMap) {

		return sysMenuMapper.insertmap(menuMap);

	}

	@Override
	public List<Map<String, Object>> getRoleIdMenuList(int roleId) {

		return sysMenuMapper.getRoleMenuList(roleId);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean saveOrUpdate(String roleId, String ids)
			throws ServiceException, PersistentDataException {

		sysMenuMapper.saveOrupdateRoleMenu(roleId, ids);

		return true;

	}

	@Override
	public List<SysMenu> getMenuList(Map<String, Object> param) {
		return sysMenuMapper.getMenuList(param);
	}

	@Override
	public BaseMapper<SysMenu,Long> getMapper() {
		return sysMenuMapper;
	}

	@Override
	public SysMenu menuFind(long id) throws ServiceException {
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List queryTreeNodeIds(String leafIds) throws PersistentDataException {
		List<Map<String,Object>> parents ;
		String menuLeafIds=leafIds;
		
		String[] Ids = menuLeafIds.split(",");

		List rIds = new ArrayList();
		do {
			parents = sysMenuMapper.getMenuParentId(Ids);
		    rIds.addAll(parents);
		    menuLeafIds=StringUtil.toString(MapUtil.collectProperty(parents,"id",false));
		    Ids= menuLeafIds.split(",");
		} while (!parents.isEmpty());

		List rlist=MapUtil.collectProperty(rIds,"id",false);

		for (String id : leafIds.split(",")) {
			rlist.add(id);
		}

		return rlist;
	}
	
	@Override
	public List<Map<String, Object>> fetchRoleMenus(String sysType, Long... roleids)
			throws ServiceException {
		try {
			String roleIds = StringUtil.toStringArray(roleids);
			
			List<Map<String,Object>> leafMenus= sysMenuMapper.getRoleSysMenu(roleIds, sysType);

			String menuLeafIds=StringUtil.toString(MapUtil.collectProperty(leafMenus, "menu_id"));

			String ids=StringUtil.toString(queryTreeNodeIds(menuLeafIds));
			
			String[] idsArray = ids.split(",");
			
			List<Map<String,Object>> menuList= sysMenuMapper.getMenuByParentIds(idsArray);

			menuList=ListUtil.list2Tree(menuList,"value","parentId");
			return menuList;
		} catch (Exception e) {
			throw new ServiceException("查询菜单失败", e);
		}
	}

	@Override
	public void saveOrUpdateMenuss(long roleId, List<Long> menuIds) throws ServiceException {
		sysRoleMenuMapper.deleteByRoleId(roleId);
		for (Long menuId : menuIds) {
			SysRoleMenu rm = new SysRoleMenu();
			rm.setMenuId(menuId);
			rm.setRoleId(roleId);
			sysRoleMenuMapper.save(rm);
		}
	}

	/**
	 * 
	 * @description 菜单页的查询
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 * @author
	 * @return List<Map<String,Object>>
	 * @since  1.0.0
	 */
	@Override
	public List<Map<String, Object>> fetchRoleMenuHas(Long roleId) throws ServiceException {
		return sysMenuMapper.fetchRoleMenuHas(roleId);
	}

	@Override
	public List<Map<String, Object>> fetchAllMenu() throws ServiceException {
		return sysMenuMapper.fetchAllMenu();
	}

}