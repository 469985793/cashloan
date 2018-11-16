package com.xindaibao.cashloan.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xindaibao.cashloan.cl.service.SystemCountService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.ServletUtils;

/**
 * 后台登陆，首页统计数据
 */
@Scope("prototype")
@Controller
public class SystemCountController extends ManageBaseController {
	
	@Resource
	private SystemCountService systemCountService;

	/**
	 * 统计首页信息
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/count/homeInfo.htm")
	public void homeInfo(HttpServletResponse response) throws Exception {
		Map<String,Object> data = systemCountService.systemCount();
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
}
