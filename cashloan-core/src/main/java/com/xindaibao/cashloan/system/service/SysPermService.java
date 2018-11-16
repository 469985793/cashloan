package com.xindaibao.cashloan.system.service;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.system.domain.SysPerm;

public interface SysPermService extends BaseService<SysPerm, Long> {

	int updateByPrimaryKeySelective(SysPerm record);
	    
	List<SysPerm> listByUserName(String userName);
	
	List<SysPerm> listByRoleId(Long roleId);
	
	List<Map<String, Object>> fetchAll();
}
