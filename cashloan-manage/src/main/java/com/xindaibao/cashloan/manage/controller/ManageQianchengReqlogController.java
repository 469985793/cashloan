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
import com.xindaibao.cashloan.cl.domain.QianchengReqlog;
import com.xindaibao.cashloan.cl.mapper.QianchengReqlogMapper;
import com.xindaibao.cashloan.cl.model.QianchengReqlogModel;
import com.xindaibao.cashloan.cl.service.QianchengReqlogService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.system.permission.annotation.RequiresPermission;

/**
 * 浅橙请求记录表Controller
 * @author
 * @version 1.0.0
 * @data 2017-03-21 09:54:01


 * 

 */
@Scope("prototype")
@Controller
public class ManageQianchengReqlogController extends ManageBaseController{
	@Resource
	private QianchengReqlogService qianchengReqlogService;
	
	@Resource
	private QianchengReqlogMapper qianchengReqlogMapper;
	
	/**
	 * 浅橙机审请求记录列表
	 * @param searchParams
	 * @param current
	 * @param pageSize
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/modules/manage/borrow/qianchengReqLog/list.htm",method={RequestMethod.GET,RequestMethod.POST})
	@RequiresPermission(code = "modules:manage:borrow:list",name = "机审请求记录列表")
	public void list(@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize){
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		Page<QianchengReqlogModel> page = qianchengReqlogService.listQianchengReqlog(params, current, pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 通过借款查询审批日志
	 * @param searchParams
	 * @param current
	 * @param pageSize
	 */
	@RequestMapping(value="/modules/manage/borrow/qianchengReqLog/listByBorrow.htm",method={RequestMethod.GET,RequestMethod.POST})
	@RequiresPermission(code = "modules:manage:borrow:qianchengReqLog:listByBorrow",name = "机审请求记录列表")
	public void listByBorrow(@RequestParam(value = "borrowId") Long borrowId){
		QianchengReqlog log = qianchengReqlogService.findByBorrowId(borrowId);
		Map<String,Object> result = new HashMap<String,Object>();
		if(log!=null){
			
			result.put(Constant.RESPONSE_DATA, log);
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
			ServletUtils.writeToResponse(response,result);
		}else{
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "获取失败");
			ServletUtils.writeToResponse(response,result);
		}
		
	}
	
	
}
