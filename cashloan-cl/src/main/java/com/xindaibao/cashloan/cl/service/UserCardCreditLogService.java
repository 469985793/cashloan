package com.xindaibao.cashloan.cl.service;

import com.xindaibao.cashloan.cl.domain.UserCardCreditLog;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * 人脸识别请求记录Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-10 14:37:56


 * 

 */
public interface UserCardCreditLogService extends BaseService<UserCardCreditLog, Long>{
	/**
	 * 判断用户是否可以人脸识别认证
	 * @param userId
	 * @return
	 */
	boolean isCanCredit(Long userId);

}
