package com.xindaibao.cashloan.core.common.util.base64;

import java.io.IOException;

import com.xindaibao.cashloan.core.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 工具类-64位加密解密工具类
 */
public class Base64Util {
	
	public static final Logger logger = LoggerFactory.getLogger(Base64Util.class);
	
	/**
	 * 字符串Base64位加密
	 * @param str
	 * @return
	 */
	public static String base64Encode(String str) {
		Base64Encoder encoder = new Base64Encoder();
		String result = encoder.encode(str.getBytes());
		return result;
	}
	
	/**
	 * 字节码数组加密
	 * @param b
	 * @return
	 */
	public static String base64Encode(byte[] b) {
		Base64Encoder encoder = new Base64Encoder();
		String result = encoder.encode(b);
		return result;
	}
	
	/**
	 * 字符串Base64位解密
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static String base64Decode(String str) {
		try {
			if (StringUtil.isNotBlank(str)) {
				Base64Decoder decoder = new Base64Decoder();
				String result = decoder.decodeStr(str);
				return result;
			} else {
				return null;
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}
	
	/**
	 * 字符串解密成数组
	 * @param str
	 * @return
	 */
	public static byte[] base64DecodeToArray(String str) {
		try {
			if (StringUtil.isNotBlank(str)) {
				Base64Decoder decoder = new Base64Decoder();
				return decoder.decodeBuffer(str);
			} else {
				return null;
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}
