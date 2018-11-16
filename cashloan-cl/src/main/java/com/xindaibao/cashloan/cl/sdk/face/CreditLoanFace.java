package com.xindaibao.cashloan.cl.sdk.face;

import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.cl.sdk.common.CreditLoanHttpAsyncClient;
import com.xindaibao.cashloan.cl.sdk.constant.Constant;
import com.xindaibao.cashloan.cl.sdk.exception.CreditLoanSDKException;
import com.xindaibao.cashloan.cl.sdk.utils.Image2Base64;
import com.xindaibao.cashloan.core.common.util.HttpUtil;
import com.xindaibao.cashloan.core.common.util.code.RSAUtils;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by junlou.liu on 2017/9/7.
 */
public class CreditLoanFace {

    private static final Logger logger = LoggerFactory.getLogger(CreditLoanFace.class);

    /**
     * 人证对比
     * @param url
     * @param apiKey
     * @param secretKey
     * @param filePath1
     * @param filePath2
     * @return
     */
    public static String compare(String url, String apiKey, String secretKey, String filePath1, String filePath2){
        String result = null;
        try {
            String fileBase64_1 = Image2Base64.convert(filePath1);
            String fileBase64_2 = Image2Base64.convert(filePath2);

            String boundaryString = getBoundary();
            Map<String, String> params = new HashMap<>();
            params.put("accept", "*/*");
            params.put("Content-Type", "multipart/form-data; boundary=" + boundaryString);
            params.put("connection", "Keep-Alive");
            params.put("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");

            StringBuffer body = new StringBuffer();
            body.append("--" + boundaryString + "\r\n");
            body.append("Content-Disposition: form-data; name=\"api_key\"\r\n");
            body.append("\r\n");
            body.append(apiKey + "\r\n");

            body.append("--" + boundaryString + "\r\n");
            body.append("Content-Disposition: form-data; name=\"api_secret\"\r\n");
            body.append("\r\n");
            body.append(secretKey + "\r\n");

            body.append("--" + boundaryString + "\r\n");
            body.append("Content-Disposition: form-data; name=\"image_base64_1\"\r\n");
            body.append("\r\n");
            body.append(fileBase64_1 + "\r\n");

            body.append("--" + boundaryString + "\r\n");
            body.append("Content-Disposition: form-data; name=\"image_base64_2\"\r\n");
            body.append("\r\n");
            body.append(fileBase64_2 + "\r\n");

            body.append("--" + boundaryString + "--" + "\r\n");
            body.append("\r\n");


            CreditLoanHttpAsyncClient httpAsyncClient = CreditLoanHttpAsyncClient.getInstance();
            HttpResponse response = httpAsyncClient.getByPost(url, params, body.toString(), Constant.ENTITY_TYPE.BYTE_ARRAY_ENTITY);
            result = EntityUtils.toString(response.getEntity(), Charset.defaultCharset());
            if (response.getStatusLine().getStatusCode() != 200){
                logger.info("人证对比请求失败，原因：{}", result);
            } else {
                logger.info("人证对比成功，结果：{} ", result);
            }
        } catch (IOException e) {
            throw new CreditLoanSDKException(400, Constant.ERROR_IO_EXCEPTION, e);
        } catch (Exception e) {
            throw new CreditLoanSDKException(400, Constant.ERROR_EXCEPTION, e);
        }
        return result;
    }
    
    public static String compare360(String url, String apiKey, String secretKey, String filePath1, String filePath2){
    	
    	return null;
    	
    }
    
    public static String ocridcard(String url, String apiKey, String secretKey, String filePath){
        return ocridcard(url, apiKey, secretKey, filePath, 0);
    }

    /**
     * 证件识别 (会员可设置legality为1)
     * @param url
     * @param apiKey
     * @param secretKey
     * @param filePath
     * @param legality
     * @return
     */
    public static String ocridcard(String url, String apiKey, String secretKey, String filePath, int legality){
        String result = null;
        try {
            String fileBase64 = Image2Base64.convert(filePath);

            String boundaryString = getBoundary();
            Map<String, String> params = new HashMap<>();
            params.put("accept", "*/*");
            params.put("Content-Type", "multipart/form-data; boundary=" + boundaryString);
            params.put("connection", "Keep-Alive");
            params.put("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");

            StringBuffer body = new StringBuffer();
            body.append("--" + boundaryString + "\r\n");
            body.append("Content-Disposition: form-data; name=\"api_key\"\r\n");
            body.append("\r\n");
            body.append(apiKey + "\r\n");

            body.append("--" + boundaryString + "\r\n");
            body.append("Content-Disposition: form-data; name=\"api_secret\"\r\n");
            body.append("\r\n");
            body.append(secretKey + "\r\n");

            body.append("--" + boundaryString + "\r\n");
            body.append("Content-Disposition: form-data; name=\"image_base64\"\r\n");
            body.append("\r\n");
            body.append(fileBase64 + "\r\n");

            body.append("--" + boundaryString + "--" + "\r\n");
            body.append("\r\n");

            CreditLoanHttpAsyncClient httpAsyncClient = CreditLoanHttpAsyncClient.getInstance();
            HttpResponse response = httpAsyncClient.getByPost(url, params, body.toString(), Constant.ENTITY_TYPE.BYTE_ARRAY_ENTITY);
            result = EntityUtils.toString(response.getEntity());
            if (response.getStatusLine().getStatusCode() != 200){
                logger.info("证件识别请求失败，原因：{}", result);
            } else {
                logger.info("证件识别成功，结果：{} ", result);
            }
        } catch (IOException e) {
            throw new CreditLoanSDKException(400, Constant.ERROR_IO_EXCEPTION, e);
        } catch (Exception e) {
            throw new CreditLoanSDKException(400, Constant.ERROR_EXCEPTION, e);
        }
        return result;
    }

