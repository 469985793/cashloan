package com.xindaibao.cashloan.cl.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.domain.OperatorReqLog;
import com.xindaibao.cashloan.cl.domain.OperatorTdBasic;
import com.xindaibao.cashloan.cl.domain.OperatorVoices;
import com.xindaibao.cashloan.cl.mapper.OperatorTdBillsMapper;
import com.xindaibao.cashloan.cl.mapper.OperatorTdCallInfoMapper;
import com.xindaibao.cashloan.cl.mapper.OperatorTdCallRecordMapper;
import com.xindaibao.cashloan.cl.mapper.OperatorTdSmsInfoMapper;
import com.xindaibao.cashloan.cl.service.OperatorTdBasicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;
import tool.util.StringUtil;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.cl.domain.OperatorTdBills;
import com.xindaibao.cashloan.cl.domain.OperatorTdCallInfo;
import com.xindaibao.cashloan.cl.domain.OperatorTdCallRecord;
import com.xindaibao.cashloan.cl.domain.OperatorTdSmsRecord;
import com.xindaibao.cashloan.cl.mapper.OperatorTdBasicMapper;
import com.xindaibao.cashloan.cl.mapper.OperatorTdSmsRecordMapper;
import com.xindaibao.cashloan.cl.mapper.UserAuthMapper;
import com.xindaibao.cashloan.cl.model.OperatorTdCallInfoModel;
import com.xindaibao.cashloan.cl.model.OperatorTdSmsInfoModel;
import com.xindaibao.cashloan.core.common.exception.BussinessException;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.mapper.UserBaseInfoMapper;


/**
 * 同盾运营商认证基本信息表ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-24 09:20:04



 */
 
@Service("operatorTdBasicService")
public class OperatorTdBasicServiceImpl extends BaseServiceImpl<OperatorTdBasic, Long> implements OperatorTdBasicService {
	
    private static final Logger logger = LoggerFactory.getLogger(OperatorTdBasicServiceImpl.class);
   
