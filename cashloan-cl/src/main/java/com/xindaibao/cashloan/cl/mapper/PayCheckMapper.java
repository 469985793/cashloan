package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.domain.PayCheck;
import com.xindaibao.cashloan.cl.model.ManagePayCheckModel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;


/**
 * 资金对账记录Dao
 */
@RDBatisDao
public interface PayCheckMapper extends BaseMapper<PayCheck,Long> {

    
	/**
	 *
	 * @param searchMap
	 * @return
	 */
	 List<ManagePayCheckModel> page(Map<String, Object> searchMap);

}
