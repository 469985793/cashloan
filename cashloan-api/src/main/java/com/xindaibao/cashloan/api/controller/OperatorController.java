package com.xindaibao.cashloan.api.controller;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.cl.domain.OperatorReqLog;
import com.xindaibao.cashloan.cl.domain.OperatorRespDetail;
import com.xindaibao.cashloan.cl.model.mohe.MoheParamHolder;
import com.xindaibao.cashloan.cl.model.tongdun.PreloanApi;
import com.xindaibao.cashloan.cl.monitor.BusinessExceptionMonitor;
import com.xindaibao.cashloan.cl.service.AccfundInfoService;
import com.xindaibao.cashloan.cl.service.ClMoheReportInfoService;
import com.xindaibao.cashloan.cl.service.OperatorReqLogService;
import com.xindaibao.cashloan.cl.service.OperatorRespDetailService;
import com.xindaibao.cashloan.cl.service.OperatorService;
import com.xindaibao.cashloan.cl.service.OperatorTdBasicService;
import com.xindaibao.cashloan.cl.service.UserAuthService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.OrderNoUtil;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.util.StringUtil;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.service.CloanUserService;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import com.xindaibao.cashloan.rc.service.TppBusinessService;
import com.xindaibao.creditrank.cr.service.CreditRatingService;
import com.xindaibao.creditrank.cr.service.CreditService;

import credit.ApiSignUtil;
import credit.CreditRequest;
import credit.Header;
 /**
 * 运营商认证
 * @author
 * @version 1.0.0
 * @date 2017-5-17 下午2:58:39



 */
@Controller	
@Scope("prototype")
public class OperatorController extends BaseController {

	public static final Logger logger = LoggerFactory.getLogger(OperatorController.class);

	@Resource
	private CloanUserService userService;
	@Resource
	private UserBaseInfoService userBaseInfoService;
	@Resource
	private UserAuthService userAuthService;
	@Resource
	private OperatorReqLogService operatorReqLogService;
	@Resource
	private OperatorRespDetailService operatorRespDetailService;
	@Resource
	private OperatorService operatorService;
	@Resource
	private TppBusinessService  tppBusinessService;
	@Resource
	private OperatorTdBasicService operatorTdBasicService;
	@Resource
	private AccfundInfoService accfundInfoService;
    @Resource
	private CreditRatingService creditRatingService;
    @Resource
    private CreditService creditService;
    @Autowired 
    private ClMoheReportInfoService clMoheReportInfoService;

