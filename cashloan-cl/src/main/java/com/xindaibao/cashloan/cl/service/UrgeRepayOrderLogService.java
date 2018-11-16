package com.xindaibao.cashloan.cl.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.UrgeRepayOrder;
import com.xindaibao.cashloan.cl.domain.UrgeRepayOrderLog;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * 催款记录表Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-07 14:28:22


 * 

 */
public interface UrgeRepayOrderLogService extends BaseService<UrgeRepayOrderLog, Long>{
	/**
	 * 催款记录信息
	 * @param params
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<UrgeRepayOrderLog> list(Map<String, Object> params, int current,
			int pageSize);
	/**
	 * 根据条件查询催款记录信息
	 * @param id
	 * @return
	 */
	List<UrgeRepayOrderLog> getLogByParam(Map<String, Object> params);
	
	/**
	 * 保存催款记录信息
	 * @param params
	 * @return
	 */
	int saveOrderInfo(UrgeRepayOrderLog  orderLog,UrgeRepayOrder order);

}
