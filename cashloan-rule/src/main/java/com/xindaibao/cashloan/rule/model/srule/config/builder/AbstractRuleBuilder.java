package com.xindaibao.cashloan.rule.model.srule.config.builder;

import java.util.concurrent.atomic.AtomicBoolean;

import com.xindaibao.cashloan.rule.model.srule.config.rule.Rule;
import com.xindaibao.cashloan.rule.model.srule.config.rule.RuleBasic;

/**
 * A base {@link AbstractRuleBuilder} that ensures the object being built is only built one time.
 * <p>
 * Created by syq on 2016/12/8.
 */
public abstract class AbstractRuleBuilder<H, O extends Rule> implements RuleBuilder<H>, Builder {

    private AtomicBoolean building = new AtomicBoolean();

    private O object;


    @Override
    public O build() throws Exception {
//        if (this.building.compareAndSet(false, true)) {
//            object = doBuild();
//            return object;
//        }
//        throw new RuleBuildException("This object has already been built");
        object = doBuild();
        return object;
    }

    /**
     * Subclasses should implement this to perform the build.
     *
     * @return the object that should be returned by {@link #build()}.
     * @throws Exception if an error occurs
     */
    protected abstract O doBuild() throws Exception;


    /**
     * Gets the object that was built. If it has not been built yet an Exception is
     * thrown.
     *
     * @return the Object that was built
     */
    public final O getObject() {
        if (!building.get()) {
            throw new IllegalStateException("This object has not been built");
        }
        return object;
    }


    /**
     * low level sub class tell the concrete rule type
     *
     * @return
     */
    protected abstract RuleBasic<H> concrete();


}
