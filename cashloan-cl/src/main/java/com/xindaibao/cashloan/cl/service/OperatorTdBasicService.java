package com.xindaibao.cashloan.cl.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.OperatorReqLog;
import com.xindaibao.cashloan.cl.domain.OperatorTdBasic;
import com.xindaibao.cashloan.cl.domain.OperatorTdCallInfo;
import com.xindaibao.cashloan.cl.domain.OperatorVoices;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * 同盾运营商认证基本信息表Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-24 09:20:04



 */
public interface OperatorTdBasicService extends BaseService<OperatorTdBasic, Long>{
	/**
	 * 保存同盾运营商
	 * @param res
	 * @param userId
	 */
	void saveTdOperatorInfos(Map<String, Object> resultData, Long userId,OperatorReqLog log);
	/**
	 * 查询同盾运营商通话记录
	 * @param params
	 * @return
	 */
	OperatorTdCallInfo findOperatorTdCallInfos(Map<String, Object> params);
	/**
	 * 查询用户运营商具体通话记录
	 * @param params
	 * @param current
	 * @param pageSize
	 * @param callInfo
	 * @return
	 */
	Page<OperatorVoices> findPageOperatorTdCallRecord(Map<String, Object> params, int current, int pageSize);

}
