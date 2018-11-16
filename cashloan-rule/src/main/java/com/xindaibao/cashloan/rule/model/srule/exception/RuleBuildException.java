package com.xindaibao.cashloan.rule.model.srule.exception;

/**
 * 规则构建过程中出现的异常
 *
 * Created by syq on 2016/12/8.
 */
public class RuleBuildException extends IllegalStateException{

    public RuleBuildException(String s) {
        super(s);
    }
}
