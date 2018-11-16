package com.xindaibao.cashloan.manage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tool.util.StringUtil;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.exception.ServiceException;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.system.domain.SysDict;
import com.xindaibao.cashloan.system.domain.SysDictDetail;
import com.xindaibao.cashloan.system.permission.annotation.RequiresPermission;
import com.xindaibao.cashloan.system.service.SysDictDetailService;
import com.xindaibao.cashloan.system.service.SysDictService;

/**
 * 
 * 数据字典管理
 * 
 * @version 1.0
 * @author
 * @created 2014年9月23日 下午2:13:03
 */
@Scope("prototype")
@Controller
public class SysDictController extends BaseController {

	@Resource
	private SysDictService sysDictService;

	@Resource
	private SysDictDetailService sysDictDetailService;

	/**
	 * @description       获取字典列表                     done
	 * @param response
	 * @param request
	 * @param start
	 * @param limit
	 * @param search
	 * @throws IOException
	 * @author
	 * @return void
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "/modules/system/dictList.htm")
	@RequestMapping(value = "/modules/manage/system/dict/page.htm")
	@RequiresPermission(code = "modules:manage:system:dict:page",name = " 获取字典列表")
	public void listDicts(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize,
			@RequestParam(value = "search", required = false) String search) throws Exception {
		Map<String,Object> searchMap = new HashMap<String,Object>();
		if (search != null) {
			searchMap = JsonUtil.parse(search, Map.class);
		}
		Page<SysDict> page = sysDictService.getDictPageList(current,pageSize,searchMap);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);

	}
	
	/**
	 * 获取字典详情列表
	 * @param current
	 * @param pageSize
	 * @param code
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/system/dict/findByTypeCode.htm")
	@RequiresPermission(code = "modules:manage:system:dict:findByTypeCode",name = "获取字典详情列表")
	public void findByTypeCode(@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize,
			@RequestParam(value = "code") String code) throws Exception {
		SysDict dict = sysDictService.findByTypeCode(code);
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		if (StringUtil.isNotBlank(dict)) {
			data.put("parentId", dict.getId());
			Page<SysDictDetail> page = sysDictDetailService.getDictDetailList(current, pageSize,data);
			result.put(Constant.RESPONSE_DATA, page);
			result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "获取失败");
		}
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 字典列表
	 * @param current
	 * @param pageSize
	 * @param code
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/system/dict/listByTypeCode.htm")
	@RequiresPermission(code = "modules:manage:system:dict:listByTypeCode",name = "获取字典列表")
	public void listByTypeCode(
			@RequestParam(value = "code") String code) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		if (StringUtil.isNotBlank(code)) {
			data.put("typeCode", code);
			List<SysDictDetail> list = sysDictDetailService.listByTypeCode(data);
			result.put(Constant.RESPONSE_DATA, list);
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "获取失败");
		}
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 修改时获取未使用字典列表
	 * @param current
	 * @param pageSize
	 * @param code
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/system/dict/listUpdateCode.htm")
	@RequiresPermission(code = "modules:manage:system:dict:listUpdateCode",name = "修改时获取未使用字典列表")
	public void listUpdateCode(
			@RequestParam(value = "code") String code,
			@RequestParam(value = "id") long id) throws Exception {
		SysDict dict = sysDictService.findByTypeCode(code);
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		if (StringUtil.isNotBlank(dict)) {
			data.put("parentId", dict.getId());
			data.put("id", id);
			List<SysDictDetail> list = sysDictDetailService.listUpdateCode(data);
			result.put(Constant.RESPONSE_DATA, list);
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "获取失败");
		}
		ServletUtils.writeToResponse(response,result);
	}

	/**
	 * @description   查询详情     done  
	 * @param response
	 * @param start
	 * @param limit
	 * @param id
	 * @throws Exception
	 * @author
	 * @return void
	 * @since  1.0.0
	 */
//	@RequestMapping(value = "/modules/system/getDictDetail.htm")
	@RequestMapping(value = "/modules/manage/system/dict/detail/find.htm")
	@RequiresPermission(code = "modules:manage:system:dict:detail:find",name = "字典详情")
	public void findDictDetail(HttpServletResponse response,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize,
			@RequestParam(value = "id", required = false) String id)
			throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String,Object> result = new HashMap<String,Object>();
		if (id != null) {
			data.put("parentId", id);
			Page<SysDictDetail> page = sysDictDetailService.getDictDetailList(current, pageSize,data);
			result.put(Constant.RESPONSE_DATA, page);
 			result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		}else{
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "获取失败");
		}

		ServletUtils.writeToResponse(response, result);

	}

	/**
	 * @description    新增或者更新字典   done 
	 * @param response
	 * @param data
	 * @param status
	 * @throws Exception
	 * @author
	 * @return void
	 * @since  1.0.0
	 */
//	@RequestMapping("/modules/system/addOrUpdateDict.htm")
	@RequestMapping("/modules/manage/system/dict/save.htm")
	@RequiresPermission(code = "modules:manage:system:dict:save",name = "新增修改字典")
	public void saveOrUpdateDict(HttpServletResponse response,
			@RequestParam(value = "form", required = false) String form,
			@RequestParam(value = "status", required = false) String status)
			throws Exception {
		SysDict sysDict = JsonUtil.parse(form, SysDict.class);
		Map<String, Object> res = new HashMap<String, Object>();

		boolean backstauts = sysDictService.addOrModify(sysDict, status);
		if (backstauts) {
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			if(Constant.INSERT.equals(status)){
				res.put(Constant.RESPONSE_CODE_MSG, "插入成功");
			}else{
				res.put(Constant.RESPONSE_CODE_MSG, "更新成功");
			}
		}
		ServletUtils.writeToResponse(response, res);
	}

	/**
	 * 新增修改字典详情
	 * @param response
	 * @param data
	 * @param status
	 * @throws Exception
	 */
