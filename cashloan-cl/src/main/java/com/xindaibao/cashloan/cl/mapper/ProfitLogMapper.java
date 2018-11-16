package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.ProfitLog;
import com.xindaibao.cashloan.cl.model.ManageCashLogModel;
import com.xindaibao.cashloan.cl.model.ProfitLogModel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 分润记录Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-18 17:04:10


 * 

 */
@RDBatisDao
public interface ProfitLogMapper extends BaseMapper<ProfitLog,Long> {

	/**
	 * 邀请明细
	 * @param searchMap
	 * @return
	 */
	List<ProfitLogModel> listInfo(Map<String, Object> searchMap);

	/**
	 * 提现记录查询
	 * @param map
	 * @return
	 */
	List<ManageCashLogModel> findCashLog(Map<String, Object> map);

	/**
	 * 管理员提现记录查询
	 * @param map
	 * @return
	 */
	List<ManageCashLogModel> findSysCashLog(Map<String, Object> map);
	
	/**
	 * 数量统计
	 * @param map
	 * @return
	 */
	int count(Map<String, Object> map);

}
