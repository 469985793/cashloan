package com.xindaibao.cashloan.cl.service;

import com.xindaibao.cashloan.cl.domain.RcHuadaoBlacklistLog;
import com.xindaibao.cashloan.cl.domain.RcR360BlacklistLog;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.rc.domain.TppBusiness;

/**
 * 华道黑名单记录表Service
 * 
 *
 * @version 1.0.0
 * @date 2017-05-24 10:28:22
 * Copyright zuoli company  cashloan All Rights Reserved
 *
 *
 */
public interface RcR360BlacklistLogService extends BaseService<RcR360BlacklistLog, Long>{

	int queryR360BlackList(Long userId);
}
