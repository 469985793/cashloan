package com.xindaibao.cashloan.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.common.collect.Maps;
import com.xindaibao.cashloan.api.user.service.MoneyCapitalUtil;
import com.xindaibao.cashloan.cl.model.ClBorrowModel;
import com.xindaibao.cashloan.cl.service.ClBorrowService;
import com.xindaibao.cashloan.core.common.util.DateUtil;
import com.xindaibao.cashloan.core.domain.ProtocolBuyLater;
import com.xindaibao.cashloan.core.domain.UserBaseInfo;
import com.xindaibao.cashloan.core.service.UserBaseInfoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.system.domain.SysConfig;
import com.xindaibao.cashloan.system.service.SysConfigService;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;


/**
* 协议Controller
* 
* @author
* @version 1.0.0
* @date 2017-02-22 13:57:14




*/
@Scope("prototype")
@Controller
public class ProtocolController  extends BaseController{
	
	@Resource
	private SysConfigService sysConfigService;
	
	
	/**
	 * 获取协议清单
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/protocol/list.htm", method = RequestMethod.GET)
	public void list() throws Exception {
		Map<String,Object>  data = new HashMap<>();
		
		List<Map<String,Object>> dataList= new ArrayList<Map<String,Object>>();
		List<SysConfig> list= sysConfigService.listByCode("protocol_");
		
		for(int i=0;i<list.size();i++){
			Map<String,Object> pro =new HashMap<>();
			pro.put("code",list.get(i).getCode());
			pro.put("value",list.get(i).getValue());
			pro.put("name",list.get(i).getName());
			dataList.add(pro);
		}
		data.put("list", dataList);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}

	@Resource
	private UserBaseInfoService userBaseInfoService;
	@Resource
	private ClBorrowService clBorrowService;

	@RequestMapping(value ="/api/protocol/borrow.htm",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String xieyi(HttpServletRequest req, String userId, String borrowId){

		Map<String, Object> model = Maps.newHashMap();
		if(null == userId || null == borrowId){
			return null;
		}
		UserBaseInfo userBaseInfo = userBaseInfoService.findByUserId(Long.parseLong(userId));
		ClBorrowModel clBorrow = clBorrowService.findBorrow(Long.parseLong(borrowId));

		Date repayDate = DateUtil.getDateBefore(Integer.parseInt(clBorrow.getTimeLimit())-1, clBorrow.getCreateTime()) ;
		BigDecimal amount = new BigDecimal(Double.toString(clBorrow.getAmount()));
		BigDecimal fee = new BigDecimal(Double.toString(clBorrow.getFee()));
		BigDecimal totalFee = amount.add(fee);

		ProtocolBuyLater protocolBuyLater = new ProtocolBuyLater(clBorrow.getOrderNo(), userBaseInfo.getRealName(), userBaseInfo.getPhone(),
				userBaseInfo.getIdNo(), userBaseInfo.getLiveAddr(), amount, MoneyCapitalUtil.number2CNMontrayUnit(amount),
				DateUtil.dateStr6(clBorrow.getCreateTime()), DateUtil.dateStr6(repayDate),clBorrow.getTimeLimit(), totalFee,
				MoneyCapitalUtil.number2CNMontrayUnit(totalFee), clBorrow.getCardNo(), clBorrow.getBank(), fee);

		model.put("protocolBuyLater", protocolBuyLater);
		JSONObject jsonobject=JSONObject.fromObject(model);

		return  jsonobject.toString();
	}

}
