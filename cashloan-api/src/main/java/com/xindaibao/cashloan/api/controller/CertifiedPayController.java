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

import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.cl.domain.PayLog;
import com.xindaibao.cashloan.cl.domain.PayReqLog;
import com.xindaibao.cashloan.cl.domain.PayRespLog;
import com.xindaibao.cashloan.cl.model.BorrowRepayLogModel;
import com.xindaibao.cashloan.cl.model.PayLogModel;
import com.xindaibao.cashloan.cl.model.PayRespLogModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.CertifiedPayModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.constant.LianLianConstant;
import com.xindaibao.cashloan.cl.service.BorrowProgressService;
import com.xindaibao.cashloan.cl.service.BorrowRepayLogService;
import com.xindaibao.cashloan.cl.service.BorrowRepayService;
import com.xindaibao.cashloan.cl.service.ClBorrowService;
import com.xindaibao.cashloan.cl.service.PayLogService;
import com.xindaibao.cashloan.cl.service.PayReqLogService;
import com.xindaibao.cashloan.cl.service.PayRespLogService;
import com.xindaibao.cashloan.cl.service.ProfitAmountService;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.core.domain.Borrow;

import tool.util.DateUtil;
import tool.util.StringUtil;

/**
 * 连连支付 - 认证支付
 */
@Controller
@Scope("prototype")
public class CertifiedPayController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(CertifiedPayController.class);

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

	/**
	 * 认证支付- 异步通知处理
	 * 
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/pay/lianlian/certifiedNotify.htm")
	public void certifiedPayNotify(HttpServletRequest request) throws Exception {
		String params = getRequestParams(request);
		logger.info("连连认证支付 - 异步通知" + params);

		CertifiedPayModel model = JSONObject.parseObject(params, CertifiedPayModel.class);

		boolean verifySignFlag = model.checkSign(model);
		if (!verifySignFlag) {
			logger.error("验签失败" + model.getNo_order());
			return;
		}

		// 校验订单是否成功或失败 否则等下次通知
		if (!LianLianConstant.RESULT_SUCCESS.equals(model.getResult_pay())
				&& !LianLianConstant.RESULT_FAILURE.equals(model.getResult_pay())) {
			logger.error("订单处理中," + model.getNo_order() + " 等下一次通知");
			return;
		}

		logger.debug("进入订单" + model.getNo_order() + "处理中.....");

		PayReqLog payReqLog = payReqLogService.findByOrderNo(model.getNo_order());
		if (null != payReqLog) {
			// 保存respLog
			PayRespLog payRespLog = new PayRespLog(model.getNo_order(), PayRespLogModel.RESP_LOG_TYPE_NOTIFY, params);
			payRespLogService.save(payRespLog);

			// 更新reqLog
			modifyPayReqLog(payReqLog, params);
		}

		PayLog payLog = payLogService.findByOrderNo(model.getNo_order());
		if (null == payLog) {
			logger.warn("未查询到对应的支付订单");
			return;
		}

		if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())) {

			// 更新支付记录状态
			String payState = "";
			if (LianLianConstant.RESULT_SUCCESS.equals(model.getResult_pay())) {
				payState = PayLogModel.STATE_PAYMENT_SUCCESS;
			} else if (LianLianConstant.RESULT_FAILURE.equals(model.getResult_pay())) {
				payState = PayLogModel.STATE_PAYMENT_FAILED;
			}
			modifyPayLog(model.getRet_msg(), payLog.getId(), payState);
			
			// 代付成功，业务处理
			if (LianLianConstant.RESULT_SUCCESS.equals(model.getResult_pay())) {
				String scene = payLog.getScenes();

				// 续期申请
				if (PayLogModel.SCENES_RENEW_APPLY.equals(scene)) {
					clBorrowService.renewNotify(payLog.getBorrowId(),payState, payLog.getOrderNo(),
							BorrowRepayLogModel.REPAY_WAY_CERTIFIED, payLog.getCardNo(),
							StringUtil.isNull(payLog.getAmount()));
					
					// 修改续借状态
					Map<String, Object> renewMap = new HashMap<>();
					renewMap.put("userId", payLog.getUserId());
					Borrow borrow = clBorrowService.findLastBorrow(renewMap);
						
					logger.info("续借完成之后，修改原订单: " + borrow.getOriginalOrderNo() + "renewState 状态：  " + borrow.getState());
					if (borrow != null && borrow.getRenewMark() != null && borrow.getRenewMark() > 0) {
						Map<String, Object> renewStateMap = new HashMap<>();
						renewStateMap.put("id", borrow.getOriginalId());
						renewStateMap.put("renewState", borrow.getState());
						clBorrowService.updateSelective(renewStateMap);
					}
				}

				// 主动还款
				if (PayLogModel.SCENES_ACTIVE_REPAYMENT.equals(scene)
						|| PayLogModel.SCENES_RENEW_ACTIVE_REPAYMENT.equals(scene)) {
					borrowRepayService.repaymentNotify(payLog, PayLogModel.STATE_PAYMENT_SUCCESS,
							StringUtil.isNull(payLog.getAmount()), BorrowRepayLogModel.REPAY_WAY_CERTIFIED,
							payLog.getCardNo());
				}

			}

			Map<String, Object> result = new HashMap<String, Object>();
			result.put(LianLianConstant.RESPONSE_CODE, LianLianConstant.RESPONSE_SUCCESS_CODE);
			result.put(LianLianConstant.RESPONSE_MSG, LianLianConstant.RESPONSE_SUCCESS_VALUE);
			ServletUtils.writeToResponse(response, result);
		} else {

			logger.info("订单" + payLog.getOrderNo() + "已处理");
		}
	}

	private void modifyPayLog(String  remark, Long payLogId, String payState) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("state", payState);
		paramMap.put("updateTime", DateUtil.getNow());
		paramMap.put("remark",remark);
		paramMap.put("id", payLogId);
		payLogService.updateSelective(paramMap);
	}

	private void modifyPayReqLog(PayReqLog payReqLog, String params) {
		payReqLog.setNotifyParams(params);
		payReqLog.setNotifyTime(DateUtil.getNow());
		payReqLogService.updateById(payReqLog);
	}
}
