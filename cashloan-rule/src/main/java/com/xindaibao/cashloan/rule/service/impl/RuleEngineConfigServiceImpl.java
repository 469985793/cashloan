package com.xindaibao.cashloan.rule.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.StringUtil;

import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.JsonUtil;
import com.xindaibao.cashloan.core.common.util.ServletUtils;
import com.xindaibao.cashloan.rule.domain.BorrowRuleConfig;
import com.xindaibao.cashloan.rule.domain.RuleEngine;
import com.xindaibao.cashloan.rule.domain.RuleEngineConfig;
import com.xindaibao.cashloan.rule.domain.RuleEngineInfo;
import com.xindaibao.cashloan.rule.domain.RuleInfo;
import com.xindaibao.cashloan.rule.mapper.BorrowRuleConfigMapper;
import com.xindaibao.cashloan.rule.mapper.BorrowRuleEngineMapper;
import com.xindaibao.cashloan.rule.mapper.RuleEngineConfigMapper;
import com.xindaibao.cashloan.rule.mapper.RuleEngineInfoMapper;
import com.xindaibao.cashloan.rule.mapper.RuleEngineMapper;
import com.xindaibao.cashloan.rule.mapper.RuleInfoMapper;
import com.xindaibao.cashloan.rule.service.RuleEngineConfigService;

/**
 * 规则引擎管理ServiceImpl
 */

