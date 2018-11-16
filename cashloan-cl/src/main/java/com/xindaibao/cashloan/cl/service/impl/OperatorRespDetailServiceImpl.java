package com.xindaibao.cashloan.cl.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xindaibao.cashloan.cl.domain.OperatorRespDetail;
import com.xindaibao.cashloan.cl.mapper.OperatorRespDetailMapper;
import com.xindaibao.cashloan.cl.service.OperatorRespDetailService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;


/**
 * 运营商认证通知详情表ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-17 12:38:22



 */
 
@Service("operatorRespDetailService")
public class OperatorRespDetailServiceImpl extends BaseServiceImpl<OperatorRespDetail, Long> implements OperatorRespDetailService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(OperatorRespDetailServiceImpl.class);
   
    @Resource
    private OperatorRespDetailMapper operatorRespDetailMapper;


	@Override
	public BaseMapper<OperatorRespDetail, Long> getMapper() {
		return operatorRespDetailMapper;
	}
	
}