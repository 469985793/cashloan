package com.xindaibao.cashloan.core.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 工具类-分表
 * @author
 * @version 1.0.0
 * @date 2017年6月5日 上午9:54:09



 */
public class ShardTableUtil {
	
	public static List<String> tables = new ArrayList<String>();
	static{
		tables.add("cl_user_contacts");
		tables.add("cl_operator_voices");
	}
	
	/**
	 * 根据主键Id生成分表名称
	 * @param shardId 拆分id段
	 * @return
	 */
	public static String generateTableNameById(String tableName, long id, long shardId){
		if(tables.contains(tableName)){
			long num = id/shardId + 1;
			return tableName + "_" + num;
		}else{
			return tableName;
		}
	}

}
