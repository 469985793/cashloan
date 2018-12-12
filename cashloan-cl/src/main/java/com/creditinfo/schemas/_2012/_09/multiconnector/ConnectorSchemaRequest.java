
package com.creditinfo.schemas._2012._09.multiconnector;

import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfguid;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConnectorSchemaRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConnectorSchemaRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConnectorIds" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfguid"/>
 *         &lt;element name="MessageId" type="{http://schemas.microsoft.com/2003/10/Serialization/}guid"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConnectorSchemaRequest", propOrder = {
    "connectorIds",
    "messageId"
})
public class ConnectorSchemaRequest {

    @XmlElement(name = "ConnectorIds", required = true, nillable = true)
    protected ArrayOfguid connectorIds;
    @XmlElement(name = "MessageId", required = true)
    protected String messageId;

    /**
     * Gets the value of the connectorIds property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfguid }
     *     
     */
    public ArrayOfguid getConnectorIds() {
        return connectorIds;
    }

    /**
     * Sets the value of the connectorIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfguid }
     *     
     */
    public void setConnectorIds(ArrayOfguid value) {
        this.connectorIds = value;
    }

    /**
     * Gets the value of the messageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Sets the value of the messageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageId(String value) {
        this.messageId = value;
    }

}
