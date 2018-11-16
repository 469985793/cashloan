package com.xindaibao.cashloan.cl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.domain.ProfitCashLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.StringUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.mapper.ProfitCashLogMapper;
import com.xindaibao.cashloan.cl.model.ManageProfitLogModel;
import com.xindaibao.cashloan.cl.service.ProfitCashLogService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.mapper.UserMapper;

/**
 * 分润提现记录ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-18 16:51:48


 * 

 */
 
@Service("profitCashLogService")
public class ProfitCashLogServiceImpl extends BaseServiceImpl<ProfitCashLog, Long> implements ProfitCashLogService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProfitCashLogServiceImpl.class);
   
    @Resource
    private ProfitCashLogMapper profitCashLogMapper;
    @Resource
    private UserMapper userMapper;
    
    

	@Override
	public BaseMapper<ProfitCashLog, Long> getMapper() {
		return profitCashLogMapper;
	}

	@Override
	public Page<ProfitCashLog> page(Map<String, Object> searchMap, int current,
			int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<ProfitCashLog> list = profitCashLogMapper.listSelective(searchMap);
		return (Page<ProfitCashLog>) list;
	}
//
//	@Override
//	public Page<ManageProfitAmountModel> findAmount(String userName,
//			int current, int pageSize) {
//		PageHelper.startPage(current, pageSize);
//		Map<String,Object> map = new HashMap<>();
//		if (StringUtil.isNotBlank(userName)) {
//			map.put("userName", userName+"%");
//		}
//		List<ManageProfitAmountModel> list = profitCashLogMapper.findSysAmount(map);
//		return (Page<ManageProfitAmountModel>)list;
//	}
	
	@Override
	public Page<ManageProfitLogModel> findLog(String phone, String userName, int current, int pageSize) {
		Map<String,Object> map = new HashMap<>();
		User user = userMapper.findByLoginName(phone);
		map.put("userId", user.getId());
		userName =  (userName == null)?"":userName.trim();
		if (StringUtil.isNotBlank(userName)) {
			map.put("userName", userName.trim()+"%");
		}
		PageHelper.startPage(current, pageSize);
		List<ManageProfitLogModel> list = profitCashLogMapper.findLog(map);
		return (Page<ManageProfitLogModel>)list;
	}

	@Override
	public Page<ManageProfitLogModel> findLog(String userName, int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		Map<String,Object> map = new HashMap<>();
		if (StringUtil.isNotBlank(userName)) {
			map.put("agentName", "%"+userName+"%");
		}
		List<ManageProfitLogModel> list = profitCashLogMapper.findSysLog(map);
		return (Page<ManageProfitLogModel>)list;
	}
	
	
}