	/**
	 * @description 运营商认证，上述和同盾 获取第三方url，用于APP跳转
	 * @author
	 * @return void
	 * @throws Exception 
	 * @since 1.0.0
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/api/act/mine/operator/operatorCredit.htm")
	public void operatorCredit() throws Exception {
		logger.info("运营商认证UserId："+request.getSession().getAttribute("userId").toString());
		long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		
		Map<String, Object> respMap = new HashMap<String, Object>();
		boolean isCanCredit = operatorReqLogService.checkUserOperator(userId);
		if (!isCanCredit) {
			respMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			respMap.put(Constant.RESPONSE_CODE_MSG, "今天请求认证次数超过限制,请明日再试");
		} else {
			UserBaseInfo userBaseInfo = userBaseInfoService.findByUserId(userId);
			
			 String operatorType= StringUtil.isNotBlank(Global.getValue("operator_type"))?Global.getValue("operator_type"):"";
			 if(operatorType.equals(OperatorReqLog.BUSINESS_TYPE_TD)){
				String orderNo=OrderNoUtil.getSerialNumber();
				//同盾运营商认证
				OperatorReqLog operatorReqLog = new OperatorReqLog(userId,orderNo,"400");
				operatorReqLog.setRespTime(DateUtil.getNow());
				operatorReqLog.setBusinessType(OperatorReqLog.BUSINESS_TYPE_TD);
				String operatorIdentifyUrl= Global.getValue("operator_identify_url");
				if(operatorIdentifyUrl!=null){
					String host=Global.getValue("server_host");
					String url=host+"/api/operatorReturnback.htm?orderNo="+orderNo;
					Map<String,Object> params=new HashMap<String,Object>();
					params.put("orderNo", orderNo);
					operatorIdentifyUrl=operatorIdentifyUrl +"&user_mobile="+userBaseInfo.getPhone() + "&real_name="+userBaseInfo.getRealName()
							+"&identity_code="+userBaseInfo.getIdNo()+"&cb="+URLEncoder.encode(url,"utf-8")+"&passback_params="+URLEncoder.encode(JsonUtil.toString(params),"utf-8");
					logger.info("同盾运营商认证,获取第三方URL:"+operatorIdentifyUrl);
					operatorReqLog.setRespCode("200");
					operatorReqLog.setRespParams(operatorIdentifyUrl);
					Map<String, Object> data = new HashMap<String, Object>();
					data.put("url", operatorIdentifyUrl);
                    data.put("orderNo", orderNo);

					respMap.put(Constant.RESPONSE_DATA, data);
					respMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
					respMap.put(Constant.RESPONSE_CODE_MSG, "获取成功");	
				}else{
					respMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
					respMap.put(Constant.RESPONSE_CODE_MSG, "同盾认证授权地址为空");	
				}
				operatorReqLogService.insert(operatorReqLog);
				
			}else{
				//上数运营商
				final String APIKEY = Global.getValue("apikey");
				final String SECRETKEY = Global.getValue("secretkey");
				final String OPERATOR_APIHOST = Global.getValue("operator_apihost");

				final String OPERATOR_CHANNELNO = Global.getValue("operator_channelNo");
				final String OPERATOR_INTERFACENAME = Global.getValue("operator_interfaceName");

				long timestamp = DateUtil.getNowTime();
				Header header = new Header(APIKEY, OPERATOR_CHANNELNO, OPERATOR_INTERFACENAME, timestamp);
				CreditRequest creditRequest = new CreditRequest(OPERATOR_APIHOST, header);
				Map<String, Object> payload = new HashMap<>();
				payload.put("prodCode", "OPERATOR");// OPERATOR 为运营商
				payload.put("name", userBaseInfo.getRealName());
				payload.put("idCard", userBaseInfo.getIdNo());
				payload.put("phone", userBaseInfo.getPhone());
				creditRequest.setPayload(payload);
				creditRequest.signByKey(SECRETKEY);

				String result = creditRequest.request();
				
				logger.debug("运营商认证,获取第三方URL:"+result);
				
				if (StringUtil.isNotBlank(result)) {
					Map<String, Object> resultMap = JsonUtil.parse(result,Map.class);
					String code = StringUtil.isNull(resultMap.get("code"));
					if (!"200".equals(code)) {
						BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_5, userId, StringUtil.isNull(resultMap.get("message")));
					}
					
					OperatorReqLog operatorReqLog = new OperatorReqLog(userId,String.valueOf(resultMap.get("orderNo")), code);
					operatorReqLog.setRespTime(DateUtil.getNow());
					operatorReqLog.setRespCode(String.valueOf(resultMap.get("code")));
					operatorReqLog.setRespParams(result);
					operatorReqLog.setBusinessType(OperatorReqLog.BUSINESS_TYPE_SS);
					operatorReqLogService.insert(operatorReqLog);

					Map<String, Object> res = (Map<String, Object>) resultMap.get("res");
					Map<String, Object> data = new HashMap<String, Object>();
					data.put("url", res.get("url"));

					respMap.put(Constant.RESPONSE_DATA, data);
					respMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
					respMap.put(Constant.RESPONSE_CODE_MSG, "获取成功");

				} else {
					respMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
					respMap.put(Constant.RESPONSE_CODE_MSG, "获取失败");
				}
				logger.debug("用户："+userId+"运营商认证请求发送成功，返回内容:"+result);
			}
		}
		ServletUtils.writeToResponse(response, respMap);
	 }

 
	/**
	 * 运营商验证，在用户提交完验证信息之后访问的接口，修改状态为认证中
	 */
	@RequestMapping(value = "/api/operatorReturnback.htm")
	public String operatorReturnback(@RequestParam(value = "orderNo") String orderNo,
			@RequestParam(value = "task_id",required=false) String task_id) {
		logger.info("运营商/公积金认证同步回调，orderNo:" + orderNo + ",  task_id: " + task_id);
		if (StringUtils.isEmpty(orderNo) || StringUtils.isEmpty(task_id)) {
            logger.warn("---WARN--- 运营商/公积金认证同步回调 - 参数错误，orderNo:" + orderNo + ",  task_id: " + task_id);
        }

		if(orderNo.indexOf("?")>0){
		  orderNo=orderNo.substring(0, orderNo.indexOf("?"));
		}
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("orderNo", orderNo);
		OperatorReqLog operatorReqLog = operatorReqLogService.findSelective(temp);
		if (operatorReqLog != null) {
			if(operatorReqLog.getBusinessType()==null){
				logger.info("运营商/公积金同步回调，orderNo:"+orderNo+"，未知的认证类型");
			}else if(operatorReqLog.getBusinessType().equals(OperatorReqLog.BUSINESS_TYPE_SS)){
				temp.clear();
				temp.put("userId", operatorReqLog.getUserId());
				temp.put("phoneState", "20");
				userAuthService.updateByUserId(temp);
				logger.info("运营商认证同步回调，orderNo:"+orderNo+"，成功将userAuth.phoneState的状态改为20");
			}else if(operatorReqLog.getBusinessType().equals(OperatorReqLog.BUSINESS_TYPE_AC)){
				temp.clear();
				temp.put("userId", operatorReqLog.getUserId());
				temp.put("accFundState", "20");
				userAuthService.updateByUserId(temp);
				logger.info("公积金同步回调，orderNo:"+orderNo+"，成功将userAuth.accFundState的状态改为20");
			}else if (operatorReqLog.getBusinessType().equals(OperatorReqLog.BUSINESS_TYPE_TD)){
				temp.clear();
				temp.put("userId", operatorReqLog.getUserId());
				temp.put("phoneState", "20");
				int i=userAuthService.updatePhoneState(temp);
				//同盾请求唯一标示 task_id   重要
				operatorReqLog.setRespOrderNo(task_id);
				operatorReqLogService.updateById(operatorReqLog);
				if(i>0){
					logger.info("同盾运营商认证 同步回调，orderNo:"+orderNo+"，成功将userAuth.phoneState的状态改为20");
					//执行同盾查询统计
                    // FIXME: 31/10/2017 task_id is null
                    operatorService.operatorQuery(operatorReqLog, task_id);
				}
				
			}
		} else {
			logger.error("运营商/公积金认证同步回调，根据orderNo:"+orderNo+"，没有找到对应的operatorReqLog记录");
		}
		return "operatorReturnback";
	}
	 

