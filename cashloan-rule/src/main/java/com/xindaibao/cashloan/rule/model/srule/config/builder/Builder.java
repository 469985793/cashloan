package com.xindaibao.cashloan.rule.model.srule.config.builder;

import com.xindaibao.cashloan.rule.model.srule.config.rule.Rule;
import com.xindaibao.cashloan.rule.model.srule.config.rule.RuleBasic;

/**
 * Created by syq on 2016/12/18.
 */
public interface Builder {
	

    /**
     * storing the rules which  produced from each thread
     */
    ThreadLocal<RuleBasic> threadLocalRules = new ThreadLocal<RuleBasic>();


    /**
     * return the rule want to build
     *
     * @return
     * @throws Exception
     */
    Rule build() throws Exception;


}
