package com.xindaibao.cashloan.core.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


/**
 * 工具类-Https协议请求
 * @author
 * @version 1.0.0
 * @date 2016年12月1日 上午11:15:15



 * 
 * 不再使用apache commons HttpClient项目，使用apache HttpClient和HttpCore项目
 * 
 * apache commons HttpClient官网的解释是：
 * <a href="http://hc.apache.org/httpclient-3.x/">
 * The Commons HttpClient project is now end of life, and is no longer being developed. 
 * It has been replaced by the Apache HttpComponents project in its HttpClient and HttpCore modules, 
 * which offer better performance and more flexibility.</a>
 */
@SuppressWarnings("deprecation")
public class HttpsUtil {
	
	private static final Logger logger = Logger.getLogger(HttpsUtil.class);
	/**
	 * HTTPS协议方案
	 */
	public static final String HTTPS_PROTOCOL_SCHEME = "https";

	/**
	 * HTTPS默认端口号
	 */
	public static final int DEFAULT_PORT_NUMBER_FOR_HTTPS = 443;
	
	/**
	 * 默认的编码格式
	 */
	private static final String CHARSET = "UTF-8";
	
	/**
	 * 默认的超时时间 60 S
	 */
	private static final int TIMEOUT = 60000;
	
	/**
	 * https - post 请求,
	 * 该方法会按照默认的编码格式utf-8 和默认的超时时间60s进行请求
	 * @param postUrl
	 * @param requestParams
	 * @return
	 */
	public static String postClient(String postUrl,
			Map<String, String> requestParams) {
		return postClient(postUrl, requestParams, CHARSET, TIMEOUT);
	}
	
	/**
	 * https - post 请求,
	 * 该方法会按照默认的编码格式utf-8 和传输的超时时间60s进行请求
	 * @param postUrl
	 * @param requestParams
	 * @param timeout
	 * @return
	 */
	public static String postClient(String postUrl,
			Map<String, String> requestParams, int timeout) {
		return postClient(postUrl, requestParams, CHARSET, timeout);
	}
	
	
    /**
     * 请求Https协议地址
     * 
     * <a href="http://blog.csdn.net/jadyer/article/details/7802139">使用HttpClient向HTTPS地址发送POST请求</a>
     * 
     * @param postUrl 地址
     * @param requestParams 请求参数
     * @param charset 字符编码集
     * @param timeout 超时时间
     * @return
     * @throws PayException
     */
    @SuppressWarnings("resource")
	public static String postClient(String postUrl,
			Map<String, String> requestParams, String charset, int timeout) {
		HttpClient httpClient = new DefaultHttpClient();

		try {
			SSLContext ctx = SSLContext.getInstance(SSLSocketFactory.TLS);
			ctx.init(null, new TrustManager[] { new HttpsX509TrustManager() },
					null);
			SSLSocketFactory sslSocketFactory = new SSLSocketFactory(ctx);

			httpClient
					.getConnectionManager()
					.getSchemeRegistry()
					.register(
							new Scheme(
									HttpsUtil.HTTPS_PROTOCOL_SCHEME,
									sslSocketFactory,
									HttpsUtil.DEFAULT_PORT_NUMBER_FOR_HTTPS));

			HttpPost httpPost = new HttpPost(postUrl);
			//设置请求和传输时长
			Builder builder = RequestConfig.custom();
			builder.setSocketTimeout(timeout);
			builder.setConnectTimeout(timeout);
			RequestConfig config = builder.build();
			httpPost.setConfig(config);
			//初始化请求参数
			List<NameValuePair> formParams = convert2NameValuePair(requestParams);
			if (!formParams.isEmpty()) {
				httpPost.setEntity(new UrlEncodedFormEntity(formParams, charset));
			}
			//发送请求
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String responseContent = "";
			if (entity != null) {
				responseContent = EntityUtils.toString(entity, charset);
				entity.consumeContent();
			}
			logger.info("HtppsUtil发送请求状态码为："+response.getStatusLine().getStatusCode() + "通知参数:"+responseContent);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				if (StringUtil.isBlank(responseContent)) {
					// 请求成功，但无参数返回
					return "3000";
				} 
				
				return responseContent;
			} else {
				// 请求状态不为200
				return "3001";
			}
		} catch (NoSuchAlgorithmException noSuex) {
			logger.info("请求发送失败，NoSuchAlgorithmException原因：", noSuex);
			// 请求出现异常
			return "3003";
		} catch (KeyManagementException keyMaEx) {
			logger.info("请求发送失败，KeyManagementException原因：秘钥异常", keyMaEx);
			// 请求出现异常
			return "3003";
		} catch (UnsupportedEncodingException unsuEx) {
			logger.info("请求发送失败，UnsupportedEncodingException原因：", unsuEx);
			// 请求出现异常
			return "3003";
		} catch (ClientProtocolException clPrEx) {
			logger.info("请求发送失败，ClientProtocolException原因：", clPrEx);
			// 请求出现异常
			return "3003";
		}catch(SocketTimeoutException e){
			logger.info("请求发送失败，SocketTimeoutException原因：", e);
			// 请求超时
			return "3002";
		} catch (IOException ioEx) {
			logger.info("请求发送失败，ClientProtocolException原因：", ioEx);
			// 请求出现异常
			return "3003";
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}

