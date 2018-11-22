package com.xindaibao.cashloan.manage.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.cl.model.kenya.LoanProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tool.util.DateUtil;
import tool.util.StringUtil;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.BorrowProgress;
import com.xindaibao.cashloan.cl.domain.PayLog;
import com.xindaibao.cashloan.cl.model.ManagePayLogModel;
import com.xindaibao.cashloan.cl.model.PayLogModel;
import com.xindaibao.cashloan.cl.service.BorrowProgressService;
import com.xindaibao.cashloan.cl.service.BorrowRepayService;
import com.xindaibao.cashloan.cl.service.ClBorrowService;
import com.xindaibao.cashloan.cl.service.PayLogService;
import com.xindaibao.cashloan.cl.service.ProfitAmountService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.model.BorrowModel;

 /**
 * 支付记录Controller
 */
@Scope("prototype")
@Controller
public class ManagePayLogController extends ManageBaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ManagePayLogController.class);
	
	@Resource
	private PayLogService payLogService;
	@Resource
	private ClBorrowService clBorrowService;
	@Resource
	private BorrowProgressService borrowProgressService;
	@Resource
	private BorrowRepayService borrowRepayService;
	@Resource
	private ProfitAmountService profitAmountService;


	/**
	 * 支付记录列表页查看
	 * 
	 * @param searchParams
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "/modules/manage/pay/log/page.htm")
//	public void page(
//			@RequestParam(value = "search", required = false) String search,
//			@RequestParam(value = "current") int current,
//			@RequestParam(value = "pageSize") int pageSize) throws Exception {
//
//		Map<String, Object> searchMap = new HashMap<String, Object>();
//		if (!StringUtils.isEmpty(search)) {
//			searchMap = JsonUtil.parse(search, Map.class);
//		}
//
//		String type  = StringUtil.isNull(searchMap.get("type"));
//		String[] typeArray = type.split(",");
//
//		List<String> typeList =  new ArrayList<String>();
//		for (String typeStr : typeArray) {
//			if (StringUtil.isNotBlank(typeStr)) {
//				typeList.add(typeStr);
//			}
//		}
//		searchMap.put("type", typeList);
//
//		Page<ManagePayLogModel> page = payLogService.page(current, pageSize,searchMap);
//
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put(Constant.RESPONSE_DATA, page);
//		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
//		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
//		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
//		ServletUtils.writeToResponse(response, result);
//	}
	 /**
	 * 放款记录列表页查看
	 *
	 * @param searchParams
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/pay/log/page.htm")
	public void page(
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String, Object> params = JsonUtil.parse(search, Map.class);
		params.put("state",BorrowModel.STATE_PASS);
		Page<LoanProduct> page = clBorrowService.listBorrowModel(params,current,pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 支付记录详情查看
	 * 
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/pay/log/findDetail.htm")
	public void findDetail(@RequestParam(value = "id") Long id)
			throws Exception {

		ManagePayLogModel model = payLogService.findDetail(id);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, model);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}

//	/**
//	 * 审核
//	 *
//	 * @param id
//	 */
//	@RequestMapping(value = "/modules/manage/pay/log/auditPay.htm", method = RequestMethod.POST)
//	public void audit(@RequestParam(value = "id") Long id,@RequestParam(value = "state") String state) {
//
//		boolean flag = true;
//		String msg = Constant.OPERATION_FAIL;
//
//		Map<String, Object> checkMap = payLogService.checkPayLogState(id,state);
//
//		if (checkMap.isEmpty()) {
//			flag = payLogService.auditPay(id,state);
//		}
//
//		Map<String, Object> result = new HashMap<String, Object>();
//		if (flag) {
//			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
//			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
//		} else {
//			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
//			result.put(Constant.RESPONSE_CODE_MSG, msg);
//		}
//		ServletUtils.writeToResponse(response, result);
//
//	}
	/**
	 * 审核
	 *
	 * @param id
	 */
	@RequestMapping(value = "/modules/manage/pay/log/auditPay.htm", method = RequestMethod.POST)
	public void audit(@RequestParam(value = "id") Long id,
					  @RequestParam(value = "lineType") String lineType,
					  @RequestParam(value = "state") String state,
					  @RequestParam(value = "remark") String remark) throws Exception {

		Map<String,Object> result = new HashMap<String,Object>();
		try{
			int msg =clBorrowService.manualVerifyBorrow(id, state, remark,lineType);
			if(msg==1){
				result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "放款成功");
			}else{
				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "放款失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
		}
		ServletUtils.writeToResponse(response, result);

	}

	
	/**
	 * 模拟付款成功 - 异步回调
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/pay/testPayNotify.htm", method = RequestMethod.POST)
	public void testPaymentNotify(@RequestParam(value = "orderNo") String orderNo) throws Exception {

		PayLog payLog = payLogService.findByOrderNo(orderNo);
		String codeMsg = null;
		if(null  == payLog ){
			logger.warn("未查询到对应的支付订单");
			codeMsg ="未查询到对应的支付订单";
			return ;
		}
		if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState()) || 
				PayLogModel.STATE_AUDIT_PASSED.equals(payLog.getState()) || 
				PayLogModel.STATE_PAYMENT_FAILED.equals(payLog.getState())) {
				if(PayLogModel.SCENES_LOANS.equals(payLog.getScenes())){
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
	
					Borrow borrow = clBorrowService.getById(payLog.getBorrowId());
					
					// 生成还款计划并授权
					borrowRepayService.genRepayPlan(borrow, new Date());
					// 更新订单状态
					Map<String,Object> paramMap = new HashMap<String, Object>();
					paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
					paramMap.put("updateTime",DateUtil.getNow());
					paramMap.put("id",payLog.getId());
					payLogService.updateSelective(paramMap);
	
				}else if(PayLogModel.SCENES_PROFIT.equals(payLog.getScenes())){
					// 更新取现金额， 添加取现记录
					profitAmountService.cash(payLog.getUserId(), payLog.getAmount());

					// 更新订单状态
					Map<String,Object> paramMap = new HashMap<String, Object>();
					paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
					paramMap.put("updateTime",DateUtil.getNow());
					paramMap.put("id",payLog.getId());
					payLogService.updateSelective(paramMap);
				} 
				
		}else{
			codeMsg = "订单状态错误";
		}

		Map<String, Object> result = new HashMap<String, Object>();
		if (StringUtil.isBlank(codeMsg)) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, codeMsg);
		}
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 再次放款
	 * 
	 * @param borrowId
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/pay/borrowLoan.htm", method = RequestMethod.POST)
	public void borrowLoan(@RequestParam(value = "id") Long id)throws Exception {
		PayLog payLog  = payLogService.getById(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		// 放款失败的记录 允许再次放款
		if (null != payLog
				&& PayLogModel.STATE_PAYMENT_FAILED.equals(payLog.getState())
				&& PayLogModel.SCENES_LOANS.equals(payLog.getScenes())) {

			Borrow borrow = clBorrowService.findByPrimary(payLog.getBorrowId());
			clBorrowService.borrowLoan(borrow, DateUtil.getNow());
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		}else{
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "当前状态不允许放款");
		}
		ServletUtils.writeToResponse(response, result);
	}
}
