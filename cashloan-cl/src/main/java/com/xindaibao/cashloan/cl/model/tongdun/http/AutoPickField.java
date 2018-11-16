package com.xindaibao.cashloan.cl.model.tongdun.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解标记哪些属性需要设值
 * Created by syq on 2015/10/13.
 */


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface AutoPickField {

    /**
     * value中的值为json各个节点的key，父子关联关系用'>'字符来区分
     * @return
     */
    String value() default "";

    /**
     * 用户自定义vo类，自动封装成vo类型
     * @return
     */
    @SuppressWarnings("rawtypes")
	Class type() default Object.class;

}
