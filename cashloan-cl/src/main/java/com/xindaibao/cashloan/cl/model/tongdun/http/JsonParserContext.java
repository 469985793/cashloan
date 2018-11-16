package com.xindaibao.cashloan.cl.model.tongdun.http;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * 解析json上下文类，被观察者
 * Created by syq on 2015/12/28.
 */
@SuppressWarnings("rawtypes")
public class JsonParserContext extends Observable {

    private List<JSONObject> preLevelJsonObjectList = new ArrayList<JSONObject>();

    private List<String> keyNameOnEachJsonNode;

	private Class customClassType;



    public List<JSONObject> getPreLevelJsonObjectList() {
        return preLevelJsonObjectList;
    }

    public void setPreLevelJsonObjectList(List<JSONObject> preLevelJsonObjectList) {
        this.preLevelJsonObjectList = preLevelJsonObjectList;
    }

    public void putRootJsonNode(JSONObject root) {
        preLevelJsonObjectList.add(root);
    }

    public void setCustomClassType(Class customClassType) {
        this.customClassType = customClassType;
    }

    /**
     * 客户端类调用方法，获取最终的所需的结果
     * @param keyNameOnEachJsonNode
     * @param fieldTypeName
     * @return
     * @throws Exception
     */
    public Object getTargetValue(List<String> keyNameOnEachJsonNode, String fieldTypeName) throws Exception {
        JsonNodeTypeParser arrayParser = new JsonArrayParser(this.customClassType);
        JsonNodeTypeParser objParser = new JsonObjectParser(fieldTypeName,this.customClassType);
        addObserver(objParser);
        this.keyNameOnEachJsonNode = keyNameOnEachJsonNode;
        Object resultValue = null;
        for (String jsonKey : keyNameOnEachJsonNode) {
            if (isTypeIsJsonArray(jsonKey)) {
                if (isOnTargetIndex(jsonKey)) {
                    resultValue = arrayParser.onTargetNode(this.preLevelJsonObjectList, jsonKey);
                    break;
                }else {
                    arrayParser.onTargetPreviousNode(this.preLevelJsonObjectList, jsonKey);
                }
            }else {
                if (isOnTargetIndex(jsonKey)) {
                    resultValue = objParser.onTargetNode(this.preLevelJsonObjectList, jsonKey);
                    break;
                } else {
                    objParser.onTargetPreviousNode(this.preLevelJsonObjectList, jsonKey);
                }
            }
        }
        return resultValue;
    }


    /**
     * 当json字段中出现数组类型是，通知观察者
     * @param jsonKey
     * @return
     */
    private boolean isTypeIsJsonArray(String jsonKey) {
        if (jsonKey.endsWith("[]")) {
            setChanged();//通知结果集是一个list
            notifyObservers();
            return true;
        } else {
            return false;
        }
    }


    /**
     * 是否已经到了最后需要获取的那个节点
     * @param jsonKey
     * @return
     */
    private boolean isOnTargetIndex(String jsonKey) {
        if (this.keyNameOnEachJsonNode.lastIndexOf(jsonKey) == this.keyNameOnEachJsonNode.size() - 1) {
            return true;
        } else {
            return false;
        }
    }


}
