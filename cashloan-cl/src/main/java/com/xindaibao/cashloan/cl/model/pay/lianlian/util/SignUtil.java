package com.xindaibao.cashloan.cl.model.pay.lianlian.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.xindaibao.cashloan.cl.model.pay.lianlian.constant.LianLianConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.core.common.context.Global;
import com.xindaibao.cashloan.core.common.util.base64.Base64Util;

public class SignUtil {

	private static Logger logger = Logger.getLogger(SignUtil.class);
	
	private static SignUtil instance;

	public static SignUtil getInstance() {
		if (instance == null)
			return new SignUtil();
		return instance;
	}

	
	public static String sign(String priKey, String signStr) {
		byte[] signed = null;
		
			try {
				PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64Util.base64DecodeToArray(priKey));

				KeyFactory keyf = KeyFactory.getInstance("RSA");
				PrivateKey myprikey = keyf.generatePrivate(priPKCS8);

				Signature signet = Signature.getInstance("MD5withRSA");
				signet.initSign(myprikey);
				signet.update(signStr.getBytes("UTF-8"));
				signed = signet.sign();
			} catch (InvalidKeyException | NoSuchAlgorithmException
					| InvalidKeySpecException | SignatureException
					| UnsupportedEncodingException e) {
				logger.error("签名失败," + e.getMessage(),e);
			}
		

		return new String(
				org.apache.commons.codec.binary.Base64.encodeBase64(signed));
	}

	public static boolean checksign(String pubKeyStr, String oidStr,String signedStr) {
		
			try {
				X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(
						Base64Util.base64DecodeToArray(pubKeyStr));
				KeyFactory keyFactory = KeyFactory.getInstance("RSA");
				PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
				byte[] signed = Base64Util.base64DecodeToArray(signedStr);
				Signature signetcheck = Signature.getInstance("MD5withRSA");
				signetcheck.initVerify(pubKey);
				signetcheck.update(oidStr.getBytes("UTF-8"));
				return signetcheck.verify(signed);
			} catch (InvalidKeyException | NoSuchAlgorithmException
					| InvalidKeySpecException | SignatureException
					| UnsupportedEncodingException e) {
				logger.error("签名失败," + e.getMessage(),e);
			}
		
		return false;
	}
	
	
	
	public static String genRSASign(String jsonStr) {
		// 生成待签名串
		String sign_src = genSignData(jsonStr);
		
		JSONObject reqObj =  JSON.parseObject(jsonStr);
		logger.debug("商户[" + reqObj.getString("oid_partner") + "]待签名原串："+ sign_src);
		
		return SignUtil.sign(Global.getValue(LianLianConstant.PRIVATE_KEY),sign_src);
	}
	
	/**
	 * 生成待签名串
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String genSignData(String jsonStr) {
		JSONObject jsonObject =  JSON.parseObject(jsonStr);
		StringBuilder content = new StringBuilder();

		// 按照key做首字母升序排列
		List<String> keys = new ArrayList<String>(jsonObject.keySet());
		Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			// sign 和ip_client 不参与签名
			if ("sign".equals(key)) {
				continue;
			}
			String value = (String) jsonObject.getString(key);
			// 空串不参与签名
			if (StringUtils.isEmpty(value)) {
				continue;
			}
			content.append((i == 0 ? "" : "&") + key + "=" + value);

		}
		String signSrc = content.toString();
		if (signSrc.startsWith("&")) {
			signSrc = signSrc.replaceFirst("&", "");
		}
		return signSrc;
	}

}
