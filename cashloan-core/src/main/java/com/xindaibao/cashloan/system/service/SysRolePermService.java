package com.xindaibao.cashloan.system.service;

import java.util.List;

import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.system.domain.SysRolePerm;

public interface SysRolePermService extends BaseService<SysRolePerm, Long> {

	/**
	 * 删除角色所有权限
	 * @param roleId
	 * @return
	 */
	int deleteByRoleId(Integer roleId);
	
	/**
	 * 修改用户权限
	 * @param roleId
	 * @param permIds
	 */
	void updatePerms(Integer roleId, List<Integer> permIds,String user);
}