    public static String compareRong360(String url, String appid, String privatekey, String livingImgPath, String frontImgPath,
			String realName,String idNo) throws Exception {
    	Map<String,String> params = new HashMap<>();
		params.put("app_id",appid);
        params.put("version", "1.0.0");
        params.put("sign_type", "RSA");
        params.put("format","json");
        params.put("timestamp", String.valueOf(new Date().getTime()));
        JSONObject biz_data = new JSONObject();
        biz_data.put("comparison_type","1");
        biz_data.put("face_image_type","raw_image");
        biz_data.put("idcard_name", realName);
        biz_data.put("idcard_number", idNo);
        biz_data.put("image_ref1",getImgStr(frontImgPath));
        biz_data.put("image", getImgStr(livingImgPath));
        biz_data.put("fail_when_multiple_faces", "0");
        params.put("biz_data", biz_data.toJSONString());
        params.put("method", "tianji.api.faceplus.photocomparison");
        String paramsStr = getSortParams(params);
        //logger.info("待签名数据：" + paramsStr);
        byte[] bytes = RSAUtils.generateSHA1withRSASigature(paramsStr,privatekey, "utf-8");
        String sign =new Base64().encodeToString(bytes);
        // logger.info("签名后数据：" + sign);
        params.put("sign", sign);
        return HttpUtil.postClient(url, params);
	}
    
    private static String getBoundary() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 32; ++i) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws InterruptedException, IOException {
//        String filePath = "/Users/liujunlou/lol/workreport/lol/21505642721_.pic.jpg";
//
//        String result = CreditLoanFace.ocridcard(Constant.FACE_CARD_URL, Constant.TEMP_API_KEY, Constant.TEMP_CECRET_KEY, filePath);
//
//        System.out.println(result);
//
//        synchronized (CreditLoanOCRClient.class) {
//            CreditLoanOCRClient.class.wait();
//        }
    	try {
    		String ret=	rong360crIDcard(null,null,null,"");
		System.out.println(ret);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public static String rong360crIDcard(String url,String appId,String privateKey,String base64Img) throws Exception {
		Map<String,String> params = new HashMap<>();
		params.put("app_id",appId);
        params.put("version", "1.0.0");
        params.put("sign_type", "RSA");
        params.put("format","json");
        params.put("timestamp", String.valueOf(new Date().getTime()));
        JSONObject biz_data = new JSONObject();
        biz_data.put("image", base64Img);
        biz_data.put("legality","0");
        params.put("biz_data", biz_data.toJSONString());
        params.put("method", "tianji.api.faceplus.idcardocr");
        String paramsStr = getSortParams(params);
        //logger.info("待签名数据：" + paramsStr);
        byte[] bytes = RSAUtils.generateSHA1withRSASigature(paramsStr,privateKey, "utf-8");
        String sign =new Base64().encodeToString(bytes);
        // logger.info("签名后数据：" + sign);
        params.put("sign", sign);
        return HttpUtil.postClient(url, params);
	}
	
	/**
     * 按key进行正序排列，之间以&相连
     * <功能描述>
     * @param params
     * @return
     */
    public static String getSortParams(Map<String, String> params) {
        SortedMap<String, String> map = new TreeMap<String, String>();
        for (String key: params.keySet()) {
            map.put(key, params.get(key));
        }
        
        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        String str = "";
        while (iter.hasNext()) {
            String key = iter.next();
            String value = map.get(key);
            str += key + "=" + value + "&";
        }
        if(str.length()>0){
            str = str.substring(0, str.length()-1);
        }
        return str;
    }

	public static String getImgStr(String url) throws Exception {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(url);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Base64().encodeToString(data);
	}
	
}
