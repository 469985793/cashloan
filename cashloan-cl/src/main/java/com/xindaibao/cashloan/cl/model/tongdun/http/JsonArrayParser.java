package com.xindaibao.cashloan.cl.model.tongdun.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by syq on 2015/12/28.
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class JsonArrayParser implements JsonNodeTypeParser {

	private Class customClazz;

    public JsonArrayParser(Class customClazz) {
        this.customClazz = customClazz;
    }

    
	@Override
    public Object onTargetNode(List<JSONObject> list, String jsonKey) throws Exception {
        List<Object> resultList = new ArrayList<Object>();
        try {
            for (JSONObject jsonObject : list) {
                JSONArray array = jsonObject.getJSONArray(StringUtils.split(jsonKey, "[]")[0]);
                for (Object anArray : array) {
                    if (anArray instanceof JSONObject && this.customClazz != Object.class) {
                        anArray = JSONObject.toJavaObject((JSON) anArray, this.customClazz);
                    }
                    resultList.add(anArray);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return resultList;
    }

    @Override
    public void onTargetPreviousNode(List<JSONObject> list, String jsonKey) throws Exception {
        List<JSONArray> currentLevelJsonArrayList = new ArrayList<JSONArray>();
        for (JSONObject preLevelJsonObject : list) {
            JSONArray array = preLevelJsonObject.getJSONArray(StringUtils.split(jsonKey, "[]")[0]);
            currentLevelJsonArrayList.add(array);
        }
        //清除上一层的list
        list.clear();
        //将新的currentLevelJsonArrayList循环获取jsonObject放入preLevelJsonObjectList
        for (JSONArray array : currentLevelJsonArrayList) {
            for (int j = 0; j < array.size(); j++) {
                list.add(array.getJSONObject(j));
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
