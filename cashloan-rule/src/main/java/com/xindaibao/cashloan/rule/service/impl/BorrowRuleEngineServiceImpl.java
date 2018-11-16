package com.xindaibao.cashloan.rule.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.rule.domain.BorrowRuleConfig;
import com.xindaibao.cashloan.rule.domain.BorrowRuleEngine;
import com.xindaibao.cashloan.rule.mapper.BorrowRuleConfigMapper;
import com.xindaibao.cashloan.rule.mapper.BorrowRuleEngineMapper;
import com.xindaibao.cashloan.rule.service.BorrowRuleEngineService;


/**
 * 借款规则管理ServiceImpl
 */
 
@Service("borrowRuleEngineService")
public class BorrowRuleEngineServiceImpl extends BaseServiceImpl<BorrowRuleEngine, Long> implements BorrowRuleEngineService {
	
    private static final Logger logger = LoggerFactory.getLogger(BorrowRuleEngineServiceImpl.class);
   
    @Resource
    private BorrowRuleEngineMapper borrowRuleEngineMapper;
    @Resource
    private BorrowRuleConfigMapper borrowRuleConfigMapper;




	@Override
	public BaseMapper<BorrowRuleEngine, Long> getMapper() {
		return borrowRuleEngineMapper;
	}




	@Override
	public Page<BorrowRuleEngine> page(Map<String, Object> params, int currentPage,
			int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<BorrowRuleEngine> list = borrowRuleEngineMapper.listSelective(params);
		return (Page<BorrowRuleEngine>)list;
	}
	
	@Override
	public int insert(BorrowRuleEngine bre){
		return borrowRuleEngineMapper.save(bre);
	}

	@Override
	public int updateSelective(Map<String, Object> params) {
		return borrowRuleEngineMapper.updateSelective(params);
	}


	public int deleteById(long id) {
		int i= borrowRuleEngineMapper.deleteById(id);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("borrowRuleId", id);
	    i=borrowRuleConfigMapper.deleteByBorrowRuleId(params);
		return i;
	}

	@Override
	public int update(BorrowRuleEngine bre) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", bre.getId());
		params.put("adaptedId", bre.getAdaptedId());
		params.put("adaptedName", bre.getAdaptedName());
		params.put("borrowType", bre.getBorrowType());
		params.put("borrowTypeName", bre.getBorrowTypeName());
		return borrowRuleEngineMapper.updateSelective(params);
	}
	@Override
	public int update(BorrowRuleEngine brc, List<BorrowRuleConfig> configlist) {
		// TODO Auto-generated method stub
	    brc.setAdaptedName(brc.getAdaptedNameById(brc.getAdaptedId()));
		int m=0;
		brc.setRuleCount(configlist != null ? configlist.size() : 0);
		if(brc.getId()!=null){
			Map<String, Object> params = new HashMap<String, Object>();
			params=changeObject(brc);
			 m = borrowRuleEngineMapper.updateSelective(params);
		}else{
			brc.setAddTime(new Date());
			brc.setReqExt("");
			 m = borrowRuleEngineMapper.save(brc);
		}
		if(configlist!=null){
			String ids=";";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("borrowRuleId", brc.getId());
			List<BorrowRuleConfig> oldList= borrowRuleConfigMapper.listSelective(params);
			
			for (BorrowRuleConfig c : configlist) {
				if(c.getId()!=null&&c.getId()!=0){
					ids=ids+c.getId()+";";
				    params = new HashMap<String, Object>();
					params=changeObject(c);
					m = borrowRuleConfigMapper.updateSelective(params);
				}else{
					c.setBorrowRuleId(brc.getId());
					m = borrowRuleConfigMapper.save(c);	
				}
			}
	
			if(oldList!=null&&oldList.size()>0){
				for (BorrowRuleConfig c : oldList) {
					if(!ids.contains(";"+String.valueOf(c.getId()+";"))){
						//logger.info("存在已删除的id"+c.getId());
						borrowRuleConfigMapper.deleteById(c.getId());
					}
				}
			}
	 	}else{
			//如果没有传入子集就将已有数据的删除
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("borrowRuleId", brc.getId());
			borrowRuleConfigMapper.deleteByBorrowRuleId(params);
		}
		return m;
	}
 
	public Map<String, Object> changeObject(Object c){
		String str=JSONObject.toJSONString(c);
		Map<String, Object> params = JsonUtil.parse(str, Map.class);
		return params;
	}
	
	
}