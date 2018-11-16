package com.xindaibao.cashloan.rule.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.rule.domain.BorrowRuleConfig;
import com.xindaibao.cashloan.rule.mapper.BorrowRuleConfigMapper;
import com.xindaibao.cashloan.rule.model.BorrowRuleConfigModel;
import com.xindaibao.cashloan.rule.service.BorrowRuleConfigService;


/**
 * 借款规则详细配置表ServiceImpl
 */
 
@Service("borrowRuleConfigService")
public class BorrowRuleConfigServiceImpl extends BaseServiceImpl<BorrowRuleConfig, Long> implements BorrowRuleConfigService {
	
    private static final Logger logger = LoggerFactory.getLogger(BorrowRuleConfigServiceImpl.class);
   
    @Resource
    private BorrowRuleConfigMapper borrowRuleConfigMapper;
 

	@Override
	public BaseMapper<BorrowRuleConfig, Long> getMapper() {
		return borrowRuleConfigMapper;
	}




	@Override
	public List<BorrowRuleConfigModel> findConfig(Map<String, Object> params) {
		// TODO Auto-generated method stub
		List<BorrowRuleConfig> list = borrowRuleConfigMapper.listSelective(params);
		List<BorrowRuleConfigModel> result = new ArrayList<BorrowRuleConfigModel> ();
		String ruleIds=";";
		for(BorrowRuleConfig config:list){
			BorrowRuleConfigModel model = new BorrowRuleConfigModel();
			BorrowRuleConfig rule = new BorrowRuleConfig();
			rule.setRuleId(config.getRuleId());
			rule.setRuleSort(config.getRuleSort());
			model.setRule(rule);
		   if(!ruleIds.contains(";"+String.valueOf(model.getRule().getRuleId())+";")){
				result.add(model);
				ruleIds=ruleIds+config.getRuleId()+";";
			}
		}
		if (result.size() > 0) {
			for (int i=0;i<result.size();i++) {
				List<BorrowRuleConfig> configList =new ArrayList<BorrowRuleConfig> ();
				for (BorrowRuleConfig config : list) {
					 if(result.get(i).getRule().getRuleId().equals(config.getRuleId())){
						//BorrowRuleConfigModel model = new BorrowRuleConfigModel();
						BorrowRuleConfig c = new BorrowRuleConfig();
						c.setConfigId(config.getConfigId());
						c.setConfigSort(config.getConfigSort());
						c.setId(config.getId());
						 if(!configList.contains(c)){
								configList.add(c);
							}
					
					 }
				}
				result.get(i).setConfigList(configList);
			}
		}
		return result;
	}




	@Override
	public void deleteByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		borrowRuleConfigMapper.deleteByMap(map);
	}

	@Override
	public List<BorrowRuleConfig> findBorrowRuleId(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return borrowRuleConfigMapper.findBorrowRuleId(paramMap);
	}
	
}