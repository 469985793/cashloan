package com.xindaibao.cashloan.cl.model.tongdun.util;

import org.apache.log4j.Logger;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * 加密工具类
 * Created by syq on 2016/5/27.
 */
public class EncryptUtil {

	private static final Logger logger = Logger.getLogger(EncryptUtil.class);
	
    /**
     * 将参数拼装成url地址
     *
     * @param paramMap
     * @return
     */
    public static MultiValueMap<String, Object> postFormAndEncoder(Map<String, Object> paramMap, boolean urlEncode) {
        MultiValueMap<String, Object> encodeMap = new LinkedMultiValueMap<>();
        if (paramMap != null) {
            List<Map.Entry<String, Object>> infoIds = new ArrayList<>(paramMap.entrySet());
            /*对map key 进行排序*/
            Collections.sort(infoIds, new Comparator<Map.Entry<String, Object>>() {
                public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                    return (o1.getKey()).compareTo(o2.getKey());
                }
            });
            try {
                for (Map.Entry<String, Object> set : infoIds) {
                    encodeMap.add(set.getKey(), urlEncode ? URLEncoder.encode((String) set.getValue(), "utf-8") : String.valueOf(set.getValue()));
                }
            } catch (UnsupportedEncodingException e) {
            	logger.error(e);
            }
        }
        return encodeMap;
    }


    public static String urlParamStrAndEncoder(Map<String, Object> paramMap, boolean urlEncode) {
    	StringBuilder sb = new StringBuilder();
        if (paramMap != null) {
            List<Map.Entry<String, Object>> infoIds = new ArrayList<>(paramMap.entrySet());
            /*对map key 进行排序*/
            Collections.sort(infoIds, new Comparator<Map.Entry<String, Object>>() {
                public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                    return (o1.getKey()).compareTo(o2.getKey());
                }
            });
            try {
                int n = 0;
                for (Map.Entry<String, Object> set : infoIds) {
                    if (n == 0) {
                        n++;
                        if (urlEncode) {
                            sb.append(set.getKey() + "=" + URLEncoder.encode((String) set.getValue(), "utf-8"));
                        } else {
                            sb.append(set.getKey() + "=" + set.getValue());
                        }
                    } else {
                        if (urlEncode) {
                            sb.append("&" + set.getKey() + "=" + URLEncoder.encode((String) set.getValue(), "utf-8"));
                        } else {
                            sb.append("&" + set.getKey() + "=" + set.getValue());
                        }
                    }
                }
            } catch (UnsupportedEncodingException e) {
            	logger.error(e);
            }
        }
        return sb.toString();
    }

}
