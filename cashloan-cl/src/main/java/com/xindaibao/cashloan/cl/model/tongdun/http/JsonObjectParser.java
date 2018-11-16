package com.xindaibao.cashloan.cl.model.tongdun.http;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * 解析jsonObject，观察者
 * Created by syq on 2015/12/28.
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class JsonObjectParser implements JsonNodeTypeParser{

    private String fieldTypeName;

    private boolean isListResult;

	private Class customClazz;

    public JsonObjectParser(String fieldTypeName,Class customClazz) {
        this.fieldTypeName = fieldTypeName;
        this.customClazz = customClazz;
    }


	@Override
    public Object onTargetNode(List<JSONObject> list, String jsonKey) throws Exception{
        if (!isListResult) {//如果结果不是list
            if(this.customClazz != Object.class){
                return JSONObject.toJavaObject(list.get(0).getJSONObject(jsonKey),this.customClazz);
            }
            return matchType(list.get(0), this.fieldTypeName, jsonKey);
        }else {
            List<Object> resultList = new ArrayList<Object>();
            for (JSONObject preLevelJsonObject : list) {
                if(this.customClazz != Object.class){
                    resultList.add(JSONObject.toJavaObject(preLevelJsonObject.getJSONObject(jsonKey),this.customClazz));
                }else{
                    resultList.add(matchType(preLevelJsonObject, this.fieldTypeName, jsonKey));
                }
            }
            return resultList;
        }
    }

    @Override
    public void onTargetPreviousNode(List<JSONObject> list, String jsonKey) throws Exception{
        List<JSONObject> currentLevelJsonObjectList = new ArrayList<JSONObject>();
        for (JSONObject preLevelJsonObject : list) {
            JSONObject currentJsonObject = preLevelJsonObject.getJSONObject(jsonKey);
            currentLevelJsonObjectList.add(currentJsonObject);
        }
        //清除上一层的list
        list.clear();
        //将新的currentLevelJsonArrayList循环获取jsonObject放入preLevelJsonObjectList
        for (JSONObject object : currentLevelJsonObjectList) {
            list.add(object);
        }
    }

    private Object matchType(JSONObject json, String fieldType, String key) {
        Object value = null;
        switch (fieldType) {
            case "Integer":
                value = json.getInteger(key);
                break;
            case "Long":
                value = json.getLong(key);
                break;
            case "Double":
                value = json.getDouble(key);
                break;
            case "String":
                value = json.getString(key);
                break;
            default:
                value = json.get(key);
                break;
        }
        return value;
    }


    /**
     * 观察是否被通知，如果有通知则说明结果是一个集合
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        this.isListResult = true;
    }
}