@Service("ruleEngineConfigService")
@SuppressWarnings({ "rawtypes", "unchecked","unused" })
public class RuleEngineConfigServiceImpl extends
		BaseServiceImpl<RuleEngineConfig, Long> implements
		RuleEngineConfigService {

	private static final Logger logger = LoggerFactory
			.getLogger(RuleEngineConfigServiceImpl.class);

	@Resource
	private RuleEngineConfigMapper ruleEngineConfigMapper;

	@Resource
	private RuleEngineMapper ruleEngineMapper;

	@Resource
	private RuleInfoMapper ruleInfoMapper;

	@Resource
	private RuleEngineInfoMapper ruleEngineInfoMapper;
	
	@Resource
	private BorrowRuleConfigMapper borrowRuleConfigMapper;
	
	@Resource
	private BorrowRuleEngineMapper borrowRuleEngineMapper;
	@Override
	public BaseMapper<RuleEngineConfig, Long> getMapper() {
		return ruleEngineConfigMapper;
	}
	/**
	 * 查询规则配置关联信息
	 */
	@Override
	public List<RuleEngineConfig> findByMap(Map<String, Object> search) {
		// TODO Auto-generated method stub
		return ruleEngineConfigMapper.listSelective(search);
	}
    /**
     * 查询数据表信息
     */
	@Override
	public List<Map<String, Object>> findTable() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = ruleEngineConfigMapper.findTable();
		return list;
	}
	
	@Override
	public List<Map<String, Object>> findTableByName(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = ruleEngineConfigMapper.findTableByName(paramMap);
		return list;
	}
	
	/**
	 * 查询数据库表字段信息
	 */
	@Override
	public List<Map<String, Object>> findColumnByName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = ruleEngineConfigMapper.findColumnByName(map);
		return list;
	}
	/**
	 * 编辑修改配置信息
	 */
	@Override
	public int updateSelective(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ruleEngineConfigMapper.updateSelective(map);
	}
	/**
	 * 整合表和字段关系集合
	 */
	public List<Map<String, Object>> findAllInfo(Map<String, Object> map) {
		List<RuleInfo> list = ruleInfoMapper.listSelective(map);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				RuleInfo rule = list.get(i);
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("tableName", rule.getTbNid());
				result.put("tableComment", rule.getTbName());
				List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
				if (rule.getDetail() != null) {
					List childrenlist = JsonUtil.parse(rule.getDetail(),
							List.class);
					for (int j = 0; j < childrenlist.size(); j++) {
						Map<String, Object> childrenMap = new HashMap<String, Object>();
						Map link = (LinkedHashMap) childrenlist.get(j);
						for (Iterator it = link.entrySet().iterator(); it
								.hasNext();) {
							Entry<String, String> entry = (Entry<String, String>) it
									.next();
							if (!"".equals(entry.getValue())) {
								if ("nid".equals(entry.getKey())) {
									childrenMap.put("columnName",
											entry.getValue());
								}
								if ("name".equals(entry.getKey())) {
									childrenMap.put("columnComment",
											entry.getValue());
								}
								if ("type".equals(entry.getKey())) {
									childrenMap.put("type", entry.getValue());
								}
							}
						}
						children.add(childrenMap);

					}
					result.put("children", children);
					data.add(result);
				}
			}
		}
		return data;
	}
	/*
	 * 规则设置保存信息 分值结果模式
	 */
	@Override
	public int saveOrUpate(Map<String, Object> map, List list, String datalist,
			HttpServletRequest request) {
		int resCode;
		RuleEngine rule = new RuleEngine();
		// 修改规则表
		if (StringUtil.isNotBlank(map.get("id"))) {
			// logger.info("==update rule=="+map.get("name"));
			rule.setId(Long.valueOf(map.get("id").toString()));
			if(!RuleEngine.TYPE_RESULT_ENABLE.equals(String.valueOf(map.get("typeResultStatus")))){
				map.put("typeResultStatus",RuleEngine.TYPE_RESULT_DISABLE);
			}
			map.put("configCount", list != null ? list.size() : 0);
			resCode = ruleEngineMapper.updateSelective(map);
		} else {
			// logger.info("==app rule=="+map.get("name"));
			// 新增规则表
			rule.setAddTime(new Date());
			rule.setAddIp(ServletUtils.getIpAddress(request));
			rule.setState("10");
			rule.setName(String.valueOf(map.get("name")));
			rule.setIntegral((Integer) map.get("integral"));
			rule.setType(String.valueOf(map.get("type")));
			rule.setTypeResultStatus(String.valueOf(map.get("typeResultStatus")));
			if(!RuleEngine.TYPE_RESULT_ENABLE.equals(rule.getTypeResultStatus())){
				rule.setTypeResultStatus(RuleEngine.TYPE_RESULT_DISABLE);
			}
			rule.setAddIp(ServletUtils.getIpAddress(request));
			rule.setConfigCount(list != null ? list.size() : 0);
			resCode = ruleEngineMapper.insertId(rule);
			// logger.info("==fid=="+rule.getId());
		}
		// 保存评分模式 分值范围定义的结果信息
		if (datalist != null&&!"[{}]".equals(datalist)) {
			List infolist = JsonUtil.parse(datalist, List.class);
			resCode = saveIntegralInfo(rule.getId(), infolist);
		}
		// 保存规则设置信息
		if (list != null && list.size() > 0) {
			resCode = saveConfig(list, rule, request);
		}
		return resCode;
	}

	
	public int saveConfig(List list, RuleEngine rule, HttpServletRequest request) {
		int resCode = 0;
		if(list!=null&&list.size()>0){
			//查询原来规则的配置信息	
			Map<String, Object> paramMap=new HashMap<String, Object>();
			paramMap.put("ruleEnginId",rule.getId());
			List<RuleEngineConfig> oldconfigs=ruleEngineConfigMapper.listSelective(paramMap);
			String olds=";";
		
			//查询借款规则是否相关rule的信息
			paramMap=new HashMap<String, Object>();
			paramMap.put("ruleId",rule.getId());
			List<BorrowRuleConfig> borrowRuleConfig = borrowRuleConfigMapper.findBorrowRuleId(paramMap);
			boolean flag=false;
			if(borrowRuleConfig!=null&&borrowRuleConfig.size()>0){
				flag=true;
			}
			int add=0;
			int plus=0;
			for (int i = 0; i < list.size(); i++) {
				RuleEngineConfig config = new RuleEngineConfig();
				Map link = (LinkedHashMap) list.get(i);
				for (Iterator it = link.entrySet().iterator(); it.hasNext();) {
					Entry<String, Object> entry = (Entry<String, Object>) it.next();
					if (!"".equals(entry.getValue())) {
						// logger.info(entry.getKey() + "\t" + entry.getValue());
						if ("id".equals(entry.getKey())) {
							config.setId(Long.valueOf(String.valueOf(entry.getValue())));
						}
						if ("ctable".equals(entry.getKey())) {
							config.setCtable(StringUtil.isNull(entry.getValue()));
						}
						if ("ccolumn".equals(entry.getKey())) {
							config.setCcolumn(StringUtil.isNull(entry.getValue()));
						}
						if ("tableComment".equals(entry.getKey())) {
							config.setTableComment(StringUtil.isNull(entry.getValue()));
						}
						if ("columnComment".equals(entry.getKey())) {
							config.setColumnComment(StringUtil.isNull(entry.getValue()));
						}
						if ("formula".equals(entry.getKey())) {
							config.setFormula(StringUtil.isNull(entry.getValue()));
						}
						if ("cvalue".equals(entry.getKey())) {
							String cvalue = StringUtil.isNull(entry.getValue());
							config.setCvalue(cvalue.replaceAll("，",","));
						}
						if ("type".equals(entry.getKey())) {
							config.setType(StringUtil.isNull(entry.getValue()));
						}
						if ("integral".equals(entry.getKey())) {
							config.setIntegral(Integer.valueOf(String.valueOf(entry.getValue())));
						}
						if ("result".equals(entry.getKey())) {
							config.setResult(StringUtil.isNull(entry.getValue()));
						}
						if ("resultType".equals(entry.getKey())) {
							config.setResultType(StringUtil.isNull(entry.getValue()));
						}
					}
				}
				// logger.info("==add config==");
				// 新增设置信息
				// 前端如果不传值，查询数据保存
				if (config.getType() == "" || config.getType() == null) {
					Map<String, Object> info = new HashMap<String, Object>();
					List<Map<String, Object>> infos = findAllInfo(info);
					if (infos != null) {
						for (int k = 0; k < infos.size(); k++) {
							Map<String, Object> table = infos.get(k);
							if (table.get("tableName").equals(config.getCtable())) {
								config.setTableComment((String) table.get("tableComment"));
								List<Map<String, Object>> children = (List<Map<String, Object>>) table.get("children");
								if (children != null) {
									for (Map<String, Object> child : children) {
										if (child.get("columnName").equals(config.getCcolumn())) {
											config.setColumnComment((String) child.get("columnComment"));
											config.setType((String) child.get("type"));
											break;
										}
									}
								}
							}
						}
					}
				}
				if(config.getId()!=null&&config.getId()!=0){
					olds=olds+config.getId()+";";
					Map<String, Object> params = JsonUtil.parse(JsonUtil.toString(config), Map.class);
					resCode = ruleEngineConfigMapper.updateSelective(params);
				}else{
					config.setAddTime(new Date());
					config.setState("10");
					config.setAddIp(ServletUtils.getIpAddress(request));
					config.setRuleEnginId(rule.getId());
					resCode = ruleEngineConfigMapper.insert(config);
					add++;
					//如果规则中有相对的规则，新增规则配置信息
					if(flag){
						for(BorrowRuleConfig br:borrowRuleConfig){
							BorrowRuleConfig bc=new BorrowRuleConfig();
							bc.setBorrowRuleId(br.getBorrowRuleId());
							bc.setRuleId(rule.getId());
							bc.setConfigId(config.getId());
							bc.setRuleSort(br.getRuleSort());
							bc.setConfigSort(0);
							borrowRuleConfigMapper.save(bc);
						}	
					}
				}
			}
			
			for(RuleEngineConfig c:oldconfigs){
				if(!olds.contains(";"+String.valueOf(c.getId())+";")){
					ruleEngineConfigMapper.deleteById(c.getId());
					//删除相关联的借款规则配置
					paramMap=new HashMap<String, Object>();
					paramMap.put("ruleId",rule.getId());
					paramMap.put("configId",c.getId());
					borrowRuleConfigMapper.deleteByMap(paramMap);
					plus++;
				}	
			}

			if(flag){
				for(BorrowRuleConfig br:borrowRuleConfig){
					int count=br.getConfigSort()+add-plus;
					if(count>0){
						paramMap=new HashMap<String, Object>();
						paramMap.put("id",br.getBorrowRuleId());
						paramMap.put("ruleCount",count);//ruleCount赋值到ConfigSort 
						borrowRuleEngineMapper.updateSelective(paramMap);
					}else{
						borrowRuleEngineMapper.deleteById(br.getBorrowRuleId());
					}
				}
			}
		}else{
			//没有配置任何数据
			ruleEngineConfigMapper.deleteByRuleId(rule.getId());
			//删除相关联的借款规则配置
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ruleId",rule.getId());
			borrowRuleConfigMapper.deleteByMap(paramMap);
		}
		return resCode;

	}
	/**
	 * 保存评分结果模式下 的结果范围
	 * @param fid
	 * @param list
	 * @return
	 */
	public int saveIntegralInfo(long fid, List list) {
		// TODO Auto-generated method stub
		int resCode = 0;
		if (StringUtil.isNotBlank(fid)) {
			ruleEngineInfoMapper.deleteInfoByRuleId(fid);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					RuleEngineInfo info = new RuleEngineInfo();
					Map link = (LinkedHashMap) list.get(i);
					for (Iterator it = link.entrySet().iterator(); it.hasNext();) {
						Entry<String, Object> entry = (Entry<String, Object>) it
								.next();
						if (!"".equals(entry.getValue())) {
							if (entry.getKey().equals("info_formula")) {
								
								info.setFormula((String)entry.getValue());
							}
							if (entry.getKey().equals("info_integral")) {
								info.setIntegral((Integer)entry
										.getValue());
							}
							if (entry.getKey().equals("info_result")) {
								info.setResult((String)entry.getValue());
							}
							if (entry.getKey().equals("info_id")) {
								info.setId((Long)entry
										.getValue());
							}
						}
					}
					info.setRuleEnginId(fid);
					resCode = ruleEngineInfoMapper.insert(info);
				}
			}
		}
		return resCode;
	}
 
}
