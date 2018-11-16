package com.xindaibao.cashloan.rc.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.service.BaseService;
import com.xindaibao.cashloan.rc.domain.Statistics;

/**
 * 风控数据统计分类Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-13 17:52:52




 */
public interface StatisticsService extends BaseService<Statistics, Long>{

	/**
	 * 风控数据统计分类分页查询
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<Statistics> page(Map<String,Object> params,int currentPage,int pageSize);
	
	/**
	 * 查询有效数据分类--下拉框使用
	 * @param params
	 * @return
	 */
	List<Statistics> listAll();
}
