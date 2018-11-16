package com.xindaibao.cashloan.rc.mapper;


import java.util.Date;
import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.rc.model.OperatorCountModel;

/**
 * 运营商信息统计
 * @author
 * @version 1.0
 * @date 2017年4月18日上午10:34:26




 */
@RDBatisDao
public interface OperatorCountMapper extends BaseMapper<OperatorCountModel,String> {
	/**
	 * 通话信息
	 * @param phone
	 * @return
	 */
	OperatorCountModel operatorVoicesInfo(Map<String,Object> params);
	
	/**
	 * 月费账单
	 * @param phone
	 * @return
	 */
	Double operatorMonthAmt(String phone);
	
	/**
	 * 入网信息
	 * @param phone
	 * @return
	 */
	Date operatorJoinDate(String phone);
	
	/**
	 * 联系次数多的号码个数
	 * @param phone
	 * @return
	 */
	OperatorCountModel operatorVoicesPhone(Map<String,Object> params);
	
	/**
	 * 联系人借款信息
	 * @param phone
	 * @return
	 */
	OperatorCountModel concatsBorrowInfo(Map<String,Object> params);
	
	/**
	 * 紧急联系人最小联系次数
	 * @param userId
	 * @return
	 */
	Integer emerConcatTimes(Map<String,Object> params);
}
