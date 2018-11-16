package com.xindaibao.cashloan.cl.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.domain.OperatorVoices;
import com.xindaibao.cashloan.cl.mapper.KanyaUserCallMapper;
import com.xindaibao.cashloan.cl.model.kenya.KanyaUserCall;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.mapper.OperatorVoicesMapper;
import com.xindaibao.cashloan.cl.service.OperatorVoicesService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.ShardTableUtil;



/**
 * 运营商信息-通话记录ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-13 16:44:01


 * 

 */
 
@Service("operatorVoicesService")
public class OperatorVoicesServiceImpl extends BaseServiceImpl<OperatorVoices, Long> implements OperatorVoicesService {
	
    @Resource
    private OperatorVoicesMapper operatorVoicesMapper;
    @Resource
    private KanyaUserCallMapper kanyaUserCallMapper;

	@Override
	public BaseMapper<OperatorVoices, Long> getMapper() {
		return operatorVoicesMapper;
	}

	@Override
	public Page<OperatorVoices> findShardPage(Map<String, Object> params, int currentPage, int pageSize) {
		long userId = (long) params.get("userId");
		// 分表
		String tableName = ShardTableUtil.generateTableNameById("cl_operator_voices", userId, 30000);
		int countTable = operatorVoicesMapper.countTable(tableName);
		if (countTable == 0) {
			operatorVoicesMapper.createTable(tableName);
		}
		
		PageHelper.startPage(currentPage, pageSize);
		List<OperatorVoices> list = operatorVoicesMapper.listShardSelective(tableName, params);
		return (Page<OperatorVoices>) list;
	}

	@Override
	public KanyaUserCall findShardKenya(Long userId, int currentPage, int pageSize) {

		return kanyaUserCallMapper.findByPrimary(userId);
	}
}