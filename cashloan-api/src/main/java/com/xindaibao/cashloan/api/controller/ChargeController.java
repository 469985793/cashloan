package com.xindaibao.cashloan.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tool.util.DateUtil;

import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.cl.domain.BankCard;
import com.xindaibao.cashloan.cl.domain.BorrowRepay;
import com.xindaibao.cashloan.cl.domain.BorrowRepayLog;
import com.xindaibao.cashloan.cl.domain.PayLog;
import com.xindaibao.cashloan.cl.domain.PayReqLog;
import com.xindaibao.cashloan.cl.domain.PayRespLog;
import com.xindaibao.cashloan.cl.model.BorrowRepayLogModel;
import com.xindaibao.cashloan.cl.model.BorrowRepayModel;
import com.xindaibao.cashloan.cl.model.PayLogModel;
import com.xindaibao.cashloan.cl.model.PayRespLogModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.RepaymentModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.constant.LianLianConstant;
import com.xindaibao.cashloan.cl.service.BankCardService;
import com.xindaibao.cashloan.cl.service.BorrowRepayLogService;
import com.xindaibao.cashloan.cl.service.BorrowRepayService;
import com.xindaibao.cashloan.cl.service.ClSmsService;
import com.xindaibao.cashloan.cl.service.PayLogService;
import com.xindaibao.cashloan.cl.service.PayReqLogService;
import com.xindaibao.cashloan.cl.service.PayRespLogService;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;

/**
 * 连连分期付(代扣)异步通知
 */
