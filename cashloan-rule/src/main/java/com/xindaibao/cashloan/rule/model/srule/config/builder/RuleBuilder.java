package com.xindaibao.cashloan.rule.model.srule.config.builder;

import com.xindaibao.cashloan.rule.model.srule.config.condition.ConditionItem;

/**
 * user interface , show the method to use
 * each type rule must have only one builder ,means builder should be the single also
 * Created by syq on 2016/12/17.
 */
public interface RuleBuilder<T> {





    /**
     * rule condition collect
     *
     * @return
     */
    ConditionItem newConditionItems();


    /**
     * pass three param which rule must be needed
     *
     * @param id
     * @param column
     * @param conditionItem
     * @return
     */
    RuleConfigurer<T> newRule(long id, String column, ConditionItem conditionItem) throws IllegalAccessException, InstantiationException;


}
