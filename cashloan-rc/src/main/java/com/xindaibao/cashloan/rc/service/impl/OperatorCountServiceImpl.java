package com.xindaibao.cashloan.rc.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.core.common.util.ShardTableUtil;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.mapper.UserBaseInfoMapper;
import com.xindaibao.cashloan.rc.domain.PhoneCallBaseCount;
import com.xindaibao.cashloan.rc.mapper.OperatorCountMapper;
import com.xindaibao.cashloan.rc.mapper.PhoneCallBaseCountMapper;
import com.xindaibao.cashloan.rc.mapper.PhoneCallBorrowCountMapper;
import com.xindaibao.cashloan.rc.model.OperatorCountModel;
import com.xindaibao.cashloan.rc.service.OperatorCountService;

/**
 * 运营商信息
 * @author
 * @version 1.0
 * @date 2017年4月18日上午10:40:07




 */
@Service("operatorCountService")
public class OperatorCountServiceImpl extends BaseServiceImpl<OperatorCountModel, String> implements OperatorCountService {

	@Resource
	private OperatorCountMapper operatorCountMapper;
	@Resource
	private UserBaseInfoMapper userBaseInfoMapper;
	@Resource
	private PhoneCallBaseCountMapper phoneCallBaseCountMapper;
	@Resource
	private PhoneCallBorrowCountMapper phoneCallBorrowCountMapper;

	@Override
	public BaseMapper<OperatorCountModel, String> getMapper() {
		return operatorCountMapper;
	}
	
	@Override
	public int operatorCountVoice(Long userId) {
		int update = 0;
		UserBaseInfo baseInfo = userBaseInfoMapper.findByUserId(userId); 
		OperatorCountModel result = null;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("phone", baseInfo.getPhone());
		params.put("tableName", "cl_operator_td_call_record");//ShardTableUtil.generateTableNameById("cl_operator_voices", userId, 30000)
		if(baseInfo!=null && baseInfo.getPhone()!=null){
			result = operatorCountMapper.operatorVoicesInfo(params);
			result.setUserId(userId);
			result.setPhone(baseInfo.getPhone());
		}
		
		if(result!=null){
			Double monthAmt = operatorCountMapper.operatorMonthAmt(baseInfo.getPhone());
			result.setMonthAmt(monthAmt);
			Date joinDate = operatorCountMapper.operatorJoinDate(baseInfo.getPhone());
			if(joinDate!=null){
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(joinDate);
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(DateUtil.getNow());
				int month = (cal2.get(Calendar.YEAR) -cal1.get(Calendar.YEAR))*12 - cal1.get(Calendar.MONTH) + cal2.get(Calendar.MONTH);
				result.setJoinMonthCount(month);
			}else{
				result.setJoinMonthCount(0);
			}
			
			OperatorCountModel voicesPhone = operatorCountMapper.operatorVoicesPhone(params);
			
			if(voicesPhone!=null){
				result.setGe3Times60SNumCount90(voicesPhone.getGe3Times60SNumCount90());
				result.setGe5TimesNumCount90(voicesPhone.getGe5TimesNumCount90());
			}
			
			Integer times = operatorCountMapper.emerConcatTimes(params);
			result.setEmerConcatTimes6Month(times);
		}
		PhoneCallBaseCount baseCount = new PhoneCallBaseCount();
		if(result!=null){
			baseCount = new PhoneCallBaseCount(result);
			if(result.getCountVoices90()!=null && result.getLiveAddrVoice90N()!=null &&result.getCountVoices90()>0 && result.getLiveAddrVoice90N()>0 && result.getCountVoices90().intValue() == result.getLiveAddrVoice90N()){
				baseCount.setAddressMatching("20");
			}else{
				baseCount.setAddressMatching("10");
			}
			if(result.getMonthAmt()!=null && result.getMonthAmt()>0){
				BigDecimal monthAmt = new BigDecimal(result.getMonthAmt()).setScale(0, BigDecimal.ROUND_HALF_UP);
				baseCount.setMonthSource(monthAmt.intValue());
			}else{
				baseCount.setMonthSource(0);
			}
//			update = phoneCallBaseCountMapper.save(baseCount);
		}
		
		if(result!=null){
			result = operatorCountMapper.concatsBorrowInfo(params);
//			PhoneCallBorrowCount borrowCount = new PhoneCallBorrowCount(result);
//			this.userId = model.getUserId();
			baseCount.setCountTwenty(result.getPre20NumBorrowY90());
			baseCount.setCountTwentyOne(result.getPre20NumBorrowN90());
			baseCount.setCountTwentyTwo(result.getPre20NumBorrowN90M3());
			baseCount.setCountTwentyThree(result.getPre20NumBorrowNMore30M1());
			baseCount.setCountTwentyFour(result.getPre20NumBorrowNLess30M1());
			update = phoneCallBaseCountMapper.save(baseCount);
		}
		
		return update;
	}

	/*@Override
	 * 合并到统计通话信息同一个方法
	public int operatorCountBorrow(Long userId) {
		int update = 0;
		UserBaseInfo baseInfo = userBaseInfoMapper.findByUserId(userId); 
		OperatorCountModel result = null;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("phone", baseInfo.getPhone());
		params.put("tableName", ShardTableUtil.generateTableNameById("cl_operator_voices", userId, 30000));
		if(baseInfo!=null && baseInfo.getPhone()!=null){
			result = operatorCountMapper.concatsBorrowInfo(params);
			result.setUserId(userId);
			result.setPhone(baseInfo.getPhone());
		}
		
		if(result!=null){
			PhoneCallBorrowCount borrowCount = new PhoneCallBorrowCount(result);
			update = phoneCallBorrowCountMapper.save(borrowCount);
		}
		
		return update;
	}*/

}
