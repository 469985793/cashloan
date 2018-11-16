package com.xindaibao.cashloan.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.xindaibao.cashloan.system.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import com.xindaibao.cashloan.core.common.exception.PersistentDataException;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.system.domain.SysRoleMenu;
import com.xindaibao.cashloan.system.mapper.SysRoleMenuMapper;

@Service(value = "sysRoleMenuServiceImpl")
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
	@Resource
	private SysRoleMenuMapper sysRoleMenuDao;
	
	@Override
	public List<SysRoleMenu> getRoleMenuList(Long roleId) throws ServiceException, PersistentDataException {
		return this.sysRoleMenuDao.getRoleMenuList(roleId);
	}

	public SysRoleMenuMapper getSysRoleMenuDao() {
		return sysRoleMenuDao;
	}

	public void setSysRoleMenuDao(SysRoleMenuMapper sysRoleMenuDao) {
		this.sysRoleMenuDao = sysRoleMenuDao;
	}



}
