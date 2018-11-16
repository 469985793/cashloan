package com.xindaibao.cashloan.system.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.xindaibao.cashloan.system.domain.SysConfig;
import org.springframework.beans.BeanUtils;


/**
 * 
 * 系统参数Model
 * @version 1.0
 * @author
 * @created 2014年9月23日 上午11:49:35
 */
public class ConfigModel extends SysConfig {
	
	private static final long serialVersionUID = 1L;
	
	/**	当前页码 */
	private int page;
	
	/** 每页数据条数 */
	private int size;
	
	private Map<String, SysConfig> map;

	public ConfigModel() {
		map = Collections.synchronizedMap(new HashMap<String, SysConfig>());
	}

	public static ConfigModel instance(SysConfig sconfig) {
		ConfigModel systemConfigModel = new ConfigModel();
		BeanUtils.copyProperties(sconfig, systemConfigModel);
		return systemConfigModel;
	}

	public SysConfig prototype() {
		SysConfig sconfig = new SysConfig();
		BeanUtils.copyProperties(this, sconfig);
		return sconfig;
	}
	
	public void addConfig(SysConfig sys) {
		map.put(sys.getCode().replace("con_", ""), sys);
	}

	private SysConfig getConfig(String key) {
		SysConfig sys = (SysConfig) map.get(key);
		return sys;
	}

	public String getValue(String key) {
		SysConfig c = getConfig(key);
		if (c == null)
			return null;
		return c.getValue();
	}

	public String getStatus(String key) {
		SysConfig c = getConfig(key);
		if (c == null)
			return null;
		return getConfig(key).getStatus()+"";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Map<String, SysConfig> getMap() {
		return map;
	}

	public void setMap(Map<String, SysConfig> map) {
		this.map = map;
	}

}
