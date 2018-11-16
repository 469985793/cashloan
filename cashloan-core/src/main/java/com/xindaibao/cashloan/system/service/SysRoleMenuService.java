package com.xindaibao.cashloan.system.service;

import java.util.List;

import com.xindaibao.cashloan.core.common.exception.PersistentDataException;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.system.domain.SysRoleMenu;


/**
 * 
 * 角色菜单关联信息service接口
 * @version 1.0
 * @author
 * @created 2014年9月23日 上午9:55:37
 */
public interface SysRoleMenuService {
	/**
	 * 角色菜单关联信息查询
	 * @param roleId 角色ID
	 * @return 角色List
	 * @throws ServiceException 
	 * @throws PersistentDataException 
	 */
	List<SysRoleMenu> getRoleMenuList(Long roleId) throws ServiceException, PersistentDataException;
	
}
