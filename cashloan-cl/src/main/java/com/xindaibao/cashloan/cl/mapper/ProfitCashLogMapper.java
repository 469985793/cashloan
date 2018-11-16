package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.ProfitCashLog;
import com.xindaibao.cashloan.cl.model.ManageProfitAmountModel;
import com.xindaibao.cashloan.cl.model.ManageProfitLogModel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 分润提现记录Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-18 16:51:48


 * 

 */
@RDBatisDao
public interface ProfitCashLogMapper extends BaseMapper<ProfitCashLog,Long> {

	/**
	 * 查询分润明细
	 * @return
	 */
	List<ManageProfitLogModel> findLog(Map<String, Object> map);
	
	/**
	 * 提现记录查询
	 * @param map
	 * @return
	 */
	List<ManageProfitAmountModel> findAmount(Map<String, Object> map);

	/**
	 * 管理员查询分润明细
	 * @param map
	 * @return
	 */
	List<ManageProfitLogModel> findSysLog(Map<String, Object> map);

	/**
	 * 管理员查看提现
	 * @param map
	 * @return
	 */
	List<ManageProfitAmountModel> findSysAmount(Map<String, Object> map);
}