//	@RequestMapping("/modules/system/addOrUpdateDictDetail.htm")
	@RequestMapping("/modules/manage/system/dict/detail/save.htm")
	@RequiresPermission(code = "modules:manage:system:dict:detail:save",name = "新增修改字典详情")
	public void saveOrUpdateDictDetail(HttpServletResponse response,
			@RequestParam(value = "form", required = false) String data,
			@RequestParam(value = "status", required = false) String status)
			throws Exception {
		SysDictDetail dictDetail = JsonUtil.parse(data, SysDictDetail.class);
		Map<String, Object> res = new HashMap<String, Object>();

		sysDictDetailService.addOrModify(dictDetail, status);

		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		if(Constant.INSERT.equals(status)){
			res.put(Constant.RESPONSE_CODE_MSG, "插入成功");
		}else{
			res.put(Constant.RESPONSE_CODE_MSG, "更新成功");
		}
		ServletUtils.writeToResponse(response, res);
	}

	/**
	 * 删除字典
	 * @param response
	 * @param id
	 * @throws ServiceException
	 */
//	@RequestMapping("/modules/system/deleteDict.htm")
	@RequestMapping("/modules/manage/system/dict/delete.htm")
	@RequiresPermission(code = "modules:manage:system:dict:delete",name = "删除字典")
	public void deleteDict(HttpServletResponse response,
			@RequestParam(value = "id") String id) throws ServiceException {
		Map<String, Object> req = new HashMap<String, Object>();

		Map<String, Object> res = new HashMap<String, Object>();

		if (id != null) {
			req.put("parentId", id);
		} else {
			return;
		}
		if (sysDictDetailService.getItemCountMap(req) > 0) {
			//throw new ServiceException("有字典子项，删除失败");
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "有字典子项，删除失败,请先删除子项");
		} else {
			sysDictService.deleteDict(Long.valueOf(id));
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除成功");
		}

		ServletUtils.writeToResponse(response, res);
	}

	/**
	 * 删除字典详情
	 * @param response
	 * @param id
	 * @throws Exception
	 */
//	@RequestMapping(value = "/modules/system/deleteDictDetail.htm")
	@RequestMapping(value = "/modules/manage/system/dict/detail/delete.htm")
	@RequiresPermission(code = "modules:manage:system:dict:detail:delete",name = "删除字典详情")
	public void deleteDictDetail(HttpServletResponse response,
			@RequestParam(value = "id") String id) throws Exception {

		Map<String, Object> res = new HashMap<String, Object>();

		if (id != null) {
			sysDictDetailService.deleteSysDictDetail(Long.valueOf(id));
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除成功");
		}
		ServletUtils.writeToResponse(response, res);
	}
	

	/**
	 * 缓存字典使用
	 * @param response
	 * @param type
	 * @throws Exception
	 */
//	@RequestMapping(value = "/getDicts.htm")
	@RequestMapping(value = "/modules/manage/system/dict/cache/list.htm")
	@RequiresPermission(code = "modules:manage:system:dict:cache:list",name = "缓存字典使用")
	public void listCache(
			HttpServletResponse response,
			@RequestParam(value = "type") String type) throws Exception {
		List<Map<String, Object>> dicList = null;
		Map<String, Object> res = new HashMap<String, Object>();
			if (type != null) {
				dicList = sysDictService.getDictsCache(type);
			}
			if (dicList != null){
				res.put(Constant.RESPONSE_DATA, dicList);
				res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				res.put(Constant.RESPONSE_CODE_MSG, "获取成功");
			}else{
				res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				res.put(Constant.RESPONSE_CODE_MSG, "获取失败");
			}
		ServletUtils.writeToResponse(response, res);
	}
	
	
	/**
	 * 
	 * @description 查询所有字典
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author
	 * @return void
	 * @since  1.0.0
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@RequestMapping("/dicAction/queryAllDic.htm")
	@RequestMapping("/modules/manage/system/dict/list.htm")
	@RequiresPermission(code = "modules:manage:system:dict:list",name = "查询所有字典")
	public void listAll(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Map<String,Object>> list=sysDictDetailService.queryAllDic();

		Map<String,Object> rec=new LinkedHashMap<String, Object>();
		for (Map<String, Object> o : list) {
			Map dic=(Map)rec.get(o.get("itemCode"));
			if(dic==null){
				dic=new LinkedHashMap();
				rec.put(o.get("itemCode").toString(),dic);
			}
			((Map)dic).put(o.get("itemCode"),o.get("itemValue"));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_DATA, rec);
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 根据类型名称查询数据字典
	 * @param name
	 */
	@RequestMapping("/modules/manage/system/dict/findAllByName.htm")
	@RequiresPermission(code = "modules:manage:system:dict:findAllByName",name = "根据名称查询字典数据")
	public void findAllByName(@RequestParam(value = "name") String name){
		List<Map<String,Object>> list = sysDictDetailService.queryAllDicByParentName(name);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_DATA, list);
		ServletUtils.writeToResponse(response, result);
	}
}
