package com.xindaibao.cashloan.cl.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.PayRespLog;
import com.xindaibao.cashloan.cl.model.ManagePayRespLogModel;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * 支付响应记录Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-07 16:18:10


 * 

 */
public interface PayRespLogService extends BaseService<PayRespLog, Long>{

	/**
	 * 保存响应记录
	 * 
	 * @param log
	 * @return
	 */
	boolean save(PayRespLog log);

	/**
	 * 分页查询
	 * 
	 * @param current
	 * @param pageSize
	 * @param searchMap
	 * @return
	 */
	Page<ManagePayRespLogModel> page(int current, int pageSize,
			Map<String, Object> searchMap);

	/**
	 * 查询详情
	 * 
	 * @param id
	 * @return
	 */
	ManagePayRespLogModel findDetail(Long id);
	
	
}
