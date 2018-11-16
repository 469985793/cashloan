package com.xindaibao.cashloan.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xindaibao.cashloan.cl.model.BorrowProgressModel;
import com.xindaibao.cashloan.cl.service.BorrowProgressService;
import com.xindaibao.cashloan.cl.service.ClBorrowService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.core.domain.Borrow;

 /**
 * 借款进度表Controller
 */
@Scope("prototype")
@Controller
public class BorrowProgressController extends BaseController {

	@Resource
	private BorrowProgressService borrowProgressService;
	@Resource
	private ClBorrowService clBorrowService;

	/**
	 * 借款进度查询
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/act/mine/borrow/findProgress.htm", method = RequestMethod.POST)
	public void findProgress(
			@RequestParam(value="borrowId") long borrowId) throws Exception {
		
		Map<String,Object> borrowMap = new HashMap<>();
		borrowMap.put("id", borrowId);
		Borrow borrow = clBorrowService.getById(borrowId);
		Map<String,Object> data = borrowProgressService.result(borrow);
		List<BorrowProgressModel> list = clBorrowService.borrowProgress(borrowId);
		data.put("list", list);
		if(list != null && !list.isEmpty()){
			data.put("isBorrow", true);
		}
		Map<String,Object> result = new HashMap<>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
}
