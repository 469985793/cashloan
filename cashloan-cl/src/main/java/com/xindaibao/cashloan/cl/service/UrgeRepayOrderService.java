package com.xindaibao.cashloan.cl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.UrgeRepayOrder;
import com.xindaibao.cashloan.cl.model.UrgeRepayCountModel;
import com.xindaibao.cashloan.cl.model.UrgeRepayOrderModel;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * 催款计划表Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-07 14:21:58


 * 

 */
public interface UrgeRepayOrderService extends BaseService<UrgeRepayOrder, Long>{
	/**
	 * 催款记录信息
	 * @param params
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<UrgeRepayOrder> listItem(Map<String, Object> params, int current,
			int pageSize);

	/**
	 * 催款记录详细信息
	 * @param params
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<UrgeRepayOrderModel> listModel(Map<String, Object> params, int current,
                                        int pageSize);
	/**
	 * 指派催收人员
	 * @param params
	 * @return
	 */
	int orderAllotUser(Map<String, Object> params);
	/**
	 * 根据借款id添加催收信息
	 * @param id
	 * @return
	 */
	Map<String, Object> saveOrder(Long id);
	/**
	 * 查询所有催收信息
	 * @param hashMap
	 * @return
	 */
	List<UrgeRepayOrder> listAll(HashMap<String, Object> hashMap);
	/**
	 *催收人员统计列表 
	 * @param params
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<UrgeRepayCountModel> memberCount(Map<String, Object> params, int current,
			int pageSize);

	/**
	 * 催收订单统计
	 * @param params
	 * @return
	 */
	List<UrgeRepayCountModel> orderCount(Map<String, Object> params);
	/**
	 * 催回率统计
	 * @param params
	 * @param current
	 * @param pageSize
	 * @return
	 */
	List<UrgeRepayCountModel> urgeCount(Map<String, Object> params);
	/**
	 * 催收员每日统计
	 * @param params
	 * @return
	 */
	List<UrgeRepayCountModel> memberDayCount(Map<String, Object> params);

	/**
	 * 查询催收订单
	 * @param borrowId 
	 * @return
	 */
	UrgeRepayOrder findByBorrowId(long borrowId);

	/**
	 * 修改逾期信息
	 * @param uroMap
	 */
	int updateLate(Map<String, Object> uroMap);

	UrgeRepayOrder findOrderByMap(Map<String, Object> orderMap);

	/**
	 * 导出查询
	 * @param params
	 * @return
	 */
	List<?> listUrgeRepayOrder(Map<String, Object> params);

	/**
	 * 催收导出查询
	 * @param params
	 * @return
	 */
	List<?> listUrgeLog(Map<String, Object> params);


}
