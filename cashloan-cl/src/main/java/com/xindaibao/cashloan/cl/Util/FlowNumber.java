package com.xindaibao.cashloan.cl.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FlowNumber {
    public static String getNewFlowNumber(String title){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        int ends = (int)((Math.random()*9+1)*100000);
        title=title+format.format(new Date())+ends;
        return title;
    }
}
