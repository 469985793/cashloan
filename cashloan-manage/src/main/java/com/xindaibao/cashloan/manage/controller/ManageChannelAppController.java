package com.xindaibao.cashloan.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xindaibao.cashloan.cl.domain.Channel;
import com.xindaibao.cashloan.cl.domain.ChannelApp;
import com.xindaibao.cashloan.cl.model.ChannelAppModel;
import com.xindaibao.cashloan.cl.service.ChannelAppService;
import com.xindaibao.cashloan.cl.service.ChannelService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;



 /**
 * app渠道版本表Controller
 * 
 * @author
 * @version 1.0.0
 * @date 2017-05-10 10:29:55




 */
@Scope("prototype")
@Controller
public class ManageChannelAppController extends BaseController {
	
	@Resource
	private ChannelAppService channelAppService;

	@Resource
	private ChannelService channelService;
	
	/**
	 * 查询
	 */
	@RequestMapping(value = "/modules/manage/channelApp/list.htm", method = RequestMethod.POST)
	public void listchannelApp() {
		List<ChannelAppModel> list = channelAppService.listChannelAppModel();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_DATA, list);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(value = "/modules/manage/channelApp/save.htm", method = RequestMethod.POST)
	public void save(@RequestParam(value="channelId") long channelId, 
			@RequestParam(value="appType") String appType,
			@RequestParam(value="latestVersion",required=false) String latestVersion,
			@RequestParam(value="minVersion",required=false) String minVersion,
			@RequestParam(value="downloadUrl",required=false) String downloadUrl) {
		ChannelApp channelApp = new ChannelApp();
		channelApp.setChannelId(channelId);
		channelApp.setAppType(appType);
		channelApp.setlatestVersion(latestVersion);
		channelApp.setMinVersion(minVersion);
		channelApp.setDownloadUrl(downloadUrl);
		channelApp.setState("10");//默认启用
		int msg = channelAppService.save(channelApp);
		Map<String, Object> result = new HashMap<String, Object>();
		if (msg>0) {
        	result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "添加成功");
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "添加失败");
		}
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 更新
	 */
	@RequestMapping(value = "/modules/manage/channelApp/update.htm", method = RequestMethod.POST)
	public void update(@RequestParam(value="id") long id, 
			@RequestParam(value="appType",required=false) String appType,
			@RequestParam(value="latestVersion",required=false) String latestVersion,
			@RequestParam(value="minVersion",required=false) String minVersion,
			@RequestParam(value="downloadUrl",required=false) String downloadUrl) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("appType", appType);
		paramMap.put("latestVersion", latestVersion);
		paramMap.put("minVersion", minVersion);
		paramMap.put("downloadUrl", downloadUrl);
		int msg = channelAppService.updateSelective(paramMap);
		Map<String, Object> result = new HashMap<String, Object>();
		if (msg>0) {
        	result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "更新成功");
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "更新失败");
		}
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 启用
	 * @param id
	 */
	@RequestMapping(value="/modules/manage/channelApp/enable.htm",method={RequestMethod.POST})
	public void enable(@RequestParam(value="id") long id){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("state", "10");//10 启用
		int msg = channelAppService.updateSelective(paramMap);
        if (msg > 0) {
        	result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "启用成功");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "启用失败");
		}
        ServletUtils.writeToResponse(response,result);
	}
	
	
	/**
	 * 禁用
	 * @param id
	 */
	@RequestMapping(value="/modules/manage/channelApp/disable.htm",method={RequestMethod.POST})
	public void disable(@RequestParam(value="id") long id){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("state", "20");//20 禁用
		int msg = channelAppService.updateSelective(paramMap);
        if (msg > 0) {
        	result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "禁用成功");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "禁用失败");
		}
        ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 渠道名列表
	 */
	@RequestMapping(value = "/modules/manage/channelApp/channelNamelist.htm", method = RequestMethod.POST)
	public void listChannelName() {
		List<Channel> list = channelService.listChannelWithoutApp();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_DATA, list);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}
}
