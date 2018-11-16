package com.xindaibao.cashloan.rule.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.rule.domain.BorrowRuleResult;
import com.xindaibao.cashloan.rule.service.BorrowRuleResultService;

 /**
 * 规则匹配结果Controller
 * 
 * @author
 * @version 1.0.0
 * @date 2016-12-21 15:04:28


 * 

 */
@Controller
@Scope("prototype")
public class BorrowRuleResultController extends BaseController {

	@Resource
	private BorrowRuleResultService borrowRuleResultService;
	
	/**
	 * 获取借款申请规则匹配结果
	 * @param borrowId
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping(value="/modules/manage/borrow/borrowRuleResult.htm")
	public void borrowRuleResult(@RequestParam(value="borrowId",required=false) String borrowId,
			@RequestParam(value = "currentPage") int currentPage,
			@RequestParam(value = "pageSize") int pageSize){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("borrowId", borrowId);
		Page<BorrowRuleResult> page = borrowRuleResultService.borrowRuleResult(params, currentPage, pageSize);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	

}
