package com.xindaibao.creditrank.cr.model.srule.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xindaibao.creditrank.cr.model.srule.config.builder.RuleBuilderCreator;
import com.xindaibao.creditrank.cr.model.srule.config.condition.ConditionItem;
import com.xindaibao.creditrank.cr.model.srule.config.core.RulesLogic;
import com.xindaibao.creditrank.cr.model.srule.config.rule.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xindaibao.creditrank.cr.model.srule.config.builder.RuleBuilder;
import com.xindaibao.creditrank.cr.model.srule.config.core.RulesExecutor;
import com.xindaibao.creditrank.cr.model.srule.utils.RulePolicy;

/**
 * Created by syq on 2016/12/18.
 */
public class Client {

	public static final Logger logger = LoggerFactory.getLogger(Client.class);
    public static void main(String[] args) throws Exception {


        Map<String, Integer> load = new HashMap<String, Integer>();
        load.put("博士", 3);
        load.put("硕士", 2);
        load.put("学士", 1);

        /*创建string类型的规则builder，所有类型的builder都是单例，只需创建一次即可*/
        RuleBuilder<String> ruleBuilder = RuleBuilderCreator.stringRuleBuilder();

        /*创建条件项，该实例对应每一条规则*/
        ConditionItem stringItem = ruleBuilder.newConditionItems();
        stringItem.add(">=", "学士");
        stringItem.add("<=", "博士");


        /*构建规则对象，规则对象为不可变对象*/
        Rule rule = ruleBuilder.newRule(1, "education", stringItem).preLoad(load).rulePolicy(RulePolicy.MATCHALL).build();
        rule.matchTo("硕士");
        /*单条规则可以直接使用beginMatch()方法获取判断结果*/
        logger.info(rule.beginMatch()+"");

        /*创建数字类型的规则builder*/
        RuleBuilder<Double> numRuleBuilder = RuleBuilderCreator.numRuleBuilder();

        /*接收数据类型 long, int ,float ,double */
        ConditionItem numItems = numRuleBuilder.newConditionItems();
        numItems.add(">=", 20);
        numItems.add(">=", 30);

        Rule rule2 = numRuleBuilder.newRule(2, "age", numItems).rulePolicy(RulePolicy.MATCHALL).build();
        rule2.matchTo(25);

        logger.info(rule2.beginMatch()+"");


        /*范围包括不包括类型的字符串规则可以不设置preLoad()方法*/
        ConditionItem includeItem = ruleBuilder.newConditionItems();
        includeItem.add("include", "学士，博士，硕士");
        Rule rule3 = ruleBuilder.newRule(3, "education", includeItem).rulePolicy(RulePolicy.MATCHALL).build();
        rule3.matchTo("学士");
        logger.info(rule3.beginMatch()+"");

        List<Rule> list = new ArrayList<Rule>();
        list.add(rule);
        list.add(rule2);
        list.add(rule3);

        /*创建多规则的逻辑判断对象，这里创建的是规则和规则之间没有逻辑关系*/
        RulesLogic rulesLogic = RulesExecutor.newNoneRelationRulesLogic();
        /*执行逻辑判断*/
        rulesLogic.doLogic(list);

        /*获取各个规则的执行结果*/
        Map<Long, Boolean> resultMap = rulesLogic.rulesResult();
        logger.info(resultMap+"");
        
        
    }


}
