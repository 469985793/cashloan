package com.xindaibao.cashloan.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xindaibao.cashloan.api.user.service.ContractService;
import com.xindaibao.cashloan.cl.service.ClSmsService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.core.service.CloanUserService;
import com.xindaibao.cashloan.system.domain.SysConfig;
import com.xindaibao.cashloan.system.service.SysConfigService;


/**
 * H5页面Controller
 * @author
 * @version 1.0.0
 * @date 2017年2月24日 下午4:34:51



 */
@Scope("prototype")
@Controller
public class H5Controller extends BaseController{
	public static final Logger logger = LoggerFactory.getLogger(H5Controller.class);
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private ClSmsService clSmsService;
	@Resource
	private CloanUserService cloanUserService;
	@Resource
	private ContractService contractService;
	
	
	/**
	 * 获取H5页面清单
	 * @param search
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/h5/list.htm", method = RequestMethod.GET)
	public void list() throws Exception {
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		List<SysConfig> list = sysConfigService.listByCode("h5_");
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> pro =new HashMap<>();
			pro.put("code",list.get(i).getCode());
			pro.put("value",list.get(i).getValue());
			pro.put("name",list.get(i).getName());
			dataList.add(pro);
		}
		Map<String,Object> data = new HashMap<>();
		data.put("list", dataList);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 生成图片验证码
	 * @param search
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/h5/imgCode/generate.htm", method = RequestMethod.GET)
	public void generate() throws Exception {
		super.generateImgCode();
	}

}
