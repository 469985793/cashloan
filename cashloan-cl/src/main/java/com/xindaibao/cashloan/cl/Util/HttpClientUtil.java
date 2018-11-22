package com.xindaibao.cashloan.cl.Util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.*;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
	private static final Logger logger = Logger.getLogger(HttpClientUtil.class);
	
	private final static int CONNECT_TIMEOUT = 5000;// 连接超时毫秒
	private final static int SOCKET_TIMEOUT = 30000;// 传输超时毫秒
	private final static int REQUESTCONNECT_TIMEOUT = 3000;// 获取请求超时毫秒
	private final static int CONNECT_TOTAL = 200;// 最大连接数
	private final static int CONNECT_ROUTE = 20;// 每个路由基础的连接数
	
	private final static String ENCODE_CHARSET = "UTF-8";// 响应报文解码字符集
	
	public final static String CONTENT_TYPE_TEXT_HTML = "text/xml";		// HTTP内容类型。
	public final static String CONTENT_TYPE_TEXT_PLAIN = "text/plain";	// PLAIN内容类型。
	public final static String CONTENT_TYPE_FORM_URL = "application/x-www-form-urlencoded";		// HTTP内容类型。相当于form表单的形式，提交数据
	public final static String CONTENT_TYPE_JSON_URL = "application/json;charset=utf-8";		// HTTP内容类型。相当于form表单的形式，提交数据

	private volatile static PoolingHttpClientConnectionManager connManager = null;
	private volatile static CloseableHttpClient httpClient = null;
	
