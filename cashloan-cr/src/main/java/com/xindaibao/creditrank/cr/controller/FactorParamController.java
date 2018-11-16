package com.xindaibao.creditrank.cr.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.creditrank.cr.service.FactorService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.creditrank.cr.domain.FactorParam;
import com.xindaibao.creditrank.cr.service.FactorParamService;

/**
 * 评分因子参数Controller
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-05 11:13:30


 * 

 */
@Scope("prototype")
@Controller
public class FactorParamController extends BaseController {

	@Resource
	private FactorParamService factorParamService;

	@Resource
	private FactorService factorService;
	
	/**
	 * 查询评分因子参数列表
	 * @param secreditrankh
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/cr/factorParam/page.htm", method=RequestMethod.POST)
	public void page(
			@RequestParam(value="search",required=false) String secreditrankh,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> secreditrankhMap = JsonUtil.parse(secreditrankh, Map.class);
		Page<FactorParam> page = factorParamService.page(secreditrankhMap,current, pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	
	/**
	 * 删除评分因子参数
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/cr/factorParam/delete.htm", method=RequestMethod.POST)
	public void delete(
			@RequestParam(value = "id") long id) throws Exception {
		Map<String,Object> result = factorParamService.deleteSelective(id);
		ServletUtils.writeToResponse(response,result);
	}
	
}
