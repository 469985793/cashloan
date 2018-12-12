package com.xindaibao.cashloan.cl.model.CreditInfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 【 】
 *
 * @author chenzhiheng
 * @version V1.0
 * @date 18/12/6
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestXml", propOrder = {
        "response"
})
public class ResponseXml {

    @XmlElement(required = true)
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
