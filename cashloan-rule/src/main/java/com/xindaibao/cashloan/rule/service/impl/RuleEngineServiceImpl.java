package com.xindaibao.cashloan.rule.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.StringUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.rule.domain.RuleEngine;
import com.xindaibao.cashloan.rule.domain.RuleEngineConfig;
import com.xindaibao.cashloan.rule.domain.RuleEngineInfo;
import com.xindaibao.cashloan.rule.mapper.RuleEngineConfigMapper;
import com.xindaibao.cashloan.rule.mapper.RuleEngineInfoMapper;
import com.xindaibao.cashloan.rule.mapper.RuleEngineMapper;
import com.xindaibao.cashloan.rule.service.RuleEngineService;


/**
 * 规则引擎管理ServiceImpl
 */
 
@Service("ruleEngineService")
public class RuleEngineServiceImpl extends BaseServiceImpl<RuleEngine, Long> implements RuleEngineService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(RuleEngineServiceImpl.class);
	@Resource
	private RuleEngineConfigMapper ruleEngineConfigMapper;
    @Resource
    private RuleEngineMapper ruleEngineMapper;
    @Resource
    private RuleEngineInfoMapper ruleEngineInfoMapper;
 
	@Override
	public BaseMapper<RuleEngine, Long> getMapper() {
		
		return ruleEngineMapper;
	}
	/**
	 * 分页查询
	 */
	@Override
	public Page<RuleEngine> findListByPage(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<RuleEngine> list = ruleEngineMapper.listByPage(params);
		return (Page<RuleEngine>)list;
	}
	/**
	 * 编辑修改
	 */
	@Override
	public int saveOrUpate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int resCode;
 		if(StringUtil.isNotBlank(map.get("id"))){
			resCode=ruleEngineMapper.updateSelective(map);
		}else{
			RuleEngine engine=new RuleEngine();
			engine.setAddTime(new Date());
			engine.setAddIp(String.valueOf(map.get("addIp")));
			engine.setName(String.valueOf(map.get("name")));
			engine.setState(String.valueOf(map.get("state")));
			engine.setConfigCount((Integer) map.get("configCount"));
			resCode=ruleEngineMapper.save(engine);
		}
		return resCode;
	}
	/**
	 * 获取单条信息
	 */
	@Override
	public RuleEngine findById(Long id) {
		// TODO Auto-generated method stub
		return ruleEngineMapper.findByPrimary(id);
	}
	/**
	 * 修改
	 */
	@Override
	public int updateByRule(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("ruleEnginId",  map.get("id"));
		paramMap.put("state", map.get("state"));
		ruleEngineConfigMapper.updateByRuleEnginId(paramMap);
		
		return ruleEngineMapper.updateSelective(map);
	}
	/**
	 * 查询
	 */
	@Override
	public List<RuleEngine> selectList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return ruleEngineMapper.listSelective(params);
	}
	@Override
	public List<Map<String, Object>> findAllRule(Map<String, Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		List<RuleEngine> allRule=ruleEngineMapper.listSelective(params);
		for(RuleEngine rule:allRule){
			Map<String,Object> search = new HashMap<String,Object>();
			search.put("ruleEnginId",rule.getId());
			List<RuleEngineConfig> configs=ruleEngineConfigMapper.listSelective(search);
			List<RuleEngineInfo> reulst=ruleEngineInfoMapper.listSelective(search);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("rule", rule);
			map.put("configList", configs);
			map.put("infoList", reulst);
			
		    list.add(map);
		}
		return list;
	}
 
	
}