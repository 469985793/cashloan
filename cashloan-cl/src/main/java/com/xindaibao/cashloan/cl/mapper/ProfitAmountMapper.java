package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.ProfitAmount;
import com.xindaibao.cashloan.cl.model.ManageProfitAmountModel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 分润资金Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-18 16:29:51


 * 

 */
@RDBatisDao
public interface ProfitAmountMapper extends BaseMapper<ProfitAmount,Long> {

	/**
	 * 可提现查询
	 * @param map
	 * @return
	 */
	List<ManageProfitAmountModel> findAmount(Map<String, Object> map);

	/**
	 * 管理员可提现查看
	 * @param map
	 * @return
	 */
	List<ManageProfitAmountModel> findSysAmount(Map<String, Object> map);

	List<ProfitAmount> listNoCash();
	
	/**
	 * 增加未打款金额
	 * @param map
	 * @return
	 */
	int addNocashedAmount(Map<String, Object> map);
}
