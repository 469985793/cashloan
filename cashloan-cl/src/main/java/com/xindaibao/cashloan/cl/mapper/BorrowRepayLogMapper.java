package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.BorrowRepayLog;
import com.xindaibao.cashloan.cl.model.BorrowRepayLogModel;
import com.xindaibao.cashloan.cl.model.ManageBRepayLogModel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 还款计录Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-14 13:46:12


 * 

 */
@RDBatisDao
public interface BorrowRepayLogMapper extends BaseMapper<BorrowRepayLog,Long> {

	List<ManageBRepayLogModel> listModel(Map<String, Object> params);

	/**
	 * 查询所有
	 * @param searchMap
	 * @return
	 */
	List<BorrowRepayLogModel> listSelModel(Map<String, Object> searchMap);

    /**
     * 退还补扣修改还款记录
     * @param paramMap
     * @return
     */
	int refundDeduction(Map<String, Object> paramMap);
	
	BorrowRepayLog findLast(Map<String, Object> searchMap);
}
