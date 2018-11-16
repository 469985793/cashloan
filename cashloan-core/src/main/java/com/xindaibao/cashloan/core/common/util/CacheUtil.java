package com.xindaibao.cashloan.core.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.core.common.context.Global;
import org.apache.log4j.Logger;

import tool.util.BeanUtil;
import tool.util.StringUtil;

import com.xindaibao.cashloan.system.domain.SysConfig;
import com.xindaibao.cashloan.system.service.SysConfigService;


/**
 * 缓存帮助类
 * 
 * @author
 * @version 1.0.0
 * @date 2017年1月7日 上午10:40:22


 * 

 */
public class CacheUtil {

    private static final Logger logger = Logger.getLogger(CacheUtil.class);

    /**
     * 初始化系统参数配置
     */
	public static void initSysConfig() {

        logger.info("初始化系统配置Config...");

        // 系统配置Config缓存
        SysConfigService sysConfigService = (SysConfigService) BeanUtil.getBean("sysConfigService");

        Map<String, Object> configMap = new HashMap<String, Object>();

        List<SysConfig> sysConfigs = sysConfigService.findAll();
        for (SysConfig sysConfig : sysConfigs) {
            if (null != sysConfig && StringUtil.isNotBlank(sysConfig.getCode())) {
                configMap.put(sysConfig.getCode(), sysConfig.getValue());
            }
        }

        Global.configMap = new HashMap<String, Object>();
        Global.configMap.putAll(configMap);
    }

 
}
