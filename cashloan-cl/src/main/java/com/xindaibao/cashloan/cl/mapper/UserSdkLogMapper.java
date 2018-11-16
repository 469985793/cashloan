package com.xindaibao.cashloan.cl.mapper;

import java.util.Map;

import com.xindaibao.cashloan.cl.domain.UserSdkLog;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;


/**
 * sdk识别记录表Dao
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-20 09:52:08




 */
@RDBatisDao
public interface UserSdkLogMapper extends BaseMapper<UserSdkLog,Long> {

	/**
	 * 查询当日可识别次数
	 * @param searchMap
	 * @return
	 */
	int countDayTime(Map<String, Object> searchMap);

    

}
