package com.xindaibao.cashloan.cl.model.tongdun;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author
 */
@Setter
@Getter
public class PreloanQueryResponse implements Serializable {

    private static final long serialVersionUID = 4152462211121573434L;

    private Boolean success = false;
    private JSONArray risk_items;
    private JSONObject address_detect;
    private String application_id;
    private String final_decision;
    private String report_id;
    private Long apply_time;
    private Long report_time;
    private Integer final_score;

    //ignore getter and setter
}
