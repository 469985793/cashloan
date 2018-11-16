package com.xindaibao.cashloan.core.common.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 附件读取
 * 
 * @version 1.0
 * @author
 * @since 2017-02-15
 */
@Controller
@Scope("prototype")
public class ReadFileController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(ReadFileController.class);
	
	@RequestMapping(value = "/readFile.htm")
	public String readImg(HttpServletRequest request, HttpServletResponse response)  {
		InputStream imageIn = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		OutputStream output = null;
		try {
			//读取文件相对路径
			String path = request.getParameter("path");
			boolean  isFile = false;
			if(path==""||path==null) return "";
			String url = path;
			if(!path.contains(File.separator)){
				return "";
			}
			String[] names = path.split("/");
			String fileName = names[names.length-1];
			File file = new File(url);
			//如果图片不存在 返回Null
			if(!file.exists()){ return null; }
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			output = response.getOutputStream();// 得到输出流
			if (url.toLowerCase().endsWith(".jpg") || url.toLowerCase().endsWith(".jpeg") || url.toLowerCase().endsWith(".png")) {
				// 表明生成的响应是图片
				response.setContentType("image/jpeg");
			}else if (url.toLowerCase().endsWith(".bmp")) {
				response.setContentType("image/bmp");
			}else if(url.toLowerCase().endsWith(".pdf")){
				isFile = true;
				response.setContentType("application/pdf");
			}else if(url.toLowerCase().endsWith(".xls")){
				isFile = true;
				response.setContentType("application/vnd.ms-excel");
			}else if(url.toLowerCase().endsWith(".xlsm")){
				isFile = true;
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			}else if(url.toLowerCase().endsWith(".xlsx")){
				isFile = true;
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			}else if(url.toLowerCase().endsWith(".doc")){
				isFile = true;
				response.setContentType("application/msword");
			}else if(url.toLowerCase().endsWith(".docm")){
				isFile = true;
				response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			}else if(url.toLowerCase().endsWith(".docx")){
				isFile = true;
				response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			}else {
				return null;
			}
			if(isFile){//文件名称
				String agent  = request.getHeader("User-Agent").toLowerCase();
				 if (agent.indexOf("firefox") > -1){  
			            response.setHeader("Content-Disposition", "attachment; filename=\"" 
				        + new String(fileName.getBytes("utf-8"), "ISO8859-1")+"\"");  
		        }else if(agent.indexOf("MSIE") > -1 || agent.indexOf("edge") > -1 ||
		        		(agent.indexOf("trident") > -1 && agent.indexOf("rv") > -1)){
		        	//ie浏览器
		        	response.setHeader("Content-disposition", 
		        			"attachment; filename=" + new String(fileName.getBytes("GBK"), "ISO-8859-1"));
		        }else{              
		        	response.setHeader("Content-disposition", 
		        			"attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
		        }  
			}
			imageIn = new FileInputStream(file);
			bis = new BufferedInputStream(imageIn);// 输入缓冲流
			bos = new BufferedOutputStream(output);// 输出缓冲流
			byte data[] = new byte[4096];// 缓冲字节数
			int size;
			while ( ( size = bis.read(data) ) != -1) {
				bos.write(data,0,size);
			}
			bos.flush();// 清空输出缓冲流
			output.flush();
		} catch (IOException e) {
			logger.error(e);
		} finally {
			try {
				if(bis!=null){bis.close();}
				if(imageIn!=null){imageIn.close();}
				if(bos!=null){bos.close();}
				if(output!=null){output.close();}
			} catch (IOException e) {
				logger.error(e);
			}
		}
		return null;
	}
    
}
