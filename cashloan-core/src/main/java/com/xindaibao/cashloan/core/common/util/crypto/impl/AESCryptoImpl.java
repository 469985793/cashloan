package com.xindaibao.cashloan.core.common.util.crypto.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * AES算法加解密实现
 * 
 */
public class AESCryptoImpl extends AbstractCryptoImpl {
	
	public static final Logger logger = LoggerFactory.getLogger(AESCryptoImpl.class);

	private static final String algorithm = "AES";

	private static final String transformation = "AES/CBC/PKCS5Padding";

	private String key = "5A82fh-e390V.qw8";

	private String ivParameter = "0102030405060708";

	public void setKey(String key) {
		this.key = key;
	}

	public void setIvParameter(String ivParameter) {
		this.ivParameter = ivParameter;
	}

	public AESCryptoImpl() {
		super();
	}

	private static AESCryptoImpl defaultInstance = new AESCryptoImpl();

	public static AESCryptoImpl getDefault() {
		return defaultInstance;
	}

	@Override
	protected Cipher getDecryptCipher() {
		Cipher cipher = null;
		try {
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), algorithm);
			cipher = Cipher.getInstance(transformation);
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
		} catch (InvalidKeyException e) {
			logger.error(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
		} catch (NoSuchPaddingException e) {
			logger.error(e.getMessage(), e);
		} catch (InvalidAlgorithmParameterException e) {
			logger.error(e.getMessage(), e);
		}
		return cipher;
	}

	@Override
	protected Cipher getEncryptCipher() {
		Cipher cipher = null;
		try {
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), algorithm);
			cipher = Cipher.getInstance(transformation);
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
		} catch (InvalidKeyException e) {
			logger.error(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
		} catch (NoSuchPaddingException e) {
			logger.error(e.getMessage(), e);
		} catch (InvalidAlgorithmParameterException e) {
			logger.error(e.getMessage(), e);
		}
		return cipher;
	}

}
