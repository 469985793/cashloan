package com.xindaibao.cashloan.core.common.web.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xindaibao.cashloan.core.common.util.json.ObjToJsonSerializer;
import com.xindaibao.cashloan.core.common.util.json.fastjson.JsonSerializer;

/**
 * 小贷基类控制层基类
 * @author
 *
 */
public abstract class AbstractController {
	
	@Autowired
	private ObjToJsonSerializer objToJsonSerializer;
	
    // 日志
    protected final Logger logger = Logger.getLogger(this.getClass());
	
	public ObjToJsonSerializer getObjToJsonSerializer() {
		return objToJsonSerializer;
	}


	public void setObjToJsonSerializer(ObjToJsonSerializer objToJsonSerializer) {
		this.objToJsonSerializer = objToJsonSerializer;
	}


	/**
	 * 
	 * 将组装好数据的jsonSerializer以json格式返回。
	 * @param response 
	 * @param jsonSerializer 已经添加好数据的 JsonSerializer;
	 */
	protected void jsonResponse(HttpServletResponse response, JsonSerializer jsonSerializer) {
        PrintWriter out = null;
        response.setContentType("text/html;charset=UTF-8");
        try {
	        out = response.getWriter();
	        out.write(jsonSerializer.toString(null));
	        out.flush();
	        out.close();
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
	}
	
	
	/**
	 * 将一个object转化成json后返回,不添加错误信息，object对应的属性名使用默认的。
	 * @param response
	 * @param object 需要转化成json的object
	 */
	protected void jsonResponse(HttpServletResponse response, Object object) {
        PrintWriter out = null;
        JsonSerializer jsonSerializer = new JsonSerializer(null);
        jsonSerializer.addObject(object, null);
        response.setContentType("application/json");
		response.setCharacterEncoding("utf8");
        try {
	        out = response.getWriter();
	        out.write(jsonSerializer.toString(null));
	        out.flush();
	        out.close();
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
	}
	
	/**
	 * 将一个object转化成json后返回,不添加错误信息，
	 * @param response
	 * @param object
	 * @param name object 对应的属性名
	 */
	protected void jsonResponse(HttpServletResponse response, Object object, String name) {
        PrintWriter out = null;
        JsonSerializer jsonSerializer = new JsonSerializer(name);
        jsonSerializer.addObject(object, name);
     	response.setContentType("text/html;charset=UTF-8");
        
        try {
	        out = response.getWriter();
	        out.write(jsonSerializer.toString(name));
	        out.flush();
	        out.close();
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
	}
	
	/**
	 * 将一个object转化成json后返回,带字段过滤,不添加错误信息，
	 * @param response
	 * @param object
	 * @param name object对应的属性名,如果传null或空字符串则使用默认属性名。
	 */
	protected void jsonResponseExclude(HttpServletResponse response, Object object, String name, String[] properties) {
        PrintWriter out = null;
        JsonSerializer jsonSerializer = new JsonSerializer(name);
        jsonSerializer.addObjectWithExclude(object, name, properties);
        response.setContentType("text/html;charset=UTF-8");
        try {
	        out = response.getWriter();
	        out.write(jsonSerializer.toString(name));
	        out.flush();
	        out.close();
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
	}
	
	/**
	 * 将一个object转化成json后返回,字转化指定的字段,不添加错误信息，
	 * @param response
	 * @param object
	 * @param name object对应的属性名,如果传null或空字符串则使用默认属性名。
	 */
	protected void jsonResponseInclude(HttpServletResponse response, Object object, String name, String[] properties) {
        PrintWriter out = null;
        JsonSerializer jsonSerializer = new JsonSerializer(name);
        jsonSerializer.addObjectWithInclude(object, name, properties);
        response.setContentType("text/html;charset=UTF-8");
        try {
	        out = response.getWriter();
	        out.write(jsonSerializer.toString(name));
	        out.flush();
	        out.close();
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
	}
	
	/**
	 * 将一个object转化成json后返回,添加错误信息，
	 * @param response
	 * @param object
	 * @param name object 对应的属性名
	 */
	protected void jsonResponseWithErrorInfo(HttpServletResponse response, Object object, String name, String errorNo, String errorInfo) {
        PrintWriter out = null;
        JsonSerializer jsonSerializer = new JsonSerializer(true, name);
        jsonSerializer.addObject(object, name);
        jsonSerializer.setErrorNo(errorNo);
        jsonSerializer.setErrorInfo(errorInfo);
        response.setContentType("text/html;charset=UTF-8");
        try {
	        out = response.getWriter();
	        out.write(jsonSerializer.toString(name));
	        out.flush();
	        out.close();
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
	}
	
	
	/**
	 * 将一个object转化成json后返回,添加错误信息，
	 * @param response
	 * @param object
	 * @param name object 对应的属性名
	 */
	protected void jsonResponseExcludeWithErrorInfo(HttpServletResponse response, Object object, String name, String errorNo, String errorInfo, String[] properties) {
        PrintWriter out = null;
        JsonSerializer jsonSerializer = new JsonSerializer(true, name);
        jsonSerializer.addObjectWithExclude(object, name, properties);
        jsonSerializer.setErrorNo(errorNo);
        jsonSerializer.setErrorInfo(errorInfo);
        response.setContentType("text/html;charset=UTF-8");
        try {
	        out = response.getWriter();
	        out.write(jsonSerializer.toString(name));
	        out.flush();
	        out.close();
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
	}
	
	/**
	 * 将一个object转化成json后返回,添加错误信息，
	 * @param response
	 * @param object
	 * @param name object 对应的属性名
	 */
	protected void jsonResponseIncludeWithErrorInfo(HttpServletResponse response, Object object, String name, String errorNo, String errorInfo, String[] properties) {
        PrintWriter out = null;
        JsonSerializer jsonSerializer = new JsonSerializer(true, name);
        jsonSerializer.addObjectWithInclude(object, name, properties);
        jsonSerializer.setErrorNo(errorNo);
        jsonSerializer.setErrorInfo(errorInfo);
        response.setContentType("text/html;charset=UTF-8");
        try {
	        out = response.getWriter();
	        out.write(jsonSerializer.toString(name));
	        out.flush();
	        out.close();
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
	}
	
	
	/**
	 * 将一个object转化成json后返回,默认errorNo为0,成功，
	 * @param response
	 * @param object
	 * @param name object 对应的属性名
	 */
	protected void jsonResponseWithErrorinfo(HttpServletResponse response, Object object, String name) {
        PrintWriter out = null;
        JsonSerializer jsonSerializer = new JsonSerializer(true, name);
        jsonSerializer.addObject(object, name);
        response.setContentType("text/html;charset=UTF-8");
        try {
	        out = response.getWriter();
	        out.write(jsonSerializer.toString(name));
	        out.flush();
	        out.close();
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
	}
	
	
	/**
	 * 将一个object转化成json后返回,默认errorNo为0,成功，
	 * @param response
	 * @param object
	 */
	protected void jsonResponseWithErrorinfo(HttpServletResponse response, Object object) {
        PrintWriter out = null;
        JsonSerializer jsonSerializer = new JsonSerializer(true, null);
        jsonSerializer.addObject(object, null);
        response.setContentType("text/html;charset=UTF-8");
        try {
	        out = response.getWriter();
	        out.write(jsonSerializer.toString(null));
	        out.flush();
	        out.close();
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
	}
	
	/**
	 * 返回只带出错信息的Json
	 * @param response
	 * @param errorNo 错误号
	 * @param errorInfo 错误信息
	 */
	protected void jsonResponseWithErrorinfo(HttpServletResponse response, String errorNo, String errorInfo) {
        PrintWriter out = null;
        JsonSerializer jsonSerializer = new JsonSerializer(true, null);
        jsonSerializer.setErrorNo(errorNo);
        jsonSerializer.setErrorInfo(errorInfo);
        try {
	        out = response.getWriter();
	        out.write(jsonSerializer.toString(null));
	        out.flush();
	        out.close();
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
	}
	
	/**
	 * 直接返回一个字符串
	 * @param response
	 * @param text 需要返回的字符串
	 */
	protected void textResponse(HttpServletResponse response, String text) {
        PrintWriter out = null;
        response.setContentType("text/html;charset=UTF-8");
        try {
	        out = response.getWriter();
	        out.write(text);
	        out.flush();
	        out.close();
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
	}
	
	/**
	 * 讲一个单一的object转化成json返回，并指定需要转化的字段
	 * @param response
	 * @param obj 需要转化的object
	 * @param properties 需要转化的字段
	 */
	protected void jsonSingleObjIncludeResponse(HttpServletResponse response, Object obj, String[] properties) {
        PrintWriter out = null;
        response.setContentType("text/html;charset=UTF-8");
        try {
	        out = response.getWriter();
	        out.write(objToJsonSerializer.objectToJsonString(obj));
	        out.flush();
	        out.close();
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
	}
	
	/**
	 * 讲一个单一的object转化成json返回，并指定需要过滤的字段
	 * @param response
	 * @param obj 需要转化的object
	 * @param properties 需要过滤的字段
	 */
	protected void jsonSingleObjExcludeResponse(HttpServletResponse response, Object obj, String[] properties) {
        PrintWriter out = null;
        response.setContentType("text/html;charset=UTF-8");
        try {
	        out = response.getWriter();
	        out.write(objToJsonSerializer.objectToJsonStringWithExclude(obj, properties));
	        out.flush();
	        out.close();
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
	}
	
	/**
	 * 讲一个单一的object转化成json返回
	 * @param response
	 * @param obj 需要转化的object
	 */
	protected void jsonSingleObjResponse(HttpServletResponse response, Object obj) {
        PrintWriter out = null;
        response.setContentType("text/html;charset=UTF-8");
        try {
	        out = response.getWriter();
	        out.write(objToJsonSerializer.objectToJsonString(obj));
	        out.flush();
	        out.close();
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
	}
}
