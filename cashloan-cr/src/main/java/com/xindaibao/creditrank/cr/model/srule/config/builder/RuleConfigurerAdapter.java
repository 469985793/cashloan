package com.xindaibao.creditrank.cr.model.srule.config.builder;

import com.xindaibao.creditrank.cr.model.srule.config.rule.Rule;


/**
 * 规则配置类的抽象适配器,主要功能是保存builder的实例
 * Created by syq on 2016/12/9.
 */
public abstract class RuleConfigurerAdapter<H, O extends Rule, B extends AbstractRuleBuilder<H, O>> implements RuleConfigurer<H> {


    private B ruleBuilder;


    protected final B getBuilder() {
        if (ruleBuilder == null) {
            throw new IllegalStateException("ruleBuilder cannot be null");
        }
        return ruleBuilder;
    }

    public void setBuilder(B builder) {
        this.ruleBuilder = builder;
    }


    @Override
    public Rule build() throws Exception {
        return this.ruleBuilder.build();
    }
}
