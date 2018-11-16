package com.xindaibao.cashloan.system.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.system.domain.SysAccessCode;
import com.xindaibao.cashloan.system.domain.SysUser;
import com.xindaibao.cashloan.system.model.SysAccessCodeModel;

/**
 * 访问码Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-24 17:37:49


 * 

 */
@RDBatisDao
public interface SysAccessCodeMapper extends BaseMapper<SysAccessCode,Long> {
	
	/**
	 * 访问码信息列表查询
	 * @param params
	 * return
	 */
	List<SysAccessCodeModel> listAccessCodeModel(Map<String, Object> params);

	/**
	 * 保存新增用户
	 * @param ac
	 * @return
	 */
	int save(SysAccessCode ac);
	
	/**
	 * 根据ID更新
	 * @param data
	 * @return
	 */
	int update(SysAccessCode ac);
	
	/**
	 * 查询某用户相同code数
	 * @param data
	 * @return
	 */
	int countCode(Map<String, Object> data);
	
	/**
	 * 查询访问码列表
	 * @param sysUserId
	 * @return
	 */
	List<SysAccessCode> listSysAccessCode(Long sysUserId);
	
	/**
	 * 查询访问码
	 * @param map
	 * @return
	 */
	SysAccessCode findSysAccessCode(Map<String,Object> map);
	
	/**
	 * 用户名列表
	 * @return
	 */
	List<SysUser> listUserName();
}
