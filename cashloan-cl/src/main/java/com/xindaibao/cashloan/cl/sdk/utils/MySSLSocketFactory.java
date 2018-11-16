package com.xindaibao.cashloan.cl.sdk.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.protocol.HttpContext;

/**
 * 
 * @author
 * @date  [2016年10月24日]
 */
@SuppressWarnings("deprecation")
public class MySSLSocketFactory extends SSLConnectionSocketFactory {

	public MySSLSocketFactory(SSLContext sslContext,
			X509HostnameVerifier hostnameVerifier) {
		super(sslContext, hostnameVerifier);
	}

	public Socket createSocket(final HttpContext context) throws IOException {
		InetSocketAddress socksAddr = (InetSocketAddress) context
				.getAttribute("socks.address");
		if (socksAddr != null) {
			Proxy proxy = new Proxy(Proxy.Type.SOCKS, socksAddr);
			return new Socket(proxy);
		}
		return new Socket();
	}
}
