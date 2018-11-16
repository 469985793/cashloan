package com.xindaibao.cashloan.rc.service;


import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.rc.model.OperatorCountModel;

/**
 * 运营商信息统计
 * @author
 * @version 1.0
 * @date 2017年4月18日上午10:34:26




 */
public interface OperatorCountService extends BaseService<OperatorCountModel,String> {
	
	/**
	 * 通话信息
	 * @param phone
	 * @return
	 */
	int operatorCountVoice(Long userId);
	
	/**
	 * 联系人借款信息，合并到统计通话信息同一个方法
	 * @param phone
	 * @return
	 */
	/*int operatorCountBorrow(Long userId);*/
	
}
