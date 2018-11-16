package com.xindaibao.cashloan.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.model.ManageCashLogModel;
import com.xindaibao.cashloan.cl.model.ManageProfitAmountModel;
import com.xindaibao.cashloan.cl.model.ManageProfitLogModel;
import com.xindaibao.cashloan.cl.model.ManageProfitModel;
import com.xindaibao.cashloan.cl.service.ProfitAgentService;
import com.xindaibao.cashloan.cl.service.ProfitAmountService;
import com.xindaibao.cashloan.cl.service.ProfitCashLogService;
import com.xindaibao.cashloan.cl.service.ProfitLogService;
import com.xindaibao.cashloan.cl.service.UserInviteService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.util.StringUtil;
import com.xindaibao.cashloan.core.service.CloanUserService;
import com.xindaibao.cashloan.system.domain.SysUser;

/**
* 代理用户信息Controller
* 
* @author
* @version 1.0.0
* @date 2017-02-28 10:24:45


* 

*/
@Scope("prototype")
@Controller
public class ManageProfitController extends ManageBaseController{

	@Resource
	private UserInviteService userInviteService;
	@Resource
	private ProfitAgentService profitAgentService;
	@Resource
	private ProfitAmountService profitAmountService;
	@Resource
	private ProfitCashLogService profitCashLogService;
	@Resource
	private ProfitLogService profitLogService;
	@Resource
	private CloanUserService cloanUserService;
	
	/**
	 * 代理商管理
	 * @param userId
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/profit/findAgent.htm")
	public void findAgent(
			@RequestParam(value="userName") String userName,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		SysUser user = (SysUser) request.getSession().getAttribute("SysUser");
		
		if(null == user || StringUtil.isBlank(user.getPhone())){
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "用户异常");
			ServletUtils.writeToResponse(response, result);
			return ;
		}
		
		Page<ManageProfitModel> page = userInviteService.findAgent(user.getPhone(), userName, current, pageSize);
		Map<String, Object> data = new HashMap<>();
		data.put("list", page.getResult());
		
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 奖励获得记录查询
	 * @param userId
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/profit/findLog.htm")
	public void findLog(
			@RequestParam(value="userName") String userName,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		SysUser user = (SysUser) request.getSession().getAttribute("SysUser");
		if(null == user || StringUtil.isBlank(user.getPhone())){
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "用户异常");
			ServletUtils.writeToResponse(response, result);
			return ;
		}

		Page<ManageProfitLogModel> page = profitCashLogService.findLog(user.getPhone(),userName,current,pageSize);
		Map<String, Object> data = new HashMap<>();
		data.put("list", page.getResult());
		
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 奖励资金账户查询
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/profit/findAmount.htm")
	public void findAmount(
			@RequestParam(value="userName") String userName,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		SysUser user = (SysUser) request.getSession().getAttribute("SysUser");

		if(null == user || StringUtil.isBlank(user.getPhone())){
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "用户异常");
			ServletUtils.writeToResponse(response, result);
			return ;
		}
		
		Page<ManageProfitAmountModel> page = profitAmountService.findAmount(user.getPhone(),userName,current,pageSize);
		Map<String, Object> data = new HashMap<>();
		data.put("list", page.getResult());

		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 奖励打款记录查询
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/profit/findCashLog.htm")
	public void findCashLog(
			@RequestParam(value="userName") String userName,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		SysUser user = (SysUser) request.getSession().getAttribute("SysUser");
		
		if(null == user || StringUtil.isBlank(user.getPhone())){
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "用户异常");
			ServletUtils.writeToResponse(response, result);
			return ;
		}
		
		Page<ManageCashLogModel> page = profitLogService.findCashLog(user.getPhone(),userName,current,pageSize);
		Map<String, Object> data = new HashMap<>();
		data.put("list", page.getResult());
		
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
}
