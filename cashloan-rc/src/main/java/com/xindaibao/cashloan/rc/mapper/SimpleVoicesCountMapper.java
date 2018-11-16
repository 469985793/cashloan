package com.xindaibao.cashloan.rc.mapper;

import org.apache.ibatis.annotations.Param;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.rc.domain.SimpleVoicesCount;

/**
 * 风控数据统计-（简）通话记录统计Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-07-06 18:11:18



 */
@RDBatisDao
public interface SimpleVoicesCountMapper extends BaseMapper<SimpleVoicesCount, Long> {
	
	/**
	 * 通话记录总次数
	 * @param tableName
	 * @param userId
	 * @return
	 */
	int countOne(@Param("tableName")String tableName, @Param("userId")long userId);

}
