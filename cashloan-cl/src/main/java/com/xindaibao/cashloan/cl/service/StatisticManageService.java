package com.xindaibao.cashloan.cl.service;


import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.model.DayNeedAmountModel;
import com.xindaibao.cashloan.cl.model.ExpendDetailModel;
import com.xindaibao.cashloan.cl.model.IncomeAndExpendModel;
import com.xindaibao.cashloan.cl.model.IncomeDetailModel;

/**
 * 统计管理
 * @author
 * @version 1.0
 * @date 2017年3月21日下午4:42:06


 * 

 */
public interface StatisticManageService {

	/**
	 * 每日收支记录
	 * @return
	 */
	Page<IncomeAndExpendModel> repayIncomeAndExpend(Map<String, Object> params, Integer current, Integer pageSize);
	
	/**
	 * 每日未还本金
	 * @return
	 */
	Page<DayNeedAmountModel> dayNeedAmount(Map<String, Object> params,Integer current,Integer pageSize);
	
	/**
	 * 收入明细
	 * @return
	 */
	Page<IncomeDetailModel> incomeDetail(Map<String, Object> params,Integer current,Integer pageSize);
	
	/**
	 * 支出明细
	 * @return
	 */
	Page<ExpendDetailModel> expendDetail(Map<String, Object> params,Integer current,Integer pageSize);
	
	/**
	 * 收入总额
	 * @param params
	 * @return
	 */
	Double incomeSum(Map<String,Object> params);
	
	/**
	 * 支出总额
	 * @param params
	 * @return
	 */
	Double expendSum(Map<String,Object> params);
}
