package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.PayLog;
import com.xindaibao.cashloan.cl.model.ManagePayLogModel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 支付记录Mapper
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-07 16:18:56


 * 

 */
@RDBatisDao
public interface PayLogMapper extends BaseMapper<PayLog, Long> {

	/**
	 * 列表查询
	 * 
	 * @param searchMap
	 * @return
	 */
	List<ManagePayLogModel> page(Map<String, Object> searchMap);

	/**
	 * 查看详情
	 * 
	 * @param id
	 * @return
	 */
	ManagePayLogModel findDetail(Long id);
	
	
	/**
	 * 更新状态校验订单状态
	 * @return
	 */
	int updateState(Map<String,Object> paramMap);
	
	/**
	 * 据条件查询对账List
	 * 
	 * @param paramMap
	 * @return
	 */
	List<PayLog> findCheckList(Map<String, Object> paramMap);
	
	/**
	 * 查询最新的代扣日志
	 * 
	 * @param paramMap
	 * @return
	 */
	PayLog findLatestOne(Map<String, Object> paramMap);
	
	/**
	 * 查询代扣次数
	 * @param borrowId
	 * @return
	 */
	int doRepaymentCount(long borrowId);
}
