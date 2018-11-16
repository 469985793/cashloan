package com.xindaibao.cashloan.manage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.core.model.KanyaUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.BankCard;
import com.xindaibao.cashloan.cl.domain.BorrowRepay;
import com.xindaibao.cashloan.cl.domain.BorrowRepayLog;
import com.xindaibao.cashloan.cl.domain.PayLog;
import com.xindaibao.cashloan.cl.model.ManageBRepayLogModel;
import com.xindaibao.cashloan.cl.model.PayLogModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.PaymentModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.QueryRepaymentModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.RepaymentModel;
import com.xindaibao.cashloan.cl.model.pay.lianlian.constant.LianLianConstant;
import com.xindaibao.cashloan.cl.model.pay.lianlian.util.LianLianHelper;
import com.xindaibao.cashloan.cl.service.BankCardService;
import com.xindaibao.cashloan.cl.service.BorrowRepayLogService;
import com.xindaibao.cashloan.cl.service.BorrowRepayService;
import com.xindaibao.cashloan.cl.service.ClBorrowService;
import com.xindaibao.cashloan.cl.service.PayLogService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.service.CloanUserService;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import com.xindaibao.cashloan.system.permission.annotation.RequiresPermission;

/**
 * 还款记录后台管理Controller
 * 
 * @author
 * @version 1.0.0
 * @date 2017年3月30日 上午10:16:17


 * 

 */

@Controller
@Scope("prototype")
public class ManageBorrowRepayLogController extends ManageBaseController{

	private static final Logger logger  = LoggerFactory.getLogger(ManageBorrowRepayLogController.class);
	
	@Resource
	private CloanUserService cloanUserService;
	@Resource
	private UserBaseInfoService userBaseInfoService;
	@Resource
	private BankCardService bankCardService;
	@Resource
	private ClBorrowService clBorrowService;
	@Resource
	private BorrowRepayService borrowRepayService;
	@Resource
	private BorrowRepayLogService borrowRepayLogService;
	@Resource
	private PayLogService payLogService;
	

