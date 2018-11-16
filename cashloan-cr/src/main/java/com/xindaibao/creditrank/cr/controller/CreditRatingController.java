package com.xindaibao.creditrank.cr.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.creditrank.cr.service.CreditRatingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;

/**
 * 评分操作
 * @author
 * @version 1.0.0
 * @date 2017-01-05 16:22:54


 * 

 */
@Controller
public class CreditRatingController extends BaseController {

	@Resource
	private CreditRatingService creditRatingService;
	
	@RequestMapping(value="/modules/manage/cr/result/testCreditRating.htm",method=RequestMethod.GET)
	public void testCreditRating(@RequestParam(value = "userId") String userId) throws Exception {
		creditRatingService.saveCreditRating(userId,1l);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}

}
