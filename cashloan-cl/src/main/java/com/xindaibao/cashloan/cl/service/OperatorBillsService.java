package com.xindaibao.cashloan.cl.service;

import java.util.List;

import com.xindaibao.cashloan.cl.domain.OperatorBills;
import com.xindaibao.cashloan.cl.model.OperatorBillsModel;
import com.xindaibao.cashloan.core.common.exception.BussinessException;
import com.xindaibao.cashloan.core.common.service.BaseService;


/**
 * 运营商信息--月账单Service
 * @author
 * @version 1.0.0
 * @date 2017-03-13 15:56:20


 * 

 */
public interface OperatorBillsService extends BaseService<OperatorBills, Long>{
	
	/**
	 * 保存月账单信息
	 * @param bills
	 * @throws BussinessException
	 */
	void saveRecords(List<OperatorBillsModel> bills) throws Exception;
}
