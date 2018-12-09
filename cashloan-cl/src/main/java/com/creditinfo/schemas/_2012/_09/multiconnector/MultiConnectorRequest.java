
package com.creditinfo.schemas._2012._09.multiconnector;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for MultiConnectorRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MultiConnectorRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MessageId" type="{http://schemas.microsoft.com/2003/10/Serialization/}guid"/>
 *         &lt;element name="OperationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RequestXml">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RequestXml" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Request}RequestXml"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ScheduledTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Timeout" type="{http://schemas.microsoft.com/2003/10/Serialization/}duration" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultiConnectorRequest", propOrder = {
    "messageId",
    "operationCode",
    "requestXml",
    "scheduledTime",
    "timeout"
})
public class MultiConnectorRequest {

    @XmlElement(name = "MessageId", required = true)
    protected String messageId;
    @XmlElementRef(name = "OperationCode", namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", type = JAXBElement.class, required = false)
    protected JAXBElement<String> operationCode;
    @XmlElement(name = "RequestXml", required = true)
    protected RequestXml requestXml;
    @XmlElementRef(name = "ScheduledTime", namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> scheduledTime;
    @XmlElementRef(name = "Timeout", namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", type = JAXBElement.class, required = false)
    protected JAXBElement<Duration> timeout;

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
     * Gets the value of the operationCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOperationCode() {
        return operationCode;
    }

    /**
     * Sets the value of the operationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOperationCode(JAXBElement<String> value) {
        this.operationCode = value;
    }

    /**
     * Gets the value of the requestXml property.
     * 
     * @return
     *     possible object is
     *     {@link RequestXml }
     *     
     */
    public RequestXml getRequestXml() {
        return requestXml;
    }

    /**
     * Sets the value of the requestXml property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestXml }
     *     
     */
    public void setRequestXml(RequestXml value) {
        this.requestXml = value;
    }

    /**
     * Gets the value of the scheduledTime property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getScheduledTime() {
        return scheduledTime;
    }

    /**
     * Sets the value of the scheduledTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setScheduledTime(JAXBElement<XMLGregorianCalendar> value) {
        this.scheduledTime = value;
    }

    /**
     * Gets the value of the timeout property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Duration }{@code >}
     *     
     */
    public JAXBElement<Duration> getTimeout() {
        return timeout;
    }

    /**
     * Sets the value of the timeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Duration }{@code >}
     *     
     */
    public void setTimeout(JAXBElement<Duration> value) {
        this.timeout = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="RequestXml" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Request}RequestXml"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "requestXml"
    })
    public static class RequestXml {

        @XmlElement(name = "RequestXml", required = true)
        protected com.creditinfo.schemas._2012._09.multiconnector.messages.request.RequestXml requestXml;

        /**
         * Gets the value of the requestXml property.
         * 
         * @return
         *     possible object is
         *     {@link com.creditinfo.schemas._2012._09.multiconnector.messages.request.RequestXml }
         *     
         */
        public com.creditinfo.schemas._2012._09.multiconnector.messages.request.RequestXml getRequestXml() {
            return requestXml;
        }

        /**
         * Sets the value of the requestXml property.
         * 
         * @param value
         *     allowed object is
         *     {@link com.creditinfo.schemas._2012._09.multiconnector.messages.request.RequestXml }
         *     
         */
        public void setRequestXml(com.creditinfo.schemas._2012._09.multiconnector.messages.request.RequestXml value) {
            this.requestXml = value;
        }

    }

}
