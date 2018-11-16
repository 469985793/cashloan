package com.xindaibao.cashloan.cl.service;

import java.util.Date;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.ProfitLog;
import com.xindaibao.cashloan.cl.model.ManageCashLogModel;
import com.xindaibao.cashloan.cl.model.ProfitLogModel;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * 分润记录Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-18 17:04:10


 * 

 */
public interface ProfitLogService extends BaseService<ProfitLog, Long>{

	/**
	 * 邀请明细
	 * @param searchMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ProfitLogModel> page(Map<String, Object> searchMap, int current,
                              int pageSize);

	/**
	 * 查询提现记录
	 * @param phone
	 * @param userName 
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ManageCashLogModel> findCashLog(String phone, String userName, int current, int pageSize);
	
	/**
	 * 管理员查询提现记录
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ManageCashLogModel> findCashLog(String userName, int current, int pageSize);

	int save(long borrowId,Date date);
}
