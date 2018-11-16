package com.xindaibao.cashloan.cl.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.mapper.StatisticManageMapper;
import com.xindaibao.cashloan.cl.model.IncomeAndExpendModel;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.model.DayNeedAmountModel;
import com.xindaibao.cashloan.cl.model.ExpendDetailModel;
import com.xindaibao.cashloan.cl.model.IncomeDetailModel;
import com.xindaibao.cashloan.cl.service.StatisticManageService;

/**
 * 统计管理
 * @author
 * @version 1.0
 * @date 2017年3月21日下午4:42:31


 * 

 */
@Service("statisticManageService")
public class StatisticManageServiceImpl implements StatisticManageService {

	@Resource
	private StatisticManageMapper statisticManageMapper;

	@Override
	public Page<IncomeAndExpendModel> repayIncomeAndExpend(Map<String, Object> params, Integer current, Integer pageSize) {
		PageHelper.startPage(current, pageSize);
		Page<IncomeAndExpendModel> page = (Page<IncomeAndExpendModel>) statisticManageMapper.repayIncomeAndExpend(params);
		return page;
	}

	@Override
	public Page<DayNeedAmountModel> dayNeedAmount(Map<String, Object> params,Integer current,Integer pageSize) {
		PageHelper.startPage(current, pageSize);
		Page<DayNeedAmountModel> page = (Page<DayNeedAmountModel>) statisticManageMapper.dayNeedAmount(params);
		return page;
	}

	@Override
	public Page<IncomeDetailModel> incomeDetail(Map<String, Object> params,Integer current, Integer pageSize) {
		PageHelper.startPage(current, pageSize);
		Page<IncomeDetailModel> page = (Page<IncomeDetailModel>) statisticManageMapper.incomeDetail(params);
		return page;
	}

	@Override
	public Page<ExpendDetailModel> expendDetail(Map<String, Object> params,Integer current, Integer pageSize) {
		PageHelper.startPage(current, pageSize);
		Page<ExpendDetailModel> page = (Page<ExpendDetailModel>) statisticManageMapper.expendDetail(params);
		return page;
	}

	@Override
	public Double incomeSum(Map<String, Object> params) {
		return statisticManageMapper.incomeSum(params);
	}

	@Override
	public Double expendSum(Map<String, Object> params) {
		return statisticManageMapper.expendSum(params);
	}
	
	
}
