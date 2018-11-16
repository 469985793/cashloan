package com.xindaibao.cashloan.cl.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.domain.PayRespLog;
import com.xindaibao.cashloan.cl.service.PayRespLogService;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.mapper.PayRespLogMapper;
import com.xindaibao.cashloan.cl.model.ManagePayRespLogModel;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;


/**
 * 支付响应记录ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-07 16:18:10


 * 

 */
 
@Service("payRespLogService")
public class PayRespLogServiceImpl extends BaseServiceImpl<PayRespLog, Long> implements PayRespLogService {
	
    @Resource
    private PayRespLogMapper payRespLogMapper;

    
	@Override
	public boolean save(PayRespLog log) {
		log.setCreateTime(DateUtil.getNow());
		int result = payRespLogMapper.save(log);
		if (result > 0L) {
			return true;
		}
		return false;
	}

	
	@Override
	public Page<ManagePayRespLogModel> page(int current, int pageSize,
			Map<String, Object> searchMap) {
		PageHelper.startPage(current,pageSize);
		Page<ManagePayRespLogModel>  page = (Page<ManagePayRespLogModel>) payRespLogMapper.page(searchMap);
		return page;
	}

	@Override
	public ManagePayRespLogModel findDetail(Long id) {
		return payRespLogMapper.findDetail(id);
	}
	
	@Override
	public BaseMapper<PayRespLog, Long> getMapper() {
		return payRespLogMapper;
	}
}