
package com.creditinfo.schemas._2012._09.multiconnector;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;


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
 *         &lt;element name="BeginQueryResult" type="{http://creditinfo.com/schemas/2012/09/MultiConnector}MultiConnectorTicket" minOccurs="0"/>
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
    "beginQueryResult"
})
@XmlRootElement(name = "BeginQueryResponse")
public class BeginQueryResponse {

    @XmlElementRef(name = "BeginQueryResult", namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", type = JAXBElement.class, required = false)
    protected JAXBElement<MultiConnectorTicket> beginQueryResult;

    /**
     * Gets the value of the beginQueryResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link MultiConnectorTicket }{@code >}
     *     
     */
    public JAXBElement<MultiConnectorTicket> getBeginQueryResult() {
        return beginQueryResult;
    }

    /**
     * Sets the value of the beginQueryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link MultiConnectorTicket }{@code >}
     *     
     */
    public void setBeginQueryResult(JAXBElement<MultiConnectorTicket> value) {
        this.beginQueryResult = value;
    }

}
