package com.xindaibao.creditrank.cr.model.srule.config.condition;

import com.xindaibao.creditrank.cr.model.srule.utils.ConditionOpt;

/**
 * Created by syq on 2016/12/11.
 */
public interface Condition<T> {

    /**
     * 设置操作符
     *
     * @param opt
     * @return
     */
    Condition<T> opt(ConditionOpt opt);


    /**
     * 设置条件的值
     *
     * @param t
     * @return
     */
    Condition<T> value(T t);


    ConditionOpt getOpt();


    T getValue();

}
