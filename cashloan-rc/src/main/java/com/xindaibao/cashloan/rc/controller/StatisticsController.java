package com.xindaibao.cashloan.rc.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.xindaibao.cashloan.rc.domain.Statistics;
import com.xindaibao.cashloan.rc.service.StatisticsService;

/**
 * 数据统计分类
 * @author
 * @version 1.0
 * @date 2017年4月13日下午6:02:30
 */
@Controller
@Scope("prototype")
public class StatisticsController extends BaseController {

	@Resource
	private StatisticsService statisticsService;

	/**
	 * 数据统计分类分页查询
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping("/modules/manage/rcdata/statistics/page.htm")
	public void page(@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "current") int currentPage,
			@RequestParam(value = "pageSize") int pageSize) {
		Map<String, Object> params = (Map<String, Object>) JSONObject.parseObject(search);
		Page<Statistics> page = statisticsService.page(params, currentPage,pageSize);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 查询所有有效分类
	 */
	@RequestMapping("/modules/manage/rcdata/statistics/listAll.htm")
	public void listAll() {
		List<Statistics> list = statisticsService.listAll();

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, list);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 添加数据统计分类
	 * @param statistics
	 */
	@RequestMapping("/modules/manage/rcdata/statistics/save.htm")
	public void save(Statistics statistics) {
		statistics.setAddTime(new Date());
		statistics.setState("10");
		int flag = statisticsService.insert(statistics);
		
		Map<String, Object> result = new HashMap<String, Object>();
		if (flag>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 修改数据统计分类
	 * @param statistics
	 */
	@RequestMapping("/modules/manage/rcdata/statistics/update.htm")
	public void update(Statistics statistics) {
		
		int flag = statisticsService.updateById(statistics);

		Map<String, Object> result = new HashMap<String, Object>();
		if (flag>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 启用数据统计分类
	 * @param id
	 */
	@RequestMapping("/modules/manage/rcdata/statistics/enalbe.htm")
	public void enalbe(@RequestParam(value = "id") Long id) {
		Statistics statistics = statisticsService.getById(id);
		statistics.setState("10");
		int flag = statisticsService.updateById(statistics);

		Map<String, Object> result = new HashMap<String, Object>();
		if (flag>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 禁用数据统计分类
	 * @param id
	 */
	@RequestMapping("/modules/manage/rcdata/statistics/disable.htm")
	public void disable(@RequestParam(value = "id") Long id) {
		Statistics statistics = statisticsService.getById(id);
		statistics.setState("20");
		int flag = statisticsService.updateById(statistics);

		Map<String, Object> result = new HashMap<String, Object>();
		if (flag>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, result);
	}

}
