package com.xindaibao.cashloan.cl.sdk.common;

import com.xindaibao.cashloan.cl.sdk.constant.Constant;
import com.xindaibao.cashloan.cl.sdk.exception.CreditLoanSDKException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthSchemeProvider;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Lookup;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.*;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.nio.conn.NoopIOSessionStrategy;
import org.apache.http.nio.conn.SchemeIOSessionStrategy;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * http请求工厂类
 * 注：异步的HTTP请求对象，可设置代理
 * Created by junlou.liu on 2017/9/7.
 */
public class CreditLoanHttpAsyncClient {
    private static final Logger logger = LoggerFactory.getLogger(CreditLoanHttpAsyncClient.class);
    // 请求连接超时时间
    private final static int CONNECT_TIME_OUT = 2000;
    private final static int CONNECT_REQUEST_OUT = 5000;
    // 请求读取超时时间
    private final static int READ_OUT_TIME = 30000;

    private final static int DEFAULT_POOL_SIZE = 500;

    private static CreditLoanHttpAsyncClient client = new CreditLoanHttpAsyncClient();
    // 异步httpclient
    private CloseableHttpAsyncClient asyncHttpClient;

    private CreditLoanHttpAsyncClient(){ this.initHttpAsyncClient(); }

    public static CreditLoanHttpAsyncClient getInstance(){
        return client;
    }

    // 初始化异步请求工厂类
    private void initHttpAsyncClient(){
        try {
            // http 代理相关参数
//            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
//                    username, password);
//            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//            credentialsProvider.setCredentials(AuthScope.ANY, credentials);
            // 配置请求设置
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(CONNECT_TIME_OUT)                // 设置连接超时
                    .setConnectionRequestTimeout(CONNECT_REQUEST_OUT)   // 设置请求等待超时
                    .setSocketTimeout(READ_OUT_TIME)                    // 请求返回超时
                    .setCircularRedirectsAllowed(false)
                    .build();
            SSLContext sslContext = SSLContexts.createDefault();
            // 设置协议http和https对应的处理socket链接工厂的对象
            Registry<SchemeIOSessionStrategy> sessionStrategyRegistry = RegistryBuilder.<SchemeIOSessionStrategy>create()
                    .register("http", NoopIOSessionStrategy.INSTANCE)
                    .register("https", new SSLIOSessionStrategy(sslContext))
                    .build();
            // 配置io线程
            IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                    .setIoThreadCount(Runtime.getRuntime().availableProcessors()).build();
            // 设置连接池大小
            ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
            PoolingNHttpClientConnectionManager nConManager = new PoolingNHttpClientConnectionManager(ioReactor, sessionStrategyRegistry);
            nConManager.setMaxTotal(DEFAULT_POOL_SIZE);
            nConManager.setDefaultMaxPerRoute(50);

            //
            ConnectionConfig connectionConfig = ConnectionConfig.custom()
                    .setMalformedInputAction(CodingErrorAction.IGNORE)
                    .setUnmappableInputAction(CodingErrorAction.IGNORE)
                    .setCharset(Charset.defaultCharset())
                    .build();

            Lookup<AuthSchemeProvider> authSchemeProvider = RegistryBuilder.<AuthSchemeProvider>create()
                    .register(AuthSchemes.BASIC, new BasicSchemeFactory())
                    .register(AuthSchemes.DIGEST, new DigestSchemeFactory())
                    .register(AuthSchemes.NTLM, new NTLMSchemeFactory())
                    .register(AuthSchemes.SPNEGO, new SPNegoSchemeFactory())
                    .register(AuthSchemes.KERBEROS, new KerberosSchemeFactory())
                    .build();
            nConManager.setDefaultConnectionConfig(connectionConfig);

            asyncHttpClient = HttpAsyncClients.custom()
                    .setConnectionManager(nConManager)
//                    .setDefaultCredentialsProvider()  // 代理设置
//                    .setProxy(new HttpHost(host, port))
                    .setDefaultCookieStore(new BasicCookieStore())
                    .setDefaultAuthSchemeRegistry(authSchemeProvider)
                    .build();
            asyncHttpClient.start();
        } catch (IOReactorException e) {
            e.printStackTrace();
        }
    }

    public HttpResponse get4HttpPost(String url, Object requestBody, Object params){
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("accept", "*/*");
            httpPost.addHeader("Content-Type", "multipart/form-data; boundary=" + params);
            httpPost.addHeader("connection", "Keep-Alive");
            httpPost.addHeader("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
            if (requestBody != null){
                ByteArrayEntity entity = new ByteArrayEntity(requestBody.toString().getBytes());
//                StringEntity entity = new StringEntity(requestBody, Charset.defaultCharset());
                httpPost.setEntity(entity);
            }
            Future<HttpResponse> responseFuture = asyncHttpClient.execute(httpPost, null);
            return responseFuture.get();
        } catch (InterruptedException e) {
            logger.error("异步请求工厂线程中断异常");
            throw new CreditLoanSDKException(415, "异步请求工厂线程中断异常", e.getCause());
        } catch (ExecutionException e) {
            logger.error("异步请求运行异常");
            throw new CreditLoanSDKException(416, "异步请求运行异常", e.getCause());
        } catch (Exception e){
            logger.error("异步请求工厂类异常");
            throw new CreditLoanSDKException(417, "异步请求工厂类异常", e.getCause());
        }
    }

    public HttpResponse getByPost(String url, Map<String, String> headers, Object body, String entityType){
        try {
            HttpPost httpPost = new HttpPost(url);
            if (headers != null) {
                for (Map.Entry entry : headers.entrySet()) {
                    httpPost.addHeader(entry.getKey().toString(), entry.getValue().toString());
                }
            }
            switch (entityType){
                case Constant.ENTITY_TYPE.URL_ENCODED_FORM_ENTITY:
                    List<NameValuePair> pairs = new ArrayList<>();
                    if (body != null){
                        if (body instanceof HashMap){
                            for (Map.Entry entry : ((HashMap<String,String>) body).entrySet()){
                                pairs.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
                            }
                            httpPost.setEntity(new UrlEncodedFormEntity(pairs, Charset.defaultCharset()));
                        }
                    }
                    break;
                case Constant.ENTITY_TYPE.STRING_ENTITY:
                    if (body != null) {
                        StringEntity stringEntity = new StringEntity(body.toString(), Charset.defaultCharset());
                        httpPost.setEntity(stringEntity);
                    }
                    break;
                case Constant.ENTITY_TYPE.BYTE_ARRAY_ENTITY:
                    ByteArrayEntity entity = new ByteArrayEntity(body.toString().getBytes());
                    httpPost.setEntity(entity);
                    break;
                default:
                    logger.error("暂不支持");
                    throw new CreditLoanSDKException(400, "请求的请求体类型暂不支持", null);
            }
            Future<HttpResponse> responseFuture = asyncHttpClient.execute(httpPost, null);
            return responseFuture.get();
        } catch (InterruptedException e) {
            logger.error("异步请求工厂线程中断异常");
            throw new CreditLoanSDKException(415, "异步请求工厂线程中断异常", e.getCause());
        } catch (ExecutionException e) {
            logger.error("异步请求运行异常");
            throw new CreditLoanSDKException(416, "异步请求运行异常", e.getCause());
        } catch (Exception e) {
            logger.error("异步请求工厂类异常");
            throw new CreditLoanSDKException(417, "异步请求工厂类异常", e.getCause());
        }
    }


}
