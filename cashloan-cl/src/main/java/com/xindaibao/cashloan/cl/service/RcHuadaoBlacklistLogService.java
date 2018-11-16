package com.xindaibao.cashloan.cl.service;

import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.rc.domain.TppBusiness;
import com.xindaibao.cashloan.cl.domain.RcHuadaoBlacklistLog;

/**
 * 华道黑名单记录表Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-24 10:28:22



 */
public interface RcHuadaoBlacklistLogService extends BaseService<RcHuadaoBlacklistLog, Long>{

	int queryHuadaoBlackList(Long userId,TppBusiness business);
}
