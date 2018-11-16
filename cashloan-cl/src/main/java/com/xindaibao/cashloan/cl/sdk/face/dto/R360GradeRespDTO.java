package com.xindaibao.cashloan.cl.sdk.face.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by xiezhao on 2017/10/22.
 */
@Getter
@Setter
@NoArgsConstructor
public class R360GradeRespDTO {

    private String error;
    private String msg;
    private ResponseData tianji_api_agenty_AntiFraud_response;

    /*
    {"error":200,"msg":null,"tianji_api_agenty_AntiFraud_response":{"score":22,"tag":""}}
     */
    @Getter
    @Setter
    @NoArgsConstructor
    public class ResponseData {
        private String score;
        private String tag;
    }
}
