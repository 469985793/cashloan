package com.xindaibao.cashloan.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xindaibao.cashloan.cl.model.PayLogModel;
import com.xindaibao.cashloan.cl.service.BorrowRepayService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;

 /**
 * 还款计划Controller
 */
@Scope("prototype")
@Controller
public class BorrowRepayController extends BaseController {

	@Resource
	private BorrowRepayService borrowRepayService;

	/**
	 * 获取平台收款账号信息
	 * @param search
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/act/borrow/repay/collectionInfo.htm", method = RequestMethod.GET)
	public void collectionInfo(
			@RequestParam(value="type") String type) throws Exception {
		Map<String,Object>  data = new HashMap<>();
		data.put("name", Global.getValue("repay_collection_info_name"));
		data.put("bank",  Global.getValue("repay_collection_info_bank"));
		data.put("bankCard", Global.getValue("repay_collection_info_bank_card"));
		data.put("alipayAccount",  Global.getValue("repay_collection_info_alipay_account"));
		data.put("type", type);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 还款方法 ：
	 * 	若参数封装成功，还款状态变更为还款处理中
	 * @param payType
	 * @param borrowId
	 * @param ip
	 */
	@RequestMapping(value = "/api/act/borrow/repay/repayment.htm", method = RequestMethod.POST)
	public void repayment(@RequestParam(value="borrowId") Long borrowId,
			@RequestParam(value="ip") String ip) {
		Long userId = getSessionUserId();
		Map<String, String> sdkParams = borrowRepayService.repayment(PayLogModel.TYPE_ACTIVE_DEBIT_LIANLIAN, borrowId, userId, ip);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, sdkParams);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "参数封装成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	@RequestMapping(value = "/api/act/borrow/repay/repaymentReturn.htm", method = RequestMethod.POST)
	public void repaymentReturn(@RequestParam(value = "payResult") String payResult, @RequestParam(value = "payOrderNo") String payOrderNo) {
		borrowRepayService.repaymentReturn(PayLogModel.TYPE_ACTIVE_DEBIT_LIANLIAN, payResult, payOrderNo);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "处理成功");
		ServletUtils.writeToResponse(response,result);
	}
}
