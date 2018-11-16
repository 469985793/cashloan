package com.xindaibao.cashloan.system.service;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.exception.PersistentDataException;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.system.domain.SysMenu;
import com.xindaibao.cashloan.system.model.MenuModel;

/**
 * 
 * 菜单服务类
 * 
 * @version 1.0
 * @author
 * @created 2014年9月23日 上午9:54:35
 */
public interface SysMenuService {

	/**
	 * 根据角色ids查询菜单model list信息
	 * 
	 * @param roleIds
	 *            角色ids
	 * @return 菜单model list
	 * @throws ServiceException 
	 * @throws Exception 
	 */
	List<MenuModel> getMenuListByRoleIds(List<Long> roleIds) throws ServiceException;



	/**
	 * 查询菜单
	 * 
	 * @param id
	 *            对象id
	 * @return 返回菜单对象
	 * @throws ServiceException 
	 */
	SysMenu menuFind(long id) throws ServiceException;


	/**
	 * 获取用户是否有此url的权限
	 * 
	 * @param userName
	 *            用户名
	 * @param href
	 *            菜单路径
	 * @return 是否拥有此菜单的权限
	 * @throws ServiceException 
	 */
	boolean getMenuPermission(String userName, String href) throws ServiceException;

	/**
	 * 根据href查询菜单信息
	 * 
	 * @param href
	 * @return
	 */
	SysMenu getMenuByHref(String href);

	/**
	 * 更新menu
	 * 
	 * @param menuMap
	 */

	int updateMenu(Map<String, Object> menuMap);

	List<? extends SysMenu> getMenuPanelByParentId(String useName,
			String parentId, int code,List<Long> roles) throws ServiceException, PersistentDataException;


	int addMenu(Map<String, Object> menuMap);

	List<Map<String, Object>> getRoleIdMenuList(int roleId);
	
	
	boolean saveOrUpdate(String roleId,String ids) throws ServiceException, PersistentDataException;
	
	/**
	 * 获得满足条件的菜单列表
	 * @param param
	 * @return 
	 * @version 1.0
	 * @author
	 * @created 2014年12月19日
	 */
	List<SysMenu> getMenuList(Map<String, Object> param);


	List<Map<String, Object>> fetchRoleMenus(String sysType,Long... roleids) throws ServiceException;

	void saveOrUpdateMenuss(long roleId,List<Long> list) throws ServiceException;

	/**
	 * 
	 * @description 角色分配权限菜单页的查询
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 * @author
	 * @return List<Map<String,Object>>
	 * @since  1.0.0
	 */
	List<Map<String, Object>> fetchRoleMenuHas(Long roleId) throws ServiceException;
	
	List<Map<String, Object>> fetchAllMenu() throws ServiceException;
}
