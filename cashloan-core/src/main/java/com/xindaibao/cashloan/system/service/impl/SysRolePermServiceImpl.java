package com.xindaibao.cashloan.system.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.system.mapper.SysRolePermMapper;
import org.springframework.stereotype.Service;

import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.system.domain.SysRolePerm;
import com.xindaibao.cashloan.system.service.SysRolePermService;

@Service("sysRolePermService")
public class SysRolePermServiceImpl extends BaseServiceImpl<SysRolePerm, Long> implements SysRolePermService {

	@Resource
	private SysRolePermMapper sysRolePermMapper;
	
	@Override
	public BaseMapper<SysRolePerm, Long> getMapper() {
		return sysRolePermMapper;
	}
	
	public int deleteByRoleId(Integer roleId) {
		return sysRolePermMapper.deleteByRoleId(roleId);
	}
	@Override
	public void updatePerms(Integer roleId, List<Integer> permIds,String user) {
		sysRolePermMapper.deleteByRoleId(roleId);
			
		for (Integer permId : permIds) {
			if(null!= permId){
			SysRolePerm rolePerm = new SysRolePerm();
			rolePerm.setRoleId(roleId);
			rolePerm.setPermId(permId);
			rolePerm.setAddTime(new Date());
			rolePerm.setAddUser(user);
			sysRolePermMapper.save(rolePerm);
			}
		}
	}

	
}
