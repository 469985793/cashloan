package com.xindaibao.cashloan.rc.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.rc.domain.StatisticsBusiness;
import com.xindaibao.cashloan.rc.service.StatisticsBusinessService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;

/**
 * 风控数据统计接口
 *
 * @version 1.0
 * @date 2017年4月14日上午9:16:51
 */
@Controller
@Scope("prototype")
public class StatisticsBusinessController extends BaseController {

	@Resource
	private StatisticsBusinessService statisticsBusinessService;
	
	/**
	 * 风控数据统计接口分页查询
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping("/modules/manage/rcdata/databusiness/page.htm")
	public void page(
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "current") int currentPage,
			@RequestParam(value = "pageSize") int pageSize) {
		Map<String, Object> params = JSONObject.parseObject(search);
		Page<StatisticsBusiness> page = statisticsBusinessService.page(params, currentPage,pageSize);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 据dataId查询风控数据统计接口
	 * @param statisticsId
	 */
	@RequestMapping("/modules/manage/rcdata/databusiness/listByDataId.htm")
	public void listByDataId(@RequestParam(value = "statisticsId") Long statisticsId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("statisticsId", statisticsId);
		List<StatisticsBusiness> list = statisticsBusinessService.listSelective(paramMap);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, list);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 添加风控数据统计接口
	 * @param statisticsBusiness
	 */
	@RequestMapping("/modules/manage/rcdata/databusiness/save.htm")
	public void save(StatisticsBusiness statisticsBusiness) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		//校验名称是否重复
		boolean flag = false;//tppBusinessService.tppBusinessExist(tppBusiness);
		int i= 0 ;
		if(flag){
			flag = false;
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "接口简称重复");
			ServletUtils.writeToResponse(response, result);
			return;
		}else{
			flag = true;
			statisticsBusiness.setState("10");
			statisticsBusiness.setAddTime(new Date());
			i = statisticsBusinessService.insert(statisticsBusiness);
		}
		
		if (i>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 修改风控数据统计接口
	 * 
	 * @param statisticsBusiness
	 */
	@RequestMapping("/modules/manage/rcdata/databusiness/update.htm")
	public void update(StatisticsBusiness statisticsBusiness) {
		int i = statisticsBusinessService.updateById(statisticsBusiness);

		Map<String, Object> result = new HashMap<String, Object>();
		if (i>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 启用风控数据统计接口
	 * @param id
	 */
	@RequestMapping("/modules/manage/rcdata/databusiness/enable.htm")
	public void enable(@RequestParam(value = "id") Long id) {
		StatisticsBusiness statisticsBusiness = statisticsBusinessService.getById(id);
		statisticsBusiness.setState("10");
		int i = statisticsBusinessService.updateById(statisticsBusiness);
		
		Map<String, Object> result = new HashMap<String, Object>();
		if (i>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 禁用风控数据统计接口
	 * 
	 * @param id
	 */
	@RequestMapping("/modules/manage/rcdata/databusiness/disable.htm")
	public void disable(@RequestParam(value = "id") Long id) {
		StatisticsBusiness statisticsBusiness = statisticsBusinessService.getById(id);
		statisticsBusiness.setState("20");
		int i = statisticsBusinessService.updateById(statisticsBusiness);
		
		Map<String, Object> result = new HashMap<String, Object>();
		if (i>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, result);
	}

}
