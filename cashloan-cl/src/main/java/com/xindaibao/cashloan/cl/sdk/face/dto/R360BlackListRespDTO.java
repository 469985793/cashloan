package com.xindaibao.cashloan.cl.sdk.face.dto;

import com.xindaibao.cashloan.cl.sdk.face.R360BlackList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by xiezhao on 2017/10/21.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class R360BlackListRespDTO {

    private String error;
    private String msg;
    private List<R360BlackListFeature> tianji_api_agentr_blacklist_response;


    public boolean isSuccess(){
        return StringUtils.equals("200", error);
    }



    /*
    {
    "error": 200,
    "msg": null,
    "tianji_api_agentr_blacklist_response": [
        {
            "feature1": "20160714",
            "feature2": "20161215",
            "feature3": "6",
            "feature4": "4",
            "feature5": "0"
        },
        {
            "feature1": "20160714",
            "feature2": "20161215",
            "feature3": "6",
            "feature4": "4",
            "feature5": "0"
        }
    ]
}


     */
}
