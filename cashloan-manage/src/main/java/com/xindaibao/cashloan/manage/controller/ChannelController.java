package com.xindaibao.cashloan.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.system.domain.SysUser;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.Channel;
import com.xindaibao.cashloan.cl.model.ChannelCountModel;
import com.xindaibao.cashloan.cl.model.ChannelModel;
import com.xindaibao.cashloan.cl.service.ChannelService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;

 /**
 * 渠道信息Controller
 * 
 * gc
 * @version 1.0.0
 * @date 2017-03-03 10:52:07
 * Copyright zuoli company  arc All Rights Reserved
 *
 * 
 *
 */
@Scope("prototype")
@Controller
public class ChannelController extends ManageBaseController {

	@Resource
	private ChannelService channelService;

	/**
	 * 保存
	 * @param code
	 * @param name
	 * @param linker
	 * @param phone
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/promotion/channel/save.htm", method = RequestMethod.POST)
	public void save(@RequestParam(value="code") String code,
			@RequestParam(value="name") String name,
			@RequestParam(value="linker") String linker,
			@RequestParam(value="phone") String phone) throws Exception {
		Channel channel=new Channel();
		channel.setCode(code);
		channel.setLinker(linker);
		channel.setName(name);
		channel.setPhone(phone);
		boolean flag = channelService.save(channel);

		Map<String, Object> result = new HashMap<String, Object>();
		if (flag) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 查询所有渠道信息
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/promotion/channel/listChannel.htm")
	public void listChannel() throws Exception {
		List<Channel> list = channelService.listChannel();
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, list);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	
	/**
	 * 渠道信息列表页查看
	 * 
	 * @param searchParams
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/promotion/channel/page.htm", method = {RequestMethod.POST,RequestMethod.GET})
	public void page(
			@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String, Object> searchMap = new HashMap<>();
		if (!StringUtils.isEmpty(searchParams)) {
			searchMap = JsonUtil.parse(searchParams, Map.class);
		}

		Page<ChannelModel> page = channelService.page(current, pageSize,searchMap);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 修改
	 * @param id
	 * @param code
	 * @param name
	 * @param linker
	 * @param phone
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/promotion/channel/update.htm", method = RequestMethod.POST)
	public void update(
			@RequestParam(value="id") Long id,
			@RequestParam(value="code") String code,
			@RequestParam(value="name") String name,
			@RequestParam(value="linker") String linker,
			@RequestParam(value="phone") String phone) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("code", code);
		paramMap.put("name", name);
		paramMap.put("linker", linker);
		paramMap.put("phone", phone);
		boolean flag = channelService.update(paramMap);
		Map<String, Object> result = new HashMap<String, Object>();
		if (flag) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, result);
	}

	
	/**
	 * 渠道信息修改状态
	 */
	@RequestMapping(value = "/modules/manage/promotion/channel/updateState.htm", method = RequestMethod.POST)
	public void updateState(@RequestParam(value="id") Long id,
					@RequestParam(value="state") String state) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("state", state);
		boolean flag = channelService.update(paramMap);
		Map<String, Object> result = new HashMap<String, Object>();
		if (flag) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 统计渠道用户信息
	 * 
	 * @param searchParams
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */ 
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/promotion/channel/channelUserList.htm", method = {RequestMethod.POST,RequestMethod.GET})
	public void channelUserList(
			@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String, Object> searchMap = new HashMap<>();
		if (!StringUtils.isEmpty(searchParams)) {
			searchMap = JsonUtil.parse(searchParams, Map.class);
		} 
		Page<ChannelCountModel> page = channelService.channelUserList(current, pageSize,searchMap);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}

	 /**
	  * 统计渠道用户信息GSP
	  *
	  * @param searchParams
	  * @param current
	  * @param pageSize
	  * @throws Exception
	  */
	 @SuppressWarnings("unchecked")
	 @RequestMapping(value = "/modules/manage/promotion/channel/channelGSPUserList.htm", method = {RequestMethod.POST,RequestMethod.GET})
	 public void channelGSPUserList(
			 @RequestParam(value="searchParams",required=false) String searchParams,
			 @RequestParam(value = "current") int current,
			 @RequestParam(value = "pageSize") int pageSize) throws Exception {
		 Map<String, Object> responsemap = new HashMap<String, Object>();
		 SysUser sysUser = this.getLoginUser(request);
		 if (null==sysUser) {
			 response.sendRedirect("/dev/index.html");
			 ServletUtils.writeToResponse(response, responsemap);
			 return;
		 }

		 Map<String, Object> searchMap = new HashMap<>();
		 if (!StringUtils.isEmpty(searchParams)) {
			 searchMap = JsonUtil.parse(searchParams, Map.class);
		 }
		 searchMap.put("linker", sysUser.getUserName());
		 Page<ChannelCountModel> page = channelService.channelUserList(current, pageSize,searchMap);

		 Map<String, Object> result = new HashMap<String, Object>();
		 result.put(Constant.RESPONSE_DATA, page);
		 result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		 result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		 result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		 ServletUtils.writeToResponse(response, result);
	 }

	 /**
	  * 统计渠道用户信息GAP
	  *
	  * @param searchParams
	  * @param current
	  * @param pageSize
	  * @throws Exception
	  */
	 @SuppressWarnings("unchecked")
	 @RequestMapping(value = "/modules/manage/promotion/channel/channelGAPUserList.htm", method = {RequestMethod.POST,RequestMethod.GET})
	 public void channelGAPUserList(
			 @RequestParam(value="searchParams",required=false) String searchParams,
			 @RequestParam(value = "current") int current,
			 @RequestParam(value = "pageSize") int pageSize) throws Exception {
		 Map<String, Object> responsemap = new HashMap<String, Object>();
		 SysUser sysUser = this.getLoginUser(request);
		 if (null==sysUser) {
			 response.sendRedirect("/dev/index.html");
			 ServletUtils.writeToResponse(response, responsemap);
			 return;
		 }

		 Map<String, Object> searchMap = new HashMap<>();
		 if (!StringUtils.isEmpty(searchParams)) {
			 searchMap = JsonUtil.parse(searchParams, Map.class);
		 }
		 searchMap.put("linker", sysUser.getUserName());
		 Page<ChannelCountModel> page = channelService.channelUserList(current, pageSize,searchMap);

		 Map<String, Object> result = new HashMap<String, Object>();
		 result.put(Constant.RESPONSE_DATA, page);
		 result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		 result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		 result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		 ServletUtils.writeToResponse(response, result);
	 }
	
}
