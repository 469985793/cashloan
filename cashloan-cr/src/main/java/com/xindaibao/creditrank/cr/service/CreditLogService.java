package com.xindaibao.creditrank.cr.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.creditrank.cr.domain.CreditLog;
import com.xindaibao.creditrank.cr.model.CreditLogModel;

/**
 * 授信额度记录Service
 * 
 * @author
 * @version 1.0.0
 * @date 2016-12-16 10:31:23


 * 

 */
public interface CreditLogService extends BaseService<CreditLog, Long>{

	/**
	 * 保存数据
	 * @param log
	 * @return
	 */
	int save(CreditLog log);
	
	/**
	 * 分页查询
	 * @param searchMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<CreditLogModel> page(Map<String,Object> searchMap,int current,int pageSize);
}
