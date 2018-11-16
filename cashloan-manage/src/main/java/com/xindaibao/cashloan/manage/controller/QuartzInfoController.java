package com.xindaibao.cashloan.manage.controller;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.manage.domain.QuartzInfo;
import com.xindaibao.cashloan.manage.model.QuartzInfoModel;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tool.util.DateUtil;
import tool.util.StringUtil;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.manage.model.QuartzManager;
import com.xindaibao.cashloan.manage.service.QuartzInfoService;

/**
 * 定时任务Controller
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-15 13:30:53


 * 

 */
@Scope("prototype")
@Controller
public class QuartzInfoController  extends BaseController {

	private static final Logger logger = Logger.getLogger(QuartzInfoController.class);
	
	@Resource
	private QuartzInfoService quartzInfoService;

	/**
	 * 定时任务列表
	 * @param search
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/quartz/page.htm")
	public void page(
			@RequestParam(value="search") String search,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(search)) {
			searchMap = JsonUtil.parse(search, Map.class);
		}
		
		Page<QuartzInfoModel> page = quartzInfoService.page(searchMap,current, pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 添加定时任务
	 * @param name
	 * @param code
	 * @param cycle
	 * @param className
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/quartz/addition.htm")
	public void addition(@RequestParam(value="name") String name,
			@RequestParam(value="code") String code,
			@RequestParam(value="cycle") String cycle,
			@RequestParam(value="className") String className) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", code);
        QuartzInfo qzInfo = quartzInfoService.findSelective(paramMap);
        
        boolean flag = false;
        Map<String,Object> data = new HashMap<String,Object>();
        if (qzInfo == null) {
        	qzInfo = new QuartzInfo();
        	qzInfo.setName(name);
        	qzInfo.setCode(code);
        	qzInfo.setCycle(cycle);
        	qzInfo.setClassName(className);
        	qzInfo.setSucceed(0);
        	qzInfo.setFail(0);
        	qzInfo.setState(QuartzInfoModel.STATE_DISABLE);
        	qzInfo.setCreateTime(DateUtil.getNow());
        	flag = quartzInfoService.save(qzInfo);
		}else {
			data.put("message", "任务已存在,请勿重复添加");
		}
        
        Map<String,Object> result = new HashMap<String,Object>();
        if (flag) {
        	result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作成功");
		}else {
			result.put(Constant.RESPONSE_DATA, data);
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作失败");
		}
        ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 启动定时任务
	 * @param job_name
	 * @param job_time
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/quartz/execute.htm")
	public void execute(@RequestParam(value = "id") Long id) {
		logger.info("【启动任务】开始...");

		boolean flag = true;
		Object cl = null;
		
		QuartzInfo quartzInfo = quartzInfoService.getById(id);
		if(null == quartzInfo || StringUtil.isBlank(quartzInfo.getClassName())){
			flag = false;
		}
		
		// 任务执行类实例化
		if(flag){
			try {
				cl = Class.forName(quartzInfo.getClassName()).newInstance();
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				logger.info("定时任务启动异常...", e);
				flag = false;
			}
		}
	
		if (flag) {
			// 任务添加
			QuartzManager.addJob(quartzInfo.getCode(), cl.getClass(),quartzInfo.getCycle());

			// 数据库状态更新
			Map<String, Object> data = new HashMap<>();
			data.put("id", quartzInfo.getId());
			data.put("state", QuartzInfoModel.STATE_ENABLE);
			flag = quartzInfoService.update(data);
		}
      
        
        Map<String,Object> result = new HashMap<String,Object>();
        if (flag) {
        	result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作成功");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作失败");
		}
        logger.info("【启动任务】结束...");
        ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 禁用定时任务
	 * @param job_name
	 * @param job_time
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/quartz/delete.htm")
	public void delete(@RequestParam(value="id") Long id) throws Exception {
        logger.info("【删除任务】开始...");
        
        QuartzInfo quartzInfo = quartzInfoService.getById(id);
        if(null != quartzInfo && QuartzInfoModel.STATE_ENABLE.equals(quartzInfo.getState())){
        	QuartzManager.removeJob(quartzInfo.getCode());
        }
        
        Map<String,Object> data = new HashMap<>();
        data.put("id", id);
        data.put("state", QuartzInfoModel.STATE_DISABLE);
        boolean flag = quartzInfoService.update(data);
        
        Map<String,Object> result = new HashMap<String,Object>();
        if (flag) {
        	result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作成功");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作失败");
		}
        logger.info("【删除任务】结束...");
        ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 修改定时任务
	 * @param job_name
	 * @param job_time
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/quartz/update.htm")
	public void update(@RequestParam(value = "id") long id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "cycle") String cycle) throws Exception {
        logger.info("【修改任务】开始...");
       
        QuartzInfo quartzInfo = quartzInfoService.getById(id);
        if(null != quartzInfo && QuartzInfoModel.STATE_ENABLE.equals(quartzInfo.getState())){
        	QuartzManager.modifyJobTime(quartzInfo.getCode(), cycle);
        }
        
        Map<String,Object> data = new HashMap<>();
        data.put("id", id);
        data.put("name", name);
        data.put("cycle", cycle);
        boolean flag = quartzInfoService.update(data);
        Map<String,Object> result = new HashMap<String,Object>();
        if (flag) {
        	result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
        logger.info("【修改任务】结束...");
        ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 关闭所有定时任务 
	 * @param job_name
	 * @param job_time
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/quartz/shutdownJobs.htm")
	public void shutdownJobs() throws Exception {
        logger.info("【关闭所有任务】开始...");
        QuartzManager.shutdownJobs();
        logger.info("【关闭所有任务】结束...");
	}

	/**
	 * 立即执行一个任务
	 * 
	 * @param job_name
	 * @param job_time
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/quartz/runJobNow.htm")
	public void runJobNow(@RequestParam(value="id") Long id) throws Exception {
		logger.info("【执行】开始...");
		QuartzInfo quartzInfo = quartzInfoService.getById(id);
		
		if( null == quartzInfo || QuartzInfoModel.STATE_DISABLE.equals(quartzInfo.getState())){
			Map<String, Object> result = new HashMap<String, Object>();
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "任务不存在或已停止");
			ServletUtils.writeToResponse(response, result);
			return ;
		}
		
		QuartzManager.startJobNow(quartzInfo.getCode());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "操作成功");
		logger.info("【执行任务】结束...");
		ServletUtils.writeToResponse(response, result);
	}

}
