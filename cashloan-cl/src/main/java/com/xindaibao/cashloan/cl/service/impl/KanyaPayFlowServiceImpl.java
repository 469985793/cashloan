package com.xindaibao.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.cl.domain.AccfundInfo;
import com.xindaibao.cashloan.cl.domain.AccfundLog;
import com.xindaibao.cashloan.cl.mapper.AccfundInfoMapper;
import com.xindaibao.cashloan.cl.mapper.AccfundLogMapper;
import com.xindaibao.cashloan.cl.mapper.KanyaPayFlowMapper;
import com.xindaibao.cashloan.cl.mapper.UserAuthMapper;
import com.xindaibao.cashloan.cl.model.kenya.KanyaPayFlow;
import com.xindaibao.cashloan.cl.service.AccfundInfoService;
import com.xindaibao.cashloan.cl.service.KanyaPayFlowService;
import com.xindaibao.cashloan.core.common.exception.BussinessException;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.mapper.UserBaseInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tool.util.StringUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 付款流水表ServiceImpl
 */
 
@Service("kanyaPayFlowService")
public class KanyaPayFlowServiceImpl extends BaseServiceImpl<KanyaPayFlow, Long> implements KanyaPayFlowService {
	
    private static final Logger logger = LoggerFactory.getLogger(KanyaPayFlowServiceImpl.class);
   
    @Resource
    private KanyaPayFlowMapper kanyaPayFlowMapper;

	@Override
	public BaseMapper<KanyaPayFlow, Long> getMapper() {
		return kanyaPayFlowMapper;
	}


	
}