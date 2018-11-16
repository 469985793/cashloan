package com.xindaibao.cashloan.cl.service.impl;


import com.xindaibao.cashloan.cl.domain.ClFraudTdBasic;
import com.xindaibao.cashloan.cl.mapper.ClFraudTdBasicMapper;
import com.xindaibao.cashloan.cl.service.ClFraudTdBasicService;
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
 * @date 2017-11-10 20:41:21



 */
 
@Service("clFraudTdBasicService")
public class ClFraudTdBasicServiceImpl extends BaseServiceImpl<ClFraudTdBasic, Long> implements ClFraudTdBasicService {
	
    private static final Logger logger = LoggerFactory.getLogger(ClFraudTdBasicServiceImpl.class);
   
    @Resource
    private ClFraudTdBasicMapper clFraudTdBasicMapper;
	@Override
	public BaseMapper<ClFraudTdBasic, Long> getClFraudTdBasicMapper() {
		return clFraudTdBasicMapper;
	}

	@Override
	public BaseMapper<ClFraudTdBasic, Long> getMapper() {
		return clFraudTdBasicMapper;
	}
	
}