package com.xindaibao.creditrank.cr.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.creditrank.cr.service.CrResultService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.creditrank.cr.domain.CrResult;

/**
 * 评分结果Controller
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-05 16:22:54


 * 

 */
@Scope("prototype")
@Controller
public class CrResultController extends BaseController {

	@Resource
	private CrResultService crResultService;

	
	@RequestMapping(value="/modules/manage/cr/result/findUserResult.htm",method=RequestMethod.POST)
	public void findUserResult(@RequestParam(value = "userId")Long userId){
		crResultService.findUserResult(userId);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	@RequestMapping(value="/modules/manage/cr/result/findAllBorrowTypeResult.htm",method=RequestMethod.POST)
	public void findAllBorrowTypeResult(@RequestParam(value = "userId")Long userId){
		List<CrResult> resultList = crResultService.findAllBorrowTypeResult(userId);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, resultList);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	@RequestMapping(value="/modules/manage/cr/result/findBorrowTypeResult.htm",method=RequestMethod.POST)
	public void findBorrowTypeResult(@RequestParam(value = "userId")Long userId,
			@RequestParam(value = "borrowTypeId")Long borrowTypeId){
		List<CrResult> resultList = crResultService.findAllBorrowTypeResult(userId);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, resultList);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
}
