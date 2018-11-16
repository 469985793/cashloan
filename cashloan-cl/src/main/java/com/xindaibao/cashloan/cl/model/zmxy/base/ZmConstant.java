package com.xindaibao.cashloan.cl.model.zmxy.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 收集芝麻信息各个接口中的常量
 * Created by syq on 2016/9/12.
 */
public class ZmConstant {
	public static final Logger logger = LoggerFactory.getLogger(ZmConstant.class);

    public static final String CHARSET = "UTF-8";

    public static final String ZmScoreProductCode = "w1010100100000000001";

    public static final String ZmPlatform = "zmop";

    public static final String ZmChannel = "apppc";

    public static final String ZmWatchListProductCode = "w1010100100000000022";

    public static final String ZmAnitFraudProductCode = "w1010100000000000103";

    private static final Properties ZmWatchListRiskTable = new Properties();

    static {
        InputStream inputStream = null;
        BufferedReader bf = null;
        try {
            inputStream  = ZmConstant.class.getResourceAsStream("/zm_risk.properties");
            bf = new BufferedReader(new InputStreamReader(inputStream));
            ZmWatchListRiskTable.load(bf);
        } catch (IOException e) {
            logger.error("加载zm_risk.properties配置文件错误！",e);
        }finally {
            try {
                if(bf != null){
                    bf.close();
                }
                if(inputStream != null){
                    inputStream.close();
                }
            } catch (IOException e) {
            	logger.error(e.getMessage(),e);
            }
        }
    }


    public static String getRiskMessage(String riskCode) {
        return ZmWatchListRiskTable.getProperty(riskCode);
    }
}
