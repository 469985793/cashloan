package com.xindaibao.cashloan.rule.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tool.util.StringUtil;

/**
 * 规则信息详情
 * @author
 * @version 1.0.0
 * @date 2016年12月20日 下午1:59:39


 * 

 */
public class RuleInfoDetail implements Serializable {
	public static final Logger logger = LoggerFactory.getLogger(RuleInfoDetail.class);
	private static final long serialVersionUID = 1L;

	/**
	 * 列名称
	 */
	private String name;
	
	/**
	 * 列名
	 */
	private String nid;
	
	/**
	 * 类型   int、string
	 */
	private String type;
	
	/**
	 * 列选项
	 */
	//private Map<String,String> options;

	/** 
	 * 获取列名称
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/** 
	 * 设置列名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** 
	 * 获取列名
	 * @return nid
	 */
	public String getNid() {
		return nid;
	}

	/** 
	 * 设置列名
	 * @param nid
	 */
	public void setNid(String nid) {
		this.nid = nid;
	}

	/** 
	 * 获取类型int、string
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/** 
	 * 设置类型int、string
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
 
	
	public boolean valid(RuleInfoDetail detail){
		boolean flag = false;
		if(detail!=null && StringUtil.isNotBlank(detail.getName()) 
				&& StringUtil.isNotBlank(detail.getNid()) 
				&& StringUtil.isNotBlank(detail.getType())){
			if("int".equals(detail.getType())){
				flag = true;
			}else if("string".equals(detail.getType())){
				flag = true;
			}
		}
		return flag;
	}
	
	public boolean validOptions(String type,Map<String,String> options){
		boolean flag = false;
		if(type.equals("int")){
			flag = true;
		}else if(StringUtil.isNotBlank(options) && "string".equals(type)){
			Iterator<String> keys = options.keySet().iterator(); 
			
			while(keys.hasNext()) { 
				String key = keys.next(); 
				logger.info(key);
				if(StringUtil.isNotBlank(key) && StringUtil.isNotBlank(options.get(key))){
					flag = true;
				}else{
					flag = false;
				}
			}
		}
		return flag;
	}
	public boolean validOptions(String type){
		boolean flag = false;
		if(type.equals("int")){
			flag = true;
		}else if("string".equals(type)){
			flag = true;
		}
		return flag;
	}
	
	public boolean validData(List<RuleInfoDetail> details){
		boolean flag = false;
		if(details != null && !details.isEmpty()){
			for(int i=0;i<details.size();i++){
				flag = this.valid(details.get(i));
				if(flag){
					flag = this.validOptions(details.get(i).getType());
				}
				if(!flag){
					break;
				}
			}
		}
		return flag;
	}
	
}
