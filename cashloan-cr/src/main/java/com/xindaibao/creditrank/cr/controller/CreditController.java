package com.xindaibao.creditrank.cr.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.creditrank.cr.domain.Credit;
import com.xindaibao.creditrank.cr.service.CreditLogService;
import com.xindaibao.creditrank.cr.service.CreditService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.system.domain.SysUser;
import com.xindaibao.cashloan.system.permission.annotation.RequiresPermission;
import com.xindaibao.creditrank.cr.model.CreditModel;

/**
 * 授信额度管理Controller
 * 
 * @author
 * @version 1.0.0
 * @date 2016-12-15 15:47:24


 * 

 */
@Scope("prototype")
@Controller
public class CreditController extends BaseController {

	@Resource
	private CreditService creditService;
	@Resource
	private CreditLogService creditLogService;
	
	/**
	 * 查询用户额度列表
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/user/credit/page.htm")
	@RequiresPermission(code = "modules:manage:user:credit:page",name = "查询用户额度列表")
	public void page(
			@RequestParam(value="search",required=false) String search,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> searchMap = JsonUtil.parse(search, Map.class);
		Page<CreditModel> page = creditService.page(searchMap,current, pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 查询用户额度详情
	 * @param consumerNo
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/user/credit/findByConsumerNo.htm")
	@RequiresPermission(code = "modules:manage:user:credit:findByConsumerNo",name = "查询用户额度详情")
	public void findByConsumerNo(
			@RequestParam(value="search",required=false) String search,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> searchMap = JsonUtil.parse(search, Map.class);
		Page<CreditModel> page = creditService.listAll(searchMap,current, pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 修改用户授信额度
	 * @param consumerNo
	 * @param total
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/user/credit/updateTotal.htm")
	@RequiresPermission(code = "modules:manage:user:credit:updateTotal",name = "调整用户授信额度")
	public void updateTotal(
			@RequestParam(value="id") long id,
			@RequestParam(value="unuse") Double unuse,
			@RequestParam(value="remark") String remark
			) throws Exception {
		SysUser sysUser = this.getLoginUser(request);
		int msg = creditService.updateSelective(id,unuse,sysUser,remark);
		Map<String,Object> result = new HashMap<String,Object>();
		if (msg>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作成功");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作失败");
		}
		
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 冻结解冻账户
	 * @param id
	 * @param unuse
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/user/credit/updateState.htm")
	@RequiresPermission(code = "modules:manage:user:credit:updateState",name = "冻结解冻账户")
	public void updateState(
			@RequestParam(value="id") long id,
			@RequestParam(value="state") String state) throws Exception {
		Credit credit = creditService.findByPrimary(id);
		Map<String,Object> result = new HashMap<String,Object>();
		if (credit.getState().equals(state)) {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作失败,已经是此状态!");
		}else {
			SysUser sysUser = this.getLoginUser(request);
			int msg = creditService.updateState(id,state,sysUser);
			if (msg>0) {
				result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "操作成功");
			}else {
				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "操作失败");
			}
		}
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 审批额度
	 * @param id
	 * @param unuse
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/user/credit/approval.htm")
	@RequiresPermission(code = "modules:manage:user:credit:approval",name = "授信额度审批")
	public void approval(
			@RequestParam(value="id") long id,
			@RequestParam(value="state") String state,
			@RequestParam(value="total") Double total) throws Exception {
		Credit credit = creditService.findByPrimary(id);
		Map<String,Object> result = new HashMap<String,Object>();
		if (credit.getState().equals(state)) {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "操作失败,已经是此状态!");
		}else {
			SysUser sysUser = this.getLoginUser(request);
			int msg = creditService.updateByApproval(id, total, state, sysUser);
			if (msg>0) {
				result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "操作成功");
			}else {
				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "操作失败");
			}
		}
		ServletUtils.writeToResponse(response,result);
	}
}
