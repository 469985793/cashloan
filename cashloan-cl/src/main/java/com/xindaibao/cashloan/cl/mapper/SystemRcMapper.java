package com.xindaibao.cashloan.cl.mapper;

import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.cl.model.DayPassApr;
import com.xindaibao.cashloan.cl.model.SystemDayData;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;

/**
 * 平台数据日报
 * @author
 * @version 1.0
 * @date 2017年3月20日下午5:18:19


 * 

 */
@RDBatisDao
public interface SystemRcMapper {

	/**
	 * 平台数据日报
	 * @return
	 */
	List<SystemDayData> dayData(Map<String,Object> params);
	
	/**
	 * 每日通过率
	 */
	List<DayPassApr> dayApr(Map<String,Object> params);
}
