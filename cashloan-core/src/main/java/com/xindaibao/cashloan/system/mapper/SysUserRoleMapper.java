package com.xindaibao.cashloan.system.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.system.domain.SysUserRole;

/**
 * 
 * 用户角色DAO
 * @version 1.0
 * @author
 * @created 2014年9月22日 下午2:47:14
 */
@RDBatisDao
public interface SysUserRoleMapper extends BaseMapper<SysUserRole, Long> {
	
	
	/**
	 * 根据用户ID删除
	 * @param userId 
	 * @version 1.0
	 * @author
	 * @created 2014年9月22日
	 */
	void deleteByUserId(long userId);
	
	List<SysUserRole> getItemListByMap(Map<String, Object> param);

}
