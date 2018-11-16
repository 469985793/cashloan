package com.xindaibao.cashloan.core.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import tool.util.StringUtil;



public class ServletUtils {
	private static final Logger logger = LoggerFactory.getLogger(ServletUtils.class);

	public static final long ONE_DAY_SECONDS = 60L * 60 * 24;
	public static final long ONE_WEEK_SECONDS = ONE_DAY_SECONDS * 7;
	public static final long ONE_MONTH_SECONDS = ONE_DAY_SECONDS * 30;
	public static final long ONE_YEAR_SECONDS = ONE_DAY_SECONDS * 365;
	private static final String CONTENT_TYPE = "content-type";

	public static final String EXCEL_TYPE = "application/vnd.ms-excel";
	public static final String HTML_TYPE = "text/html";
	public static final String JS_TYPE = "text/javascript";
	public static final String CSS_TYPE = "text/css";
	public static final String JSON_TYPE = "application/json";
	public static final String XML_TYPE = "text/xml";
	public static final String TEXT_TYPE = "text/plain";

	public static final String CODE_UTF8 = "UTF-8";

	// public static final String CODE_GBK = "GBK";

	public static void setContentType(HttpServletResponse response,
			String contentType, String encoding) {
		setContentType(response, contentType);
		response.setCharacterEncoding(encoding);
	}

	public static void setContentType(HttpServletResponse response,
			String contentType) {
		response.setContentType(contentType);
	}

	/**
	 * 把资源输入到响应流
	 * 
	 * @param response
	 * @param rs
	 * @param gzip
	 * @throws IOException
	 */
	public static void outputFile(HttpServletResponse response, Resource[] rs,
			boolean gzip) throws IOException {
		OutputStream output = null;
		if (gzip) {
			output = buildGzipOutputStream(response);
		} else {
			output = response.getOutputStream();
		}
		long contentLen = 0l;
		try {
			for (Resource r : rs) {
				contentLen += r.contentLength();
				if (!AppContextHolder.isDevMode()) {
					File f = r.getFile();
					FileUtils.copyFile(f, output);
				} else {
					InputStream input = r.getInputStream();
					try {
						IOUtils.copy(input, output);
					} finally {
						input.close();
					}
				}
				output.write(10);
			}
			if (!gzip) {
				response.setContentLength((int) contentLen);
			}
		} finally {
			output.close();
		}
	}

	/**
	 * 把资源输入到响应流
	 * 
	 * @param response
	 * @param r
	 * @param gzip
	 * @throws IOException
	 */
	public static void outputFile(HttpServletResponse response, Resource r,
			boolean gzip) throws IOException {
		OutputStream output = null;

		if (gzip) {
			output = buildGzipOutputStream(response);
		} else {
			output = response.getOutputStream();
			response.setContentLength((int) r.contentLength());
		}

		if (!AppContextHolder.isDevMode()) {
			File f = r.getFile();
			try {
				FileUtils.copyFile(f, output);
			} finally {
				output.close();
			}
		} else {
			InputStream input = r.getInputStream();
			try {
				IOUtils.copy(input, output);
			} finally {
				input.close();
				output.close();
			}
		}
	}

	/**
	 * 创建gzip流
	 * 
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public static OutputStream buildGzipOutputStream(
			HttpServletResponse response) throws IOException {
		response.setHeader("Content-Encoding", "gzip");
		response.setHeader("Vary", "Accept-Encoding");
		return new GZIPOutputStream(response.getOutputStream());
	}

	/**
	 * 是否可以接受gzip压缩
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAcceptGzip(HttpServletRequest request) {
		String acceptEncoding = request.getHeader("Accept-Encoding");
		return StringUtil.contains(acceptEncoding, "gzip");
	}

	/**
	 * 判定请求资源是否已经变动修改，有则设置新的标记和过期时间
	 * 
	 * @param request
	 * @param response
	 * @param lastModi
	 * @return
	 */
	public static boolean checkAndSetExpiresHeaders(HttpServletRequest request,
			HttpServletResponse response, long lastModi) {
		String etag = "W/\"" + lastModi + "\"";
		if (!ServletUtils.checkIfModifiedSince(request, response, lastModi)
				|| !ServletUtils.checkIfNoneMatchEtag(request, response, etag)) {
			return false;
		}
		setLastModifiedHeader(response, lastModi);
		setEtag(response, etag);
		if (AppContextHolder.isDevMode()) {
			setNoCacheHeader(response);
		} else {
			setExpiresHeader(response, ServletUtils.ONE_DAY_SECONDS);
		}
		return true;
	}

