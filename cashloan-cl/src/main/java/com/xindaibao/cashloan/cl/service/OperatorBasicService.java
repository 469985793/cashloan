package com.xindaibao.cashloan.cl.service;

import java.util.List;

import com.xindaibao.cashloan.cl.domain.OperatorBasic;
import com.xindaibao.cashloan.core.common.exception.BussinessException;
import com.xindaibao.cashloan.core.common.service.BaseService;


/**
 * 运营商信息-基础信息Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-13 16:35:43


 * 

 */
public interface OperatorBasicService extends BaseService<OperatorBasic, Long>{

	void saveRecords(List<OperatorBasic> basics) throws BussinessException;
	
	/**
	 * 统计运营商信息中的通话时长及次数
	 * @param userId
	 * @throws BussinessException
	 */
	void countOperatorVoice(Long userId) throws BussinessException;
	
	/**
	 * 根据运营商信息统计常联系人的借款信息
	 * @param userId
	 * @throws BussinessException
	 */
	void countOperatorBorrow(Long userId) throws BussinessException;
}
