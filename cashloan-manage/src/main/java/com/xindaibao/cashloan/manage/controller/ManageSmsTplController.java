package com.xindaibao.cashloan.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.SmsTpl;
import com.xindaibao.cashloan.cl.service.SmsTplService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;


/**
 *
 */
@Controller
@Scope("prototype")
public class ManageSmsTplController extends BaseController{
	@Resource
	private SmsTplService smsTplService;
	
	/**
	 * 短信模板列表
	 * @param searchParams
	 * @param current
	 * @param pageSize
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/modules/manage/smstpl/list.htm",method={RequestMethod.GET})
	public void listMessages(@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize){
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		Page<SmsTpl> page = smsTplService.list(params, current, pageSize);
		Map<String,Object> list = new HashMap<String,Object>();
		list.put("list", page);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, list);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE,Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 新增短信模板
	 * @param type
	 * @param typeName
	 * @param tpl
	 * @param number
	 */
	@RequestMapping(value="/modules/manage/smstpl/save.htm",method={RequestMethod.POST})
	public void save(@RequestParam(value="type") String type, 
			@RequestParam(value="typeName") String typeName,
			@RequestParam(value="tpl") String tpl,
			@RequestParam(value="number") String number){
		Map<String,Object> result = new HashMap<String,Object>();
		SmsTpl smsTpl = new SmsTpl();
		smsTpl.setNumber(number);
		smsTpl.setTpl(tpl);
		smsTpl.setType(type);
		smsTpl.setTypeName(typeName);
		smsTpl.setState("10");
		int msg = smsTplService.insert(smsTpl);
		if (msg>0) {
        	result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "添加成功");
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "添加失败");
		}
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 修改短信模板
	 * @param params
	 */
	@RequestMapping(value="/modules/manage/smstpl/update.htm",method={RequestMethod.POST})
	public void update(@RequestParam(value="type") String type, 
			@RequestParam(value="typeName") String typeName,
			@RequestParam(value="tpl") String tpl,
			@RequestParam(value="number") String number, 
			@RequestParam(value="id") long id){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", type);
		paramMap.put("typeName", typeName);
		paramMap.put("id", id);
		paramMap.put("tpl", tpl);
		paramMap.put("number", number);
		int msg = smsTplService.updateSelective(paramMap);
		if (msg>0) {
        	result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "修改成功");
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "修改失败");
		}
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 禁用短信模板
	 * @param id
	 */
	@RequestMapping(value="/modules/manage/smstpl/disable.htm",method={RequestMethod.POST,RequestMethod.GET})
	public void disable(@RequestParam(value="id") long id){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id",id);
		paramMap.put("state", "20");
		int msg = smsTplService.updateSelective(paramMap);
		if (msg>0) {
        	result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "禁用成功");
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "禁用失败");
		}
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 启用短信模板
	 * @param id
	 */
	@RequestMapping(value="/modules/manage/smstpl/enable.htm",method={RequestMethod.POST,RequestMethod.GET})
	public void enable(@RequestParam(value="id") long id){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("id",id);
		paramMap.put("state", "10");
		int msg = smsTplService.updateSelective(paramMap);
		if (msg>0) {
        	result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "启用成功");
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "启用失败");
		}
		ServletUtils.writeToResponse(response,result);
	}
}
