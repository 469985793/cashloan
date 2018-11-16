package com.xindaibao.cashloan.cl.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.domain.PayReqLog;
import com.xindaibao.cashloan.cl.mapper.PayReqLogMapper;
import com.xindaibao.cashloan.cl.model.ManagePayReqLogModel;
import com.xindaibao.cashloan.cl.service.PayReqLogService;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;


/**
 * 支付请求记录ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-07 16:18:30


 * 

 */
 
@Service("payReqLogService")
public class PayReqLogServiceImpl extends BaseServiceImpl<PayReqLog, Long> implements PayReqLogService {
	
    @Resource
    private PayReqLogMapper payReqLogMapper;

	@Override
	public boolean save(PayReqLog log) {
		log.setCreateTime(DateUtil.getNow());
		int result = payReqLogMapper.save(log);
		if (result > 0L) {
			return true;
		}
		return false;
	}

	@Override
	public PayReqLog findByOrderNo(String orderNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderNo", orderNo);
		return payReqLogMapper.findSelective(paramMap);
	}

	@Override
	public Page<ManagePayReqLogModel> page(int current, int pageSize, Map<String, Object> searchMap) {
		PageHelper.startPage(current,pageSize);
		Page<ManagePayReqLogModel> page = (Page<ManagePayReqLogModel>) payReqLogMapper.page(searchMap);
		return page;
	}
	
	@Override
	public ManagePayReqLogModel findDetail(Long id){
		return payReqLogMapper.findDetail(id);
	}

	@Override
	public BaseMapper<PayReqLog, Long> getMapper() {
		return payReqLogMapper;
	}
	
}