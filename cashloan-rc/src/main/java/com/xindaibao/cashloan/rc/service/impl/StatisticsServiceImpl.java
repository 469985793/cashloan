package com.xindaibao.cashloan.rc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.rc.domain.Statistics;
import com.xindaibao.cashloan.rc.mapper.StatisticsMapper;
import com.xindaibao.cashloan.rc.service.StatisticsService;


/**
 * 风控数据统计分类ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-13 17:52:52




 */
 
@Service("statisticsService")
public class StatisticsServiceImpl extends BaseServiceImpl<Statistics, Long> implements StatisticsService {
	
    @Resource
    private StatisticsMapper statisticsMapper;

	@Override
	public BaseMapper<Statistics, Long> getMapper() {
		return statisticsMapper;
	}

	@Override
	public Page<Statistics> page(Map<String, Object> params, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		Page<Statistics> page = (Page<Statistics>) statisticsMapper.listSelective(params);
		return page;
	}

	@Override
	public List<Statistics> listAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("state", "10");
		return statisticsMapper.listSelective(params);
	}
	
}