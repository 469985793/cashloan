package com.xindaibao.cashloan.system.mapper;

import java.util.List;

import com.xindaibao.cashloan.core.common.exception.PersistentDataException;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.system.domain.SysRoleMenu;

/**
 * 
 * 角色菜单关系DAO
 * @version 1.0
 * @author
 * @created 2014年9月22日 下午2:48:38
 */
@RDBatisDao
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu,Long> {
	
	/**
	 * 删除角色菜单关联表信息（物理删除）
	 * @param roleId 角色ID
	 * @version 1.0
	 * @author
	 * @created 2014年9月22日
	 */
	void deleteByRoleId(long roleId);
	
	/**
	 * 角色菜单关联信息查询 LIST
	 * @param roleId  角色ID
	 * @return 角色菜单关系列表
	 * @version 1.0
	 * @author
	 * @throws PersistentDataException 
	 * @created 2014年9月22日
	 */
	List<SysRoleMenu> getRoleMenuList(long roleId) throws PersistentDataException;
	
	void addRoleMenu(long roleId,Long menuId);
}
