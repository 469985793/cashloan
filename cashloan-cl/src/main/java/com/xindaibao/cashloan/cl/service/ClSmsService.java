package com.xindaibao.cashloan.cl.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.Sms;
import com.xindaibao.cashloan.core.common.service.BaseService;

/**
 * 短信记录Service
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-09 14:48:24


 * 

 */
public interface ClSmsService extends BaseService<Sms, Long>{
	
	int smsBatch(String id);

	/**
	 * 查询与最近一条短信的时间差（秒）
	 * @param phone
	 * @param type
	 * @return
	 */
	long findTimeDifference(String phone, String type);
	
	/**
	 * 根据手机号码、短信验证码类型查询今日可获取次数，防短信轰炸
	 * @param phone
	 * @param type
	 * @return
	 */
	int countDayTime(String phone, String type);
	
	/**
	 * 发送短信
	 * @param phone
	 * @param type
	 * @return
	 */
	String sendSms(String phone, String type);

	/**
	 * 验证短信
	 * @param phone
	 * @param type
	 * @param code
	 * @param signMsg
	 * @return
	 */
	int verifySms(String phone, String type, String code);

	/**
	 * 查询用户
	 * @param phone 
	 * @return
	 */
	int findUser(String phone);
	
	/**
	 * 放款通知发送短信
	 * @param userId
	 * @param borrowId
	 * @return
	 */
	String loanInform(long userId,long borrowId);
	
	/**
	 * 扣款通知发送短信
	 * @param phone
	 * @param type
	 * @param type
	 * @return
	 */
	String repayInform(long userId,long borrowId,String type);

	/**
	 * 逾期通知短信
	 * @param parseLong
	 * @return
	 */
	int overdue(long parseLong);

	/**
	 * 逾期通知短信(指定模板类型)
	 * @param parseLong
	 * @param type
	 * @return
	 */
	int overdue(long parseLong,String type);

	/**
	 * 还款提醒
	 * @param phone
	 * @param type
	 * @return
	 */
	String repayBefore(long userId,long borrowId);
	
	/**
	 * 短信列表查询
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<Sms> list(Map<String, Object> params, int currentPage, int pageSize);
	
	/**
	 * 获取短信报告结果
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	int getReportByOrderNo(String orderNo);
}
