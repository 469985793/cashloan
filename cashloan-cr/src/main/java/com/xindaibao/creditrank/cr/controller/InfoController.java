package com.xindaibao.creditrank.cr.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.creditrank.cr.domain.Info;
import com.xindaibao.creditrank.cr.model.InfoModel;
import com.xindaibao.creditrank.cr.model.RuleInfoDetail;
import com.xindaibao.creditrank.cr.service.InfoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tool.util.StringUtil;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;


/**
 * 评分关联表Controller
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-04 15:05:09


 * 

 */
@Scope("prototype")
@Controller
public class InfoController extends BaseController {

	@Resource
	private InfoService infoService;
	
	/**
	 * 查询评分表关联列表
	 * @param secreditrankh
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/cr/info/page.htm", method=RequestMethod.POST)
	public void page(
			@RequestParam(value="search",required=false) String secreditrankh,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> secreditrankhMap = JsonUtil.parse(secreditrankh, Map.class);
		Page<InfoModel> page = infoService.page(secreditrankhMap,current, pageSize);
		for (InfoModel infoModel : page) {
			JSONArray arr = JSONArray.parseArray(infoModel.getDetail());
			infoModel.setChildren(arr);
		}
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 新增评分表关联
	 * @param tbNid
	 * @param tbName
	 * @param detail
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/cr/info/save.htm", method=RequestMethod.POST)
	public void save(
			@RequestParam(value = "tbNid") String tbNid,
			@RequestParam(value = "tbName") String tbName,
			@RequestParam(value = "detail") String detail) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		Info info = new Info();
		info = infoService.findByTbNid(tbNid);
		if (StringUtil.isBlank(info)) {
			int msg = infoService.save(tbNid,tbName,detail);
			if (msg<0) {
				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "新增失败");
			}else {
				result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "新增成功");
			}
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "记录已存在");
		}
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 修改评分表关联
	 * @param id
	 * @param tbNid
	 * @param tbName
	 * @param detail
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/cr/info/update.htm", method=RequestMethod.POST)
	public void update(
			@RequestParam(value = "id") long id,
			@RequestParam(value = "tbNid") String tbNid,
			@RequestParam(value = "tbName") String tbName,
			@RequestParam(value = "detail") String detail) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> updateMap = new HashMap<String,Object>();
		updateMap.put("id", id);
		updateMap.put("tbNid", tbNid);
		updateMap.put("tbName", tbName);
		updateMap.put("detail", detail);
		int msg = infoService.updateSelective(updateMap);
		if (msg>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "修改成功");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "修改失败");
		}
		
		ServletUtils.writeToResponse(response,result);
	}

	/**
	 * 修改评分表关联状态
	 * @param id
	 * @param tbNid
	 * @param tbName
	 * @param detail
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/cr/info/updateState.htm", method=RequestMethod.POST)
	public void updateState(
			@RequestParam(value = "id") long id,
			@RequestParam(value = "state") String state) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> updateMap = new HashMap<String,Object>();
		updateMap.put("id", id);
		updateMap.put("state", state);
		int msg = infoService.updateSelective(updateMap);
		if (msg>0) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "修改成功");
		}else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "修改失败");
		}
		
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 查询评分表关联列表
	 * @param secreditrankh
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/cr/info/infoPage.htm", method=RequestMethod.POST)
	public void infoPage(
			@RequestParam(value="secreditrankh",required=false) String secreditrankh,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> secreditrankhMap = JsonUtil.parse(secreditrankh, Map.class);
		Page<InfoModel> page = infoService.page(secreditrankhMap,current, pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	
	
	/**
	 * 查询所有数据库中表和字段 
	 */
	@RequestMapping(value="/modules/manage/cr/info/findAllDataTable.htm", method=RequestMethod.POST)
	public void findAllDataTable(){
		List<Map<String, Object>> data=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list=infoService.findTable();
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String, Object>> columnList=infoService.findColumnByName(map);
		if(list!=null){
			List<Info> infolist = infoService.listSelective(map);
		  for(int i=0;i<list.size();i++){
			  Map<String, Object> result=list.get(i);
			  //是否已选中
			  result.put("checked", checkTable(infolist,(String)result.get("tableName")));
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
	                		  }else{
	                			  if(columnComment.length()>10){
		                			  columnComment=columnComment.substring(0,11); 
		                		  } 
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
                	  childrenmap.put("checked", checkColumn(infolist,(String)column.get("tableName"),(String)column.get("columnName")));;
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
	
	public boolean checkTable(List<Info> list,String table) {
		for(Info info:list){
			if(info.getTbNid().equals(table)){
			   return true;	
			}
		}
		return false;
	}
	
	public boolean checkColumn(List<Info> list ,String table,String column) {
		for(Info info:list){
			if(info.getTbNid().equals(table)){
				List<RuleInfoDetail> rules = JSONArray.parseArray(info.getDetail(),RuleInfoDetail.class);
				for(RuleInfoDetail d:rules){
					if(d.getNid().equals(column)){
					   return true;	
					}
				}
			}
		}
		return false;
	}
}
