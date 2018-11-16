package com.xindaibao.cashloan.rc.mapper;

import com.xindaibao.cashloan.rc.domain.SimpleContactCount;
import org.apache.ibatis.annotations.Param;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 风控数据统计-（简）通讯录统计Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-07-06 18:12:49



 */
@RDBatisDao
public interface SimpleContactCountMapper extends BaseMapper<SimpleContactCount, Long> {

	/**
	 * 通讯录总条数
	 * @param tableName
	 * @param userId
	 * @return
	 */
	int countOne(@Param("tableName")String tableName, @Param("userId")long userId);

}
