package com.xindaibao.cashloan.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xindaibao.cashloan.cl.model.UserAuthModel;
import com.xindaibao.cashloan.cl.service.UserAuthService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.util.StringUtil;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.core.domain.UserOtherInfo;
import com.xindaibao.cashloan.core.service.UserOtherInfoService;
import com.xindaibao.creditrank.cr.domain.Credit;
import com.xindaibao.creditrank.cr.mapper.CreditMapper;

/**
* 用户更多信息Controller
*/

@Slf4j
@Scope("prototype")
@Controller
public class UserOtherInfoController extends BaseController {

	@Resource
	private UserOtherInfoService userOtherInfoService;
	
	@Resource
	private UserAuthService userAuthService;
	
	@Resource
	private CreditMapper creditMapper;
	/**
	 * 保存或修改用户其他信息
	 * @param userId
	 * @param taobao
	 * @param email
	 * @param qq
	 * @param wechat
	 */
	@RequestMapping(value = "/api/act/mine/other/saveOrUpdate.htm", method = RequestMethod.POST)
	public void saveOrUpdate(
			@RequestParam(value = "userId", required = true) Long userId,
			@RequestParam(value = "taobao", required = true) String taobao,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "qq", required = true) String qq,
			@RequestParam(value = "wechat", required = true) String wechat) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean isAuth = userAuthService.findAuthState(userId);
		if(!isAuth){
			resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, "请先完成必要认证信息");
			ServletUtils.writeToResponse(response, resultMap);
			return;
		}
		
		String increaseCredit = Global.getValue("credit_increase_byAuth");
        if (StringUtils.isEmpty(increaseCredit)) {
            log.error("系统配置项 credit_increase_byAuth 为空, 请联系后台管理员.");
            increaseCredit = "0.0";
        }
		
//		//判断邮箱的@前面的长度，超过15将会报错，999贷用
//		if(email!=null&&email.contains("@")){
//			int i=email.substring(0,email.indexOf("@")).length();
//			if(i>15){
//				Map<String, Object> result = new HashMap<String, Object>();
//				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
//				result.put(Constant.RESPONSE_CODE_MSG, "邮箱过长，请重新填写");
//				ServletUtils.writeToResponse(response, result);
//				return ;
//			}
//		}
		
		if(!StringUtil.isMail(email)){
			Map<String, Object> result = new HashMap<String, Object>();
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "邮箱格式错误");
			ServletUtils.writeToResponse(response, result);
			return ;
		}
		
		UserOtherInfo info = userOtherInfoService.getInfoByUserId(userId);
		boolean flag =  false;
		//是否提额
		boolean isCredit = false;
		if (null != info) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", info.getId());
			paramMap.put("taobao", taobao);
			paramMap.put("email", email);
			paramMap.put("qq", qq);
			paramMap.put("wechat", wechat);
			flag = userOtherInfoService.updateSelective(paramMap);
		} else {
			UserOtherInfo otherInfo = new UserOtherInfo();
			otherInfo.setUserId(userId);
			otherInfo.setTaobao(taobao);
			otherInfo.setEmail(email);
			otherInfo.setQq(qq);
			otherInfo.setWechat(wechat);
			flag = userOtherInfoService.save(otherInfo);
			isCredit = true;
		}

		if(flag){
			Map<String,Object> authMap=new HashMap<String,Object>();
			authMap.put("userId",userId);
			authMap.put("otherInfoState",UserAuthModel.STATE_VERIFIED);
			userAuthService.updateByUserId(authMap);
			if(isCredit){
				Credit c = creditMapper.findByConsumerNo(userId.toString());
				Double total = c.getTotal();
				Double unuse = c.getUnuse();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("consumerNo", userId);
				map.put("total", total+Double.parseDouble(increaseCredit));
				map.put("unuse", unuse+Double.parseDouble(increaseCredit));
				 creditMapper.updateByUserId(map);
			}
				
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		if (flag) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "保存失败");
		}

		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 保存或修改用户其他信息
	 * 
	 * @param userId
	 * @param taobao
	 * @param email
	 * @param qq
	 * @param wechat
	 */
	@RequestMapping(value = "/api/act/mine/other/findDetail.htm", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public void findDetail(
			@RequestParam(value = "userId", required = true) Long userId) {
		UserOtherInfo info = userOtherInfoService.getInfoByUserId(userId);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, info);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response, result);
	}

}
