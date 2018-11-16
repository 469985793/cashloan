package com.xindaibao.cashloan.rc.service;

import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.rc.domain.SimpleVoicesCount;

/**
 * 风控数据统计-（简）通话记录统计Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-07-06 18:11:18



 */
public interface SimpleVoicesCountService extends BaseService<SimpleVoicesCount, Long>{
	
	/**
	 * 通话记录总次数
	 * @param userId
	 * @return
	 */
	int countOne(long userId);

}
