package com.xindaibao.cashloan.system.mapper;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.system.domain.SysAppVersion;
import com.xindaibao.cashloan.system.domain.SysUser;
import com.xindaibao.cashloan.system.model.SysAccessCodeModel;

import java.util.List;
import java.util.Map;

/**
 * 访问码Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-24 17:37:49


 * 

 */
@RDBatisDao
public interface SysAppVersionMapper extends BaseMapper<SysAppVersion,Long> {


	/**
	 * 新增版本
	 * @param ac
	 * @return
	 */
	int save(SysAppVersion ac);

	/**
	 * 删除版本
	 * @param ac
	 * @return
	 */
	int delete(SysAppVersion ac);



	/**
	 * 查询访问码列表
	 * @return
	 */
	SysAppVersion listSysAppVersion();


}
