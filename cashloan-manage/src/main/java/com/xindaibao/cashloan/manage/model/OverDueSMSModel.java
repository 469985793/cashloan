package com.xindaibao.cashloan.manage.model;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/10/14
 */
public class OverDueSMSModel {

    public static final String BEFOR_DUE_ONE = "Hi{$Name},Your Jumbopesa loan{$amount} due date will be tomorrow. Please repay to our paybill 206512 before 18:00 on tomorrow. ";

    public static final String OVER_DUE_ONE = "Dear {$Name},your Jumbopesa loan was overdue. Please repay immediately to avoid additional costs increase and  impact your credit.Only use paybill 206512 .";

    public static final String OVER_DUE_THREE = "Dear {$Name},Your Jumbopesa loan has been overdue more than 3 days, we strongly require you to repay {$amount}  immediately. Continue Overdue will increase additional costs and which might impact your credit. Use paybill 206512.";

    public static final String OVER_DUE_SAVEN = "Dear {$Name},Your loan has been overdue over 7 days and we strongly  require you  again to repay {$amount} immediately.Continue Overdue will increase additional costs and  impact your credit at CRB.Use paybill 206512.";

    public static final String OVER_DUE_TWOWEEK = "Dear {$Name},Your loan has been overdue over 14 days and we strongly  require you  again to repay {$amount} immediately.Continue Overdue will increase additional costs and seriouly impact your credit at CRB.Use paybill 206512.";

}