//	public static void main(String[] args) {
//		String reqUrl = "https://172.16.40.3:18071/payBopInst/bop/pay/payFirst";
//		String param = "{}";
////		String param = "{\"timestamp\":1517811612330,\"recordId\":\"465\",\"times\":0,\"extData\":{\"bopRecv\":[{\"accountNo\":\"6217710200443166\",\"inChannel\":\"12000000\",\"mac\":\"CEBB2CD0AC24B28BEA3D2E2851A3CA55\",\"origdate\":\"20180201\",\"origlogno\":\"1517557393243\",\"outAbbr\":\"CUPZJ\",\"recvId\":465,\"srcSys\":\"03\",\"totalAmount\":8}]}}";
//		String res = HttpsClientUtil.sendPostRequest(reqUrl, param);
//		System.out.println(res);
//		
////		String reqUrl = "http://172.16.40.3:9071/pos.hkrt.com/login/checkInfo";
////		String param = "{}";
////		String res = HttpsClientUtil.sendPostRequest(reqUrl, param);
////		System.out.println(res);
//	}
	
	private static PoolingHttpClientConnectionManager getConnManager() {
		if(connManager != null)
			return connManager;
		
		synchronized (HttpClientUtil.class) {
			if(connManager == null) {
				ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
				LayeredConnectionSocketFactory sslsf = createSSLConnSocketFactory();
				Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", plainsf).register("https", sslsf).build();
				connManager = new PoolingHttpClientConnectionManager(registry);
				// 将最大连接数增加到200
				connManager.setMaxTotal(CONNECT_TOTAL);
				// 将每个路由基础的连接增加到20
				connManager.setDefaultMaxPerRoute(CONNECT_ROUTE);
				// 可用空闲连接过期时间,重用空闲连接时会先检查是否空闲时间超过这个时间，如果超过，释放socket重新建
				connManager.setValidateAfterInactivity(40000);
				// 设置socket超时时间
				SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(SOCKET_TIMEOUT).build();
				connManager.setDefaultSocketConfig(socketConfig);
//				RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUESTCONNECT_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
			}
		}
		return connManager;
	}

	private static CloseableHttpClient getHttpClient() {
		if (httpClient != null) {
			return httpClient;
		}
		synchronized (HttpClientUtil.class) {
			if (httpClient == null) {
				PoolingHttpClientConnectionManager phcm = getConnManager();
				RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUESTCONNECT_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
//				HttpRequestRetryHandler httpRequestRetryHandler = new HttpClientUtil().new MyHttpRequestRetryHandler();
				HttpRequestRetryHandler httpRequestRetryHandler = getHttpRequestRetryHandler();
				
				httpClient = HttpClients.custom().setConnectionManager(phcm).setDefaultRequestConfig(requestConfig).setRetryHandler(httpRequestRetryHandler).build();
			}
		}
		return httpClient;
	}
	
	public static String sendReq(HttpUriRequest request) {
		String respContent = ""; // 响应内容
		CloseableHttpResponse response = null;
		try {
			logger.info("http request time " + DateUtil.getCurrentDateTime(DateUtil.Y_M_D_H_M_S));
			response = getHttpClient().execute(request, HttpClientContext.create()); // 执行GET请求
			HttpEntity entity = response.getEntity(); // 获取响应实体

			// 判断响应状态
			if (response.getStatusLine().getStatusCode() >= 300) {
				throw new Exception("HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
			}
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode() && null != entity) {
				respContent = EntityUtils.toString(entity, ContentType.getOrDefault(entity).getCharset());
//				respContent = EntityUtils.toString(entity, charset);
				EntityUtils.consume(entity);
			}
		} catch (ConnectTimeoutException cte) {
			logger.error("请求通信[" + request.getURI() + "]时连接超时,堆栈轨迹如下", cte);
		} catch (SocketTimeoutException ste) {
			logger.error("请求通信[" + request.getURI() + "]时读取超时,堆栈轨迹如下", ste);
		} catch (ClientProtocolException cpe) {
			// 该异常通常是协议错误导致:比如构造HttpGet对象时传入协议不对(将'http'写成'htp')or响应内容不符合HTTP协议要求等
			logger.error("请求通信[" + request.getURI() + "]时协议异常,堆栈轨迹如下", cpe);
		} catch (ParseException pe) {
			logger.error("请求通信[" + request.getURI() + "]时解析异常,堆栈轨迹如下", pe);
		} catch (IOException ioe) {
			// 该异常通常是网络原因引起的,如HTTP服务器未启动等
			logger.error("请求通信[" + request.getURI() + "]时网络异常,堆栈轨迹如下", ioe);
		} catch (Exception e) {
			logger.error("请求通信[" + request.getURI() + "]时偶遇异常,堆栈轨迹如下", e);
		} finally {
			try {
				if (response != null)
					response.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return respContent;
	}

	/**
	 * 发送GET请求
	 * @Title: sendGet 
	 * @param reqUrl 请求地址
	 * @param param 请求参数
	 * @param charset 数据格式
	 * @return String 
	 */
	public static String sendGet(String reqUrl, String param, String charset) {
		if (DataUtil.isNull(charset)) {
			charset = ENCODE_CHARSET;
		}
		if (!DataUtil.isNull(param)) {
			reqUrl += "?" + param;
		}

		HttpGet httpGet = new HttpGet(reqUrl);
		httpGet = new HttpGet(reqUrl);
		httpGet.setHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_FORM_URL + "; charset=" + charset);
		
		String result = sendReq(httpGet);
		if (httpGet != null) {
			httpGet.releaseConnection();
		}
		return result;
	}
	
	/**
	 * 发送POST请求
	 * @Title: sendPost 
	 * @param reqUrl 请求地址
	 * @param param 请求参数
	 * @param contentType 参数方式
	 * @param charset 数据格式
	 * @return String 
	 */
	public static String sendPost(String reqUrl, String param, String contentType, String charset) {
		if (DataUtil.isNull(contentType)) {
			contentType = CONTENT_TYPE_FORM_URL;
		}
		if (DataUtil.isNull(charset)) {
			charset = ENCODE_CHARSET;
		}
		HttpPost httpPost = new HttpPost(reqUrl);
		httpPost.setHeader(HTTP.CONTENT_TYPE, contentType + "; charset=" + charset);
		
		if (!DataUtil.isNull(param)) {
			StringEntity entity = new StringEntity(param, charset);
			httpPost.setEntity(entity);
		}
		String result = sendReq(httpPost);
		if (httpPost != null) {
			httpPost.releaseConnection();
		}
		return result;
	}

	/**
	 * 发送 post请求（带文件）
	 * 
	 * @param httpUrl 地址
	 * @param maps 参数
	 * @param fileLists 附件
	 */
	public static String sendPostFile(String httpUrl, Map<String, String> maps, List<File> fileLists, String contentType, String charset) {
		if (DataUtil.isNull(contentType)) {
			contentType = CONTENT_TYPE_TEXT_PLAIN;
		}
		if (DataUtil.isNull(charset)) {
			charset = ENCODE_CHARSET;
		}
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
		if (maps != null) {
			for (String key : maps.keySet()) {
				meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.create(contentType, charset)));
			}
		}
		if (fileLists != null) {
			for (File file : fileLists) {
				FileBody fileBody = new FileBody(file);
				meBuilder.addPart("files", fileBody);
			}
		}
		HttpEntity reqEntity = meBuilder.build();
		httpPost.setEntity(reqEntity);
		logger.info("reqUril: " + httpUrl + ", reqData: "+maps);
		return sendReq(httpPost);
	}
	
	
	// SSL的socket工厂创建
	private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
		SSLConnectionSocketFactory sslsf = null;
		// 创建TrustManager() 用于解决javax.net.ssl.SSLPeerUnverifiedException: peer not
		// authenticated
		X509TrustManager trustManager = new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(X509Certificate[] arg0, String authType) throws CertificateException {
				;
			}

			@Override
			public void checkServerTrusted(X509Certificate[] arg0, String authType) throws CertificateException {
				;
			}
		};
		SSLContext sslContext;
		try {
			sslContext = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
			sslContext.init(null, new TrustManager[] { (TrustManager) trustManager }, null);
			// 创建SSLSocketFactory , // 不校验域名 ,取代以前验证规则
			sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return sslsf;
	}
	
	public static HttpRequestRetryHandler getHttpRequestRetryHandler() {
		return new HttpRequestRetryHandler() {
			@Override
			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				if (executionCount >= 3) {// 如果已经重试了3次，就放弃
					return false;
				}
				if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
					return true;
				}
				if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
					return false;
				}
				if (exception instanceof InterruptedIOException) {// 超时
					return true;
				}
				if (exception instanceof UnknownHostException) {// 目标服务器不可达
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
					return false;
				}
				if (exception instanceof SSLException) {// ssl握手异常
					return false;
				}
				HttpClientContext clientContext = HttpClientContext.adapt(context);
				HttpRequest request = clientContext.getRequest();
				// 如果请求是幂等的，就再次尝试
				if (!(request instanceof HttpEntityEnclosingRequest)) {
					return true;
				}
				return false;
			}
		};
	}
	
	public class MyHttpRequestRetryHandler implements HttpRequestRetryHandler {
		public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
			if (executionCount >= 3) {// 如果已经重试了3次，就放弃
				return false;
			}
			if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
				return true;
			}
			if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
				return false;
			}
			if (exception instanceof InterruptedIOException) {// 超时
				return true;
			}
			if (exception instanceof UnknownHostException) {// 目标服务器不可达
				return false;
			}
			if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
				return false;
			}
			if (exception instanceof SSLException) {// ssl握手异常
				return false;
			}
			HttpClientContext clientContext = HttpClientContext.adapt(context);
			HttpRequest request = clientContext.getRequest();
			// 如果请求是幂等的，就再次尝试
			if (!(request instanceof HttpEntityEnclosingRequest)) {
				return true;
			}
			return false;
		}
	}
	
	/**
	 * 将map集合的键值对转化成：key1=value1&key2=value2 的形式
	 * 
	 * @param parameterMap 需要转化的键值对集合
	 * @return 字符串
	 */
	public static String convertParamter(Map<String, Object> parameterMap) {
		StringBuffer parameterBuffer = new StringBuffer();
		if (parameterMap != null) {
			logger.info("request params:"+JSONObject.toJSONString(parameterMap));
			Iterator<String> iterator = parameterMap.keySet().iterator();
			String key = null;
			String value = null;
			while (iterator.hasNext()) {
				key = iterator.next();
				if (parameterMap.get(key) != null) {
					try {
						value = URLEncoder.encode(parameterMap.get(key).toString(), ENCODE_CHARSET);
					} catch (UnsupportedEncodingException e) {
						logger.error(e.getMessage(), e);
					}
				} else {
					value = "";
				}
				parameterBuffer.append(key).append("=").append(value);
				if (iterator.hasNext()) {
					parameterBuffer.append("&");
				}
			}
		}
		return parameterBuffer.toString();
	}

}
