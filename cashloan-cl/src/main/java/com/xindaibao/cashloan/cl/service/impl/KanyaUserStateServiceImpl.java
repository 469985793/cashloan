package com.xindaibao.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.cl.domain.AccfundInfo;
import com.xindaibao.cashloan.cl.domain.AccfundLog;
import com.xindaibao.cashloan.cl.mapper.AccfundInfoMapper;
import com.xindaibao.cashloan.cl.mapper.AccfundLogMapper;
import com.xindaibao.cashloan.cl.mapper.KanyaUserStateMapper;
import com.xindaibao.cashloan.cl.mapper.UserAuthMapper;
import com.xindaibao.cashloan.cl.model.kenya.KanyaUserState;
import com.xindaibao.cashloan.cl.service.AccfundInfoService;
import com.xindaibao.cashloan.cl.service.KanyaUserStateService;
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


 
@Service("kanyaUserStateService")
public class KanyaUserStateServiceImpl extends BaseServiceImpl<KanyaUserState, Long> implements KanyaUserStateService {
	
    private static final Logger logger = LoggerFactory.getLogger(KanyaUserStateServiceImpl.class);
   
    @Resource
    private KanyaUserStateMapper kanyaUserStateMapper;

	@Override
	public BaseMapper<KanyaUserState, Long> getMapper() {
		return kanyaUserStateMapper;
	}

	@Override
	public void updateCurrentState(KanyaUserState kanyaUserState) {
		kanyaUserStateMapper.updateCurrentState(kanyaUserState);
	}
	
}