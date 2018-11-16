package com.xindaibao.cashloan.cl.service;

import java.util.Map;

import com.xindaibao.cashloan.cl.domain.OperatorReqLog;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * 运营商认证中间表Service
 */
public interface OperatorReqLogService extends BaseService<OperatorReqLog, Long>{

	public OperatorReqLog findSelective(Map<String, Object> paramMap);
	
	/**
	 * 查找用户是否有认证记录
	 * @param userId
	 * @return
	 */
	String findOrderByUserId(Long userId);
	/**
	 * 查询用户是否可以进行运营商认证
	 * @param userId
	 * @return
	 */
	public boolean checkUserOperator(long userId);

	/**
	 * 查询最后一条符合条件的记录
	 * @param paramMap
	 * @return
	 */
	public OperatorReqLog findLastRecord(Map<String, Object> paramMap);
}
