package com.xindaibao.cashloan.cl.service;

import com.xindaibao.cashloan.cl.domain.RcR360BlacklistLog;
import com.xindaibao.cashloan.cl.domain.Rong360Grade;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * 融360积分表
 * 
 *
 * @version 1.0.0
 * @date 2017-05-24 10:28:22
 * Copyright zuoli company  cashloan All Rights Reserved
 *
 *
 */
public interface RcR360GradeService extends BaseService<Rong360Grade, Long>{

	int queryR360Grade(Long userId);
}
