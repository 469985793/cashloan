package com.xindaibao.cashloan.api.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xindaibao.cashloan.cl.domain.Adver;
import com.xindaibao.cashloan.cl.service.AdverService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;

@Scope("prototype")
@Controller
public class AdverController extends BaseController{
	
	public static final Logger logger = LoggerFactory.getLogger(AdverController.class);
	
	@Resource
	private AdverService adverService;
	
	/**
	 * @description 广告展示
	 * @param request
	 * @throws Exception
	 * @author
	 * @return void
	 * @since  1.0.0
	 */
	@RequestMapping(value="/api/adver/banner.htm")
	public void banner(HttpServletRequest request) {
		List<Adver> banners = adverService.getBanner();
		String serverHost = Global.getValue("server_host");
			
		Map<String, Object> temp = new HashMap<String, Object>();
		for (Adver adver : banners) {
			adver.setPath(serverHost + "/readFile.htm?path=" + adver.getPath());
		}
		temp.put("list", banners);
		//返回结果
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, temp);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		
		ServletUtils.writeToResponse(response, result);
	}
	


}
