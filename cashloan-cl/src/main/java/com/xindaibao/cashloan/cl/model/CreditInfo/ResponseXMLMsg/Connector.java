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
@XmlType(name = "Connector", propOrder = {
        "data"
})
public class Connector {

    @XmlElement(required = true)
    private com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg.Data data;

    public com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg.Data getData() {
        return data;
    }

    public void setData(com.xindaibao.cashloan.cl.model.CreditInfo.ResponseXMLMsg.Data data) {
        this.data = data;
    }
}
