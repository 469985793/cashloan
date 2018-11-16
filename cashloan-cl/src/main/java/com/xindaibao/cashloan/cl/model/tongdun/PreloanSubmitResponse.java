package com.xindaibao.cashloan.cl.model.tongdun;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PreloanSubmitResponse implements Serializable {
    private static final long serialVersionUID = 4152462611121573434L;
    private Boolean                 success          = false;
    private String                  report_id;
    private PreloanQueryResponse    preLoanQueryResponse;

    //ignore getter and setter

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("success", success)
                .append("report_id", report_id)
//                .append("preloanQueryResponse", preloanQueryResponse)
                .toString();
    }

}
