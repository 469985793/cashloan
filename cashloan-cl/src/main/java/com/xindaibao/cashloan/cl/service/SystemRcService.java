package com.xindaibao.cashloan.cl.service;


import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.model.DayPassApr;
import com.xindaibao.cashloan.cl.model.SystemDayData;

/**
 * 平台数据日报
 * @author
 * @version 1.0
 * @date 2017年3月20日下午4:56:21


 * 

 */
public interface SystemRcService {

	/**
	 * 平台数据日报
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<SystemDayData> findDayData(Map<String,Object> params, Integer current, Integer pageSize);
	
	/**
	 * 每日通过率
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<DayPassApr> findDayApr(Map<String,Object> params, Integer current, Integer pageSize);
}
