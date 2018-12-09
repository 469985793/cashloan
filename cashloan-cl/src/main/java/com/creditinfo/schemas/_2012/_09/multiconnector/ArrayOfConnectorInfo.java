
package com.creditinfo.schemas._2012._09.multiconnector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for ArrayOfConnectorInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfConnectorInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConnectorInfo" type="{http://creditinfo.com/schemas/2012/09/MultiConnector}ConnectorInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfConnectorInfo", propOrder = {
    "connectorInfo"
})
public class ArrayOfConnectorInfo {

    @XmlElement(name = "ConnectorInfo", nillable = true)
    protected List<ConnectorInfo> connectorInfo;

    /**
     * Gets the value of the connectorInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the connectorInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConnectorInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConnectorInfo }
     * 
     * 
     */
    public List<ConnectorInfo> getConnectorInfo() {
        if (connectorInfo == null) {
            connectorInfo = new ArrayList<ConnectorInfo>();
        }
        return this.connectorInfo;
    }

}
