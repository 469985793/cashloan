package com.xindaibao.creditrank.cr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xindaibao.creditrank.cr.domain.Rank;
import com.xindaibao.creditrank.cr.model.RankModel;
import com.xindaibao.creditrank.cr.service.RankService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.xindaibao.cashloan.core.common.context.Constant;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.RdPage;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.core.common.web.controller.BaseController;

/**
 * 评分等级Controller
 * 
 * @author
 * @version 1.0.0
 * @date 2017-01-04 15:09:59


 * 

 */
@Scope("prototype")
@Controller
public class RankController extends BaseController {

	@Resource
	private RankService rankService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/cr/rank/page.htm", method = RequestMethod.POST)
	public void page(
			@RequestParam(value="search",required=false) String search,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> searchMap = JsonUtil.parse(search, Map.class);
		Page<Rank> page = rankService.page(searchMap,current, pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 查询所有有效的评分等级
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/cr/rank/findAll.htm", method = RequestMethod.POST)
	public void findAll() throws Exception {
		List<Rank> page = rankService.findAll();
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 评分等级名称列表
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/cr/rank/getRankList.htm", method = RequestMethod.POST)
	public void getRankList(
			@RequestParam(value="search",required=false) String search,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> searchMap = JsonUtil.parse(search, Map.class);
		Page<RankModel> page = rankService.countList(searchMap,current,pageSize);
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 评分等级删除
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/cr/rank/delete.htm", method = RequestMethod.POST)
	public void delete(
			@RequestParam(value = "id") long id) throws Exception {
		Map<String,Object> result  = rankService.deleteSelective(id);
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 新增评分等级
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/cr/rank/save.htm", method = RequestMethod.POST)
	public void save(
			@RequestParam(value="id") long id,
			@RequestParam(value="rankName") String rankName,
			@RequestParam(value="search") String search
			) throws Exception {
		List<Map<String,Object>> list = JsonUtil.parse(search, List.class);
		Map<String,Object> result = rankService.save(list,rankName,id);
		ServletUtils.writeToResponse(response,result);
	}
	
}
