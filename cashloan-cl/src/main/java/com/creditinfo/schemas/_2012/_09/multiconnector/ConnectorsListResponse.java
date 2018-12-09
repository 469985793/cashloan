
package com.creditinfo.schemas._2012._09.multiconnector;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for ConnectorsListResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConnectorsListResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Connectors" type="{http://creditinfo.com/schemas/2012/09/MultiConnector}ArrayOfConnectorInfo" minOccurs="0"/>
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
@XmlType(name = "ConnectorsListResponse", propOrder = {
    "connectors",
    "messageId"
})
public class ConnectorsListResponse {

    @XmlElementRef(name = "Connectors", namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfConnectorInfo> connectors;
    @XmlElement(name = "MessageId", required = true)
    protected String messageId;

    /**
     * Gets the value of the connectors property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfConnectorInfo }{@code >}
     *     
     */
    public JAXBElement<ArrayOfConnectorInfo> getConnectors() {
        return connectors;
    }

    /**
     * Sets the value of the connectors property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfConnectorInfo }{@code >}
     *     
     */
    public void setConnectors(JAXBElement<ArrayOfConnectorInfo> value) {
        this.connectors = value;
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
