package com.xindaibao.creditrank.cr.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.creditrank.cr.model.CreditLogModel;
import com.xindaibao.creditrank.cr.service.CreditLogService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.system.permission.annotation.RequiresPermission;
import com.xindaibao.cashloan.system.service.SysDictDetailService;

/**
 * 授信额度记录Controller
 * 
 * @author
 * @version 1.0.0
 * @date 2016-12-16 10:31:23


 * 

 */
@Scope("prototype")
@Controller
public class CreditLogController extends BaseController {

	@Resource
	private CreditLogService creditLogService;
	@Resource
	private SysDictDetailService sysDictDetailService;

	/**
	 * 查询用户额度变动记录列表
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/user/creditLog/page.htm")
	@RequiresPermission(code = "modules:manage:user:creditLog:page",name = "查询用户额度变动记录列表")
	public void page(
			@RequestParam(value="search",required=false) String search,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> searchMap = JsonUtil.parse(search, Map.class);
		Page<CreditLogModel> page = creditLogService.page(searchMap,current, pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
}
