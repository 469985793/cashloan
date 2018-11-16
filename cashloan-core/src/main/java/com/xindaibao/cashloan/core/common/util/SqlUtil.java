package com.xindaibao.cashloan.core.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lsk on 2015/10/21.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SqlUtil {
	
    public static String buildSaveOrUpdatesMap(String tableName, List<Map<String, Object>> list) {
        StringBuilder sql = new StringBuilder(buildInsertSqlMap(tableName, list));
        sql.append("on duplicate key update ");
        for (String name : list.get(0).keySet()) {
            sql.append("`" + name + "`=values(`" + name + "`),");
        }
        sql.deleteCharAt(sql.length() - 1);
        return sql.toString();

    }

    public static String buildInsertSqlMap(String tableName, List<Map<String, Object>> list) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder("insert into `" + tableName + "`(");

        Set<String> keys = list.get(0).keySet();
        for (String key : keys) {
            sb.append("`" + key + "`,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")values");

        for (Map<String, Object> rec : list) {
            sb.append("(");
            for (String key : keys) {
                Object value = rec.get(key);
                if (value != null && !(value instanceof Number) && !(value instanceof Boolean)) {
                    if (value instanceof Date) {
                        value = sdf.format(value);
                    } else {
                        value = value.toString().replaceAll("['\\\\]", "\\\\$0");
                    }
                    value = "'" + value + "'";
                }
                sb.append(value + ",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("),");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

	public static String buildUpdateSql(String tableName, Object[][] params) {
        return buildUpdateSql(tableName, MapUtil.array2Map(params));
    }

    public static String buildUpdateSql(String tableName, Map<String, Object> rec) {
        return buildUpdateSql(tableName, rec, "id");
    }

    public static String buildUpdateSql(String tableName, Map<String, Object> rec, String idProperty) {
        StringBuilder sb = new StringBuilder("update `" + tableName + "` set ");
        Object keyValue = rec.remove(idProperty);
        rec.remove("id");
        if (rec.isEmpty()) {
            return null;
        } else {
            Iterator i$ = rec.keySet().iterator();

            while (i$.hasNext()) {
                String key = (String) i$.next();
                sb.append("`" + key + "`=" + formatValue(rec.get(key)));
                sb.append(",");
            }

            sb.deleteCharAt(sb.length() - 1);
            if (keyValue instanceof String) {
                keyValue = "\'" + keyValue + "\'";
            }

            sb.append(" where " + idProperty + "=" + keyValue);
            return sb.toString();
        }
    }

    private static Object formatValue(Object value) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (value != null && !(value instanceof Number) && !(value instanceof Boolean)) {
            String value1;
            if (value instanceof Date) {
                value1 = sdf.format(value);
            } else {
                value1 = value.toString().replaceAll("[\'\\\\]", "\\\\$0");
            }

            value = "\'" + value1 + "\'";
        }

        return value;
    }


    public static String buildInsertSqlMap(String tableName, Object[][] params) {
        return buildInsertSqlMap(tableName, MapUtil.array2Map(params));
    }

	public static String buildInsertSqlMap(String tableName, Map<String, Object> rec) {
        ArrayList list = new ArrayList();
        list.add(rec);
        return buildInsertSqlMap(tableName, (List) list);
    }

}
