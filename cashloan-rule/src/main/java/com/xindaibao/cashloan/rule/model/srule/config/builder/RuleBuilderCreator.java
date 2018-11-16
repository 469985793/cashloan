package com.xindaibao.cashloan.rule.model.srule.config.builder;


import static com.xindaibao.cashloan.rule.model.srule.utils.ConditionOpt.INCLUDE;
import static com.xindaibao.cashloan.rule.model.srule.utils.ConditionOpt.NOT_INCLUDE;

import java.util.HashSet;
import java.util.Set;

import com.xindaibao.cashloan.rule.model.srule.config.condition.Condition;
import com.xindaibao.cashloan.rule.model.srule.config.rule.AbstractRule;
import com.xindaibao.cashloan.rule.model.srule.config.rule.RuleBasic;
import com.xindaibao.cashloan.rule.model.srule.exception.RuleValueException;
import com.xindaibao.cashloan.rule.model.srule.utils.ConditionOpt;
import com.xindaibao.cashloan.rule.model.srule.utils.RulePolicy;

/**
 * builder factory single instance
 * Created by syq on 2016/12/17.
 */
public class RuleBuilderCreator {


    private static final RuleBuilder<String> stringRuleBuilder = new StringRuleBuilder();


    private static final RuleBuilder<Double> numRuleBuilder = new NumRuleBuilder();


    private RuleBuilderCreator() {

    }


    public static RuleBuilder<String> stringRuleBuilder() {
        return stringRuleBuilder;
    }


    public static RuleBuilder<Double> numRuleBuilder() {
        return numRuleBuilder;
    }


    private static final class StringRuleBuilder extends SimpleRuleBuilder<String, StringRule> {

        @Override
        protected RuleBasic<String> concrete() {
            return new StringRule();
        }
    }


    private static final class NumRuleBuilder extends SimpleRuleBuilder<Double, NumRule> {


        @Override
        protected RuleBasic<Double> concrete() {
            return new NumRule();
        }
    }


    final static class StringRule extends AbstractRule<String> {


        @Override
        public boolean beginMatch() throws RuleValueException {
            Set<Boolean> matchSet = new HashSet<Boolean>();
            for (Condition<String> condition : this.conditions) {
                ConditionOpt opt = condition.getOpt();
                String value = condition.getValue();

                if(this.matchTo == null){
                    throw new RuleValueException("can not found mathTo value!");
                }
                if (opt == INCLUDE) {
                    matchSet.add(value.contains(matchTo));
                    continue;
                }

                if (opt == NOT_INCLUDE) {
                    matchSet.add(!value.contains(matchTo));
                    continue;
                }

                if (this.preLoad == null || this.preLoad.size() == 0) {
                    throw new RuleValueException("StringRule must have preLoad! ");
                }

                if (this.preLoad != null && !this.preLoad.containsKey(value)) {
                    throw new RuleValueException("value : " + value + " is not in the preLoad! ");
                }

                Integer valueSortId = preLoad.get(value);

                Integer matchSortId = preLoad.get(matchTo);
                if (matchSortId == null) {
                    throw new RuleValueException("matchTo value : " + matchTo + " is not found! ");
                }

                switch (opt) {
                    case BIGGER:
                        matchSet.add(matchSortId > valueSortId);
                        break;
                    case BIGGER_EQUAL:
                        matchSet.add(matchSortId >= valueSortId);
                        break;
                    case SMALL:
                        matchSet.add(matchSortId < valueSortId);
                        break;
                    case SMALL_EQUAL:
                        matchSet.add(matchSortId <= valueSortId);
                        break;
                    case EQUAL:
                        matchSet.add(matchSortId.intValue() == valueSortId.intValue());
                        break;
                    case NOT_EQUAL:
                        matchSet.add(matchSortId.intValue() != valueSortId.intValue());
                        break;
                    default:
                        throw new RuleValueException("opt : " + opt + " is not accepted! ");
                }
            }
            boolean isMatch = false;
            if (policy == RulePolicy.MATCHALL) {
                if (!matchSet.contains(false)) {
                    isMatch = true;
                }
            } else if (policy == RulePolicy.MATCHONE) {
                if (matchSet.contains(true)) {
                    isMatch = true;
                }
            }
            return isMatch;
        }


    }


    final static class NumRule extends AbstractRule<Double> {


        @Override
        public boolean beginMatch() throws RuleValueException {
        /*数字类型的比对，较为简单，直接比对即可*/
            Set<Boolean> matchSet = new HashSet<Boolean>();
            for (Condition<Double> condition : this.conditions) {
                ConditionOpt opt = condition.getOpt();
                Double value = condition.getValue();

                if(this.matchTo == null){
                    throw new RuleValueException("can not found mathTo value!");
                }

                switch (opt) {
                    case BIGGER:
                        matchSet.add(matchTo.compareTo(value) == 1);
                        break;
                    case BIGGER_EQUAL:
                        matchSet.add(matchTo.compareTo(value) != -1);
                        break;
                    case SMALL:
                        matchSet.add(matchTo.compareTo(value) == -1);
                        break;
                    case SMALL_EQUAL:
                        matchSet.add(matchTo.compareTo(value) != 1);
                        break;
                    case EQUAL:
    					matchSet.add(matchTo.compareTo(value) == 0);
    					break;
                    case NOT_EQUAL:
                        matchSet.add(matchTo.compareTo(value) != 0);
                        break;
                    default:
                        throw new RuleValueException("opt : " + opt + " is not accepted! ");
                }

            }
            boolean isMatch = false;
            if (policy == RulePolicy.MATCHALL) {
                if (!matchSet.contains(false)) {
                    isMatch = true;
                }
            } else if (policy == RulePolicy.MATCHONE) {
                if (matchSet.contains(true)) {
                    isMatch = true;
                }
            }
            return isMatch;
        }
    }
}
