package com.xindaibao.cashloan.cl.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.ProfitCashLog;
import com.xindaibao.cashloan.cl.model.ManageProfitLogModel;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * 分润提现记录Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-18 16:51:48


 * 

 */
public interface ProfitCashLogService extends BaseService<ProfitCashLog, Long>{

	/**
	 * 提现记录查看
	 * @param searchMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ProfitCashLog> page(Map<String, Object> searchMap, int current,
			int pageSize);
//
//	/**
//	 * 平台查询可提现用户
//	 * @param userName
//	 * @param current
//	 * @param pageSize
//	 * @return
//	 */
//	Page<ManageProfitAmountModel> findAmount(String userName, int current,
//			int pageSize);

	/**
	 * 查询分润明细
	 * @param phone
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ManageProfitLogModel> findLog(String phone, String userName, int current, int pageSize);

	/**
	 * 管理员查询分润明细
	 * @param userName
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<ManageProfitLogModel> findLog(String userName, int current,
			int pageSize);

}
