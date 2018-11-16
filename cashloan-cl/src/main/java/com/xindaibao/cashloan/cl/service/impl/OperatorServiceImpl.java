package com.xindaibao.cashloan.cl.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.domain.OperatorBasic;
import com.xindaibao.cashloan.cl.domain.OperatorReqLog;
import com.xindaibao.cashloan.cl.domain.OperatorVoices;
import com.xindaibao.cashloan.cl.mapper.OperatorBasicMapper;
import com.xindaibao.cashloan.cl.mapper.OperatorBillsMapper;
import com.xindaibao.cashloan.cl.mapper.OperatorVoicesMapper;
import com.xindaibao.cashloan.cl.mapper.UserAuthMapper;
import com.xindaibao.cashloan.cl.model.OperatorBillsModel;
import com.xindaibao.cashloan.cl.model.tongdun.PreloanApi;
import com.xindaibao.cashloan.cl.service.OperatorService;
import com.xindaibao.cashloan.cl.service.OperatorTdBasicService;
import com.xindaibao.cashloan.cl.service.UserAuthService;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.creditrank.cr.service.CreditRatingService;
import com.xindaibao.creditrank.cr.service.CreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tool.util.StringUtil;
import vo.GzipUtil;

import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.core.common.util.ShardTableUtil;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.mapper.UserBaseInfoMapper;

/**
 * 运营商回调信息处理
 * @author
 * @version 1.0
 * @date 2017年3月22日上午10:24:47


 * 

 */
@Service("operatorService")
@Transactional(rollbackFor=Exception.class)
public class OperatorServiceImpl implements OperatorService {
	
	public static final Logger logger = LoggerFactory.getLogger(OperatorServiceImpl.class);
	
	@Resource
	private OperatorBasicMapper operatorBasicMapper;
	
	@Resource
	private OperatorBillsMapper operatorBillsMapper;

	@Resource
	private OperatorVoicesMapper operatorVoicesMapper;
	
	@Resource
	private UserBaseInfoMapper userBaseInfoMapper;
	@Resource
	private UserAuthService userAuthService;
	@Resource
	private CreditRatingService creditRatingService;
	@Resource
	private CreditService creditService;
	@Resource
	private OperatorTdBasicService operatorTdBasicService;

