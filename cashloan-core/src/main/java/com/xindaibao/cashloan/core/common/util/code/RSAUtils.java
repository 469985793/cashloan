package com.xindaibao.cashloan.core.common.util.code;

import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

import com.xindaibao.cashloan.core.common.util.base64.Base64Util;


/**
 * RSA相关工具类
 * @author
 * @date  [2016年3月29日]
 */
public class RSAUtils {

    /**
     * 私钥签名
     * <功能描述>
     * @param src
     * @param priKey
     * @return
     * @throws Exception 
     */
    public static byte[] generateSHA1withRSASigature(String src, String priKey, String charset) throws Exception
    {
    	Signature sigEng = Signature.getInstance("SHA1withRSA");
    	byte[] pribyte = Base64Util.base64DecodeToArray(priKey);
    	PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pribyte);
    	KeyFactory fac = KeyFactory.getInstance("RSA");
    	RSAPrivateKey privateKey = (RSAPrivateKey) fac.generatePrivate(keySpec);
    	sigEng.initSign(privateKey);
    	sigEng.update(src.getBytes(charset));
    	byte[] signature = sigEng.sign();
    	return signature;
    }




}