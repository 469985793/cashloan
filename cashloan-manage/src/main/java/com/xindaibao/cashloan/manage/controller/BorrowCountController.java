package com.xindaibao.cashloan.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.rc.service.BorrowCountService;

 /**
 * 借款统计Controller
 */
@Scope("prototype")
@Controller
public class BorrowCountController extends ManageBaseController {

	@Resource
	private BorrowCountService borrowCountService;

	@RequestMapping(value = "/modules/manage/borrow/save.htm")
	public void save() throws Exception{
		int msg  =  borrowCountService.save();
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, msg);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
}
