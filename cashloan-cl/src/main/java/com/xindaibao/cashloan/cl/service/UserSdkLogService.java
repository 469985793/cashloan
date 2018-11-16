package com.xindaibao.cashloan.cl.service;

import java.util.Map;

import com.xindaibao.cashloan.cl.domain.UserSdkLog;
import com.xindaibao.cashloan.core.common.service.BaseService;


/**
 * sdk识别记录表Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-20 09:47:04




 */
public interface UserSdkLogService extends BaseService<UserSdkLog, Long>{

	/**
	 * 查询当日可识别次数
	 * @param searchMap
	 * @return
	 */
	Map<String, Object> countDayTime(Map<String, Object> searchMap);

	/**
	 * 保存
	 * @param usl
	 * @return
	 */
	int save(UserSdkLog usl);

}
