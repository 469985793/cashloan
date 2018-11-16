package com.xindaibao.cashloan.manage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tool.util.StringUtil;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.cl.model.ManageAgentListModel;
import com.xindaibao.cashloan.cl.model.ManageAgentModel;
import com.xindaibao.cashloan.cl.model.ManageCashLogModel;
import com.xindaibao.cashloan.cl.model.ManageProfitAmountModel;
import com.xindaibao.cashloan.cl.model.ManageProfitLogModel;
import com.xindaibao.cashloan.cl.service.ProfitAgentService;
import com.xindaibao.cashloan.cl.service.ProfitAmountService;
import com.xindaibao.cashloan.cl.service.ProfitCashLogService;
import com.xindaibao.cashloan.cl.service.ProfitLogService;
import com.xindaibao.cashloan.cl.service.UserInviteService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.domain.User;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.mapper.UserBaseInfoMapper;
import com.xindaibao.cashloan.core.model.UserBaseInfoModel;

/**
* 代理用户信息Controller
*/
@Scope("prototype")
@Controller
public class ManageSysProfitController extends ManageBaseController{

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
	private UserBaseInfoMapper userBaseInfoMapper;
	
	/**
	 * 代理商管理列表
	 * @param userId
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/sysProfit/findAgentList.htm")
	public void findAgentList(
			@RequestParam(value="userName",required=false) String userName,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Page<ManageAgentListModel> page = profitAgentService.findAgentList(userName,current,pageSize);
		Map<String, Object> data = new HashMap<>();
		data.put("list", page.getResult());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 查询用户代理等级
	 * @param loginName
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/sysProfit/findUserLevel.htm")
	public void findUserLevel(
			@RequestParam(value="userName") String userName,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Page<User> page = profitAgentService.findUserLevel(userName,current,pageSize);
		Map<String, Object> data = new HashMap<>();
		data.put("list", page.getResult());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 代理商关系
	 * @param userId
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/sysProfit/findAgent.htm")
	public void findAgent(
			@RequestParam(value="userName") String userName,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Page<ManageAgentModel> page = userInviteService.findSysAgent(userName,current,pageSize);
		Map<String, Object> data = new HashMap<>();
		data.put("list", page.getResult());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 分润记录查询
	 * @param userId
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/sysProfit/findLog.htm")
	public void findLog(
			@RequestParam(value="userName") String userName,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Page<ManageProfitLogModel> page = profitCashLogService.findLog(userName,current,pageSize);
		Map<String, Object> data = new HashMap<>();
		data.put("list", page.getResult());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 管理员可提现查询
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/sysProfit/findAmount.htm")
	public void findAmount(
			@RequestParam(value="userName") String userName,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Page<ManageProfitAmountModel> page = profitAmountService.findSysAmount(userName,current,pageSize);
		Map<String, Object> data = new HashMap<>();
		data.put("list", page.getResult());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 管理员提现记录查询
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/sysProfit/findCashLog.htm")
	public void findCashLog(
			@RequestParam(value="userName") String userName,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Page<ManageCashLogModel> page = profitLogService.findCashLog(userName,current,pageSize);
		Map<String, Object> data = new HashMap<>();
		data.put("list", page.getResult());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 代理商申请
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/profit/saveAgent.htm", method = RequestMethod.POST)
	public void saveAgent(
			@RequestParam(value="level") int level) throws Exception {
		String leaderId = request.getParameter("userId");
		String userId = request.getParameter("inviteId");
		String rate = request.getParameter("rate");
		UserBaseInfo userBaseInfo = userBaseInfoMapper.findByUserId(Long.parseLong(userId));
		Map<String,Object> result = new HashMap<String,Object>();
		int msg = 0;
		Date updateTime = new Date();
		if (StringUtil.isNotBlank(rate)&&Integer.valueOf(rate)<=5) {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "分润比例设置错误");
		}else {
			//校验用户是否在黑名单
			if(userBaseInfo != null && UserBaseInfoModel.USER_STATE_BLACK.equals(userBaseInfo.getState())){
				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "该账号无法借款,请联系客服人员。");
			}else {
				if (level==1) {
					msg = profitAgentService.saveOne(Long.parseLong(userId),updateTime);
			    }else {
			    	msg = profitAgentService.saveTwo(Long.parseLong(userId),leaderId,rate,updateTime);
			    }
			}
		}
		
		if (msg>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "添加成功");
		}
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 取消代理
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/profit/freezeAgent.htm", method = RequestMethod.POST)
	public void freezeAgent(
			@RequestParam(value="userId") long userId) throws Exception {
		Date updateTime = new Date();
		int msg = profitAgentService.freezeAgent(userId,updateTime);
		Map<String,Object> result = new HashMap<String,Object>();
		if (msg>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "取消成功");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "取消失败");
		}
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 2级代理升级
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/profit/rankUp.htm", method = RequestMethod.POST)
	public void rankUp(
			@RequestParam(value="id") long id,
			@RequestParam(value="userId") long userId) throws Exception {
		int msg = profitAgentService.rankUp(id,userId);
		Map<String,Object> result = new HashMap<String,Object>();
		if (msg>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "添加成功");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "添加失败");
		}
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 代理商状态修改
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/profit/pass.htm", method = RequestMethod.POST)
	public void pass(
			@RequestParam(value="id") long id,
			@RequestParam(value="isUse") int isUse) throws Exception {
		int msg = profitAgentService.pass(isUse,id);
		Map<String,Object> result = new HashMap<String,Object>();
		if (msg>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "代理商审核通过");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "代理商审核失败");
		}
		ServletUtils.writeToResponse(response,result);
	}
}
