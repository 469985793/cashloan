package com.xindaibao.cashloan.cl.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.mapper.SystemAnalysisMapper;
import com.xindaibao.cashloan.cl.model.RepayAnalisisModel;
import com.xindaibao.cashloan.cl.service.SystemAnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.StringUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.model.OverdueAnalisisModel;
import com.xindaibao.cashloan.core.common.util.DateUtil;

/**
 * 运营分析
 * @author
 * @version 1.0
 * @date 2017年3月21日下午3:00:36


 * 

 */
@Service("systemAnalysisService")
public class SystemAnalysisServiceImpl implements SystemAnalysisService {

	private static final Logger logger = LoggerFactory.getLogger(SystemAnalysisServiceImpl.class);
	@Resource
	private SystemAnalysisMapper systemAnalysisMapper;
	
	@Override
	public List<RepayAnalisisModel> monthRepayAnalisis(Map<String, Object> params) {
		List<RepayAnalisisModel> list = null;
		List<String> mouthList = null;
		list = new ArrayList<>();
		try {
			if (params==null) {
				mouthList = systemAnalysisMapper.mouthList();
			}else {
				String minMouth = StringUtil.isNull(params.get("startDate"));
				String maxMouth = StringUtil.isNull(params.get("endDate"));
				mouthList = DateUtil.getMonthBetween(minMouth, maxMouth);
			}
			for (int i = mouthList.size()-1; i > 0; i--) {
				Map<String,Object> map = new HashMap<>();
				map.put("date", mouthList.get(i)+"-01");
				map.put("dateType", "%Y-%m");
				
				RepayAnalisisModel repayModel = new RepayAnalisisModel();
				repayModel.setDate(mouthList.get(i));
				String repayCount = systemAnalysisMapper.repayCount(map);
				repayModel.setRepayCount(repayCount==null?"0":repayCount);
				
				String overdueCount = systemAnalysisMapper.overdueCount(map);
				repayModel.setOverdueCount(overdueCount==null?"0":overdueCount);
				
				String repayAmt = systemAnalysisMapper.repayAmt(map);
				repayModel.setRepayAmt(repayAmt==null?"0":repayAmt);
				
				String penaltyRepayAmt = systemAnalysisMapper.penaltyRepayAmt(map);
				repayModel.setPenaltyRepayAmt(penaltyRepayAmt==null?"0":penaltyRepayAmt);
				
				repayModel.setApr(repayModel.getApr());
				repayModel.setAmountApr(repayModel.getAmountApr());
				list.add(repayModel);
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(),e);
		}
		return list;
	}

	@Override
	public List<RepayAnalisisModel> dayRepayAnalisis(Map<String, Object> params) {
		List<RepayAnalisisModel> list = null;
		try {
			list = new ArrayList<>();
			Date startTime = new Date();
			Date endTime = new Date();
			List<Date> dateList = null;
			if (params==null) {
				startTime = DateUtil.getDateBefore(-10, endTime);
				dateList = dateList(startTime, endTime);
			}else {
				endTime = DateUtil.valueOf(params.get("afterTime").toString());
				startTime = DateUtil.valueOf(params.get("beforeTime").toString());
				dateList = dateList(startTime, endTime);
			}
			for (int i = 0; i < dateList.size(); i++) {
				Map<String,Object> map = new HashMap<>();
				map.put("date", dateList.get(i));
				map.put("dateType", "%y-%m-%d");
				
				RepayAnalisisModel repayModel = new RepayAnalisisModel();
				repayModel.setDate(DateUtil.dateStr2(dateList.get(i)));
				String repayCount = systemAnalysisMapper.repayCount(map);
				repayModel.setRepayCount(repayCount==null?"0":repayCount);
				
				String overdueCount = systemAnalysisMapper.overdueCount(map);
				repayModel.setOverdueCount(overdueCount==null?"0":overdueCount);
				
				String repayAmt = systemAnalysisMapper.repayAmt(map);
				repayModel.setRepayAmt(repayAmt==null?"0":repayAmt);
				
				String penaltyRepayAmt = systemAnalysisMapper.penaltyRepayAmt(map);
				repayModel.setPenaltyRepayAmt(penaltyRepayAmt==null?"0":penaltyRepayAmt);
				
				repayModel.setApr(repayModel.getApr());
				repayModel.setAmountApr(repayModel.getAmountApr());
				list.add(repayModel);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return list;
	}

	@Override
	public Page<OverdueAnalisisModel> overdueAnalisis(Map<String, Object> params, Integer current, Integer pageSize) {
		PageHelper.startPage(current, pageSize);
		params.put("dateType", "%Y-%m");
		Page<OverdueAnalisisModel> page = (Page<OverdueAnalisisModel>) systemAnalysisMapper.overdueAnalisis(params);
		return page;
	}

	/**
	 * 返回时间集合
	 * @return
	 * @throws Exception
	 */
	private List<Date> dateList(Date startTime,Date endTime) throws Exception{
			startTime=DateUtil.getDateBefore(-1, startTime);
			List<Date> lists = DateUtil.dateSplit(startTime, endTime);
			if (!lists.isEmpty()) {
			    return lists;
			}
			return null;
	}
}
