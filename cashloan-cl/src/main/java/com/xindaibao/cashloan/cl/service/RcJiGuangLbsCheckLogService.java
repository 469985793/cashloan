package com.xindaibao.cashloan.cl.service;

import com.xindaibao.cashloan.cl.domain.RcJiGuangBlacklistLog;
import com.xindaibao.cashloan.cl.domain.RcJiGuangLbsCheckLog;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.rc.domain.TppBusiness;

/**
 * 极光黑名单记录表Service
 *
 *
 */
public interface RcJiGuangLbsCheckLogService extends BaseService<RcJiGuangLbsCheckLog, Long>{

	int queryJiGuangLbsCheck(Borrow borrow, TppBusiness business);
}