	/**
	 * 还款记录列表
	 * 
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/borrow/repay/log/list.htm")
	@RequiresPermission(code = "modules:manage:borrow:repay:log:list", name = "还款记录列表")
	public void page(
			@RequestParam(value = "searchParams", required = false) String searchParams,
			@RequestParam(value = "current") int currentPage,
			@RequestParam(value = "pageSize") int pageSize) {
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		Page<ManageBRepayLogModel> page = borrowRepayLogService.listModel(params, currentPage, pageSize);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 退还 还款金额
	 * @param id
	 * @param amount
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/borrow/repayLog/refund.htm", method = { RequestMethod.POST })
	public void refund(@RequestParam(value = "id") Long id,
			@RequestParam(value = "amount") String amount) throws Exception {

		BorrowRepayLog borrowRepayLog = borrowRepayLogService.getById(id);
		BankCard bankCard = bankCardService.getBankCardByUserId(borrowRepayLog.getUserId());
		UserBaseInfo baseInfo = userBaseInfoService.findByUserId(borrowRepayLog.getUserId());
		Borrow borrow = clBorrowService.getById(borrowRepayLog.getBorrowId());

		Date date = DateUtil.getNow();
		
		Map<String, Object> paymentMap = new HashMap<>();
		paymentMap.put("payTime", date);
		paymentMap.put("amount", amount);
		paymentMap.put("cardNo", bankCard.getCardNo());
		paymentMap.put("realName", baseInfo.getRealName());
		paymentMap.put("orderMemoInfo", "repayPlan:" + borrowRepayLog.getRepayId() + "退还");
		paymentMap.put("notifyUrl", Global.getValue("server_host") + "/pay/lianlian/refundNotify.htm");

		PaymentModel payment = (PaymentModel) LianLianHelper.payment(paymentMap);

		PayLog payLog = new PayLog();
		payLog.setOrderNo(payment.getNo_order());
		payLog.setUserId(borrow.getUserId());
		payLog.setBorrowId(borrow.getId());
		payLog.setAmount(payment.getAmount());
		payLog.setCardNo(bankCard.getCardNo());
		payLog.setBank(bankCard.getBank());
		payLog.setSource(PayLogModel.SOURCE_FUNDS_OWN);
		payLog.setType(PayLogModel.TYPE_PAYMENT);
		payLog.setScenes(PayLogModel.SCENES_REFUND);
		
		if (payment.checkReturn()) { // 已生成连连支付单，付款处理中（交易成功，不是指付款成功，是指流程正常）
			payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
		} else if ("4002".equals(payment.getRet_code())
				|| "4003".equals(payment.getRet_code())
				|| "4004".equals(payment.getRet_code())) { // 疑似重复订单，待人工审核
			payLog.setConfirmCode(payment.getConfirm_code());
			payLog.setState(PayLogModel.STATE_PENDING_REVIEW);
			payLog.setUpdateTime(DateUtil.getNow());
		} else if ("4006".equals(payment.getRet_code())		//敏感信息加密异常
				|| "4007".equals(payment.getRet_code())     //敏感信息解密异常
				|| "4009".equals(payment.getRet_code())) {  //验证码异常
			payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
		} else {
			payLog.setState(PayLogModel.STATE_PAYMENT_FAILED);
			payLog.setUpdateTime(DateUtil.getNow());
		}
		payLog.setRemark(payment.getRet_msg());
		payLog.setPayReqTime(date);
		payLog.setCreateTime(DateUtil.getNow());
		payLogService.save(payLog);
		
		Map<String,Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, result);
	}
	
	
	/**
	 * 补扣-还款金额
	 * 
	 * @param userId
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/borrow/repayLog/deduction.htm", method = { RequestMethod.POST })
	public void deduction(@RequestParam(value = "id") Long id,
			@RequestParam(value = "amount") String amount) throws Exception {

		BorrowRepayLog borrowRepayLog = borrowRepayLogService.getById(id);
		BorrowRepay borrowRepay = borrowRepayService.getById(borrowRepayLog.getRepayId());
		KanyaUser user = cloanUserService.getById(borrowRepayLog.getUserId());
		UserBaseInfo baseInfo = userBaseInfoService.findByUserId(borrowRepayLog.getUserId());
		Borrow borrow = clBorrowService.getById(borrowRepayLog.getBorrowId());
		BankCard bankCard = bankCardService.getBankCardByUserId(borrowRepayLog
				.getUserId());

		// 扣款失败无异步通知 故先查询订单是否已经在支付处理中
		Map<String, Object> payLogMap = new HashMap<String, Object>();
		payLogMap.put("userId", borrowRepayLog.getUserId());
		payLogMap.put("borrowId", borrowRepayLog.getBorrowId());
		payLogMap.put("type", PayLogModel.TYPE_COLLECT);
		payLogMap.put("scenes",PayLogModel.SCENES_DEDUCTION);
		PayLog deductionLog = payLogService.findLatestOne(payLogMap);

		// 订单存在并不是支付失败记录
		if (null != deductionLog
				&& !PayLogModel.STATE_PAYMENT_FAILED.equals(deductionLog.getState())) {

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("queryOrderNo", deductionLog.getOrderNo());
			paramMap.put("queryOrderTime", deductionLog.getPayReqTime());
			QueryRepaymentModel queryRepayment = (QueryRepaymentModel) LianLianHelper.queryRepayment(paramMap);

			if (queryRepayment.checkReturn()
					&& LianLianConstant.RESULT_SUCCESS.equals(queryRepayment.getResult_pay())) {
				// 更新订单状态
				deductionLog.setState(PayLogModel.STATE_PAYMENT_SUCCESS);
				deductionLog.setUpdateTime(DateUtil.getNow());
				payLogService.updateById(deductionLog);
			}
		}

		Date payReqTime = DateUtil.getNow();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("user", user);
		paramMap.put("userBaseInfo", baseInfo);
		paramMap.put("agreeNo", bankCard.getAgreeNo());
		paramMap.put("payTime", payReqTime);
		paramMap.put("amount", amount);
		paramMap.put("repayTime", borrowRepay.getRepayTime());
		paramMap.put("borrowOrderNo", borrow.getOrderNo());
		paramMap.put("orderMemoInfo","repayPlan:" + borrowRepayLog.getRepayId() + "补扣");
		paramMap.put("notifyUrl", Global.getValue("server_host") + "/pay/lianlian/deductionNotify.htm");
		RepaymentModel repayment = (RepaymentModel) LianLianHelper.repayment(paramMap);

		PayLog payLog = new PayLog();
		payLog.setOrderNo(repayment.getOrderNo());
		payLog.setUserId(borrowRepay.getUserId());
		payLog.setBorrowId(borrowRepay.getBorrowId());
		payLog.setAmount(repayment.getAmount());
		payLog.setCardNo(bankCard.getCardNo());
		payLog.setBank(bankCard.getBank());
		payLog.setSource(PayLogModel.SOURCE_FUNDS_OWN);
		payLog.setType(PayLogModel.TYPE_COLLECT);
		payLog.setScenes(PayLogModel.SCENES_DEDUCTION);
		payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
		payLog.setRemark(repayment.getRet_msg());
		payLog.setPayReqTime(payReqTime);
		payLog.setCreateTime(DateUtil.getNow());
		payLogService.save(payLog);
		
		
		
		Map<String,Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, result);
	}
}
