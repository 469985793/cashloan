package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.UrgeRepayOrder;
import com.xindaibao.cashloan.cl.model.UrgeRepayCountModel;
import com.xindaibao.cashloan.cl.model.UrgeRepayOrderModel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;


/**
 * 催款计划表Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-07 14:21:58


 * 

 */
@RDBatisDao
public interface UrgeRepayOrderMapper extends BaseMapper<UrgeRepayOrder,Long> {

	List<UrgeRepayOrder> listSelective(Map<String, Object> paramMap);

	List<UrgeRepayOrderModel> listModel(Map<String, Object> params);

	List<UrgeRepayOrder> listAll(Map<String, Object> params);

	String allOrderCount(Map<String, Object> map);

	String successCount(Map<String, Object> map);

	String count(Map<String, Object> map);

	List<UrgeRepayOrder> listOrder(Map<String, Object> map);

	String newOrderByUser(Map<String, Object> map);

	String repayOrderByUser(Map<String, Object> map);

	String successOrderByUser(Map<String, Object> map);

	String failOrderByUser(Map<String, Object> map);

	String countByUser(Map<String, Object> map);

	String allOrderSum(Map<String, Object> map);

	String allSuccessSum(Map<String, Object> map);

	String allFailSum(Map<String, Object> map);
	
	List<UrgeRepayCountModel> listSysUserByRole(Map<String, Object> params);
	
	int countOrder(Map<String, Object> params);
	
}
