package com.xindaibao.cashloan.system.service;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.system.domain.SysAppVersion;

import java.util.List;


/**
 * 访问码Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-24 17:37:49


 * 

 */
public interface SysAppVersionService extends BaseService<SysAppVersion, Long> {
	/**
	 * app版本列表查询
	 * @return
	 */
	List<SysAppVersion> listSysAppVersion();


	/**
	 * 保存
	 * @param appVersion
	 * @return
	 */
	int save(SysAppVersion appVersion);

	/**
	 * 删除
	 * @param appVersion
	 * @return
	 */
	int delete(SysAppVersion appVersion);

}
