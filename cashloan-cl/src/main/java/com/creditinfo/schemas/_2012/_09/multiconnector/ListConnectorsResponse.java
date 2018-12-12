
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
 *         &lt;element name="ListConnectorsResult" type="{http://creditinfo.com/schemas/2012/09/MultiConnector}ConnectorsListResponse" minOccurs="0"/>
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
    "listConnectorsResult"
})
@XmlRootElement(name = "ListConnectorsResponse")
public class ListConnectorsResponse {

    @XmlElementRef(name = "ListConnectorsResult", namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", type = JAXBElement.class, required = false)
    protected JAXBElement<ConnectorsListResponse> listConnectorsResult;

    /**
     * Gets the value of the listConnectorsResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ConnectorsListResponse }{@code >}
     *     
     */
    public JAXBElement<ConnectorsListResponse> getListConnectorsResult() {
        return listConnectorsResult;
    }

    /**
     * Sets the value of the listConnectorsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ConnectorsListResponse }{@code >}
     *     
     */
    public void setListConnectorsResult(JAXBElement<ConnectorsListResponse> value) {
        this.listConnectorsResult = value;
    }

}
