package com.xindaibao.creditrank.cr.model.srule.exception;

/**
 * Created by syq on 2016/12/14.
 */
@SuppressWarnings("serial")
public class RuleNotFoundException extends RuntimeException{

    public RuleNotFoundException(String message) {
        super(message);
    }
}
