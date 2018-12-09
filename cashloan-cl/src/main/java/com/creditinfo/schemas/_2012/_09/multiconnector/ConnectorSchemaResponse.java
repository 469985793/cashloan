
package com.creditinfo.schemas._2012._09.multiconnector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConnectorSchemaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConnectorSchemaResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MessageId" type="{http://schemas.microsoft.com/2003/10/Serialization/}guid"/>
 *         &lt;element name="Schemas" type="{http://creditinfo.com/schemas/2012/09/MultiConnector}ArrayOfConnectorSchemas"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConnectorSchemaResponse", propOrder = {
    "messageId",
    "schemas"
})
public class ConnectorSchemaResponse {

    @XmlElement(name = "MessageId", required = true)
    protected String messageId;
    @XmlElement(name = "Schemas", required = true, nillable = true)
    protected ArrayOfConnectorSchemas schemas;

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

    /**
     * Gets the value of the schemas property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfConnectorSchemas }
     *     
     */
    public ArrayOfConnectorSchemas getSchemas() {
        return schemas;
    }

    /**
     * Sets the value of the schemas property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfConnectorSchemas }
     *     
     */
    public void setSchemas(ArrayOfConnectorSchemas value) {
        this.schemas = value;
    }

}
