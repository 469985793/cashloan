package com.xindaibao.cashloan.cl.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.OperatorVoices;
import com.xindaibao.cashloan.cl.model.kenya.KanyaUserCall;
import com.xindaibao.cashloan.core.common.service.BaseService;


/**
 * 运营商信息-通话记录Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-13 16:44:01


 * 

 */
public interface OperatorVoicesService extends BaseService<OperatorVoices, Long>{

	/**
	 * (分表)查询通话记录
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<OperatorVoices> findShardPage(Map<String, Object> params, int currentPage, int pageSize);

	/**
	 * (分表)查询通话记录
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	KanyaUserCall findShardKenya(Long userId, int currentPage, int pageSize);


}
