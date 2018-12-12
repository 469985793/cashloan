
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
 *         &lt;element name="SchemaResult" type="{http://creditinfo.com/schemas/2012/09/MultiConnector}ConnectorSchemaResponse" minOccurs="0"/>
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
    "schemaResult"
})
@XmlRootElement(name = "SchemaResponse")
public class SchemaResponse {

    @XmlElementRef(name = "SchemaResult", namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector", type = JAXBElement.class, required = false)
    protected JAXBElement<ConnectorSchemaResponse> schemaResult;

    /**
     * Gets the value of the schemaResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ConnectorSchemaResponse }{@code >}
     *     
     */
    public JAXBElement<ConnectorSchemaResponse> getSchemaResult() {
        return schemaResult;
    }

    /**
     * Sets the value of the schemaResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ConnectorSchemaResponse }{@code >}
     *     
     */
    public void setSchemaResult(JAXBElement<ConnectorSchemaResponse> value) {
        this.schemaResult = value;
    }

}
