
package com.creditinfo.schemas._2012._09.multiconnector.messages.request;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.Duration;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for RequestXml complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestXml">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="connector" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="data">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;choice>
 *                             &lt;element ref="{http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/INT/IdmStrategy/Request}request"/>
 *                             &lt;element ref="{http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/KEN/IdmCb5/Company/GetCustomReport/Request}request"/>
 *                             &lt;element ref="{http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/KEN/IdmCb5/Individual/GetCustomReport/Request}request"/>
 *                           &lt;/choice>
 *                           &lt;attribute name="priority" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Request}priority" />
 *                           &lt;attribute name="id" use="required" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Request}guid" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="id" use="required" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Request}guid" />
 *                 &lt;attribute name="useCache" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="maxAge" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *                 &lt;attribute name="retry" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *                 &lt;attribute name="ignore" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="includeMetadata" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestXml", propOrder = {
    "connector"
})
public class RequestXml {

    @XmlElement(required = true)
    protected List<Connector> connector;

    /**
     * Gets the value of the connector property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the connector property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConnector().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Connector }
     * 
     * 
     */
    public List<Connector> getConnector() {
        if (connector == null) {
            connector = new ArrayList<Connector>();
        }
        return this.connector;
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
     *         &lt;element name="data">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;choice>
     *                   &lt;element ref="{http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/INT/IdmStrategy/Request}request"/>
     *                   &lt;element ref="{http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/KEN/IdmCb5/Company/GetCustomReport/Request}request"/>
     *                   &lt;element ref="{http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/KEN/IdmCb5/Individual/GetCustomReport/Request}request"/>
     *                 &lt;/choice>
     *                 &lt;attribute name="priority" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Request}priority" />
     *                 &lt;attribute name="id" use="required" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Request}guid" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="id" use="required" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Request}guid" />
     *       &lt;attribute name="useCache" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;attribute name="maxAge" type="{http://www.w3.org/2001/XMLSchema}duration" />
     *       &lt;attribute name="retry" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
     *       &lt;attribute name="ignore" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;attribute name="includeMetadata" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "data"
    })
    public static class Connector {

        @XmlElement(required = true)
        protected Data data;
        @XmlAttribute(name = "id", required = true)
        protected String id;
        @XmlAttribute(name = "useCache")
        protected Boolean useCache;
        @XmlAttribute(name = "maxAge")
        protected Duration maxAge;
        @XmlAttribute(name = "retry")
        @XmlSchemaType(name = "unsignedByte")
        protected Short retry;
        @XmlAttribute(name = "ignore")
        protected Boolean ignore;
        @XmlAttribute(name = "includeMetadata")
        protected Boolean includeMetadata;

        /**
         * Gets the value of the data property.
         * 
         * @return
         *     possible object is
         *     {@link Data }
         *     
         */
        public Data getData() {
            return data;
        }

        /**
         * Sets the value of the data property.
         * 
         * @param value
         *     allowed object is
         *     {@link Data }
         *     
         */
        public void setData(Data value) {
            this.data = value;
        }

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
            this.id = value;
        }

        /**
         * Gets the value of the useCache property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isUseCache() {
            return useCache;
        }

        /**
         * Sets the value of the useCache property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setUseCache(Boolean value) {
            this.useCache = value;
        }

        /**
         * Gets the value of the maxAge property.
         * 
         * @return
         *     possible object is
         *     {@link Duration }
         *     
         */
        public Duration getMaxAge() {
            return maxAge;
        }

        /**
         * Sets the value of the maxAge property.
         * 
         * @param value
         *     allowed object is
         *     {@link Duration }
         *     
         */
        public void setMaxAge(Duration value) {
            this.maxAge = value;
        }

        /**
         * Gets the value of the retry property.
         * 
         * @return
         *     possible object is
         *     {@link Short }
         *     
         */
        public Short getRetry() {
            return retry;
        }

        /**
         * Sets the value of the retry property.
         * 
         * @param value
         *     allowed object is
         *     {@link Short }
         *     
         */
        public void setRetry(Short value) {
            this.retry = value;
        }

        /**
         * Gets the value of the ignore property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isIgnore() {
            return ignore;
        }

        /**
         * Sets the value of the ignore property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIgnore(Boolean value) {
            this.ignore = value;
        }

        /**
         * Gets the value of the includeMetadata property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isIncludeMetadata() {
            return includeMetadata;
        }

        /**
         * Sets the value of the includeMetadata property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIncludeMetadata(Boolean value) {
            this.includeMetadata = value;
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
         *       &lt;choice>
         *         &lt;element ref="{http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/INT/IdmStrategy/Request}request"/>
         *         &lt;element ref="{http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/KEN/IdmCb5/Company/GetCustomReport/Request}request"/>
         *         &lt;element ref="{http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/KEN/IdmCb5/Individual/GetCustomReport/Request}request"/>
         *       &lt;/choice>
         *       &lt;attribute name="priority" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Request}priority" />
         *       &lt;attribute name="id" use="required" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Request}guid" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "content"
        })
        public static class Data {

            @XmlElementRefs({
                @XmlElementRef(name = "request", namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/KEN/IdmCb5/Individual/GetCustomReport/Request", type = com.creditinfo.schemas._2012._09.multiconnector.connectors.ken.idmcb5.individual.getcustomreport.request.Request.class, required = false),
                @XmlElementRef(name = "request", namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/KEN/IdmCb5/Company/GetCustomReport/Request", type = com.creditinfo.schemas._2012._09.multiconnector.connectors.ken.idmcb5.company.getcustomreport.request.Request.class, required = false),
                @XmlElementRef(name = "request", namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/INT/IdmStrategy/Request", type = com.creditinfo.schemas._2012._09.multiconnector.connectors._int.idmstrategy.request.Request.class, required = false)
            })
            protected List<Object> content;
            @XmlAttribute(name = "priority")
            protected Short priority;
            @XmlAttribute(name = "id", required = true)
            protected String id;

            /**
             * Gets the rest of the content model. 
             * 
             * <p>
             * You are getting this "catch-all" property because of the following reason: 
             * The field name "Request" is used by two different parts of a schema. See: 
             * line 1 of file:/C:/Cis/Data/Ken/_IDM/Idm.wsdl
             * line 1 of file:/C:/Cis/Data/Ken/_IDM/Idm.wsdl
             * <p>
             * To get rid of this property, apply a property customization to one 
             * of both of the following declarations to change their names: 
             * Gets the value of the content property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the content property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getContent().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link com.creditinfo.schemas._2012._09.multiconnector.connectors.ken.idmcb5.individual.getcustomreport.request.Request }
             * {@link com.creditinfo.schemas._2012._09.multiconnector.connectors.ken.idmcb5.company.getcustomreport.request.Request }
             * {@link com.creditinfo.schemas._2012._09.multiconnector.connectors._int.idmstrategy.request.Request }
             * 
             * 
             */
            public List<Object> getContent() {
                if (content == null) {
                    content = new ArrayList<Object>();
                }
                return this.content;
            }

            /**
             * Gets the value of the priority property.
             * 
             * @return
             *     possible object is
             *     {@link Short }
             *     
             */
            public Short getPriority() {
                return priority;
            }

            /**
             * Sets the value of the priority property.
             * 
             * @param value
             *     allowed object is
             *     {@link Short }
             *     
             */
            public void setPriority(Short value) {
                this.priority = value;
            }

            /**
             * Gets the value of the id property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getId() {
                return id;
            }

            /**
             * Sets the value of the id property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setId(String value) {
                this.id = value;
            }

        }

    }

}
