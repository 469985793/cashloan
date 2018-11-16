package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.ClFraudTdHitList;
import com.xindaibao.cashloan.cl.mapper.ClFraudTdHitListMapper;
import com.xindaibao.cashloan.cl.service.ClFraudTdHitListService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 风控数据统计-（简）通话记录统计ServiceImpl
 * 
 * @author xx
 * @version 1.0.0
 * @date 2017-11-10 20:45:10



 */
 
@Service("clFraudTdHitListService")
public class ClFraudTdHitListServiceImpl extends BaseServiceImpl<ClFraudTdHitList, Long> implements ClFraudTdHitListService {
	
    private static final Logger logger = LoggerFactory.getLogger(ClFraudTdHitListServiceImpl.class);
   
    @Resource
    private ClFraudTdHitListMapper clFraudTdHitListMapper;
	@Override
	public BaseMapper<ClFraudTdHitList, Long> getClFraudTdHitListMapper() {
		return clFraudTdHitListMapper;
	}

	@Override
	public BaseMapper<ClFraudTdHitList, Long> getMapper() {
		return clFraudTdHitListMapper;
	}
	
}