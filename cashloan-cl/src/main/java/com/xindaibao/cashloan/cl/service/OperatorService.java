package com.xindaibao.cashloan.cl.service;

import com.xindaibao.cashloan.cl.domain.OperatorReqLog;

import java.util.Date;

/**
 * 运营商信息保存
 * @author
 * @version 1.0
 * @date 2017年3月22日上午10:24:15


 * 

 */
public interface OperatorService {

	/**
	 * 运营商信息保存
	 * @param res
	 * @param compressStatus
	 * @param userId
	 * @param createTime
	 */
	void saveOperatorInfos(String res, String compressStatus, Long userId, Date createTime);

    void operatorQuery(OperatorReqLog operatorReqLog, String task_id);
}
