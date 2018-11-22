package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.mapper.KanyaPayFlowMapper;
import com.xindaibao.cashloan.cl.mapper.KanyaPayRecordMapper;
import com.xindaibao.cashloan.cl.model.kenya.KanyaPayFlow;
import com.xindaibao.cashloan.cl.model.kenya.KanyaPayRecord;
import com.xindaibao.cashloan.cl.service.KanyaPayFlowService;
import com.xindaibao.cashloan.cl.service.KanyaPayRecordService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 付款记录表ServiceImpl
 */
 
@Service("kanyaPayRecordService")
public class KanyaPayRecordServiceImpl extends BaseServiceImpl<KanyaPayRecord, Long> implements KanyaPayRecordService {
	
    private static final Logger logger = LoggerFactory.getLogger(KanyaPayRecordServiceImpl.class);
   
    @Resource
    private KanyaPayRecordMapper kanyaPayRecordMapper;

	@Override
	public BaseMapper<KanyaPayRecord, Long> getMapper() {
		return kanyaPayRecordMapper;
	}

	
}