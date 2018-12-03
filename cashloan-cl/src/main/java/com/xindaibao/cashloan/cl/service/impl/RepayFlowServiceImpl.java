package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.mapper.RepayFlowMapper;
import com.xindaibao.cashloan.cl.model.kenya.RepayFlow;
import com.xindaibao.cashloan.cl.service.RepayFlowService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 还款记录ServiceImpl
 */
 
@Service("repayFlowService")
public class RepayFlowServiceImpl extends BaseServiceImpl<RepayFlow, Long> implements RepayFlowService {
	
    private static final Logger logger = LoggerFactory.getLogger(RepayFlowServiceImpl.class);
   
    @Resource
    private RepayFlowMapper repayFlowMapper;

	@Override
	public BaseMapper<RepayFlow, Long> getMapper() {
		return repayFlowMapper;
	}
}