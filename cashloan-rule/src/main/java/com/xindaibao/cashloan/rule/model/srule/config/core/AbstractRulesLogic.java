package com.xindaibao.cashloan.rule.model.srule.config.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xindaibao.cashloan.rule.model.srule.config.rule.Rule;
import com.xindaibao.cashloan.rule.model.srule.exception.RuleNotFoundException;

/**
 * Created by syq on 2016/12/14.
 */
public abstract class AbstractRulesLogic implements RulesLogic {


    protected List<Rule> ruleList;

    protected Map<Long, Boolean> resultMap = new HashMap<Long, Boolean>();

    @Override
    public Map<Long, Boolean> rulesResult() throws RuleNotFoundException {
        if (ruleList == null || ruleList.size() == 0) {
            throw new RuleNotFoundException("can not found rules to be matched! ");
        }
        return resultMap;
    }

}
