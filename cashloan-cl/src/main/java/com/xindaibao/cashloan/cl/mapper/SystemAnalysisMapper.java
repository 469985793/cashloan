package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.model.OverdueAnalisisModel;
import com.xindaibao.cashloan.cl.model.RepayAnalisisModel;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 运营分析
 */
@RDBatisDao
public interface SystemAnalysisMapper {
	
	/**
	 * 还款统计
	 * @param params
	 * @return
	 */
	List<RepayAnalisisModel> repayAnalisis(Map<String,Object> params);

	/**
	 * 每月逾期统计
	 * @param params
	 * @return
	 */
	List<OverdueAnalisisModel> overdueAnalisis(Map<String,Object> params);

	/**
	 * 还款笔数
	 * @param map 
	 */
	String repayCount(Map<String, Object> map);

	/**
	 * 逾期还款笔数
	 * @param map
	 * @return
	 */
	String overdueCount(Map<String, Object> map);

	/**
	 * 还款金额
	 * @param map
	 * @return
	 */
	String repayAmt(Map<String, Object> map);

	/**
	 * 逾期还款金额
	 * @param map
	 * @return
	 */
	String penaltyRepayAmt(Map<String, Object> map);

	/**
	 * 得到有数据的月份
	 * @return
	 */
	List<String> mouthList();
}
