package com.xindaibao.cashloan.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.system.domain.SysUserRole;
import com.xindaibao.cashloan.system.mapper.SysUserRoleMapper;
import com.xindaibao.cashloan.system.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;

@Service(value = "sysUserRoleServiceImpl")
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole, Long> implements SysUserRoleService {

	@Resource
	private SysUserRoleMapper sysUserRoleDao;
	
	@Override
	public List<SysUserRole> getSysUserRoleList(Long userId) throws ServiceException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return sysUserRoleDao.getItemListByMap(map);
	}


	@Override
	public BaseMapper<SysUserRole, Long> getMapper() {
		return sysUserRoleDao;
	}
	


}
