package com.xindaibao.cashloan.cl.model.tongdun.http;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.ReflectionUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AutoPickField注解的生成器
 * Created by syq on 2015/10/13.
 */
@SuppressWarnings("rawtypes")
public class JsonFieldAutoPickGenerator {

    private static final Map<String, Field> fieldCache = new ConcurrentHashMap<>();
    private static Logger logger = Logger.getLogger(JsonFieldAutoPickGenerator.class);

    /**
     * 解析请求返回的json字符串，根据OsaResponse子类中的属性注解自动设值
     * @param jsonStr 请求后获取的json字符串
     * @param clazz   OsaResponse子类实例
     * @param <T>     OsaResponse子类型
     * @return
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws Exception
     */
    public static <T> T autoSetter(final String jsonStr, Class<T> clazz) {
        T rsp = null;
        final JSONObject rootObject = JSONObject.parseObject(jsonStr);
        try {
			rsp = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			logger.error(e1);
		}
        final T finalRsp = rsp;
        ReflectionUtils.doWithFields(clazz, new ReflectionUtils.FieldCallback() {

            private String autoPickFieldAnnoValue;

            private String fieldTypeName;

			private Class customClassType;

            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                AutoPickField autoPickField = field.getAnnotation(AutoPickField.class);
                if (autoPickField != null) {
                    field.setAccessible(true);//设置可动态设值
                    this.autoPickFieldAnnoValue = autoPickField.value();
                    this.fieldTypeName = field.getType().getSimpleName();
                    this.customClassType = autoPickField.type();
                    Object result = scanEachJsonNodeKey();
                    field.set(finalRsp, result);
                }
            }

            private Object scanEachJsonNodeKey(){
                JsonParserContext context = new JsonParserContext();
                context.putRootJsonNode(rootObject);
                context.setCustomClassType(this.customClassType);
                Object obj = null;
                try {
                    List<String> nodes = getKeyNameOnEachJsonNode();
                    if(nodes.size() == 0){
                        obj = rootObject;
                    }else{
                        obj = context.getTargetValue(getKeyNameOnEachJsonNode(),this.fieldTypeName);
                    }
                } catch (Exception e) {
                	logger.error("异常,",e);
                }
                return obj;
            }


            private List<String> getKeyNameOnEachJsonNode() {
                return Arrays.asList(StringUtils.split(this.autoPickFieldAnnoValue, ">"));//根据"->"符号来分隔各个节点的名称
            }

        });
        return rsp;
    }


    public static <T> T toGenerator(Map<String, Object> params, Class<T> clazz) throws HttpRestException {
        T rsp = null;
        try {
            rsp = clazz.newInstance();
            BeanInfo e = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] pds = e.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                Method method = pd.getWriteMethod();
                if (method != null) {
                    //判断该属性上是否有注解
                    Field field = getField(clazz, pd);
                    AutoPickField autoPickField = field.getAnnotation(AutoPickField.class);
                    if (autoPickField != null && StringUtils.isNotBlank(autoPickField.value())) {
                        //说明是需要进行设值的属性，从params中找出该属性的值
                        //获取该属性的类型
                        Object param = params.get(autoPickField.value());
                        method.invoke(rsp, param);
                    }
                }
            }
            return rsp;
        } catch (Exception e) {
            throw new HttpRestException(e);
        }
    }


    private static Field getField(Class<?> clazz, PropertyDescriptor pd) {
        String key = clazz.getName() + "_" + pd.getName();
        Field field = fieldCache.get(key);
        if (field == null) {
            try {
                field = clazz.getDeclaredField(pd.getName());
            } catch (NoSuchFieldException e) {
                //如果类中没有次属性，则在父类中查找
                try {
					field = clazz.getSuperclass().getDeclaredField(pd.getName());
				} catch (NoSuchFieldException | SecurityException e1) {
					logger.error("异常,",e);
				}
            } catch (SecurityException e) {
            	logger.error("异常,",e);
            }
            fieldCache.put(key, field);
        }
        return field;
    }


}
