package com.xindaibao.cashloan.cl.mapper;

import java.util.Map;

import com.xindaibao.cashloan.cl.domain.UrgeRepayOrderLog;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 催款记录表Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-07 14:28:22


 * 

 */
@RDBatisDao
public interface UrgeRepayOrderLogMapper extends BaseMapper<UrgeRepayOrderLog,Long> {
	
	/**
	 * 统计数量
	 * @param params
	 * @return
	 */
	int countLog(Map<String, Object> params);

}
