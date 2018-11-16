package com.xindaibao.cashloan.cl.service;

import com.xindaibao.cashloan.cl.domain.ClShuMeiBlacklist;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.rc.domain.TppBusiness;

/**
 * 数美逾期黑名单Service
 */
public interface ClShuMeiBlacklistService extends BaseService<ClShuMeiBlacklist, Long> {

    int queryShuMeiBlackList(Borrow borrow, TppBusiness business);
}
