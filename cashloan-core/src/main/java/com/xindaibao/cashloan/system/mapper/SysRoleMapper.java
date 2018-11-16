package com.xindaibao.cashloan.system.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.system.domain.SysRole;

/**
 * 
 * 角色DAO
 * 
 * @version 1.0
 * @author
 * @created 2014年9月22日 下午2:51:25
 */
@RDBatisDao
public interface SysRoleMapper extends BaseMapper<SysRole,Long> {
	List<? extends SysRole> getRolePageList(Map<String, Object> data);

	List<SysRole> getRoleListByUserId(Long userId);

	List<SysRole> getListByMap(Map<String, Object> param);

	int deleteById(long id);

	int updateByMap(Map<String, Object> arg);
	
	List<Map<String, Object>> getByUserPassRolesList(Map<String, Object> data);

	int getRolecount(Map<String, Object> mapdata);

	long insertByMap(Map<String, Object> data);

}
