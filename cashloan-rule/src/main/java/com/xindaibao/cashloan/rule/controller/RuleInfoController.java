package com.xindaibao.cashloan.rule.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tool.util.StringUtil;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;
import com.xindaibao.cashloan.rule.domain.RuleInfo;
import com.xindaibao.cashloan.rule.model.RuleInfoDetail;
import com.xindaibao.cashloan.rule.service.RuleEngineConfigService;
import com.xindaibao.cashloan.rule.service.RuleInfoService;
import com.xindaibao.cashloan.system.permission.annotation.RequiresPermission;

 /**
 * 规则信息Controller
 */
@Controller
@Scope("prototype")
public class RuleInfoController extends BaseController {

	@Resource
	private RuleInfoService ruleInfoService;
	@Resource
	private RuleEngineConfigService ruleEngineConfigService;
		
	/**
	 * 借款规则列表
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/modules/manage/rule/ruleList.htm")
	@RequiresPermission(code = "modules:manage:rule:ruleList",name = "查询借款规则列表")
	public void ruleList(@RequestParam(value="search",required=false) String search,
			@RequestParam(value = "current") int currentPage,
			@RequestParam(value = "pageSize") int pageSize){
		Map<String, Object> params = JsonUtil.parse(search, Map.class);
		Page<RuleInfo> page = ruleInfoService.ruleList(params,currentPage,pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}

	/**
	 * 添加规则信息
	 * @param detail
	 * @param tbNid
	 * @param tbName
	 */
	@RequestMapping(value="/modules/manage/rule/addRuleInfo.htm")
	@RequiresPermission(code = "modules:manage:rule:addRuleInfo",name = "添加规则配置信息")
	public void addRuleInfo(@RequestParam(value="detail") String details,
			@RequestParam(value = "tbNid") String tbNid,
			@RequestParam(value = "tbName") String tbName){
		List<RuleInfoDetail> rules = JSONArray.parseArray(details,RuleInfoDetail.class);
		RuleInfoDetail detail = new RuleInfoDetail();
		Map<String,Object> result = new HashMap<String,Object>();
		if(detail.validData(rules)){
			RuleInfo ruleInfo = new RuleInfo();
			ruleInfo.setTbName(tbName);
			ruleInfo.setTbNid(tbNid);
			ruleInfo.setDetail(details);
			ruleInfo.setAddTime(new Date());
 			ruleInfo.setState("10");
			ruleInfoService.insert(ruleInfo);
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "添加成功");
		}else{
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "添加失败，参数错误");
		}
		
		ServletUtils.writeToResponse(response,result);
	}
	/**
	 * 查询规则信息
	 * @param detail
	 * @param tbNid
	 * @param tbName
	 * @param id
	 */
	@RequestMapping(value="/modules/manage/rule/getRuleInfo.htm")
	@RequiresPermission(code = "modules:manage:rule:getRuleInfo",name = "查询规则配置信息")
	public void getRuleInfo(@RequestParam(value = "id") Long id){
		RuleInfo ruleInfo = ruleInfoService.getById(id);
		Map<String,Object> data = new HashMap<String,Object>();
		if(ruleInfo!=null){
			data.put("id", ruleInfo.getId());
			data.put("tbNid", ruleInfo.getTbNid());
			data.put("tbName", ruleInfo.getTbName());
			data.put("detail", ruleInfo.getDetail());
			if(StringUtil.isNotBlank(ruleInfo.getDetail())){
				List<RuleInfoDetail> rules = JSONArray.parseArray(ruleInfo.getDetail(),RuleInfoDetail.class);
				if (rules != null && !rules.isEmpty()) {
					data.put("detail", rules);
				}
			}
		}
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		result.put(Constant.RESPONSE_DATA, data);
		ServletUtils.writeToResponse(response,result);
	}
	/**
	 * 修改规则信息
	 * @param detail
	 * @param tbNid
	 * @param tbName
	 * @param id
	 */
	@RequestMapping(value="/modules/manage/rule/modifyRuleInfo.htm")
	@RequiresPermission(code = "modules:manage:rule:modifyRuleInfo",name = "修改规则配置信息")
	public void modifyRuleInfo(@RequestParam(value="detail") String detail,
			@RequestParam(value = "id") Long id){
		RuleInfo ruleInfo = ruleInfoService.getById(id);
		ruleInfo.setDetail(detail);
		ruleInfoService.updateById(ruleInfo);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "修改成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 查询所有数据库中表和字段
	 */
	@RequestMapping(value="/modules/manage/rule/findAllDataTable.htm")
	@RequiresPermission(code = "modules:manage:rule:findAllDataTable",name = "查询数据中所有表和字段")
	public void findAllDataTable(){
			List<Map<String, Object>> data=new ArrayList<Map<String, Object>>();
			Map<String,Object> paramMap = new HashMap<String,Object>();
			if(StringUtil.isNotEmpty(Global.getValue("rule_tables"))){
				List<String> tableNames=new ArrayList<String>(Arrays.asList(Global.getValue("rule_tables").split(",")));
				paramMap.put("tableNames",tableNames);
			}			
			List<Map<String, Object>> list=ruleEngineConfigService.findTableByName(paramMap);
//			List<Map<String, Object>> list=ruleEngineConfigService.findTable();
			Map<String,Object> map = new HashMap<String,Object>();
			List<Map<String, Object>> columnList=ruleEngineConfigService.findColumnByName(map);
			if(list!=null){
				List<RuleInfo> infolist = ruleInfoService.findAll(map);
			  for(int i=0;i<list.size();i++){
				  Map<String, Object> result=list.get(i);
				  //是否已选中
				  result.put("checked", ruleInfoService.checkTable(infolist,(String)result.get("tableName")));
				  List<Map<String, Object>> children=new ArrayList<Map<String, Object>>();
				  if(columnList!=null){
				   for(int j=0;j<columnList.size();j++){
					  Map<String, Object> childrenmap=new HashMap<String, Object>();
					  Map<String, Object> column=columnList.get(j); 
	                  if(column.get("tableName").equals(result.get("tableName"))){
	                	  childrenmap.put("columnName", column.get("columnName"));
	                	  String columnComment=((String)column.get("columnComment"));
	                	  //中文字符特殊处理
	                	  if(StringUtil.isNotBlank(columnComment)){
	                		  if(columnComment.length()>1){
		                		  int colu=columnComment.indexOf(" ");
		                		  if(colu!=-1){
		                			  columnComment=columnComment.substring(0, colu);
		                		  }
	                		  }
	                	  }
	                	  childrenmap.put("columnComment",columnComment);
	                	  if(("bigint;int;decimal;integer;tinyint;double;decimal;float;bit;smallint;mediumint;").contains((String)column.get("data_type"))){
	                		  childrenmap.put("type", "int");
	                	  }else{
	                		  childrenmap.put("type", "string");
	                	  }
	                	  //是否已选中
	                	  childrenmap.put("checked", ruleInfoService.checkColumn(infolist,(String)column.get("tableName"),(String)column.get("columnName")));;
	                	  children.add(childrenmap);  
	                  }
				   }
				  }
				  result.put("children", children);
				  data.add(result) ;
				}
			}
			Map<String,Object> result = new HashMap<String,Object>();
			result.put(Constant.RESPONSE_DATA,data);
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
			ServletUtils.writeToResponse(response,result);
		}
	
	/**
	 * 修改状态
	 * 
	 */
	@RequestMapping("/modules/manage/rule/modifyInfoState.htm")
	@RequiresPermission(code = "modules:manage:rule:modifyInfoState",name = "编辑状态")
	public void DelInfoConfig(@RequestParam(value = "id")  Long id,@RequestParam(value = "state")  String state){
		int r=ruleInfoService.modifyInfoState(id,state);
		Map<String,Object> result = new HashMap<String,Object>();
		if(r==1){
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "成功");
		}else{
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "失败");	
		}
		ServletUtils.writeToResponse(response,result);
	}
	
	
	
}
