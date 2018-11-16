package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.BorrowRepay;
import com.xindaibao.cashloan.cl.model.BorrowRepayModel;
import com.xindaibao.cashloan.cl.model.ManageBRepayModel;
import com.xindaibao.cashloan.cl.model.ManageBorrowModel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 还款计划Dao
 */
@RDBatisDao
public interface BorrowRepayMapper extends BaseMapper<BorrowRepay,Long> {

	int updateByBorrowId(Map<String, Object> repayMap);
	/**
	 * 后台还款记录列表 
	 * @param params
	 * @return
	 */
	List<ManageBRepayModel> listModel(Map<String, Object> params);
	
	/**
	 * 逾期更新数据
	 * @param data
	 * @return
	 */
	int updateLate(BorrowRepay data);
	/**
	 * 
	 * @param paramMap
	 */
	int updateParam(Map<String, Object> paramMap);
	/**
	 * 催款管理
	 * @param params
	 * @return
	 */
	List<ManageBorrowModel> listRepayModel(Map<String, Object> params);
	/**
	 * 逾期未入催
	 * @param params
	 * @return
	 */
	List<ManageBorrowModel> listModelNotUrge(Map<String, Object> params);
	/**
	 * 查询所有
	 * @param searchMap
	 * @return
	 */
	List<BorrowRepayModel> listSelModel(Map<String, Object> searchMap);

	/**
	 * 查询借款成功未还款还款已过还款时间记录(放款成功及逾期但未还款的)
	 * 
	 * @param paramMap
	 * @return
	 */
	List<BorrowRepay> findUnRepay(Map<String, Object> paramMap);
	/**
	 * 查询还款
	 * @param borrowId
	 * @return
	 */
	BorrowRepayModel findOverdue(long borrowId);


	/**
	 * 查询当天还款计划总额
	 * 
	 * @param map
	 * @return
	 */
	double findRepayTotal();
	
	/**
	 * 据borrowId查询还款计划
	 * 
	 * @param borrowId
	 * @return
	 */
	BorrowRepay findByBorrowId(Long borrowId);

	/**
	 * 当天
	 * 查询借款成功未还款
	 * @param paramMap
	 * @return
	 */
	List<BorrowRepay> findUnRepayIntraday(Map<String, Object> paramMap);
	
}
