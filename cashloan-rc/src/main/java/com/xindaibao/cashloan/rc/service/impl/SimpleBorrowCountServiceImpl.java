package com.xindaibao.cashloan.rc.service.impl;

import javax.annotation.Resource;

import com.xindaibao.cashloan.rc.domain.SimpleBorrowCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.rc.mapper.SimpleBorrowCountMapper;
import com.xindaibao.cashloan.rc.service.SimpleBorrowCountService;


/**
 * 风控数据统计-（简）借款统计ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-07-06 18:12:18



 */
 
@Service("simpleBorrowCountService")
public class SimpleBorrowCountServiceImpl extends BaseServiceImpl<SimpleBorrowCount, Long> implements SimpleBorrowCountService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SimpleBorrowCountServiceImpl.class);
   
    @Resource
    private SimpleBorrowCountMapper simpleBorrowCountMapper;

	@Override
	public BaseMapper<SimpleBorrowCount, Long> getMapper() {
		return simpleBorrowCountMapper;
	}

	@Override
	public int countOne(long userId) {
		int count = simpleBorrowCountMapper.countOne(userId);
		
		SimpleBorrowCount simpleBorrowCount = new SimpleBorrowCount();
		simpleBorrowCount.setUserId(userId);
		simpleBorrowCount.setCountOne(count);
		simpleBorrowCount.setCreateTime(DateUtil.getNow());
		
		return simpleBorrowCountMapper.save(simpleBorrowCount);
	}
	
}