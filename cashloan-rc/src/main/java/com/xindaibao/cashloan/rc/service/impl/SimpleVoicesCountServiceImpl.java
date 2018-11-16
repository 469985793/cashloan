package com.xindaibao.cashloan.rc.service.impl;

import javax.annotation.Resource;

import com.xindaibao.cashloan.rc.domain.SimpleVoicesCount;
import com.xindaibao.cashloan.rc.mapper.SimpleVoicesCountMapper;
import com.xindaibao.cashloan.rc.service.SimpleVoicesCountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.ShardTableUtil;


/**
 * 风控数据统计-（简）通话记录统计ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-07-06 18:11:18



 */
 
@Service("simpleVoicesCountService")
public class SimpleVoicesCountServiceImpl extends BaseServiceImpl<SimpleVoicesCount, Long> implements SimpleVoicesCountService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SimpleVoicesCountServiceImpl.class);
   
    @Resource
    private SimpleVoicesCountMapper simpleVoicesCountMapper;

	@Override
	public BaseMapper<SimpleVoicesCount, Long> getMapper() {
		return simpleVoicesCountMapper;
	}
	
	@Override
	public int countOne(long userId) {
//		String tableName = ShardTableUtil.generateTableNameById("cl_operator_voices", userId, 30000);
		int count = simpleVoicesCountMapper.countOne("cl_operator_td_call_record", userId);
		
		SimpleVoicesCount simpleVoicesCount = new SimpleVoicesCount();
		simpleVoicesCount.setUserId(userId);
		simpleVoicesCount.setCountOne(count);
		simpleVoicesCount.setCreateTime(DateUtil.getNow());
		
		return simpleVoicesCountMapper.save(simpleVoicesCount);
	}
	
}