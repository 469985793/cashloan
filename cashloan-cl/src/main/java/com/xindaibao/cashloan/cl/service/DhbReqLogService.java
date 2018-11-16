package com.xindaibao.cashloan.cl.service;

import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.cl.domain.DhbReqLog;
import com.xindaibao.cashloan.rc.domain.TppBusiness;

/**
 * 贷后邦反欺诈请求记录表Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-06-02 18:20:59



 */
public interface DhbReqLogService extends BaseService<DhbReqLog, Long>{
	/**
	 * 贷后邦反欺诈请求
	 * @param userId
	 * @param business
	 * @return
	 */
	int queryDhbSauron(Borrow borrow, TppBusiness business);

}
