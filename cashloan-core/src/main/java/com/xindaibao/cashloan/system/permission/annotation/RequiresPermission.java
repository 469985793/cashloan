package com.xindaibao.cashloan.system.permission.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermission {
	/** 用":"分割权限唯一标识  ,与请求路径一致  */
    String code();
    /** 声明权限点 */
    String name();
}