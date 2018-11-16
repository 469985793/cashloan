package com.xindaibao.cashloan.rule.service.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.StringUtil;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.rule.domain.RuleEngineInfo;
import com.xindaibao.cashloan.rule.mapper.RuleEngineInfoMapper;
import com.xindaibao.cashloan.rule.service.RuleEngineInfoService;


/**
 * 规则评分设置管理ServiceImpl
 */
 
@Service("ruleEngineInfoService")
@SuppressWarnings({ "rawtypes", "unchecked","unused" })
public class RuleEngineInfoServiceImpl extends BaseServiceImpl<RuleEngineInfo, Long> implements RuleEngineInfoService {
	
	private static final Logger logger = LoggerFactory.getLogger(RuleEngineInfoServiceImpl.class);
   
    @Resource
    private RuleEngineInfoMapper ruleEngineInfoMapper;

	@Override
	public BaseMapper<RuleEngineInfo, Long> getMapper() {
		return ruleEngineInfoMapper;
	}
	/**
	 * 保存评分结果模式下 的结果范围   暂没有使用
	 * @param fid
	 * @param list
	 * @return
	 */
	@Override
	public int saveIntegralInfo(Map<String, Object> map, List list) {
		int resCode=0;
 		if(StringUtil.isNotBlank(map.get("id"))){
			if (list != null && !list.isEmpty()) {
	 			for(int i=0;i<list.size();i++){
					RuleEngineInfo info = new RuleEngineInfo();
	  				Map link = (LinkedHashMap) list.get(i);
	 				 for(Iterator it = link.entrySet().iterator();it.hasNext();){
						Entry<String, String> entry = (Entry<String, String>) it.next();
						if ("min".equals(entry.getKey())) {
							info.setMinIntegral(Integer.valueOf(entry.getValue()));
						}
						if (!"".equals(entry.getValue())) {
							if ("max".equals(entry.getKey())) {
								info.setMaxIntegral(Integer.valueOf(entry.getValue()));
							}
							if ("result".equals(entry.getKey())) {
								info.setResult(entry.getValue());
							}
							if ("id".equals(entry.getKey())) {
								info.setId(Long.valueOf((String) entry.getValue().trim()));
							}
						}
	 				}
 				    info.setRuleEnginId(Long.valueOf((String)map.get("id")));
	 				resCode = ruleEngineInfoMapper.insert(info);
	 			 }
 			}
 		}
		return resCode;
	}
	/**
	 * 查询
	 */
	@Override
	public List<RuleEngineInfo> findByMap(Map<String, Object> search) {
		// TODO Auto-generated method stub
		return ruleEngineInfoMapper.listSelective(search);
	}
}