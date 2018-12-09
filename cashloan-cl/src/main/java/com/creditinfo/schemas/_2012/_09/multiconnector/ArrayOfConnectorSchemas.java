
package com.creditinfo.schemas._2012._09.multiconnector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for ArrayOfConnectorSchemas complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfConnectorSchemas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConnectorSchemas" type="{http://creditinfo.com/schemas/2012/09/MultiConnector}ConnectorSchemas" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfConnectorSchemas", propOrder = {
    "connectorSchemas"
})
public class ArrayOfConnectorSchemas {

    @XmlElement(name = "ConnectorSchemas", nillable = true)
    protected List<ConnectorSchemas> connectorSchemas;

    /**
     * Gets the value of the connectorSchemas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the connectorSchemas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConnectorSchemas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConnectorSchemas }
     * 
     * 
     */
    public List<ConnectorSchemas> getConnectorSchemas() {
        if (connectorSchemas == null) {
            connectorSchemas = new ArrayList<ConnectorSchemas>();
        }
        return this.connectorSchemas;
    }

}
