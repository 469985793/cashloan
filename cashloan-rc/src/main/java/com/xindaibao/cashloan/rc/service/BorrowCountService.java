package com.xindaibao.cashloan.rc.service;

import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.rc.domain.BorrowCount;

/**
 * 借款统计Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-10 14:59:59


 * 

 */
public interface BorrowCountService extends BaseService<BorrowCount, Long>{

	int save();
	
	int countBorrowRefusedTimes(Long userId);
}
