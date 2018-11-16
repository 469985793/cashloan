/**
 *
 * 系统权限
 */
package com.xindaibao.cashloan.system.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.system.domain.SysPerm;

@RDBatisDao
public interface SysPermMapper extends BaseMapper<SysPerm,Long> {

    SysPerm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPerm record);
    
    List<SysPerm> listByUserName(String userName);
    
    List<SysPerm> selectAll();
    
	List<SysPerm> listByRoleId(Long roleId);

	List<Map<String, Object>> fetchAll();
}