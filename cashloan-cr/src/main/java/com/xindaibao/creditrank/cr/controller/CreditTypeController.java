package com.xindaibao.creditrank.cr.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.creditrank.cr.domain.CreditType;
import com.xindaibao.creditrank.cr.model.CreditTypeModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tool.util.StringUtil;

import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.creditrank.cr.model.CreditRatingModel;
import com.xindaibao.creditrank.cr.service.CreditTypeService;

 /**
 * 额度类型管理Controller
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-18 16:43:13


 * 

 */
@Controller
@Scope("prototype")
public class CreditTypeController extends BaseController {

	@Resource
	private CreditTypeService creditTypeService;
	

	/**
	 * 查询额度列表
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/cr/creditType/creditTypeList.htm")
	public void creditTypeList() throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		List<CreditTypeModel> list = creditTypeService.findAllCreditType();
		result.put(Constant.RESPONSE_DATA, list);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 保存额度类型
	 * @param cardId
	 * @param rankId
	 * @param borrowId
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/cr/creditType/saveCreditType.htm")
	public void saveCreditType(@RequestParam("cardId")String cardId,
			@RequestParam("rankId")String rankId,
			@RequestParam("borrowTypeId")String borrowTypeId,
			@RequestParam("creditTypeId")Long creditTypeId,
			@RequestParam("name")String name) throws Exception{
		Map<String,Object> result = new HashMap<String,Object>();
		int rtValue = 0;
		
		if(StringUtil.isNotBlank(cardId) 
				&& StringUtil.isNotBlank(rankId) 
				&& StringUtil.isNotBlank(borrowTypeId)
				&& StringUtil.isNotBlank(creditTypeId)
				&& StringUtil.isNotBlank(name)){
			//重复校验
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("creditTypeId", creditTypeId);
			List<CreditType> typeList = creditTypeService.findCreditType(params);
			if(typeList != null && !typeList.isEmpty()){
				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "保存失败,额度类型已经存在");
				ServletUtils.writeToResponse(response,result);
				return;
			}
			CreditType type = new CreditType();
			type.setCreditTypeId(creditTypeId);
			type.setCardId(cardId);
			type.setRankId(rankId);
			type.setBorrowTypeId(borrowTypeId);
			type.setAddTime(new Date());
			type.setName(name);
			rtValue = creditTypeService.insert(type);
		}
		if(rtValue>0){
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		}else{
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "保存失败");
		}
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 更新额度类型
	 * @param id
	 * @param cardId
	 * @param rankId
	 * @param borrowId
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/cr/creditType/updateCreditType.htm")
	public void updateCreditType(@RequestParam("id")Long id,
			@RequestParam("cardId")String cardId,
			@RequestParam("rankId")String rankId,
			@RequestParam("borrowTypeId")String borrowTypeId,
			@RequestParam("creditTypeId")Long creditTypeId,
			@RequestParam("name")String name) throws Exception{
		int rtValue = 0;
		id = 1L;//目前只支持贷前评分
		if(id!=null	&& StringUtil.isNotBlank(cardId) 
				&& StringUtil.isNotBlank(rankId) 
				&& StringUtil.isNotBlank(borrowTypeId)
				&& StringUtil.isNotBlank(creditTypeId)
				&& StringUtil.isNotBlank(name)){
			CreditType type = new CreditType();
			type.setId(id);
			type.setCreditTypeId(creditTypeId);
			type.setCardId(cardId);
			type.setRankId(rankId);
			type.setBorrowTypeId(borrowTypeId);
			type.setName(name);
			rtValue = creditTypeService.updateById(type);
		}
		Map<String,Object> result = new HashMap<String,Object>();
		if(rtValue>0){
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "更新成功");
		}else{
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "更新失败");
		}
		ServletUtils.writeToResponse(response,result);
	}
	
	@RequestMapping(value = "/modules/manage/cr/creditType/findDetail.htm")
	public void findDetail(@RequestParam("id")Long id) throws Exception {
		CreditType creditType = creditTypeService.getById(id);
		CreditTypeModel info = creditTypeService.findCreditTypeInfo(creditType);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, info);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 额度类型编辑页面可以显示的借款类型
	 * @param id 额度类型ID
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/cr/creditType/editBorrowType.htm")
	public void editBorrowType(@RequestParam("id")Long id) throws Exception {
		List<CreditRatingModel> list = creditTypeService.findEditBorrowType(id);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, list);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 查询未被额度类型关联的借款类型
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/cr/creditType/findUnUsedBorrowType.htm")
	public void findUnUsedBorrowType() throws Exception {
		List<Map<Long,String>> borrowTypes = creditTypeService.findUnUsedBorrowType();
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, borrowTypes);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
}
