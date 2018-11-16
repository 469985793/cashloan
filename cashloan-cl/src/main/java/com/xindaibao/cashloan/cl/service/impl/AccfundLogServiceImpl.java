package com.xindaibao.cashloan.cl.service.impl;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.domain.AccfundLog;
import com.xindaibao.cashloan.cl.service.AccfundLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xindaibao.cashloan.cl.mapper.AccfundLogMapper;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;


/**
 * 公积金详细信息表(流水)ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-16 11:15:39




 */
 
@Service("accfundLogService")
public class AccfundLogServiceImpl extends BaseServiceImpl<AccfundLog, Long> implements AccfundLogService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(AccfundLogServiceImpl.class);
   
    @Resource
    private AccfundLogMapper accfundLogMapper;




	@Override
	public BaseMapper<AccfundLog, Long> getMapper() {
		return accfundLogMapper;
	}
	
}