package com.xindaibao.cashloan.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.domain.UrgeRepayOrder;
import com.xindaibao.cashloan.cl.model.UrgeRepayCountModel;
import com.xindaibao.cashloan.cl.model.UrgeRepayOrderModel;
import com.xindaibao.cashloan.cl.service.BorrowRepayService;
import com.xindaibao.cashloan.cl.service.UrgeRepayOrderLogService;
import com.xindaibao.cashloan.cl.service.UrgeRepayOrderService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.system.domain.SysUser;
import com.xindaibao.cashloan.system.permission.annotation.RequiresPermission;

 /**
 * 催款专员以及催收统计相关接口Controller
 * 
 * @author
 * @version 1.0.0
 * @date 2017-03-07 14:21:58


 * 

 */
@Scope("prototype")
@Controller
public class ManageUrgeCollectionRepayController extends ManageBaseController {

	@Resource
	private UrgeRepayOrderService urgeRepayOrderService;
	
	@Resource
	private UrgeRepayOrderLogService urgeRepayOrderLogService;

	@Resource
	private BorrowRepayService borrowRepayService;
	
	/**
	 * 我的催款计划信息列表
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/modules/manage/borrow/repay/urge/collection/list.htm")
	@RequiresPermission(code = "modules:manage:borrow:repay:urge:collection:list",name = "我的催款计划信息列表")
	public void list(
			HttpServletResponse response,
			HttpServletRequest request, 
			HttpSession session,
			@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		SysUser sysUser=getLoginUser(request);
		Page<UrgeRepayOrder> page =new Page<UrgeRepayOrder>();
		if(sysUser!=null){
			if(params==null){
				params=new HashMap<String, Object>();
			}
			params.put("userId",sysUser.getId());
		    page =urgeRepayOrderService.listItem(params,current,pageSize);
			result.put(Constant.RESPONSE_DATA, page);
			result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		}else{
			result.put(Constant.RESPONSE_DATA, page);
			result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "登陆过期请重新登录");
		}
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 我的催款记录信息列表   
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/modules/manage/borrow/repay/urge/collection/loglist.htm")
	@RequiresPermission(code = "modules:manage:borrow:repay:urge:collection:loglist",name = "我的催款记录信息列表")
	public void loglist(
			HttpServletResponse response,
			HttpServletRequest request, 
			HttpSession session,
			@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		Page<UrgeRepayOrderModel> page =new Page<UrgeRepayOrderModel>();
		SysUser sysUser=getLoginUser(request);
		if(sysUser!=null){
			if(params==null){
				params=new HashMap<String, Object>();
			}
			params.put("userId",sysUser.getId());
			page =urgeRepayOrderService.listModel(params,current,pageSize);
			result.put(Constant.RESPONSE_DATA, page);
			result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		}else{
			result.put(Constant.RESPONSE_DATA, page);
			result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "登陆过期请重新登录");
		}
		ServletUtils.writeToResponse(response,result);
	}

	/**
	 * 催收人员统计列表
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/modules/manage/borrow/repay/urge/collection/member.htm")
	@RequiresPermission(code = "modules:manage:borrow:repay:urge:collection:member",name = "催收人员统计列表")
	public void member(
			@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize){
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		Page<UrgeRepayCountModel> page = urgeRepayOrderService.memberCount(params,current,pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		 
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 催回率统计
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/modules/manage/borrow/repay/urge/collection/urgeCount.htm")
	@RequiresPermission(code = "modules:manage:borrow:repay:urge:collection:urge",name = "催回率统计")
	public void urgeCount(
			@RequestParam(value="searchParams",required=false) String searchParams){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		List<UrgeRepayCountModel> list =urgeRepayOrderService.urgeCount(params);
		result.put(Constant.RESPONSE_DATA, list);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 催收订单统计
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/modules/manage/borrow/repay/urge/collection/orderCountt.htm")
	@RequiresPermission(code = "modules:manage:borrow:repay:urge:collection:orderCount",name = "催收订单统计")
	public void orderCount(
			@RequestParam(value="searchParams",required=false) String searchParams){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		List<UrgeRepayCountModel> list =urgeRepayOrderService.orderCount(params);
		result.put(Constant.RESPONSE_DATA, list);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		 
		ServletUtils.writeToResponse(response,result);
	}
	
	
	/**
	 * 催收员每日统计
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/modules/manage/borrow/repay/urge/collection/memberDayCount.htm")
	@RequiresPermission(code = "modules:manage:borrow:repay:urge:collection:memberDayCount",name = "催收员每日统计")
	public void memberDayCount(
			@RequestParam(value="searchParams",required=false) String searchParams){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		List<UrgeRepayCountModel> list = urgeRepayOrderService.memberDayCount(params);
		result.put(Constant.RESPONSE_DATA, list);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		 
		ServletUtils.writeToResponse(response,result);
	}
	
	
	
	
}
