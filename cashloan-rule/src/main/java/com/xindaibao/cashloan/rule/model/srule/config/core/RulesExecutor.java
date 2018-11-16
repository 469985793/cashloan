package com.xindaibao.cashloan.rule.model.srule.config.core;


import java.util.List;

import com.xindaibao.cashloan.rule.model.srule.config.rule.Rule;

/**
 * 规则结果执行器
 * Created by syq on 2016/12/13.
 */
public class RulesExecutor {


    /**
     * 产出一个规则与规则之间没有关系的Logic执行器
     *
     * @return
     */
    public static RulesLogic newNoneRelationRulesLogic() {
        return new NoneRelationRulesLogic();
    }


    private static class NoneRelationRulesLogic extends AbstractRulesLogic {


        @Override
        public boolean doLogic(List<Rule> list) {
            this.ruleList = list;
            /*因为各个规则之间没有关系，所以不做算法比对*/
            for (Rule rule : list) {
                boolean matchResult = rule.beginMatch();
                this.resultMap.put(rule.getId(), matchResult);
            }
            return true;
        }
    }


    private static class ComplexRelationRulesLogic extends AbstractRulesLogic {


        @Override
        public boolean doLogic(List<Rule> list) {
            /*TODO 待以后实现*/
            return false;
        }
    }


}
