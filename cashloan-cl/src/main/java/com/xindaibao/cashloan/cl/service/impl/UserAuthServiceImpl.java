package com.xindaibao.cashloan.cl.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.domain.OperatorReqLog;
import com.xindaibao.cashloan.cl.domain.OperatorRespDetail;
import com.xindaibao.cashloan.cl.domain.UserAuth;
import com.xindaibao.cashloan.cl.mapper.UserAuthMapper;
import com.xindaibao.cashloan.cl.model.UserAuthModel;
import com.xindaibao.cashloan.cl.service.OperatorReqLogService;
import com.xindaibao.cashloan.cl.service.OperatorRespDetailService;
import com.xindaibao.cashloan.cl.service.OperatorService;
import com.xindaibao.cashloan.cl.service.UserAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import vo.GzipUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.DateUtil;

import credit.Header;
import credit.SimpleRequest;

/**
 * 用户认证信息表ServiceImpl
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-14 11:18:17


 * 

 */
 
@Service("userAuthService")
public class UserAuthServiceImpl extends BaseServiceImpl<UserAuth, Long> implements UserAuthService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserAuthServiceImpl.class);
   
    @Resource
    private UserAuthMapper userAuthMapper;
    @Resource
	private OperatorReqLogService operatorReqLogService;
    @Resource
    private OperatorRespDetailService operatorRespDetailService;
    @Resource
    private OperatorService operatorService;

	@Override
	public BaseMapper<UserAuth, Long> getMapper() {
		return userAuthMapper;
	}

	@Override
	public UserAuth getUserAuth(Map<String, Object> paramMap) {
		UserAuth userAuth = userAuthMapper.findSelective(paramMap);
		String phoneState = userAuth.getPhoneState();
		OperatorReqLog operatorReqLog = operatorReqLogService.findLastRecord(paramMap);
		
		if (UserAuthModel.STATE_ERTIFICATION.equals(userAuth.getPhoneState()) &&
				null != operatorReqLog) {
			int resetTime = 10;
			int diffTime = DateUtil.minuteBetween(operatorReqLog.getCreateTime(),DateUtil.getNow());
			//判断是否超过10分钟。
			if (resetTime <= diffTime) {
				String host = "https://api.dsdatas.com/notify/iq"; 
				long timestamp = new Date().getTime();
				Header header = new Header(Global.getValue("apikey"), timestamp);
				SimpleRequest request = new SimpleRequest(host, header);
				request.setOrderNo(operatorReqLog.getOrderNo());
				request.signByKey(Global.getValue("secretkey"));
				String result = null;
				try {
					result = request.request();
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
				JSONObject json = JSON.parseObject(result);
				String code = json.getString("code");
				if("200".equals(code)){
					try {
						result = GzipUtil.decompressWithBase64(json.getString("res"));
						phoneState = UserAuthModel.STATE_VERIFIED;
					} catch (Exception e) {
						phoneState = UserAuthModel.STATE_NOT_CERTIFIED;
						logger.error("大圣返回数据解压失败 orderNo:"+operatorReqLog.getOrderNo());
					}
					if(UserAuthModel.STATE_VERIFIED.equals(phoneState)){
						OperatorRespDetail operatorRespDetail = new OperatorRespDetail(operatorReqLog.getId(), operatorReqLog.getOrderNo(), result);
						operatorRespDetailService.insert(operatorRespDetail);
						operatorService.saveOperatorInfos(result, null, userAuth.getUserId() ,DateUtil.getNow());
					}
				} else { 
					phoneState = UserAuthModel.STATE_NOT_CERTIFIED;
				}
				Map<String, Object> modifyMap = new HashMap<String, Object>();
				modifyMap.put("userId", userAuth.getUserId());
				modifyMap.put("phoneState", phoneState);
				this.updateByUserId(modifyMap);
			} 
		}	
		userAuth.setPhoneState(phoneState);
		return userAuth;
	}

	@Override
	public Integer updateByUserId(Map<String, Object> paramMap) {
		return userAuthMapper.updateByUserId(paramMap);
	}
	
	@Override
	public Page<UserAuthModel> listUserAuth(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<UserAuthModel> list = userAuthMapper.listUserAuthModel(params);
		return (Page<UserAuthModel>) list;
	}

	@Override
	public UserAuth findSelective(long userId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return userAuthMapper.findSelective(map);
	}

	@Override
	public Map<String, Object> getAuthState(Map<String, Object> paramMap) {
		//定义需要的变量
		String resultSql="";//result返回值中的语句
		String qualifiedSql="";//qualified返回值中的语句
		int qualifiedCount=4;//基础必填项数量
		//芝麻信用sql语句拼接，需要sys_config表里设置的zhima_auth属性，10-去除 20-选填 30-必填
		String zhima_auth=Global.getValue("zhima_auth");
		if("30".equals(zhima_auth)){
			resultSql="+IF (zhima_state = 30, 1, 0)";
			qualifiedSql="+IF (zhima_state = 30, 1, 0)";
			qualifiedCount++;
		}else if("20".equals(zhima_auth)){
			resultSql="+IF (zhima_state = 30, 1, 0)";
		}
		//芝麻信用sql拼接结束
		//公积金sql语句拼接，需要sys_config表里设置的acc_fund_auth属性，10-去除 20-选填 30-必填
		String acc_fund_auth=Global.getValue("acc_fund_auth");
		if("30".equals(acc_fund_auth)){
			resultSql="+IF (acc_fund_state = 30, 1, 0)";
			qualifiedSql="+IF (acc_fund_state = 30, 1, 0)";
			qualifiedCount++;
		}else if("20".equals(acc_fund_auth)){
			resultSql="+IF (acc_fund_state = 30, 1, 0)";
		}
		//公积金sql拼接结束
		//拼接整个sql
		String sql="	SELECT ("
				+ "IF (id_state = 30, 1, 0) +"
				+ "IF (phone_state = 30, 1, 0) +"
				+ "IF (contact_state = 30, 1, 0) +"
				+ "IF (bank_card_state = 30, 1, 0) +"
				+ "IF (work_info_state = 30, 1, 0) +"
				+ "IF (other_info_state = 30, 1, 0)"
				+ resultSql
				+ ") AS result,"
				+ Global.getValue("auth_total")+" AS total,"
				+ "IF ("
				+ "(IF (id_state = 30, 1, 0) +"
				+ "IF (phone_state = 30, 1, 0) +"
				+ "IF (contact_state = 30, 1, 0) +"
				+ "IF (bank_card_state = 30, 1, 0) "
				+ qualifiedSql
				+ ") = "
				+ qualifiedCount
				+ ",1,0) AS qualified "
				+ "FROM cl_user_auth "
				+ "WHERE user_id = "+paramMap.get("userId");
		paramMap=new HashMap<String,Object>();
		paramMap.put("sqlstring", sql);
		return userAuthMapper.executeSql(paramMap);
	}

	@Override
	public int updatePhoneState(Map<String, Object> userAuth) {
		// TODO Auto-generated method stub
		return userAuthMapper.updatePhoneState(userAuth);
	}

	@Override
	public boolean findAuthState(long userId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("idState", "30");
		map.put("contactState", "30");
		map.put("bankCardState", "30");
		//map.put("zhimaState", "30");
		map.put("phoneState", "30");
		UserAuth auth = userAuthMapper.findSelective(map);
		if (auth==null){
			return false;
		}
		return true;
	}
	
}
