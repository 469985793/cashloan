package com.xindaibao.cashloan.cl.sdk.face.ocr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xindaibao.cashloan.cl.sdk.common.CreditLoanHttpAsyncClient;
import com.xindaibao.cashloan.cl.sdk.constant.Constant;
import com.xindaibao.cashloan.cl.sdk.utils.Base64Util;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 本客户端对接Face++证件识别
 * 默认采用base64编码的二进制图片数据请求
 * Created by junlou.liu on 2017/9/8.
 */
public class CreditLoanOCRClient {
    private static final Logger logger = LoggerFactory.getLogger(CreditLoanOCRClient.class);

    private static CreditLoanHttpAsyncClient httpAsyncClient;
    private static CreditLoanOCRClient client = new CreditLoanOCRClient();
    private CreditLoanOCRClient(){ httpAsyncClient = CreditLoanHttpAsyncClient.getInstance(); }
    public static CreditLoanOCRClient getClient(){
        return client;
    }

    public String getByImageBase64(String apiKey, String apiSecret, String imageBase64){
        return this.getByImageBase64(apiKey,apiSecret,imageBase64,0);
    }

    /**
     * 根据文件或二进制文件来请求第三方证件识别
     * @param apiKey
     * @param apiSecret
     * @param imageFile
     * @param legality
     * @return
     */
    public String getByImageFile(String apiKey, String apiSecret, String imageFile, int legality){
        try {
            String boundaryString = getBoundary();
            StringBuffer body = new StringBuffer();
            body.append("--" + boundaryString + "\r\n");
            body.append("Content-Disposition: form-data; name=\"api_key\"\r\n");
            body.append("\r\n");
            body.append(apiKey + "\r\n");
            body.append("--" + boundaryString + "\r\n");
            body.append("Content-Disposition: form-data; name=\"api_secret\"\r\n");
            body.append("\r\n");
            body.append(apiSecret + "\r\n");

            body.append("--" + boundaryString + "\r\n");
            body.append("Content-Disposition: form-data; name=\"image_file\"; filename=\"" + encode(" ") + "\"\r\n");
            body.append("\r\n");
            body.append(imageFile);
            body.append("\r\n");
            body.append("--" + boundaryString + "--" + "\r\n");
            body.append("\r\n");

            HttpResponse response = httpAsyncClient.get4HttpPost(Constant.FACE_CARD_URL, body.toString(), boundaryString);
            int code = response.getStatusLine().getStatusCode();
            String content = EntityUtils.toString(response.getEntity());
            JSONObject object = JSON.parseObject(content);
            if (code >= 400){
                logger.error(object.getString("error_message"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *
     * @param apiKey
     * @param apiSecret
     * @param imageBase64
     * @param legality  只有正式 API Key 能够调用此参数返回分类结果，免费 API Key 调用后无法返回分类结果,1: 返回; 0: 不返回; 注：默认值为 0
     * @return
     */
    public String getByImageBase64(String apiKey, String apiSecret, String imageBase64, int legality){
        String result = null;
        try {
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
            body.append(apiSecret + "\r\n");

            body.append("--" + boundaryString + "\r\n");
            body.append("Content-Disposition: form-data; name=\"image_base64\"\r\n");
            body.append("\r\n");
            body.append(imageBase64);
            body.append("\r\n");
            body.append("--" + boundaryString + "--" + "\r\n");
            body.append("\r\n");

            HttpResponse response = httpAsyncClient.getByPost(Constant.FACE_CARD_URL, params, body.toString(), Constant.ENTITY_TYPE.BYTE_ARRAY_ENTITY);
            int code = response.getStatusLine().getStatusCode();
            result = EntityUtils.toString(response.getEntity());
            JSONObject object = JSON.parseObject(result);
            if (code >= 400){
                logger.error(object.getString("error_message"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String getBoundary() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 32; ++i) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
        }
        return sb.toString();
    }

    private static String encode(String value) throws Exception{
        return URLEncoder.encode(value, "UTF-8");
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        String filename = "/Users/liujunlou/lol/workreport/lol/21505642721_.pic.jpg";

        FileChannel fc = null;
        try {
            fc = new RandomAccessFile(filename, "r").getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0,
                    fc.size()).load();
            System.out.println(byteBuffer.isLoaded());
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            // 转base64
            String fileBase64 = Base64Util.encode(result);
            CreditLoanOCRClient client1 = CreditLoanOCRClient.getClient();
            String response = client1.getByImageBase64(Constant.TEMP_API_KEY, Constant.TEMP_CECRET_KEY, fileBase64);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                fc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        synchronized (CreditLoanOCRClient.class) {
            CreditLoanOCRClient.class.wait();
        }
    }
}
