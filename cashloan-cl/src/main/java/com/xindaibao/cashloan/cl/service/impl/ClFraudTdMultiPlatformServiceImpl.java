package com.xindaibao.cashloan.cl.service.impl;


import com.xindaibao.cashloan.cl.domain.ClFraudTdMultiPlatform;
import com.xindaibao.cashloan.cl.mapper.ClFraudTdMultiPlatformMapper;
import com.xindaibao.cashloan.cl.service.ClFraudTdMultiPlatformService;
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
 * @date 2017-11-10 20:47:39



 */
 
@Service("clFraudTdMultiPlatformService")
public class ClFraudTdMultiPlatformServiceImpl extends BaseServiceImpl<ClFraudTdMultiPlatform, Long> implements ClFraudTdMultiPlatformService {
	
    private static final Logger logger = LoggerFactory.getLogger(ClFraudTdMultiPlatformServiceImpl.class);
   
    @Resource
    private ClFraudTdMultiPlatformMapper clFraudTdMultiPlatformMapper;
	@Override
	public BaseMapper<ClFraudTdMultiPlatform, Long> getClFraudTdMultiPlatformMapperMapper() {
		return clFraudTdMultiPlatformMapper;
	}

	@Override
	public BaseMapper<ClFraudTdMultiPlatform, Long> getMapper() {
		return clFraudTdMultiPlatformMapper;
	}
	
}