@Controller
@Scope("prototype")
public class ChargeController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChargeController.class);
	
	@Resource
	private PayReqLogService payReqLogService;
	@Resource
	private PayRespLogService payRespLogService;
	@Resource
	private PayLogService payLogService;
	@Resource	
	private BorrowRepayService borrowRepayService;
	@Resource
	private BorrowRepayLogService borrowRepayLogService;
	@Resource
	private BankCardService bankCardService;
	@Resource
	private ClSmsService clSmsService;
	
	/**
	 * 还款代扣 - 异步通知处理
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/pay/lianlian/repaymentNotify.htm")
	public void repaymentNotify(HttpServletRequest request) throws Exception {
		String params = getRequestParams(request);
		logger.debug("分期付 (还款)- 异步通知：" + params);
		
		RepaymentModel  model  = JSONObject.parseObject(params,RepaymentModel.class);
		boolean verifySignFlag   = model.checkSign(model);
		if (!verifySignFlag) {
			logger.error("验签失败" + model.getNo_order());
			return;
		}
		logger.info("进入订单"+ model.getNo_order() +"处理中.....");
		
		PayReqLog payReqLog = payReqLogService.findByOrderNo(model.getNo_order());
		if (payReqLog != null) {
			// 保存respLog
			PayRespLog payRespLog = new PayRespLog(model.getNo_order(),PayRespLogModel.RESP_LOG_TYPE_NOTIFY,params);
			payRespLogService.save(payRespLog);
			
			// 更新reqLog
			modifyPayReqLog(payReqLog,params);
		}
		
		PayLog payLog = payLogService.findByOrderNo(model.getNo_order());
		if(null  == payLog ){
			logger.warn("未查询到对应的支付订单");
			return ;
		}
		
		/*if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
				|| PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {*/

			// 分期付扣款成功，更新借款状态及支付订单 ，否则只更新订单状态
			if (LianLianConstant.RESULT_SUCCESS.equals(model.getResult_pay())) {

				// 查找对应的还款计划
				Map<String, Object> repayMap = new HashMap<String, Object>();
				repayMap.put("userId", payLog.getUserId());
				repayMap.put("borrowId", payLog.getBorrowId());
				BorrowRepay borrowRepay = borrowRepayService.findSelective(repayMap);
				BankCard bankCard = bankCardService.getBankCardByUserId(payLog.getUserId());
			
				if (borrowRepay != null) {
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("id", borrowRepay.getId());
					//param.put("repayTime", DateUtil.dateStr4(DateUtil.getNow()));
					param.put("repayTime", DateUtil.getNow());
					param.put("repayWay", BorrowRepayLogModel.REPAY_WAY_CHARGE);
					param.put("repayAccount", bankCard.getCardNo());
					param.put("amount", payLog.getAmount());
					param.put("serialNumber", payLog.getOrderNo());
					param.put("penaltyAmout", borrowRepay.getPenaltyAmout());
					param.put("state", "10");
					if (!borrowRepay.getState().equals(BorrowRepayModel.STATE_REPAY_YES)) {
						   borrowRepayService.confirmRepay(param);
					} 
				} 
				
				
				// 更新订单状态
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);
				//发送扣款成功短信
				if(DateUtil.daysBetween(DateUtil.getNow(), borrowRepay.getRepayTime())>=0){
					//未逾期还款成功
					clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId(),"noRepayInform");
				}else{
					clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId(),"repayInformSucess");
				}


			} else if (LianLianConstant.RESULT_FAILURE.equals(model.getResult_pay())) {
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_FAILED);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);
			}
		

			Map<String, Object> result = new HashMap<String, Object>();
			result.put(LianLianConstant.RESPONSE_CODE,LianLianConstant.RESPONSE_SUCCESS_CODE);
			result.put(LianLianConstant.RESPONSE_MSG,LianLianConstant.RESPONSE_SUCCESS_VALUE);
			ServletUtils.writeToResponse(response, result);
			logger.info("订单" + payLog.getOrderNo() + "已处理完成");

		/*}else{
			logger.info("订单"+ payLog.getOrderNo() +"已处理");
		}*/
	}

	/**
	 * 手动还款 - 异步通知处理
	 * @throws Exception
	 */
	@RequestMapping(value = "/pay/lianlian/manualRepayNotify.htm")
	public void manualRepayNotify(HttpServletRequest request) throws Exception {
		String params = getRequestParams(request);
		logger.info("手动还款 (异步)- 异步通知：" + params);

		RepaymentModel  model  = JSONObject.parseObject(params,RepaymentModel.class);
		boolean verifySignFlag   = model.checkSign(model);
		if (!verifySignFlag) {
			logger.error("手动还款-异步-验签失败" + model.getNo_order());
			return;
		}
		logger.info("手动还款-异步-进入订单"+ model.getNo_order() +"处理中.....");

		PayReqLog payReqLog = payReqLogService.findByOrderNo(model.getNo_order());
		if (payReqLog != null) {
			// 保存respLog
			PayRespLog payRespLog = new PayRespLog(model.getNo_order(),PayRespLogModel.RESP_LOG_TYPE_NOTIFY,params);
			payRespLogService.save(payRespLog);

			// 更新reqLog
			modifyPayReqLog(payReqLog,params);
		}

		PayLog payLog = payLogService.findByOrderNo(model.getNo_order());
		if(null  == payLog ){
			logger.warn("未查询到对应的支付订单-异步",model.getNo_order());
			return ;
		}

	/*	if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
				|| PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {*/

			// FIXME: 2017/11/20 判断连连状态，
			if (LianLianConstant.RESULT_SUCCESS.equals(model.getResult_pay())) {

				// 查找对应的还款计划
				Map<String, Object> repayMap = new HashMap<String, Object>();
				repayMap.put("userId", payLog.getUserId());
				repayMap.put("borrowId", payLog.getBorrowId());
				BorrowRepay borrowRepay = borrowRepayService.findSelective(repayMap);
				BankCard bankCard = bankCardService.getBankCardByUserId(payLog.getUserId());

				if (borrowRepay != null) {
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("id", borrowRepay.getId());
					//param.put("repayTime", DateUtil.dateStr4(DateUtil.getNow()));
					param.put("repayTime", DateUtil.getNow());
					param.put("repayWay", BorrowRepayLogModel.REPAY_WAY_CERTIFIED);
					param.put("repayAccount", bankCard.getCardNo());
					param.put("amount", payLog.getAmount());
					param.put("serialNumber", payLog.getOrderNo());
					param.put("penaltyAmout", borrowRepay.getPenaltyAmout());
					param.put("state", "10");
					if (!borrowRepay.getState().equals(BorrowRepayModel.STATE_REPAY_YES)) {
						borrowRepayService.confirmRepay(param);
					}
				}


				// 更新订单状态
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				paramMap.put("remark", "交易成功");
				payLogService.updateSelective(paramMap);
				//发送扣款成功短信
				if(DateUtil.daysBetween(DateUtil.getNow(), borrowRepay.getRepayTime())>0){
					//未逾期还款成功
					clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId(),"noRepayInform");
				}else{
					clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId(),"repayInformSucess");
				}

			} else if (LianLianConstant.RESULT_FAILURE.equals(model.getResult_pay())) {
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_FAILED);
				paramMap.put("remark", "async"+model.getRet_msg());
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);
			}


			Map<String, Object> result = new HashMap<String, Object>();
			result.put(LianLianConstant.RESPONSE_CODE,LianLianConstant.RESPONSE_SUCCESS_CODE);
			result.put(LianLianConstant.RESPONSE_MSG,LianLianConstant.RESPONSE_SUCCESS_VALUE);
			ServletUtils.writeToResponse(response, result);
			logger.info("订单" + payLog.getOrderNo() + "已处理完成");
		/*}else{
			logger.info("订单"+ payLog.getOrderNo() +"手动还款-异步-已处理");
		}*/
		Map<String, Object> r_result = new HashMap<String, Object>();
		r_result.put(LianLianConstant.RESPONSE_CODE,LianLianConstant.RESPONSE_SUCCESS_CODE);
		r_result.put(LianLianConstant.RESPONSE_MSG,LianLianConstant.RESPONSE_SUCCESS_VALUE);
		ServletUtils.writeToResponse(response, r_result);
	}


	/**
	 * 手动还款 - 异步通知处理
	 * fast 快捷
	 * @throws Exception
	 */
	@RequestMapping(value = "/pay/lianlian/manualFastRepayNotify.htm")
	public void manualFastRepayNotify(HttpServletRequest request) throws Exception {
		String params = getRequestParams(request);
		logger.info("手动还款-快捷 (异步)- 异步通知：" + params);

		RepaymentModel  model  = JSONObject.parseObject(params,RepaymentModel.class);
		boolean verifySignFlag   = model.checkSign(model);
		if (!verifySignFlag) {
			logger.error("手动还款-快捷-异步-验签失败" + model.getNo_order());
			return;
		}
		logger.info("手动还款-快捷-异步-进入订单"+ model.getNo_order() +"处理中.....");

		PayReqLog payReqLog = payReqLogService.findByOrderNo(model.getNo_order());
		if (payReqLog != null) {
			// 保存respLog
			PayRespLog payRespLog = new PayRespLog(model.getNo_order(),PayRespLogModel.RESP_LOG_TYPE_NOTIFY,params);
			payRespLogService.save(payRespLog);

			// 更新reqLog
			modifyPayReqLog(payReqLog,params);
		}

		PayLog payLog = payLogService.findByOrderNo(model.getNo_order());
		if(null  == payLog ){
			logger.warn("未查询到对应的支付订单-异步-快捷",model.getNo_order());
			return ;
		}

		/*if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
				|| PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {*/

			// FIXME: 2017/11/20 判断连连状态，
			if (LianLianConstant.RESULT_SUCCESS.equals(model.getResult_pay())) {

				// 查找对应的还款计划
				Map<String, Object> repayMap = new HashMap<String, Object>();
				repayMap.put("userId", payLog.getUserId());
				repayMap.put("borrowId", payLog.getBorrowId());
				BorrowRepay borrowRepay = borrowRepayService.findSelective(repayMap);
				BankCard bankCard = bankCardService.getBankCardByUserId(payLog.getUserId());

				if (borrowRepay != null) {
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("id", borrowRepay.getId());
					//param.put("repayTime", DateUtil.dateStr4(DateUtil.getNow()));
					param.put("repayTime", DateUtil.getNow());
					param.put("repayWay", BorrowRepayLogModel.REPAY_WAY_CERTIFIED);
					param.put("repayAccount", bankCard.getCardNo());
					param.put("amount", payLog.getAmount());
					param.put("serialNumber", payLog.getOrderNo());
					param.put("penaltyAmout", borrowRepay.getPenaltyAmout());
					param.put("state", "10");
					if (!borrowRepay.getState().equals(BorrowRepayModel.STATE_REPAY_YES)) {
						borrowRepayService.confirmRepay(param);
					}
				}



				// 更新订单状态
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				paramMap.put("remark", "交易成功");
				payLogService.updateSelective(paramMap);
				//发送扣款成功短信
				if(DateUtil.daysBetween(DateUtil.getNow(), borrowRepay.getRepayTime())>=0){
					//未逾期还款成功
					clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId(),"noRepayInform");
				}else{
					clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId(),"repayInformSucess");
				}

			} else if (LianLianConstant.RESULT_FAILURE.equals(model.getResult_pay())) {
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_FAILED);
				paramMap.put("remark", "async"+model.getRet_msg());
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);
			}
			logger.info("订单" + payLog.getOrderNo() + "已处理完成");

			Map<String, Object> result = new HashMap<String, Object>();
			result.put(LianLianConstant.RESPONSE_CODE,LianLianConstant.RESPONSE_SUCCESS_CODE);
			result.put(LianLianConstant.RESPONSE_MSG,LianLianConstant.RESPONSE_SUCCESS_VALUE);
			ServletUtils.writeToResponse(response, result);


		/*}else{
			logger.info("订单"+ payLog.getOrderNo() +"手动还款-异步-快捷-已处理");
		}*/
		Map<String, Object> r_result = new HashMap<String, Object>();
		r_result.put(LianLianConstant.RESPONSE_CODE,LianLianConstant.RESPONSE_SUCCESS_CODE);
		r_result.put(LianLianConstant.RESPONSE_MSG,LianLianConstant.RESPONSE_SUCCESS_VALUE);
		ServletUtils.writeToResponse(response, r_result);
	}




	/**
	 * 补扣 - 异步通知处理
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/pay/lianlian/deductionNotify.htm")
	public void deductionNotify(HttpServletRequest request) throws Exception {
		
		String params = getRequestParams(request);
		logger.debug("分期付 (补扣)- 异步通知：" + params);
		
		RepaymentModel  model  = JSONObject.parseObject(params,RepaymentModel.class);
		boolean verifySignFlag   = model.checkSign(model);
		if (!verifySignFlag) {
			logger.error("验签失败" + model.getNo_order());
			return;
		}
		logger.info("进入订单" + model.getNo_order() + "处理中.....");
		
		PayReqLog payReqLog = payReqLogService.findByOrderNo(model.getNo_order());
		if (payReqLog != null) {
			// 保存respLog
			PayRespLog payRespLog = new PayRespLog(model.getNo_order(),PayRespLogModel.RESP_LOG_TYPE_NOTIFY,params);
			payRespLogService.save(payRespLog);
			
			// 更新reqLog
			modifyPayReqLog(payReqLog,params);
		}
		
		PayLog payLog = payLogService.findByOrderNo(model.getNo_order());
		if(null  == payLog ){
			logger.warn("未查询到对应的支付订单");
			return ;
		}
		
		/*if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
				|| PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())) {*/

			// 查找对应的还款计划
			Map<String, Object> repayMap = new HashMap<String, Object>();
			repayMap.put("userId", payLog.getUserId());
			repayMap.put("borrowId", payLog.getBorrowId());
			BorrowRepay borrowRepay = borrowRepayService.findSelective(repayMap);

			// 分期付扣款成功，更新借款状态及支付订单 ，否则只更新订单状态
			if (LianLianConstant.RESULT_SUCCESS.equals(model.getResult_pay())) {
				
				// 查询还款记录
				Map<String, Object> repayLogMap =  new HashMap<String, Object>();
				repayLogMap.put("borrowId", payLog.getBorrowId());
				repayLogMap.put("userId", payLog.getUserId());
				BorrowRepayLog repayLog = borrowRepayLogService.findSelective(repayLogMap);
				
				// 更新还款记录
				Map<String, Object> refundDeductionMap = new HashMap<String, Object>();
				refundDeductionMap.put("id", repayLog.getId());
				refundDeductionMap.put("refundDeduction", payLog.getAmount());
				refundDeductionMap.put("payTime", DateUtil.getNow());
				borrowRepayLogService.refundDeduction(refundDeductionMap);
				
				// 更新订单状态
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);

				//发送扣款成功短信
				if(DateUtil.daysBetween(DateUtil.getNow(), borrowRepay.getRepayTime())>=0){
					//未逾期还款成功
					clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId(),"noRepayInform");
				}else{
					clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId(),"repayInformSucess");
				}
			} else if (LianLianConstant.RESULT_FAILURE.equals(model.getResult_pay())) {
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_FAILED);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);
			}

			Map<String, Object> result = new HashMap<String, Object>();
			result.put(LianLianConstant.RESPONSE_CODE,LianLianConstant.RESPONSE_SUCCESS_CODE);
			result.put(LianLianConstant.RESPONSE_MSG,LianLianConstant.RESPONSE_SUCCESS_VALUE);
			ServletUtils.writeToResponse(response, result);
			logger.info("订单" + payLog.getOrderNo() + "已处理完成");

		/*}else{
			logger.info("订单" + payLog.getOrderNo() + "已处理");
		}*/
	}
	
	private void modifyPayReqLog (PayReqLog payReqLog,String params){
		payReqLog.setNotifyParams(params);
		payReqLog.setNotifyTime(DateUtil.getNow());
		payReqLogService.updateById(payReqLog);
	}
	
}
