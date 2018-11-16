package com.xindaibao.cashloan.core.common.util.crypto.impl;

import java.io.UnsupportedEncodingException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xindaibao.cashloan.core.common.util.Base32;
import com.xindaibao.cashloan.core.common.util.crypto.Crypto;

/**
 *加解密基类
 * 
 */
public abstract class AbstractCryptoImpl implements Crypto {
	
	public static final Logger logger = LoggerFactory.getLogger(AbstractCryptoImpl.class);

	private static final Encoding DefaultEncoding = Encoding.Base32;

	private static final String DefaultCharset = "UTF-8";

	private static final String Base64StringCharset = "ISO-8859-1";

	private class CryptoCipher {
		private Cipher encryptCipher;// 加密
		private Cipher decryptCipher;// 解密

		private CryptoCipher() {
			super();
			this.encryptCipher = getEncryptCipher();
			this.decryptCipher = getDecryptCipher();
		}

		public byte[] encrypt(byte[] bs) {
			try {
				return this.encryptCipher.doFinal(bs);
			} catch (IllegalBlockSizeException e) {
				logger.error(e.getMessage(), e);
			} catch (BadPaddingException e) {
				logger.error(e.getMessage(), e);
			}
			return bs;
		}

		public byte[] dectypt(byte[] bs) {
			try {
				return this.decryptCipher.doFinal(bs);
			} catch (IllegalBlockSizeException e) {
				logger.error(e.getMessage(), e);
			} catch (BadPaddingException e) {
				logger.error(e.getMessage(), e);
			}
			return bs;
		}
	}

	private ThreadLocal<CryptoCipher> local = new ThreadLocal<CryptoCipher>();

	protected CryptoCipher getLocalCipher() {
		CryptoCipher cc = local.get();
		if (cc == null) {
			cc = new CryptoCipher();
			local.set(cc);
		}
		return cc;
	}

	protected AbstractCryptoImpl() {
		super();
	}

	protected abstract Cipher getEncryptCipher();

	protected abstract Cipher getDecryptCipher();

	public String dectypt(String s) {
		return dectypt(s, DefaultEncoding, DefaultCharset);
	}

	public String dectypt(String s, Encoding en) {
		return dectypt(s, en, DefaultCharset);
	}

	public String dectypt(String s, String charset) {
		return dectypt(s, DefaultEncoding, charset);
	}

	public String dectypt(String s, Encoding en, String charset) {
		if (s == null) {
			throw new NullPointerException("dectypt string can't be null");
		}
		if (en == null) {
			throw new NullPointerException("dectypt Encoding can't be null");
		}
		if (charset == null) {
			throw new NullPointerException("dectypt charset can't be null");
		}
		try {
			byte[] bs = en == Encoding.Base32 ? Base32.decode(s) : Base64
					.decodeBase64(s.getBytes(Base64StringCharset));
			bs = this.dectypt(bs);
			return new String(bs, charset);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		return charset;
	}

	public String encrypt(String s) {
		return encrypt(s, DefaultEncoding, DefaultCharset);
	}

	public String encrypt(String s, Encoding en) {
		return encrypt(s, en, DefaultCharset);
	}

	public String encrypt(String s, String charset) {
		return encrypt(s, DefaultEncoding, charset);
	}

	public String encrypt(String s, Encoding en, String charset) {
		if (s == null) {
			throw new NullPointerException("encrypt string can't be null");
		}
		if (en == null) {
			throw new NullPointerException("encrypt Encoding can't be null");
		}
		if (charset == null) {
			throw new NullPointerException("encrypt charset can't be null");
		}
		try {
			byte[] bs = s.getBytes(charset);
			bs = this.encrypt(bs);
			return en == Encoding.Base32 ? Base32.encode(bs) : new String(
					Base64.encodeBase64(bs), Base64StringCharset);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		return charset;
	}

	public byte[] dectypt(byte[] bytes) {
		if (bytes == null) {
			throw new NullPointerException("dectypt bytes can't be null");
		}
		return this.getLocalCipher().dectypt(bytes);
	}

	public byte[] encrypt(byte[] bytes) {
		if (bytes == null) {
			throw new NullPointerException("encrypt bytes can't be null");
		}
		return this.getLocalCipher().encrypt(bytes);
	}
}
