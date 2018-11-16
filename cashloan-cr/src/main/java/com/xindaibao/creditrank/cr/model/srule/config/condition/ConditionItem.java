package com.xindaibao.creditrank.cr.model.srule.config.condition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 条件收集器
 * 非线程安全类
 * Created by syq on 2016/12/13.
 */
public class ConditionItem implements Iterator<Object[]> {


    private List<String> opts;

    private List<Object> values;

    private int point = 0;//初始化时循环计数point指向表头

    private int length = 0;//初始化表长为0，此时表中无数据


    public ConditionItem() {
        this.opts = new ArrayList<String>();
        this.values = new ArrayList<Object>();
    }


    /**
     * 添加一组元素，就增加表的长度，这两个list的长度毕竟保证统一
     *
     * @param opt
     * @param value
     */
    public void add(String opt, Object value) {
        opts.add(opt);
        values.add(value);
        length++;
    }



    @Override
    public boolean hasNext() {
        if (point >= length) {
            this.reset();
            return false;
        }
        return true;
    }

    @Override
    public Object[] next() {
        Object[] objs = new Object[2];
        objs[0] = opts.get(point);
        objs[1] = values.get(point);
        point++;
        return objs;
    }

    @Override
    public void remove() {
        /*移除所有条件*/
        this.opts.clear();
        this.values.clear();
        this.reset();
        this.length = 0;
    }

    private void reset() {
        this.point = 0;
    }
}