    private static List<NameValuePair> convert2NameValuePair(Map<String, String> requestParams) {
        if (requestParams == null || requestParams.isEmpty()) {
            return null;
        }

        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : requestParams.entrySet()) {
            formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return formParams;
    }

	private static class HttpsX509TrustManager implements X509TrustManager {
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			// 不校验服务器端证书，什么都不做，视为通过检查
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			// 不校验服务器端证书，什么都不做，视为通过检查
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}
	
	/**
	 * https - get 请求
	 * @param postUrl
	 * @return
	 */
	public static String getClient(String postUrl) {
		return getClient(postUrl, CHARSET, TIMEOUT);
	}
	
	/**
	 * GET请求Https协议地址
	 * @param postUrl
	 * @param requestParams
	 * @param charset
	 * @param timeout
	 * @return
	 */
	@SuppressWarnings("resource")
	public static String getClient(String postUrl, String charset, int timeout) {
		HttpClient httpClient = new DefaultHttpClient();

		try {
			SSLContext ctx = SSLContext.getInstance(SSLSocketFactory.TLS);
			ctx.init(null, new TrustManager[] { new HttpsX509TrustManager() },
					null);
			SSLSocketFactory sslSocketFactory = new SSLSocketFactory(ctx);

			httpClient
					.getConnectionManager()
					.getSchemeRegistry()
					.register(
							new Scheme(
									HttpsUtil.HTTPS_PROTOCOL_SCHEME,
									sslSocketFactory,
									HttpsUtil.DEFAULT_PORT_NUMBER_FOR_HTTPS));

			HttpGet httpGet = new HttpGet(postUrl);

			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();

			String responseContent = "";
			if (entity != null) {
				responseContent = EntityUtils.toString(entity, charset);
				entity.consumeContent();
			}

			return responseContent;
		} catch (NoSuchAlgorithmException noSuex) {
			logger.info("请求发送失败，NoSuchAlgorithmException原因：", noSuex);
			// 请求出现异常
			return "3003";
		} catch (KeyManagementException keyMaEx) {
			logger.info("请求发送失败，KeyManagementException原因：", keyMaEx);
			// 请求出现异常
			return "3003";
		} catch (UnsupportedEncodingException unsuEx) {
			logger.info("请求发送失败，UnsupportedEncodingException原因：", unsuEx);
			// 请求出现异常
			return "3003";
		} catch (ClientProtocolException clPrEx) {
			logger.info("请求发送失败，ClientProtocolException原因：", clPrEx);
			// 请求出现异常
			return "3003";
		} catch (IOException ioEx) {
			logger.info("请求发送失败，ClientProtocolException原因：", ioEx);
			// 请求出现异常
			return "3003";
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}
	
	
	/**
	 * https - post 请求, 该方法会按照默认的编码格式utf-8 和默认的超时时间60s进行请求
	 * 
	 * @param postUrl
	 * @param requestParams
	 * @return
	 */
	public static String postStrClient(String postUrl, String jonStr) {
		return postStrClient(postUrl, jonStr, CHARSET, TIMEOUT);
	}

	/**
	 * https - post 请求, 该方法会按照默认的编码格式utf-8 和传输的超时时间60s进行请求
	 * 
	 * @param postUrl
	 * @param requestParams
	 * @param timeout
	 * @return
	 */
	public static String postStrClient(String postUrl, String jonStr, int timeout) {
		return postStrClient(postUrl, jonStr, CHARSET, timeout);
	}

	/**
	 * 请求Https协议地址
	 * 
	 * <a href="http://blog.csdn.net/jadyer/article/details/7802139">
	 * 使用HttpClient向HTTPS地址发送POST请求</a>
	 * 
	 * @param postUrl
	 *            地址
	 * @param requestParams
	 *            请求参数
	 * @param charset
	 *            字符编码集
	 * @param timeout
	 *            超时时间
	 * @return
	 */
	@SuppressWarnings("resource")
	public static String postStrClient(String postUrl, String jonStr, String charset, int timeout) {
		HttpClient httpClient = new DefaultHttpClient();
		try {
			SSLContext ctx = SSLContext.getInstance(SSLSocketFactory.TLS);
			ctx.init(null, new TrustManager[] { new HttpsX509TrustManager() },
					null);
			SSLSocketFactory sslSocketFactory = new SSLSocketFactory(ctx);
			sslSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);//运行所有的hostname验证  
			httpClient
					.getConnectionManager()
					.getSchemeRegistry()
					.register(
							new Scheme(HttpsUtil.HTTPS_PROTOCOL_SCHEME,
									sslSocketFactory,
									HttpsUtil.DEFAULT_PORT_NUMBER_FOR_HTTPS)
							);

			HttpPost httpPost = new HttpPost(postUrl);
			// 设置请求和传输时长
			Builder builder = RequestConfig.custom();
			builder.setSocketTimeout(timeout);
			builder.setConnectTimeout(timeout);
			RequestConfig config = builder.build();
			httpPost.setConfig(config);
			httpPost.setHeader(HTTP.USER_AGENT, "httpcomponents");
			httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
			StringEntity stringEntiry = new StringEntity(jonStr, "utf-8");
			httpPost.setEntity(stringEntiry);

			// 发送请求
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String responseContent = "";
			if (entity != null) {
				responseContent = EntityUtils.toString(entity, charset);
				entity.consumeContent();
			}
			logger.info("HtppsUtil发送请求状态码为："
					+ response.getStatusLine().getStatusCode() + "通知参数:"
					+ responseContent);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				if (StringUtil.isBlank(responseContent)) {
					// 请求成功，但无参数返回
					return "3000";
				}

				return responseContent;
			} else {
				// 请求状态不为200
				return "3001";
			}
		} catch (NoSuchAlgorithmException noSuex) {
			logger.info("请求发送失败，NoSuchAlgorithmException原因：", noSuex);
			// 请求出现异常
			return "3003";
		} catch (KeyManagementException keyMaEx) {
			logger.info("请求发送失败，KeyManagementException原因：秘钥异常", keyMaEx);
			// 请求出现异常
			return "3003";
		} catch (UnsupportedEncodingException unsuEx) {
			logger.info("请求发送失败，UnsupportedEncodingException原因：", unsuEx);
			// 请求出现异常
			return "3003";
		} catch (ClientProtocolException clPrEx) {
			logger.info("请求发送失败，ClientProtocolException原因：", clPrEx);
			// 请求出现异常
			return "3003";
		} catch (SocketTimeoutException e) {
			logger.info("请求发送失败，SocketTimeoutException原因：", e);
			// 请求超时
			return "3002";
		} catch (IOException ioEx) {
			logger.info("请求发送失败，ClientProtocolException原因：", ioEx);
			// 请求出现异常
			return "3003";
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}

}
