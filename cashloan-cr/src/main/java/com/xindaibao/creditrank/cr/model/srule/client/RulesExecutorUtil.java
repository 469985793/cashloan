package com.xindaibao.creditrank.cr.model.srule.client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.xindaibao.creditrank.cr.model.srule.config.rule.Rule;
import com.xindaibao.creditrank.cr.model.srule.model.ScoreOrientedRule;
import com.xindaibao.creditrank.cr.model.srule.model.SimpleRule;
import com.xindaibao.creditrank.cr.model.srule.utils.GenerateRule;

/**
 * 规则执行工具类
 * @author
 * @version 1.0.0
 * @date 2017年1月3日 下午2:22:58


 * 

 */
public class RulesExecutorUtil {

	
	/**
	 * 批量规则匹配
	 * @param maps
	 * @return
	 */
	public static Map<String,List<SimpleRule>> builtResult(Map<String,List<SimpleRule>> maps){
		Iterator<Entry<String, List<SimpleRule>>> entries = maps.entrySet().iterator(); 
		Map<String,String> resultMap = new HashMap<String, String>();
	    while (entries.hasNext()) {  
	        Entry<String, List<SimpleRule>> entry = entries.next();  
	        String key = entry.getKey();
			List<SimpleRule> simpleList = entry.getValue();
			for(int i=0;i<simpleList.size();i++){
				SimpleRule simpleRule = simpleList.get(i);
				boolean result = false;
				if(SimpleRule.TEXT.equals(simpleRule.getType())){
					result = GenerateRule.genTextResult(simpleRule);
				}else if(SimpleRule.NUMERIC.equals(simpleRule.getType())){
					Rule rule  = GenerateRule.genNumRule(simpleRule);
					result = rule.beginMatch();
				}
				
				if(result){
					simpleRule.setComparResult(SimpleRule.COMPAR_PASS);
					resultMap.put(key, simpleRule.getResultType());
					if(simpleRule instanceof ScoreOrientedRule){
						ScoreOrientedRule soRule = (ScoreOrientedRule) simpleRule;
						soRule.setResultScore(soRule.getScore());
					}
					break;
				}else{
					simpleRule.setComparResult(SimpleRule.COMPAR_FAIL);
				}
				simpleList.set(i, simpleRule);
			}
			maps.put(key, simpleList);
	    } 
	    return maps;
	}
	
	/**
	 * 单条规则匹配
	 * @param simpleRule
	 * @return
	 */
	public static SimpleRule singleRuleResult(SimpleRule simpleRule){
		boolean result = false;
		if(SimpleRule.TEXT.equals(simpleRule.getType())){
			//字符类型匹配
			result = GenerateRule.genTextResult(simpleRule);
		}else if(SimpleRule.NUMERIC.equals(simpleRule.getType())){
			//生成数字类型的规则
			Rule rule = GenerateRule.genNumRule(simpleRule);
			result = rule.beginMatch();
		}
		if(result){
			simpleRule.setComparResult(SimpleRule.COMPAR_PASS);
		}else{
			simpleRule.setComparResult(SimpleRule.COMPAR_FAIL);
		}
		
		return simpleRule;
	}
	
	/**
	 * 单条规则匹配
	 * @param id 唯一标识
	 * @param name 名称
	 * @param formula 表达式
	 * @param range 范围
	 * @param value 需要做匹配的值
	 * @param type 类型 int  string
	 * @param resultType 10 不通过 20 需要人工复审	 30 通过
	 * @return
	 */
	public static SimpleRule singleRuleResult(Long id,String name,String formula,String range,String value,String type,String resultType){
		SimpleRule simpleRule = new SimpleRule(id, name, formula, value, range, type, resultType);
		return singleRuleResult(simpleRule);
	}
}
