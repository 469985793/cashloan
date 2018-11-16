package com.xindaibao.cashloan.core.common.util.crypto;

/**
 * 加解密接口
 */
public interface Crypto {

	public enum Encoding {
		Base64, Base32
	}

	/**
	 * 加密string
	 * 
	 * @param s
	 *            不能为null
	 * @return 加密后的string
	 */
	public String encrypt(String s);

	/**
	 * 加密string
	 * 
	 * @param s
	 *            不能为null
	 * @param en
	 *            编码方式
	 * @return
	 */
	public String encrypt(String s, Encoding en);

	/**
	 * 加密string
	 * 
	 * @param s
	 *            不能为null
	 * @param encoding
	 *            string的编码字符集
	 * @return 加密后的string
	 */
	public String encrypt(String s, String charset);

	/**
	 * 加密string
	 * 
	 * @param s
	 *            不能为null
	 * @param en
	 *            编码方式
	 * @param charset
	 *            string的编码字符集
	 * @return
	 */
	public String encrypt(String s, Encoding en, String charset);

	/**
	 * 对string串进行解密
	 * 
	 * @param s
	 *            不能为null
	 * @return 解密后的string
	 */
	public String dectypt(String s);

	/**
	 * 对string串进行解密
	 * 
	 * @param s
	 *            不能为null
	 * @param en
	 *            编码方式
	 * @return
	 */
	public String dectypt(String s, Encoding en);

	/**
	 * 对string串使用指定的编码进行解密
	 * 
	 * @param s
	 *            不能为null
	 * @param encoding
	 *            字符集
	 * @return 解密后的string
	 */
	public String dectypt(String s, String encoding);

	/**
	 * 对string串进行解密
	 * 
	 * @param s
	 *            不能为null
	 * @param en
	 *            编码
	 * @param encoding
	 *            字符集
	 * @return
	 */
	public String dectypt(String s, Encoding en, String encoding);

	public byte[] encrypt(byte[] bytes);

	public byte[] dectypt(byte[] bytes);

}
