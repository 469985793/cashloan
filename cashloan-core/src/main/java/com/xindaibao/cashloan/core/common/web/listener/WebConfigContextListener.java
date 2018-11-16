package com.xindaibao.cashloan.core.common.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.xindaibao.cashloan.core.common.util.CacheUtil;
import org.apache.log4j.Logger;

/**
 * 监听器
 */

public class WebConfigContextListener implements ServletContextListener,HttpSessionAttributeListener{

	private static Logger logger=Logger.getLogger(WebConfigContextListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("启动加载...");
		
		// 系统参数
		CacheUtil.initSysConfig();
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		
	}
}
