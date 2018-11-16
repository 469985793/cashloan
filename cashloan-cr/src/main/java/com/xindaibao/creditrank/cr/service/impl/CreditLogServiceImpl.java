package com.xindaibao.creditrank.cr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.creditrank.cr.domain.CreditLog;
import com.xindaibao.creditrank.cr.model.CreditLogModel;
import com.xindaibao.creditrank.cr.service.CreditLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.creditrank.cr.mapper.CreditLogMapper;


/**
 * 授信额度记录ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2016-12-16 10:31:23


 * 

 */
 
@Service("creditLogService")
public class CreditLogServiceImpl extends BaseServiceImpl<CreditLog, Long> implements CreditLogService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CreditLogServiceImpl.class);
   
    @Resource
    private CreditLogMapper creditLogMapper;




	@Override
	public BaseMapper<CreditLog, Long> getMapper() {
		return creditLogMapper;
	}
	
	@Override
	public int save(CreditLog log) {
		return creditLogMapper.save(log);
	}

	@Override
	public Page<CreditLogModel> page(Map<String, Object> searchMap,
                                     int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<CreditLogModel> list = creditLogMapper.findLog(searchMap);
		return (Page<CreditLogModel>)list;
	}
	
}