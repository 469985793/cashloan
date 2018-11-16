package com.xindaibao.cashloan.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.system.domain.SysConfig;
import com.xindaibao.cashloan.system.service.SysConfigService;


/**
* 备注Controller
* 
* @author
* @version 1.0.0
* @date 2017-02-22 13:57:14




*/
@Scope("prototype")
@Controller
public class RemarkController  extends BaseController{
	
	@Resource
	private SysConfigService sysConfigService;
	
	
	/**
	 * 获取备注清单
	 * @param search
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/remark/list.htm", method = RequestMethod.GET)
	public void list() throws Exception { 
		Map<String,Object>  data = new HashMap<>();
		
		List<Map<String,Object>> dataList= new ArrayList<Map<String,Object>>();
		List<SysConfig> list= sysConfigService.listByCode("remark_");
		
		for(int i=0;i<list.size();i++){
			Map<String,Object> pro =new HashMap<>();
			pro.put("code",list.get(i).getCode());
			pro.put("value",list.get(i).getValue());
			pro.put("name",list.get(i).getName());
			dataList.add(pro);
		}
		data.put("list", dataList);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}

}
