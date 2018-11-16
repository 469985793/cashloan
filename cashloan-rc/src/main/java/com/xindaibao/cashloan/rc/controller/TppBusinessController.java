package com.xindaibao.cashloan.rc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.cashloan.rc.domain.TppBusiness;
import com.xindaibao.cashloan.rc.model.ManageTppBusinessModel;
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
import com.xindaibao.cashloan.rc.service.TppBusinessService;

 /**
 * 第三方征信接口信息Controller
 *
 * @version 1.0.0
 * @date 2017-03-14 13:41:57
 */
@Controller
@Scope("prototype")
public class TppBusinessController extends BaseController {

	@Resource
	private TppBusinessService tppBusinessService;
	
	/**
	 * 第三方征信接口分页查询
	 *
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping("/modules/manage/creditdata/tppBusiness/page.htm")
	public void page(
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "current") int currentPage,
			@RequestParam(value = "pageSize") int pageSize) {
		Map<String, Object> params = JSONObject.parseObject(search);
		Page<ManageTppBusinessModel> page = tppBusinessService.page(params, currentPage,
				pageSize);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 据tppId查询第三方下所有接口
	 * 
	 * @param tppId
	 */
	@RequestMapping("/modules/manage/creditdata/tppBusiness/listByTppId.htm")
	public void listByTppId(@RequestParam(value = "tppId") Long tppId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tppId", tppId);
		List<TppBusiness> list = tppBusinessService.listSelective(paramMap);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, list);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 添加第三方征信接口信息
	 * 
	 * @param tpp
	 */
	@RequestMapping("/modules/manage/creditdata/tppBusiness/save.htm")
	public void save(TppBusiness tppBusiness) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		//校验名称是否重复
		boolean flag = tppBusinessService.tppBusinessExist(tppBusiness);
		if(flag){
			
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "接口简称重复");
			ServletUtils.writeToResponse(response, result);
			return;
		}else{
			flag = tppBusinessService.save(tppBusiness);
		}
		
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
	 * 修改第三方征信接口信息
	 * 
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping("/modules/manage/creditdata/tppBusiness/update.htm")
	public void update(TppBusiness tppBusiness) {
		boolean flag = tppBusinessService.update(tppBusiness);

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
	 * 启用第三方征信接口
	 * 
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping("/modules/manage/creditdata/tppBusiness/enable.htm")
	public void enable(@RequestParam(value = "id") Long id) {
		boolean flag = tppBusinessService.enable(id);
		
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
	 * 禁用第三方征信接口
	 * 
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping("/modules/manage/creditdata/tppBusiness/disable.htm")
	public void disable(@RequestParam(value = "id") Long id) {
		boolean flag = tppBusinessService.disable(id);

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

}
