package com.xindaibao.cashloan.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xindaibao.cashloan.cl.domain.UserEmerContacts;
import com.xindaibao.cashloan.cl.domain.UserEquipmentInfo;
import com.xindaibao.cashloan.cl.service.UserAuthService;
import com.xindaibao.cashloan.cl.service.UserEmerContactsService;
import com.xindaibao.cashloan.cl.service.UserEquipmentInfoService;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.creditrank.cr.service.CreditRatingService;
import com.xindaibao.creditrank.cr.service.CreditService;


 /**
 * 紧急联系人表Controller
 * 
 * @author
 * @version 1.0.0
 * @date 2017-02-14 11:24:05


 * 

 */
@Scope("prototype")
@Controller
public class UserEmerContactsController extends BaseController {

	@Resource
	private UserEmerContactsService userEmerContactsService;
	@Resource
	private UserAuthService userAuthService;
	@Resource
	private UserEquipmentInfoService userEquipmentInfoService;
	
	@Resource
	private CreditRatingService creditRatingService;
    
    @Resource
    private CreditService creditService;

	public static final Logger logger = LoggerFactory.getLogger(UserEmerContactsController.class);
	/**
	 * 保存联系人以及设备信息
	 * @param name
	 * @param phone
	 * @param relation
	 * @param type
	 * @param userId
	 * @param operatingSystem
	 * @param systemVersions
	 * @param phoneType
	 * @param phoneBrand
	 * @param phoneMark
	 * @param versionName
	 * @param versionCode
	 * @param mac
	 */
	@RequestMapping(value = "/api/act/mine/contact/saveOrUpdate.htm", method = RequestMethod.POST)
	public void saveOrUpdate(
			@RequestParam(value="name",required=true) String name,
			@RequestParam(value="phone",required=true) String phone,
			@RequestParam(value="relation",required=true) String relation,
			@RequestParam(value="type",required=true) String type,
			@RequestParam(value="userId",required=true) String userId,
			@RequestParam(value = "operatingSystem", required = false) String operatingSystem,
			@RequestParam(value = "systemVersions", required = false) String systemVersions,
			@RequestParam(value = "phoneType", required = false) String phoneType,
			@RequestParam(value = "phoneBrand", required = false) String phoneBrand,
			@RequestParam(value = "phoneMark", required = false) String phoneMark,
			@RequestParam(value = "versionName", required = false) String versionName,
			@RequestParam(value = "versionCode", required = false) String versionCode,
			@RequestParam(value = "mac", required = false) String mac
			){
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("userId", userId);
		List<UserEmerContacts> contacts=userEmerContactsService.getUserEmerContactsByUserId(result);
		result.clear();
		String[] names=name.split(",");
		String[] phones=phone.split(",");
		
		//判断手机号
		int phoneCount=0;
		for (int i = 0; i < phones.length; i++) {
			phones[i]=phones[i].replaceAll("[^0-9]", "");
			logger.info("用户："+userId+"紧急联系人认证的手机号："+phones[i]);
			if(phones[i].length()<11){
				logger.info("");
				result.put(Constant.RESPONSE_CODE,Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "当前版本只支持手机号");
				ServletUtils.writeToResponse(response,result);
				return;
			}
			phones[i]=phones[i].substring(phones[i].length()-11);
			Pattern pattern = Pattern.compile("^(1[34578])\\d{9}$");  
	        Matcher matcher = pattern.matcher(phones[i]);  
			if(matcher.matches()){
				phoneCount++;
			}
		}
		if(phoneCount==2){
			if(phones[0].equals(phones[1])){
				result.put(Constant.RESPONSE_CODE,Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "请勿选择两个一样的手机号");
				ServletUtils.writeToResponse(response,result);
				return;
			}
		}else{
			result.put(Constant.RESPONSE_CODE,Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "手机号格式错误");
			ServletUtils.writeToResponse(response,result);
			return;
		}
		//判断手机号结束
		
		String[] relations=relation.split(",");
		String[] types=type.split(",");
		int count=0;
		if(contacts.size()==0){
			//新增
			// FIXME: 2017/11/13 名字中如果有表情符， 会报sql异常
			for (int i = 0; i < names.length; i++) {
				UserEmerContacts info=new UserEmerContacts();
				info.setName(names[i]);
				info.setPhone(phones[i]);
				info.setRelation(relations[i]);
				info.setType(types[i]);
				info.setUserId(Long.parseLong(userId));
				count=userEmerContactsService.insert(info);
			}
			result.put("contactState", "30");
			result.put("userId", userId);
			userAuthService.updateByUserId(result);
			//验证状态，启用评分，更新额度
			boolean authFlag = userAuthService.findAuthState(Long.parseLong(userId));
			if (authFlag){//完成全部认证
				String consumerNo = userId.toString();
				//开视评分
				creditRatingService.saveCreditRating(consumerNo, 1l);//借款类型默认为现金贷，即 long borrowTypeId=1l
				//开始更新授信额度数据
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("used", "0");
				map.put("consumerNo", userId);
				map.put("state", "10");//待审批
				creditService.updateByAuth(map);
			}
		}else{
			//更新
			for (int i = 0; i < names.length; i++) {
				// FIXME: 2017/11/13 用户紧急联系人只有一条时报错
				if(contacts.get(i).getType().equals(types[i])){
					UserEmerContacts info=contacts.get(i);
					info.setName(names[i]);
					info.setPhone(phones[i]);
					info.setRelation(relations[i]);
					info.setType(types[i]);
					count=userEmerContactsService.updateById(info);
				}else{
					int j=i==0?1:0;
					UserEmerContacts info=contacts.get(i);
					info.setName(names[j]);
					info.setPhone(phones[j]);
					info.setRelation(relations[j]);
					info.setType(types[j]);
					count=userEmerContactsService.updateById(info);
				}
			}
			if(count>0){
				result.put("contactState", "30");
				result.put("userId", userId);
				userAuthService.updateByUserId(result);
				//验证状态，启用评分，更新额度
				boolean authFlag = userAuthService.findAuthState(Long.parseLong(userId));
				if (authFlag){//完成全部认证
					String consumerNo = userId.toString();
					//开视评分
					creditRatingService.saveCreditRating(consumerNo, 1l);//借款类型默认为现金贷，即 long borrowTypeId=1l
					//开始更新授信额度数据
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("used", "0");
					map.put("consumerNo", userId);
					map.put("state", "10");//待审批
					creditService.updateByAuth(map);
				}
			}
		}
		UserEquipmentInfo uei = new UserEquipmentInfo();
		uei.setUserId(Long.parseLong(userId));
		uei.setBlackBox("");
		uei.setOperatingSystem(operatingSystem);
		uei.setSystemVersions(systemVersions);
		uei.setPhoneType(phoneType);
		uei.setPhoneBrand(phoneBrand);
		uei.setPhoneMark(phoneMark);
		uei.setVersionName(versionName);
		uei.setVersionCode(versionCode);
		uei.setMac(mac);
		count = userEquipmentInfoService.saveOrUpdate(uei);
		result.put(Constant.RESPONSE_CODE, count>0?Constant.SUCCEED_CODE_VALUE:Constant.FAIL_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, count>0?"保存成功":"保存失败");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * @description  获取单个用户的紧急联系人
	 * @param userId 用户的id
	 * @author
	 * @return void
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/api/act/mine/contact/getContactInfoList.htm", method = RequestMethod.GET)
	public void getContactInfoList(@RequestParam(value="userId",required=true) String userId){
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("userId", userId);
		List<UserEmerContacts> userEmerContacts=userEmerContactsService.getUserEmerContactsByUserId(paramMap);
		Map<String,Object> result=new HashMap<String,Object>();
		Map<String,Object> temp=new HashMap<String,Object>();
		temp.put("list", userEmerContacts);
		result.put(Constant.RESPONSE_CODE,Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_DATA, temp);
		result.put(Constant.RESPONSE_CODE_MSG,"获取成功");
		ServletUtils.writeToResponse(response,result);
	}
}
