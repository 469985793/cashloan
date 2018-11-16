package com.xindaibao.cashloan.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xindaibao.cashloan.cl.model.ManageBorrowTestModel;
import com.xindaibao.cashloan.cl.service.BorrowRepayLogService;
import com.xindaibao.cashloan.cl.service.ClBorrowService;
import com.xindaibao.cashloan.cl.service.DhbReqLogService;
import com.xindaibao.cashloan.cl.service.OperatorReqLogService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.domain.Borrow;
import com.xindaibao.cashloan.core.service.CloanUserService;
import com.xindaibao.cashloan.rc.domain.TppBusiness;
import com.xindaibao.cashloan.rc.service.TppBusinessService;

@Scope("prototype")
@Controller
public class ManageBorrowTest extends ManageBaseController{

	@Resource
	private ClBorrowService clBorrowService;
	@Resource
	private CloanUserService userService;
	@Resource
	private OperatorReqLogService operatorReqLogService;
	@Resource
	private CloanUserService cloanUserService;
	@Resource
	private BorrowRepayLogService borrowRepayLogService;
	@Resource
	private TppBusinessService tppBusinessService;
	@Resource
	private DhbReqLogService dhbReqLogService;
	
	@RequestMapping(value = "/modules/manage/user/list.htm")
	public void list()throws Exception{
		List<ManageBorrowTestModel> list = clBorrowService.seleteUser();
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("list", list);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	
	@RequestMapping(value = "/modules/manage/borrow/apply.htm")
	public void apply(
			@RequestParam(value="amount") double amount,
			@RequestParam(value="timeLimit") String timeLimit,
			@RequestParam(value="userId") long userId,
			@RequestParam(value="cardId") long cardId
			) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		Borrow borrow = new Borrow(1l, amount, timeLimit, cardId, "", "", "","");
		borrow = clBorrowService.rcBorrowApply(borrow,"","");
		if (borrow != null && borrow.getId() > 0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "申请成功");
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "申请失败");
		}
		
		ServletUtils.writeToResponse(response,result);
	}
	/**
	 * 贷后邦测试
	 * @param borrowId
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/borrow/dhbSauron.htm")
	public void dhbSauron(
			@RequestParam(value="borrowId") long borrowId
			) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		Borrow borrow=new Borrow();
		borrow=clBorrowService.findByPrimary(borrowId);
		TppBusiness business = tppBusinessService.findByNid("DhbSauron",Long.valueOf(2));
		dhbReqLogService.queryDhbSauron(borrow, business);
		
		ServletUtils.writeToResponse(response,result);
	}

	
	
}
