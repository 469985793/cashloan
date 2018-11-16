package com.xindaibao.creditrank.cr.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.creditrank.cr.domain.CreditLog;
import com.xindaibao.creditrank.cr.model.CreditLogModel;

/**
 * 授信额度记录Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2016-12-16 10:31:23


 * 

 */
@RDBatisDao
public interface CreditLogMapper extends BaseMapper<CreditLog,Long> {

	/**
	 * 分页查询
	 * @param searchMap
	 * @return
	 */
	List<CreditLogModel> findLog(Map<String, Object> searchMap);

}
