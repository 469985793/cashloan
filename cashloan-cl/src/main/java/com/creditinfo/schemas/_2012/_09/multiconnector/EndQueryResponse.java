
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
 *         &lt;element name="EndQueryResult" type="{http://creditinfo.com/schemas/2012/09/MultiConnector}MultiConnectorResponse" minOccurs="0"/>
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
    "endQueryResult"
})
@XmlRootElement(name = "EndQueryResponse")
public class EndQueryResponse {

    @XmlElementRef(name = "EndQueryResult", namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", type = JAXBElement.class, required = false)
    protected JAXBElement<MultiConnectorResponse> endQueryResult;

    /**
     * Gets the value of the endQueryResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link MultiConnectorResponse }{@code >}
     *     
     */
    public JAXBElement<MultiConnectorResponse> getEndQueryResult() {
        return endQueryResult;
    }

    /**
     * Sets the value of the endQueryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link MultiConnectorResponse }{@code >}
     *     
     */
    public void setEndQueryResult(JAXBElement<MultiConnectorResponse> value) {
        this.endQueryResult = value;
    }

}