	@Override
	public void saveOperatorInfos(String res, String compressStatus, Long userId, Date createTime){
		if(StringUtil.isNotBlank(res)){
			if("1".equals(compressStatus)){ //res压缩了，进行解压
				try {
					res = GzipUtil.decompressWithBase64(res);
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
			
			Map<String, Object> resJson = JSONObject.parseObject(res);
			
			UserBaseInfo baseInfo = null;
			if (userId != null && userId > 0) {
				Map<String,Object> params = new HashMap<String, Object>();
				params.put("userId", userId);
				baseInfo = userBaseInfoMapper.findSelective(params);
			}
			if(baseInfo == null){
				logger.error("保存用户userId：" + userId + "运营商数据时未找到其userBaseInfo，处理失败");
				return;
			}
			
			OperatorBasic basic = null;
			String operator_basic = String.valueOf(resJson.get("shuli_operator_basic"));
			if(StringUtil.isNotBlank(operator_basic)){
				//basic网龄转化后存入
				List<OperatorBasic> basicList = JSONObject.parseArray(modifyExtendPhoneAge(operator_basic), OperatorBasic.class);
				if(basicList != null && !basicList.isEmpty()){
					basic = basicList.get(0);
				}
				
				if(basic == null){
					logger.info("保存用户userId：" + userId + "运营商数据时operatorBasic对象为空");
				} else {
					/*if(StringUtil.isNotBlank(baseInfo.getPhone()) 
							&& StringUtil.isNotBlank(basic.getBasicPhoneNum()) 
							&& !baseInfo.getPhone().equals(basic.getBasicPhoneNum())){
						throw new BussinessException("保存用户userId：" + userId + "运营商数据时，数据中的手机号码"+basic.getBasicPhoneNum()+"与用户手机号码"+baseInfo.getPhone()+"不一致，处理失败");
					}*/ // 手机号码作为入参，此处不再判断手机号码是否和注册号码一致
					
					if (basic.getExtendJoinDt() != null) {
						basic.setExtendPhoneAge(DateUtil.daysBetween(basic.getExtendJoinDt(), DateUtil.getNow()));//保存网龄为天
					}
					basic.setUserId(userId);
					basic.setCreateTime(createTime);
					operatorBasicMapper.save(basic);
				}
			}
			
			String operator_bills = String.valueOf(resJson.get("shuli_operator_bill"));
			if(StringUtil.isNotBlank(operator_bills)){
				List<OperatorBillsModel> billsList = JSONObject.parseArray(operator_bills, OperatorBillsModel.class);
				if(billsList!=null && billsList.size()>0){
					for(OperatorBillsModel bill:billsList){
						String billMonthDate = bill.getBillMonthDate().replace("/", "");
						if(billMonthDate.length()==21){
							Date start = DateUtil.valueOf(billMonthDate.substring(0, 10)+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
							Date end = DateUtil.valueOf(billMonthDate.substring(11, 21)+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
							bill.setBillMonthDateStart(start);
							bill.setBillMonthDateEnd(end);
						}else if(billMonthDate.length()==10){
							Date start = DateUtil.valueOf(billMonthDate.substring(0, 10)+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
							Date end = DateUtil.valueOf(billMonthDate.substring(0, 10)+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
							bill.setBillMonthDateStart(start);
							bill.setBillMonthDateEnd(end);
						}else if(billMonthDate.length()==7){
							Date start = DateUtil.valueOf(billMonthDate+"-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
							bill.setBillMonthDateStart(start);
							bill.setBillMonthDateEnd(start);
						}else{
							Date start = DateUtil.getNow();
							Date end = DateUtil.getNow();
							bill.setBillMonthDateStart(start);
							bill.setBillMonthDateEnd(end);
						}
						bill.setCreateTime(createTime);
						operatorBillsMapper.save(bill);
					}
				}
			}
			
			String operator_voices = StringUtil.isNull(resJson.get("shuli_operator_voice"));
			if (StringUtil.isNotBlank(operator_voices)) {
				List<OperatorVoices> voicesList = JSONObject.parseArray(operator_voices, OperatorVoices.class);
				if (!voicesList.isEmpty()) {
					// 分表
					String tableName = ShardTableUtil.generateTableNameById("cl_operator_voices", userId, 30000);
					int countTable = operatorVoicesMapper.countTable(tableName);
					if (countTable == 0) {
						operatorVoicesMapper.createTable(tableName);
					}
					
					for (OperatorVoices voice : voicesList) {
						voice.setUserId(userId);
						voice.setCreateTime(createTime);
						logger.debug("GmtCreate----"+voice.getGmtCreate());
						logger.debug("GmtModified----"+voice.getGmtModified());
						logger.debug("CreateTime()----"+voice.getCreateTime());
						operatorVoicesMapper.saveShard(tableName, voice);
					}
				}
			}
		} else {
			logger.error("保存用户userId：" + userId + "运营商数据时，res为空，处理失败");
		}
	}

    /**
     * 查询同盾运营商信息以及业务处理
     * @param operatorReqLog
     * @param task_id
     */
    @Override
    public void operatorQuery(OperatorReqLog operatorReqLog, String task_id) {
        logger.info("开始根据task_id=" + task_id + "查询同盾运营商信息。。。。。。");

        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put("task_id", task_id);

        PreloanApi api = new PreloanApi();
        String notify_data = api.operatorQuery(temp);
        final Long userId = operatorReqLog.getUserId();
        @SuppressWarnings("unchecked")
        Map<String, Object> resultData = JsonUtil.parse(notify_data, Map.class);
        String code = String.valueOf(resultData.get("code"));
        if (code.equals("200")) {
            Map<String, Object> userAuth = new HashMap<String, Object>();
            userAuth.put("userId", userId);
            userAuth.put("phoneState", "30");
            int i = userAuthService.updatePhoneState(userAuth);
            if (i > 0) {
                /*//验证状态，启用评分，更新额度
                boolean authFlag = userAuthService.findAuthState(userId);
                if (authFlag){//完成全部认证
                    String consumerNo = userId.toString();
                    //开视评分
                    creditRatingService.saveCreditRating(consumerNo, 1l);//借款类型默认为现金贷，即 long borrowTypeId=1l
                    //开始更新授信额度数据
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("used", "0");
                    map.put("consumerNo", userId);
                    map.put("state", "10");//待审批
                    creditService.updateByAuth(map);
                }*/

                @SuppressWarnings("unchecked")
               /* final Map<String, Object> res = (Map<String, Object>) resultData.get("data");
                final OperatorReqLog log = operatorReqLog;
                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        try {
                            operatorTdBasicService.saveTdOperatorInfos(res,userId, log);
                        } catch (Exception e) {
                            logger.error(e.toString(), e);
                        }
                    }
                });
                thread.start();*/

				// FIXME: 2017/11/3 确保数据落库后查询，采用同步入库
				Map<String, Object> res = (Map<String, Object>) resultData.get("data");
				try {
					operatorTdBasicService.saveTdOperatorInfos(res,userId, operatorReqLog);
				} catch (Exception e) {
					logger.error(e.toString(), e);
				}
			}
        } else {
            Map<String, Object> userAuth = new HashMap<String, Object>();
            userAuth.put("userId", userId);
            userAuth.put("phoneState", "10");
            userAuthService.updateByUserId(userAuth);
        }

    }
	
	/**
	 * 改写手机号网龄
	 * @param operator_basic
	 * @return
	 */
	private static String modifyExtendPhoneAge(String operator_basic){
		String afterStr = operator_basic;
		
		int beginIndex = afterStr.indexOf("extendPhoneAge");
		if(beginIndex == -1){
			return operator_basic;
		}
		
		String subStr = afterStr.substring(beginIndex + 17);
		int endIndex = subStr.indexOf("\"");
		
		
		//字段值
		String extendPhoneAge = subStr.substring(0, endIndex);
		//无数据则为0
		if (StringUtil.isEmpty(extendPhoneAge)) {
			StringBuffer stringBuffer = new StringBuffer(afterStr);
			return stringBuffer.insert(beginIndex + 17, 0).toString();
		} else {
			//年月相加
			int monthIndex = extendPhoneAge.indexOf("个月");
			int yearIndex = extendPhoneAge.indexOf("年");
			Integer year = 0;
			Integer month = 0;
			if (yearIndex != -1) {
				year = Integer.valueOf(extendPhoneAge.substring(0, yearIndex)) * 12;
			}
			if (monthIndex != -1) {
				month = Integer.valueOf(extendPhoneAge.substring(yearIndex + 1, monthIndex));
			}		
			return afterStr.replace(extendPhoneAge, String.valueOf(year + month));
		}
	}
	
}