	/**1.
	 * @description 运营商回调接口，在访问第三方并验证之后，由大圣数据重新打包返回信息
	 * @param res 上数主要返回的数据全都放在这个变量里面
	 * @param message "获取成功"
	 * @param code 200
	 * @param orderNo 订单号
	 * @param timestamp 时间戳
	 * @param sign 验签，暂时只接收不使用
	 * @return void
	 */
	@RequestMapping(value = "/api/operatorCallback.htm")
	public void operatorCallback(HttpServletRequest request) throws Exception {

		final Map<String, Object> params = getRequestFormMap(request);
		String sign = String.valueOf(params.get("sign"));
		final String code = String.valueOf(params.get("code"));
		final String res = String.valueOf(params.get("res"));
		final String orderNo = String.valueOf(params.get("orderNo"));
		final String compressStatus = String.valueOf(params.get("compressStatus"));
		logger.info("运营商/公积金认证异步回调，orderNo:"+orderNo+"，code:"+code);
		
		Map<String, Object> signMap = new HashMap<>();/* 验签map */
		signMap.put("res", res);
		signMap.put("orderNo", orderNo);
		signMap.put("timestamp", params.get("timestamp"));
		String tempSign = ApiSignUtil.sign(Global.getValue("apikey"), Global.getValue("secretkey"), signMap);

		String result = "FAIL";
		if (!tempSign.equals(sign)) {
			logger.error("运营商/公积金认证验签失败，orderNo:"+orderNo);
		} else {
			OperatorReqLog operatorReqLog = null;
			if (StringUtil.isNotBlank(orderNo)) {
				Map<String, Object> temp = new HashMap<String, Object>();
				temp.put("orderNo", orderNo);
				operatorReqLog = operatorReqLogService.findSelective(temp);
			}
			if (operatorReqLog != null) {
				final Date updateTime = DateUtil.getNow();
				
				// 将运营商认证请求记录里的notify_params存入分表   2017-5-17  xx
				params.put("res", "");
				OperatorRespDetail operatorRespDetail = new OperatorRespDetail(operatorReqLog.getId(), operatorReqLog.getOrderNo(), JSONObject.toJSONString(params));
				operatorRespDetailService.insert(operatorRespDetail);
				
				final Long userId = operatorReqLog.getUserId();
				final String businessType = StringUtil.isNull(operatorReqLog.getBusinessType());
				Thread thread = new Thread(new Runnable() {
					public void run() {
						if(businessType.equals(OperatorReqLog.BUSINESS_TYPE_SS)){// 运营商认证
							if ("200".equals(code)) {
								Map<String,Object> userAuth = new HashMap<String, Object>();
								userAuth.put("userId", userId);
								userAuth.put("phoneState", "30");
								userAuthService.updateByUserId(userAuth);
								
								//验证状态，启用评分，更新额度
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
								}
								
								try {
									operatorService.saveOperatorInfos(res, compressStatus, userId ,updateTime);
								} catch (Exception e) {
									// 运营商数据保存失败，将认证状态改回未认证
									userAuth.put("phoneState", "10");
									userAuthService.updateByUserId(userAuth);
									logger.error("严重问题，userId:"+userId+"运营商数据保存失败", e);
								}
							} else {
								Map<String, Object> userAuth = new HashMap<String, Object>();
								userAuth.put("userId", userId);
								userAuth.put("phoneState", "10");
								userAuthService.updateByUserId(userAuth);
							}
						} else if(businessType.equals(OperatorReqLog.BUSINESS_TYPE_AC)){// 公积金
							if ("200".equals(code)) {
								try {
									logger.debug("公积金认证异步回调，业务处理开始，参数：" + res);
									accfundInfoService.saveAccfundInfos(res, userId ,updateTime);
								} catch (Exception e) {
									logger.error(e.getMessage(), e);
								}
							} else {
								Map<String, Object> userAuth = new HashMap<String, Object>();
								userAuth.put("userId", userId);
								userAuth.put("accFundState", "10");
								userAuthService.updateByUserId(userAuth);
							}
						}
					}
				});
				thread.start();
				result = "SUCCESS";
			} else {
				logger.error("运营商/公积金认证异步回调，根据orderNo:"+orderNo+"，没有找到对应的operatorReqLog记录");
			}
		}
		PrintWriter writer = response.getWriter();
		writer.print(result);
		writer.flush();
		writer.close();
	}
	
	/**
	 * @description  同盾运营商异步回调
	 * @return void
	 */
	@RequestMapping(value = "/api/act/operatorTongdunCallback.htm")
	public void operatorTongdunCallback(HttpServletRequest request,
			@RequestParam(value = "sign") String sign,
			@RequestParam(value = "notify_type") String notify_type,
			@RequestParam(value = "notify_event") String notify_event,
			@RequestParam(value = "notify_time") String notify_time,
			@RequestParam(value = "notify_data") String notify_data,
			@RequestParam(value = "passback_params",required=false) String passback_params) throws Exception {
		   logger.info("进入同盾运营商认证异步回调。。。。。。");
		   logger.info("回调参数:notify_type="+notify_type+"&notify_event="+notify_event+"&notify_time="+notify_time+"&notify_data="+notify_data+"&passback_params="+passback_params);
		if(!StringUtil.isBlank(notify_data)&&notify_data!=null&&notify_data!="null"){
			@SuppressWarnings("unchecked")
            // FIXME: 31/10/2017 refine it.
            Map<String, Object> resultMap = JsonUtil.parse(notify_data, Map.class);
            String task_id = String.valueOf(resultMap.get("task_id"));
            JSONObject dataJsonObj = JSONObject.parseObject(notify_data);
            if (StringUtils.isEmpty(task_id)) {
                task_id = dataJsonObj.get("task_id").toString();
            }

	    	Map<String,Object> params=JSONObject.parseObject(passback_params);
	    	
	    	String orderNo=String.valueOf(params.get("orderNo"));
			OperatorReqLog operatorReqLog = null;
			
			if (StringUtil.isNotBlank(task_id)) {
				//temp.put("respOrderNo", task_id);
				//operatorReqLog = operatorReqLogService.findSelective(temp);
				Map<String, Object> tempLog = new HashMap<String, Object>();
				tempLog.put("orderNo", orderNo);
				OperatorReqLog operatorReqLog_task_id = operatorReqLogService.findSelective(tempLog);
				operatorReqLog_task_id.setRespOrderNo(task_id);
				operatorReqLog_task_id.setRespTime(new Date());
				operatorReqLogService.updateById(operatorReqLog_task_id);
			} 
			if(operatorReqLog==null&&StringUtil.isNotBlank(passback_params)){
				Map<String, Object> temp = new HashMap<String, Object>();
				temp = new HashMap<String, Object>();
				temp.put("orderNo", orderNo);
				operatorReqLog = operatorReqLogService.findSelective(temp);
			}
			if (operatorReqLog != null) {
                task_id = dataJsonObj.get("task_id").toString();
				//执行同盾查询统计
				operatorService.operatorQuery(operatorReqLog, task_id);
			} else {
				logger.error("运营商认证异步回调，根据resOrderNo:"+task_id+"或者根据orderNo:"+orderNo+",没有找到对应的operatorReqLog记录");
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
	        paramMap.put("userId", operatorReqLog.getUserId());
	        UserBaseInfo info = userBaseInfoService.findSelective(paramMap);
			
			MoheParamHolder moheParamHolder = new MoheParamHolder(task_id);
			moheParamHolder.setIdNo(info.getIdNo());
			moheParamHolder.setReal_name(info.getRealName());
			moheParamHolder.setTask_id(task_id);
			moheParamHolder.setUserId(info.getUserId());

			moheParamHolder.append(info);
	        clMoheReportInfoService.runMohe(moheParamHolder);

			// FIXME: 2017/11/3 获得数据之后再评分
			// FIXME: 2017/11/3 把运营商数据落库之后再评分
			//验证状态，启用评分，更新额度
			boolean authFlag = userAuthService.findAuthState(operatorReqLog.getUserId());
			if (authFlag){//完成全部认证
				String consumerNo = operatorReqLog.getUserId().toString();
				//开视评分
				creditRatingService.saveCreditRating(consumerNo, 1l);//借款类型默认为现金贷，即 long borrowTypeId=1l
				//开始更新授信额度数据
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("used", "0");
				map.put("consumerNo", operatorReqLog.getUserId());
				map.put("state", "10");//待审批
				creditService.updateByAuth(map);
			}


		}
		Map<String, Object>  result= new HashMap<String, Object>();
		result.put("code", 0);
		result.put("message", "success");
		PrintWriter writer = response.getWriter();
		writer.print(result);
		writer.flush();
		writer.close();
	}
	

	
	
	 

}
