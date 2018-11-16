package com.xindaibao.cashloan.cl.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.mapper.PayCheckMapper;
import com.xindaibao.cashloan.cl.model.ManagePayCheckModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.domain.PayCheck;
import com.xindaibao.cashloan.cl.service.PayCheckService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;

/**
 * 资金对账记录ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-04-13 17:12:20



 */
@Service("payCheckService")
public class PayCheckServiceImpl extends BaseServiceImpl<PayCheck, Long> implements PayCheckService {
	
    private static final Logger logger = LoggerFactory.getLogger(PayCheckServiceImpl.class);
   
    @Resource
    private PayCheckMapper payCheckMapper;

	@Override
	public BaseMapper<PayCheck, Long> getMapper() {
		return payCheckMapper;
	}

	@Override
	public boolean save(PayCheck payCheck) {
		int result = payCheckMapper.save(payCheck);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Page<ManagePayCheckModel> page(int current, int pageSize,
                                          Map<String, Object> searchMap) {
		PageHelper.startPage(current, pageSize);
		Page<ManagePayCheckModel> page = (Page<ManagePayCheckModel>) payCheckMapper.page(searchMap);
		return page;
	}

	@Override
	public PayCheck findSelective(Map<String, Object> searchMap) {
		PayCheck payCheck = null;
		try {
			payCheck = payCheckMapper.findSelective(searchMap);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return payCheck;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List listPayCheck(Map<String, Object> params) {
		List<ManagePayCheckModel> list = payCheckMapper.page(params);
		return list;
	}
	
}