package com.xindaibao.cashloan.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.rc.service.ContactCountService;

 /**
 * 通讯录统计Controller
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-10 15:04:13


 * 

 */
@Controller
@Scope("prototype")
public class ContactCountController extends ManageBaseController {

	@Resource
	private ContactCountService contactCountService;

	@RequestMapping(value = "/modules/manage/Contact/save.htm")
	public void save() throws Exception{
		int msg  =  contactCountService.save();
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, msg);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
}
