package com.xindaibao.cashloan.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.xindaibao.cashloan.api.user.service.ContractService;
import com.xindaibao.cashloan.cl.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tool.util.DateUtil;

import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.cl.domain.BorrowProgress;
import com.xindaibao.cashloan.cl.domain.BorrowRepayLog;
import com.xindaibao.cashloan.cl.domain.PayLog;
import com.xindaibao.cashloan.cl.domain.PayReqLog;
import com.xindaibao.cashloan.cl.domain.PayRespLog;
import com.xindaibao.cashloan.cl.model.PayLogModel;
import com.xindaibao.cashloan.cl.model.PayRespLogModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.PaymentModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.constant.LianLianConstant;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.model.BorrowModel;

/**
 * 连连实时付款(代付)异步通知
 * 
 * @author
 * @version 1.0.0
 * @date 2017年3月24日 下午2:49:56


 * 

 */
@Controller
@Scope("prototype")
public class PayController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(PayController.class);

	@Resource
	private PayReqLogService payReqLogService;
	@Resource
	private PayRespLogService payRespLogService;
	@Resource
	private PayLogService payLogService;
	@Resource
	private ClBorrowService clBorrowService;
	@Resource
	private BorrowProgressService borrowProgressService;
	@Resource
	private BorrowRepayService borrowRepayService;
	@Resource
	private BorrowRepayLogService borrowRepayLogService;
	@Resource
	private ProfitAmountService profitAmountService;
	@Resource
    private ContractService contractService;
	@Resource
	private ClSmsService clSmsService;

	/**
	 * 放款付款 - 异步通知处理
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/pay/lianlian/paymentNotify.htm")
	public void paymentNotify(HttpServletRequest request) throws Exception {
		String params = getRequestParams(request);

		logger.info("实时付款(放款)- 异步通知" + params);
		PaymentModel model = JSONObject.parseObject(params, PaymentModel.class);
		boolean verifySignFlag = model.checkSign(model);

		if (!verifySignFlag) {
			logger.error("验签失败" + model.getNo_order());
			return;
		}

		logger.debug("进入订单" + model.getNo_order() + "处理中.....");
		
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
		
		if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
				|| PayLogModel.STATE_AUDIT_PASSED.equals(payLog.getState())) {

			// 代付成功，更新借款状态及支付订单 ，否则只更新订单状态
			if (LianLianConstant.RESULT_SUCCESS.equals(model.getResult_pay())) {
				// 修改借款状态
				Map<String, Object> map = new HashMap<>();
				map.put("id", payLog.getBorrowId());
				map.put("state", BorrowModel.STATE_REPAY);
				clBorrowService.updatePayState(map);

				// 放款进度添加
				BorrowProgress bp = new BorrowProgress();
				bp.setUserId(payLog.getUserId());
				bp.setBorrowId(payLog.getBorrowId());
				bp.setState(BorrowModel.STATE_REPAY);
				bp.setRemark(BorrowModel.convertBorrowRemark(bp.getState()));
				bp.setCreateTime(DateUtil.getNow());
				borrowProgressService.save(bp);

				// 生成还款计划
				Borrow borrow = clBorrowService.getById(payLog.getBorrowId());
				borrowRepayService.genRepayPlan(borrow, DateUtil.getNow());

				// 用户还款计划代扣授权
				borrowRepayService.authSignApply(payLog.getBorrowId());
				
				// 更新订单状态
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);
				//短信通知(打款成功)
				clSmsService.loanInform(payLog.getUserId(), payLog.getBorrowId());
//				e签宝生成pdf文件
//				new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        contractService.buildPdf(borrow.getId().toString());
//                    }
//                }).start();
				
			}else if(LianLianConstant.RESULT_FAILURE.equals(model.getResult_pay())){
				Map<String, Object> borrowMap = new HashMap<String, Object>();
				borrowMap.put("id", payLog.getBorrowId());
				borrowMap.put("state", BorrowModel.STATE_REPAY_FAIL);
				clBorrowService.updatePayState(borrowMap);
				
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_FAILED);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("remark",model.getInfo_order());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);
				
			}
	
			Map<String, Object> result = new HashMap<String, Object>();
			result.put(LianLianConstant.RESPONSE_CODE,LianLianConstant.RESPONSE_SUCCESS_CODE);
			result.put(LianLianConstant.RESPONSE_MSG,LianLianConstant.RESPONSE_SUCCESS_VALUE);
			ServletUtils.writeToResponse(response, result);
		}else{
			logger.info("订单" + payLog.getOrderNo() + "已处理");
		}
	}
	
	/**
	 * 奖励发放 - 异步通知处理
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/pay/lianlian/profitNotify.htm")
	public void profitNotify(HttpServletRequest request) throws Exception {
		String params = getRequestParams(request);
		logger.info("实时付款(取现) - 异步通知:" + params);

		PaymentModel model = JSONObject.parseObject(params, PaymentModel.class);
		boolean verifySignFlag = model.checkSign(model);

		if (!verifySignFlag) {
			logger.error("验签失败"+ model.getNo_order());
			return;
		}

		logger.debug("进入订单" + model.getNo_order() + "处理中.....");
		
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
		
		if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
				|| PayLogModel.STATE_AUDIT_PASSED.equals(payLog.getState())) {

			// 代付成功，更新借款状态及支付订单 ，否则只更新订单状态
			if (LianLianConstant.RESULT_SUCCESS.equals(model.getResult_pay())) {
				// 更新取现金额， 添加取现记录
				profitAmountService.cash(payLog.getUserId(), payLog.getAmount());
				
				// 更新订单状态
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);
				
			}else if(LianLianConstant.RESULT_FAILURE.equals(model.getResult_pay())){
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
		}else{
			logger.info("订单" + payLog.getOrderNo() + "已处理");
		}
	}
	
	
	
	/**
	 * 退还 - 异步通知处理
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/pay/lianlian/refundNotify.htm")
	public void refundNotify(HttpServletRequest request) throws Exception {
		String params = getRequestParams(request);
		logger.info("实时付款(退还) - 异步通知:" + params);

		PaymentModel model = JSONObject.parseObject(params, PaymentModel.class);
		boolean verifySignFlag = model.checkSign(model);

		if (!verifySignFlag) {
			logger.error("验签失败" + model.getNo_order());
			return;
		}

		logger.debug("进入订单" + model.getNo_order() + "处理中.....");
		
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
		
		if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
				|| PayLogModel.STATE_AUDIT_PASSED.equals(payLog.getState())) {

			// 代付成功 ，否则只更新订单状态
			if (LianLianConstant.RESULT_SUCCESS.equals(model.getResult_pay())) {
				
				// 查询还款记录
				Map<String, Object> repayLogMap =  new HashMap<String, Object>();
				repayLogMap.put("borrowId", payLog.getBorrowId());
				repayLogMap.put("userId", payLog.getUserId());
				BorrowRepayLog repayLog = borrowRepayLogService.findSelective(repayLogMap);
				
				// 更新还款记录
				Map<String, Object> refundDeductionMap = new HashMap<String, Object>();
				refundDeductionMap.put("id", repayLog.getId());
				refundDeductionMap.put("refundDeduction", - payLog.getAmount());
				refundDeductionMap.put("payTime", DateUtil.getNow());
				borrowRepayLogService.refundDeduction(refundDeductionMap);
				
				// 更新订单状态
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
				paramMap.put("updateTime",DateUtil.getNow());
				paramMap.put("id",payLog.getId());
				payLogService.updateSelective(paramMap);
				
			}else if(LianLianConstant.RESULT_FAILURE.equals(model.getResult_pay())){
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
		}else{
			logger.info("订单" + payLog.getOrderNo() + "已处理");
		}
	}
	
	private void modifyPayReqLog (PayReqLog payReqLog,String params){
		payReqLog.setNotifyParams(params);
		payReqLog.setNotifyTime(DateUtil.getNow());
		payReqLogService.updateById(payReqLog);
	}
	
	
}