package com.xindaibao.cashloan.core.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  
 * <p>Copyright: All Rights Reserved</p>

 * <p>System: 小贷管理系统</p>
 * <p>Description: ip获取工具类</p>
 *  
 * @author
 * @version 1.0
 * @since 2014-4-2
 */
public class IpUtil {
	
	public static final Logger logger = LoggerFactory.getLogger(IpUtil.class);
	
	/*private static final int IPV6Length = 8; // IPV6地址的分段  
    private static final int IPV4Length = 4; // IPV6地址分段  
    private static final int IPV4ParmLength = 2; // 一个IPV4分段占的长度  
    private static final int IPV6ParmLength = 4; // 一个IPV6分段占的长 
	 */
	
    @Resource  
    private WebServiceContext wsContext;
    
	/**
	 * 获取请求IP
	 * @param request
	 * @return
	 */
	public static String getRemortIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		
		 //这里主要是获取本机的ip,可有可无  
	    if ("127.0.0.1".equals(ip) || ip.endsWith("0:0:0:0:0:0:1")) {  
	        // 根据网卡取本机配置的IP  
	        InetAddress inet = null;
	        try {  
	            inet = InetAddress.getLocalHost();  
	        } catch (UnknownHostException e) {  
	            logger.error(e.getMessage(), e);
	        }
	        if(inet != null){
	        	ip = inet.getHostAddress();
	        }
	        return ip;
	    } 
		if(ip.length() > 0){
			String[] ipArray = ip.split(",");
			if (ipArray != null && ipArray.length > 1) {
				return ipArray[0];
			}
			return ip;
		}
		
		return "";
	}

}
