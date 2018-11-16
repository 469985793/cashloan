package com.xindaibao.cashloan.core.common.util;

import groovy.util.logging.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {

    /**
     * bean to Map, skip null properties
     * @throws Exception
     */
    public static Map<String, String> beanToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }

        Map<String, String> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    if (value == null) {
                        continue;
                    }
                    map.put(key, value.toString());
                }

            }
        } catch (Exception e) {
            throw new Exception("bean2Map error");
        }

        return map;
    }

}
