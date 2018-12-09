package com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg;

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
@XmlType(name = "Data", propOrder = {
        "response"
})
public class Data {

    @XmlElement(required = true)
    private model.CreditInfo.ResponseXMLMsg.Response response;

    public model.CreditInfo.ResponseXMLMsg.Response getResponse() {
        return response;
    }

    public void setResponse(model.CreditInfo.ResponseXMLMsg.Response response) {
        this.response = response;
    }
}
