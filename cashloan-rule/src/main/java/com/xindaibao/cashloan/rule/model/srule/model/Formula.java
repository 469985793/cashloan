package com.xindaibao.cashloan.rule.model.srule.model;

/**
 * 基础运算符及匹配表达式
 * @author
 * @version 1.0.0
 * @date 2017年1月4日 上午11:15:23


 * 

 */
public enum Formula {

	//大于，例如：20>3，仅允许使用在数值类型的表达式中
	greater(">", ">"), 
	//小于，例如 3<10，仅允许使用在数值类型的表达式中
	less("<", "<"),
	//等于，判断两个值是否相同，可以匹配文字和数值类型
	equal("=", "="), 
	//不等于，判断两个值不相同，可以匹配文字和数值类型
	not_equal("!=", "!="),
	//大于等于，例如：3>=3，仅允许使用在数值类型的表达式中
	greater_equal(">=",">="),
	//小于等于，例如 3<=3， 仅允许使用在数值类型的表达式中
	less_equal("<=","<="),
	
	
	//大于等于并且小于等于，例如  1<=3<=5， 仅允许使用在数值类型的表达式中
	greater_equal_and_less_equal("<=and<=","<=and<="), 
	//大于等于并且小于，例如  1<=3<5， 仅允许使用在数值类型的表达式中
	greater_equal_and_less("<=and<","<=and<"),
	//大于并且小于等于，例如  1<3<=5， 仅允许使用在数值类型的表达式中
	greater_and_less_equal("<and<=","<and<="),
	//大于并且小于，例如  1<3<5， 仅允许使用在数值类型的表达式中
	greater_and_less("<and<","<and<"),
	
	
	//包含，允许使用在字符类型，如果填写数字，将被当作字符类型匹配
	include("include","include"),
	//不包含，允许使用在字符类型的表达式，如果填写数字，将被当作字符类型匹配
	exclude("exclude","exclude");
//	and("and",14),
//	or("or",14);
	
    // 成员变量  
    private String name;  
    private String index;  
    // 构造方法  
    private Formula(String name, String index) {  
        this.name = name;  
        this.index = index;  
    }  
    // 普通方法  
    public static String getName(String index) {  
        for (Formula c : Formula.values()) {  
            if (c.getIndex().equals(index)) {  
                return c.name;  
            }  
        }  
        return null;  
    }
    
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}  
	
}
