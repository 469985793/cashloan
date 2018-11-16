package com.xindaibao.cashloan.rule.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.rule.domain.RuleInfo;
import com.xindaibao.cashloan.rule.mapper.RuleInfoMapper;
import com.xindaibao.cashloan.rule.model.RuleInfoDetail;
import com.xindaibao.cashloan.rule.service.RuleInfoService;


/**
 * 规则信息ServiceImpl
 */
 
@Service("ruleInfoService")
public class RuleInfoServiceImpl extends BaseServiceImpl<RuleInfo, Long> implements RuleInfoService {
	
    private static final Logger logger = LoggerFactory.getLogger(RuleInfoServiceImpl.class);
	
    @Resource
    private RuleInfoMapper ruleInfoMapper;
 
	@Override
	public BaseMapper<RuleInfo, Long> getMapper() {
		return ruleInfoMapper;
	}
	/**
	 * 查询
	 */
	@Override
	public List<RuleInfo> findAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ruleInfoMapper.listSelective(map);
	}
	/**
	 * 分页查询
	 */
	@Override
	public Page<RuleInfo> ruleList(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<RuleInfo> list = ruleInfoMapper.listSelective(params);
		return (Page<RuleInfo>)list;
	}
	/**
	 * 检验表是否已经保存过
	 */
	@Override
	public boolean checkTable(List<RuleInfo> list,String table) {
		// TODO Auto-generated method stub
		for(RuleInfo info:list){
			if(info.getTbNid().equals(table)){
			   return true;	
			}
		}
		return false;
	}
	/**
	 * 检验表字段是否已经保存过
	 */
	@Override
	public boolean checkColumn(List<RuleInfo> list ,String table,String column) {
		// TODO Auto-generated method stub
		for(RuleInfo info:list){
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
	/**
	 * 	编辑状态
	 */
	@Override
	public int modifyInfoState(Long id, String state) {
		// TODO Auto-generated method stub
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("id", id);
		paramMap.put("state", state);
	    return  ruleInfoMapper.updateSelective(paramMap);
	}
 
}
