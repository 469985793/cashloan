/**
 *
 *
 * @author
 * @version 1.0.0.0
 * @date 2016年12月02日 下午14:56:41


 * 

 */
package com.xindaibao.cashloan.system.mapper;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.system.domain.SysRolePerm;

@RDBatisDao
public interface SysRolePermMapper extends BaseMapper<SysRolePerm, Long>{

    SysRolePerm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRolePerm record);
    
    int deleteByRoleId(Integer roleId);
    
}