	/**
	 * 设置不缓存
	 * 
	 * @param response
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		response.setDateHeader("Expires", 1L);
		response.addHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
	}

	/**
	 * 设置缓存过期时间
	 * 
	 * @param response
	 * @param expiresSeconds
	 */
	public static void setExpiresHeader(HttpServletResponse response,
			long expiresSeconds) {
		response.setDateHeader("Expires", System.currentTimeMillis()
				+ expiresSeconds * 1000);
		response.setHeader("Cache-Control", "private, max-age="
				+ expiresSeconds);
	}

	/**
	 * 设置最后修改时间
	 * 
	 * @param response
	 * @param lastModifiedDate
	 */
	public static void setLastModifiedHeader(HttpServletResponse response,
			long lastModifiedDate) {
		response.setDateHeader("Last-Modified", lastModifiedDate);
	}

	/**
	 * 设置Etag
	 * 
	 * @param response
	 * @param etag
	 */
	public static void setEtag(HttpServletResponse response, String etag) {
		response.setHeader("ETag", etag);
	}

	/**
	 * 检查请求带过来的资源的最后修改时间是否已经改变
	 * 
	 * @param request
	 * @param response
	 * @param lastModified
	 * @return
	 */
	public static boolean checkIfModifiedSince(HttpServletRequest request,
			HttpServletResponse response, long lastModified) {
		long ifModifiedSince = request.getDateHeader("If-Modified-Since");
		if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return false;
		}
		return true;
	}

	/**
	 * 检查请求带过来的Etag是否和服务器匹配
	 * 
	 * @param request
	 * @param response
	 * @param etag
	 * @return
	 */
	public static boolean checkIfNoneMatchEtag(HttpServletRequest request,
			HttpServletResponse response, String etag) {
		String headerValue = request.getHeader("If-None-Match");
		if (headerValue != null) {
			boolean conditionSatisfied = false;
			if (!"*".equals(headerValue)) {
				StringTokenizer commaTokenizer = new StringTokenizer(
						headerValue, ",");
				while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
					String currentToken = commaTokenizer.nextToken();
					if (currentToken.trim().equals(etag)) {
						conditionSatisfied = true;
					}
				}
			} else {
				conditionSatisfied = true;
			}
			if (conditionSatisfied) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				response.setHeader("ETag", etag);
				return false;
			}
		}
		return true;
	}

	/**
	 * 设定响应为文件下载
	 * 
	 * @param response
	 * @param fileName
	 */
	public static void setFileDownloadHeader(HttpServletResponse response,
			String fileName) {
		try {
			String encodedfileName = new String(fileName.getBytes(),
					"ISO8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ encodedfileName + "\"");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e); 
		}
	}

	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static void writeToResponse(HttpServletResponse response, Map<? extends Object, Object> res) {
		response.addHeader(CONTENT_TYPE, JS_TYPE);
		response.setContentType("application/json");
		response.setCharacterEncoding(CODE_UTF8);
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(response.getOutputStream(), CODE_UTF8);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e); 
		} catch (IOException e) {
			logger.error(e.getMessage(), e); 
		}
		JsonUtil.write(out, res);
	}

	public static void writeToResponseWithOnlyYMDDate(HttpServletResponse response, Map<? extends Object, Object> res) {
		response.addHeader(CONTENT_TYPE, JS_TYPE);
		response.setContentType("application/json");
		response.setCharacterEncoding(CODE_UTF8);
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(
					response.getOutputStream(), CODE_UTF8);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e); 
		} catch (IOException e) {
			logger.error(e.getMessage(), e); 
		}
		JsonUtil.writeWithOnlyYMDDate(out, res);
	}

	public static void writeToResponselist(HttpServletResponse response,
			List<? extends Object> res) throws IOException {
		response.addHeader(CONTENT_TYPE, JS_TYPE);
		OutputStreamWriter out = new OutputStreamWriter(
				response.getOutputStream(), CODE_UTF8);
		JsonUtil.write(out, res);
	}

	public static void writeToResponselist2(HttpServletResponse response,
			List<Object> res) throws IOException {
		response.addHeader(CONTENT_TYPE, JS_TYPE);
		OutputStreamWriter out = new OutputStreamWriter(
				response.getOutputStream(), CODE_UTF8);
		JsonUtil.write(out, res);
	}

	public static void writeToResponse(HttpServletResponse response,
			Object object) throws UnsupportedEncodingException, IOException {
		response.addHeader(CONTENT_TYPE, JS_TYPE);
		response.setContentType("application/json");
		response.setCharacterEncoding(CODE_UTF8);

		OutputStreamWriter out = new OutputStreamWriter(
				response.getOutputStream(), CODE_UTF8);

		JsonUtil.write(out, object);
	}

}
