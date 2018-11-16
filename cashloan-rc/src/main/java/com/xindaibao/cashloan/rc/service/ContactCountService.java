package com.xindaibao.cashloan.rc.service;

import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.rc.domain.ContactCount;

/**
 * 通讯录统计Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-10 15:04:13


 * 

 */
public interface ContactCountService extends BaseService<ContactCount, Long>{

	/**
	 * 统计数据
	 * @return
	 */
	int save();
	
	int countContacts(Long userId);

}
