package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.OperatorReqLog;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 运营商认证中间表Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-01 16:27:59


 * 

 */
@RDBatisDao
public interface OperatorReqLogMapper extends BaseMapper<OperatorReqLog,Long> {

	/**
	 * 根据用户查找认证订单号
	 * @param userId
	 * @return
	 */
	String findOrderByUserId(Long userId);
	/**
	 * 根据用户查找当天认证记录
	 * @param paramMap
	 * @return
	 */
	List<OperatorReqLog> listByUserId(Map<String, Object> paramMap);
	/**
	 * 查询最后一条符合条件的记录
	 * @param paramMap
	 * @return
	 */
	OperatorReqLog findLastRecord(Map<String, Object> paramMap);

}