    @Resource
    private OperatorTdBasicMapper operatorTdBasicMapper;
    @Resource
    private OperatorTdBillsMapper operatorTdBillsMapper;
    @Resource
    private OperatorTdCallInfoMapper operatorTdCallInfoMapper;
    @Resource
    private OperatorTdCallRecordMapper operatorTdCallRecordMapper;
    @Resource
    private OperatorTdSmsInfoMapper operatorTdSmsInfoMapper;
    @Resource
    private OperatorTdSmsRecordMapper operatorTdSmsRecordMapper;
    @Resource
	private UserBaseInfoMapper userBaseInfoMapper;
    @Resource
	private UserAuthMapper userAuthMapper;
	@Override
	public BaseMapper<OperatorTdBasic, Long> getMapper() {
		return operatorTdBasicMapper;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public void saveTdOperatorInfos(Map<String, Object> resultData, Long userId,OperatorReqLog log) {
		// TODO Auto-generated method stub
		logger.info("===开始解析运营商数据====");
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotBlank(resultData)) {
			UserBaseInfo userBaseInfo = null;
			if (userId != null && userId > 0) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("userId", userId);
				userBaseInfo = userBaseInfoMapper.findSelective(params);
			}
			
	/*		同盾认证数据不限制
	    	Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put("userId", userId);
			List<OperatorTdBasic> tdBasic=operatorTdBasicMapper.listSelective(params2);
			if(StringUtil.isNotBlank(tdBasic)&&tdBasic.size()>0){
				logger.error("保存失败,用户【"+userId+"】该用户运营商数据已经存在");
				throw new BussinessException("保存失败,该用户运营商数据已经存在");
			}*/
			Map<String, Object> dataMap = (Map<String, Object>) resultData.get("data");
			if (userBaseInfo != null&& StringUtil.isNotBlank(userBaseInfo.getPhone())
					&& dataMap != null&& StringUtil.isNotBlank(dataMap.get("user_mobile"))
					&& userBaseInfo.getPhone().equals(dataMap.get("user_mobile"))) {
				Map<String, Object> taskMap = (Map<String, Object>) dataMap.get("task_data");
				if (taskMap != null && StringUtil.isNotBlank(taskMap)) {
					// 账户信息
					OperatorTdBasic basic = null;
					String operator_basic = JsonUtil.toString((taskMap.get("account_info")));
					if (StringUtil.isNotBlank(operator_basic)) {
						Map<String, Object> params = JsonUtil.parse(operator_basic, Map.class);
						//params.replace("net_age", "未知", "0");
						if(params.containsKey("net_age")&&!StringUtil.isNumber(String.valueOf(params.get("net_age")))){
						   params.remove("net_age");
						   params.put("net_age", 0);
						 }
						List<OperatorTdBasic> basicList = JSONObject.parseArray(change("[" + JsonUtil.toString(params) + "]"),OperatorTdBasic.class);
						if (basicList != null && !basicList.isEmpty()) {
							basic = basicList.get(0);
							String netTime=basic.getNetTime();
							//logger.info("===入网时间="+netTime+"==网龄="+basic.getNetAge());
							if(com.xindaibao.cashloan.core.common.util.StringUtil.isValidDate(netTime)){
								Date date=DateUtil.getNow();
								int age=0;
								age=DateUtil.daysBetween(DateUtil.valueOf(netTime), date);
								basic.setNetAge(age);
							}else if(StringUtil.isNotBlank(basic.getNetAge())){
								basic.setNetAge(basic.getNetAge()*30);
							}else{
								basic.setNetAge(0);
							}
						}
					}
					basic.setChannelSrc(String.valueOf(dataMap.get("channel_src")));
					basic.setUserId(userId);
					basic.setUserMobile(String.valueOf(dataMap.get("user_mobile")));
					basic.setReqLogId(log.getId());
					basic.setOrderNo(log.getOrderNo());
					operatorTdBasicMapper.save(basic);

				//账单信息
				String operator_bills = JsonUtil.toString(taskMap.get("bill_info"));
				if(StringUtil.isNotBlank(operator_bills)){
					List<OperatorTdBills> billsList = JSONObject.parseArray(change(operator_bills), OperatorTdBills.class);
					if(billsList != null && !billsList.isEmpty()){
						for(OperatorTdBills bills : billsList){
							bills.setUserId(userId);
							bills.setReqLogId(log.getId());
							bills.setOrderNo(log.getOrderNo());
							bills.setBillRecord("");
							bills.setUsageDetail("");
							operatorTdBillsMapper.save(bills);
						}
					}
				}
				
				//通话详单
				String operator_call_info = JsonUtil.toString(taskMap.get("call_info"));
				if(StringUtil.isNotBlank(operator_call_info)){
					List<OperatorTdCallInfoModel> billsList = JSONObject.parseArray(change(operator_call_info), OperatorTdCallInfoModel.class);
					if(billsList != null && !billsList.isEmpty()){
						for(OperatorTdCallInfoModel callInfo : billsList){
							callInfo.setUserId(userId);
							callInfo.setReqLogId(log.getId());
							callInfo.setOrderNo(log.getOrderNo());
							operatorTdCallInfoMapper.save(callInfo);
						    String record=callInfo.getCallRecord();
						    if(record != null && !record.isEmpty()){
							    List<OperatorTdCallRecord> records = JSONObject.parseArray(change(record), OperatorTdCallRecord.class);
								if(records != null && !records.isEmpty()){
									for(OperatorTdCallRecord r : records){
										r.setInfoId(callInfo.getId());
										operatorTdCallRecordMapper.save(r);
									}
								}
						    }  
						}
					}
				}
				
				//短信详单
				String operator_sms_info = JsonUtil.toString(taskMap.get("sms_info"));
				if(StringUtil.isNotBlank(operator_call_info)){
					List<OperatorTdSmsInfoModel> smsList = JSONObject.parseArray(change(operator_sms_info), OperatorTdSmsInfoModel.class);
					if(smsList != null && !smsList.isEmpty()){
						for(OperatorTdSmsInfoModel smsInfo : smsList){
							smsInfo.setUserId(userId);
							smsInfo.setReqLogId(log.getId());
							smsInfo.setOrderNo(log.getOrderNo());
							operatorTdSmsInfoMapper.save(smsInfo);
						    String record=smsInfo.getSmsRecord();
						    if(record != null && !record.isEmpty()){
							    List<OperatorTdSmsRecord> records = JSONObject.parseArray(change(record), OperatorTdSmsRecord.class);
								if(records != null && !records.isEmpty()){
									for(OperatorTdSmsRecord r : records){
										r.setInfoId(smsInfo.getId());
										operatorTdSmsRecordMapper.save(r);
									}
								}
						    } 
						}
					}
				}
				}else{
					logger.error("用户【"+userId+"】没有运营商关键信息task_data，处理失败");
				}
				
			} else {
				throw new BussinessException("用户【"+userId+"】运营商认证回调数据与用户数据不匹配");
			}
			
		}else{
			logger.error("用户【"+userId+"】运营商认证异步回调信息没有返回res，处理失败");
		}
		logger.info("===结束解析运营商数据====");
	}
	
	//统一将 格式 user_id 转换成 userId 
	public   String change(String name) {
		StringBuffer sb = new StringBuffer();  
        sb.append(name);  
        int count = sb.indexOf("_");  
        while(count!=0){  
            int num = sb.indexOf("_",count);  
            count = num+1;  
            if(num!=-1){  
                char ss = sb.charAt(count);  
                char ia = (char) (ss - 32);  
                sb.replace(count,count+1,ia+"");  
            }  
        }  
        String data = sb.toString().replaceAll("_","");
		return data.toString();
	}

	@Override
	public OperatorTdCallInfo findOperatorTdCallInfos(Map<String, Object> params) {
		// TODO Auto-generated method stub
		OperatorTdCallInfo info = new OperatorTdCallInfo();
		List<OperatorTdCallInfo> list=operatorTdCallInfoMapper.listSelective(params);
		if(StringUtil.isNotBlank(list)&&list.size()>0){
			info=list.get(0);
		}
		return  info;
	}

	@Override
	public Page<OperatorVoices> findPageOperatorTdCallRecord(
			Map<String, Object> params, int current, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(current, pageSize);
		List<OperatorVoices> list = operatorTdCallRecordMapper.listOperatorVoicesModel(params);
		return (Page<OperatorVoices>) list;
	}
	   
	 
	
 
	
}