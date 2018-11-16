package com.xindaibao.cashloan.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.cl.model.kenya.KanyaUserAddressBook;
import com.xindaibao.cashloan.cl.model.kenya.KanyaUserCall;
import com.xindaibao.cashloan.cl.model.kenya.KanyaUserSms;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tool.util.StringUtil;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.OperatorTdCallInfo;
import com.xindaibao.cashloan.cl.domain.OperatorVoices;
import com.xindaibao.cashloan.cl.domain.UserContacts;
import com.xindaibao.cashloan.cl.domain.UserMessages;
import com.xindaibao.cashloan.cl.service.OperatorTdBasicService;
import com.xindaibao.cashloan.cl.service.OperatorVoicesService;
import com.xindaibao.cashloan.cl.service.UserContactsService;
import com.xindaibao.cashloan.cl.service.UserMessagesService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.service.CloanUserService;

/**
* 用户信息Controller
* 
* @author
* @version 1.0.0
* @date 2017-02-28 10:24:45


* 

*/
@Scope("prototype")
@Controller
public class ManageMsgController extends ManageBaseController{

	@Resource
	private UserContactsService userContactsService;
	@Resource
	private UserMessagesService userMessagesService;
	@Resource
	private OperatorVoicesService operatorVoicesService;
	@Resource
	private CloanUserService cloanUserService;
	@Resource
	private OperatorTdBasicService operatorTdBasicService;
	/**
	 * 通讯录查询
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/msg/listContacts.htm")
	public void listContacts(
			@RequestParam(value="userId") long userId,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		KanyaUserAddressBook kanyaUserAddressBook = userContactsService.listContacts(userId,current,pageSize);
		JSONArray jsonObject = (JSONArray) JSONArray.parse(kanyaUserAddressBook.getAddressbook());
		Map<String, Object> data = new HashMap<>();
		data.put("list", jsonObject);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);

	}
	
	/**
	 * 短信查询
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/msg/listMessages.htm")
	public void listMessages(
			@RequestParam(value="userId") long userId,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		KanyaUserSms kanyaUserSms = userMessagesService.listMessagesForKenya(userId,current,pageSize);
		JSONArray jsonObject = (JSONArray) JSONArray.parse(kanyaUserSms.getSmsContent());
		Map<String, Object> data = new HashMap<>();
		data.put("list", jsonObject);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 通话记录查询
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/msg/listRecords.htm")
	public void listRecords(@RequestParam(value="userId") long userId,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		KanyaUserCall kanyaUserCall = operatorVoicesService.findShardKenya(userId, current, pageSize);
		JSONArray jsonObject = (JSONArray) JSONArray.parse(kanyaUserCall.getCallRecords());
		Map<String, Object> data = new HashMap<>();
		data.put("list", jsonObject);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	/**
	 * 同盾通话记录查询
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/msg/tdListRecords.htm")
	public void tdListRecords(@RequestParam(value="userId") long userId,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String, Object> data = new HashMap<>();
		Page<OperatorVoices> page = new Page<OperatorVoices>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		OperatorTdCallInfo callInfo = operatorTdBasicService.findOperatorTdCallInfos(params);
		if (StringUtil.isNotBlank(callInfo)) {
			params = new HashMap<String, Object>();
			params.put("reqLogId", callInfo.getReqLogId());
			page = operatorTdBasicService.findPageOperatorTdCallRecord(params,current, pageSize);
			data.put("list", page.getResult());
		}

		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
}
