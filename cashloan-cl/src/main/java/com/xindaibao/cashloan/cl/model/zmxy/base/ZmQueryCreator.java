package com.xindaibao.cashloan.cl.model.zmxy.base;

import java.lang.reflect.Constructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xindaibao.cashloan.core.common.context.Global;

/**
 * 工厂类
 * 为每个接口创建实例
 * Created by syq on 2016/9/14.
 */
public class ZmQueryCreator {

	public static final Logger logger = LoggerFactory.getLogger(ZmQueryCreator.class);
    private final String privateKey;

    private final String zhimaPublicKey;

    private final String appId;

    
    public ZmQueryCreator() {
        this.privateKey = Global.getValue("zhima_private_key");
        this.zhimaPublicKey = Global.getValue("zhima_public_key");
        this.appId = Global.getValue("zhima_app_id");
    }
    
    private ZmQueryCreator(String privateKey, String zhimaPublicKey, String appId) {
        this.privateKey = privateKey;
        this.zhimaPublicKey = zhimaPublicKey;
        this.appId = appId;
    }

    public static ZmQueryCreator newCreator(String privateKey, String zhimaPublicKey, String appId) {
        return new ZmQueryCreator(privateKey, zhimaPublicKey, appId);
    }

    public <T extends BaseQuery> T create(Class<T> clazz) {
        T t = null;
        try {
            Constructor<T> constructor = clazz.getConstructor(String.class, String.class, String.class);
            t = constructor.newInstance(privateKey, zhimaPublicKey, appId);
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
        }
        return t;
    }


}
