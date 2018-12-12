
package com.creditinfo.schemas._2012._09.multiconnector.messages.response;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element name="connector" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;choice>
 *                     &lt;element name="timeout" minOccurs="0">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;attribute name="id" use="required" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Response}guid" />
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="invalid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="notFound" minOccurs="0">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;sequence>
 *                               &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;/sequence>
 *                             &lt;attribute name="id" use="required" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Response}guid" />
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="error" minOccurs="0">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;sequence>
 *                               &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                               &lt;element name="stackTrace" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;/sequence>
 *                             &lt;attribute name="id" use="required" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Response}guid" />
 *                             &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="data" minOccurs="0">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;choice>
 *                               &lt;element ref="{http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/INT/IdmStrategy/Response}response"/>
 *                               &lt;element ref="{http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/KEN/IdmCb5/Company/GetCustomReport/Response}response"/>
 *                               &lt;element ref="{http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/KEN/IdmCb5/Individual/GetCustomReport/Response}response"/>
 *                             &lt;/choice>
 *                             &lt;attribute name="id" use="required" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Response}guid" />
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                   &lt;/choice>
 *                   &lt;element name="metadata" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="isCacheUsed" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                           &lt;attribute name="requestCreated" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *                           &lt;attribute name="elapsedMilliseconds" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *                           &lt;attribute name="connectorAttemptsCount" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                           &lt;attribute name="isBusinessTimeouted" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                           &lt;attribute name="priority" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                           &lt;attribute name="userName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="inserted" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *                           &lt;attribute name="validTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="id" use="required" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Response}guid" />
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
@XmlType(name = "", propOrder = {
    "connector"
})
@XmlRootElement(name = "response")
public class Response {

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
     *         &lt;choice>
     *           &lt;element name="timeout" minOccurs="0">
     *             &lt;complexType>
     *               &lt;complexContent>
     *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                   &lt;attribute name="id" use="required" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Response}guid" />
     *                 &lt;/restriction>
     *               &lt;/complexContent>
     *             &lt;/complexType>
     *           &lt;/element>
     *           &lt;element name="invalid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *           &lt;element name="notFound" minOccurs="0">
     *             &lt;complexType>
     *               &lt;complexContent>
     *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                   &lt;sequence>
     *                     &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;/sequence>
     *                   &lt;attribute name="id" use="required" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Response}guid" />
     *                 &lt;/restriction>
     *               &lt;/complexContent>
     *             &lt;/complexType>
     *           &lt;/element>
     *           &lt;element name="error" minOccurs="0">
     *             &lt;complexType>
     *               &lt;complexContent>
     *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                   &lt;sequence>
     *                     &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                     &lt;element name="stackTrace" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;/sequence>
     *                   &lt;attribute name="id" use="required" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Response}guid" />
     *                   &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;/restriction>
     *               &lt;/complexContent>
     *             &lt;/complexType>
     *           &lt;/element>
     *           &lt;element name="data" minOccurs="0">
     *             &lt;complexType>
     *               &lt;complexContent>
     *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                   &lt;choice>
     *                     &lt;element ref="{http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/INT/IdmStrategy/Response}response"/>
     *                     &lt;element ref="{http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/KEN/IdmCb5/Company/GetCustomReport/Response}response"/>
     *                     &lt;element ref="{http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/KEN/IdmCb5/Individual/GetCustomReport/Response}response"/>
     *                   &lt;/choice>
     *                   &lt;attribute name="id" use="required" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Response}guid" />
     *                 &lt;/restriction>
     *               &lt;/complexContent>
     *             &lt;/complexType>
     *           &lt;/element>
     *         &lt;/choice>
     *         &lt;element name="metadata" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="isCacheUsed" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                 &lt;attribute name="requestCreated" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
     *                 &lt;attribute name="elapsedMilliseconds" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
     *                 &lt;attribute name="connectorAttemptsCount" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
     *                 &lt;attribute name="isBusinessTimeouted" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                 &lt;attribute name="priority" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
     *                 &lt;attribute name="userName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="inserted" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
     *                 &lt;attribute name="validTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="id" use="required" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Response}guid" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "timeout",
        "invalid",
        "notFound",
        "error",
        "data",
        "metadata"
    })
    public static class Connector {

        protected Timeout timeout;
        protected String invalid;
        protected NotFound notFound;
        protected Error error;
        protected Data data;
        protected Metadata metadata;
        @XmlAttribute(name = "id", required = true)
        protected String id;

        /**
         * Gets the value of the timeout property.
         * 
         * @return
         *     possible object is
         *     {@link Timeout }
         *     
         */
        public Timeout getTimeout() {
            return timeout;
        }

        /**
         * Sets the value of the timeout property.
         * 
         * @param value
         *     allowed object is
         *     {@link Timeout }
         *     
         */
        public void setTimeout(Timeout value) {
            this.timeout = value;
        }

        /**
         * Gets the value of the invalid property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getInvalid() {
            return invalid;
        }

        /**
         * Sets the value of the invalid property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInvalid(String value) {
            this.invalid = value;
        }

        /**
         * Gets the value of the notFound property.
         * 
         * @return
         *     possible object is
         *     {@link NotFound }
         *     
         */
        public NotFound getNotFound() {
            return notFound;
        }

        /**
         * Sets the value of the notFound property.
         * 
         * @param value
         *     allowed object is
         *     {@link NotFound }
         *     
         */
        public void setNotFound(NotFound value) {
            this.notFound = value;
        }

        /**
         * Gets the value of the error property.
         * 
         * @return
         *     possible object is
         *     {@link Error }
         *     
         */
        public Error getError() {
            return error;
        }

        /**
         * Sets the value of the error property.
         * 
         * @param value
         *     allowed object is
         *     {@link Error }
         *     
         */
        public void setError(Error value) {
            this.error = value;
        }

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
         * Gets the value of the metadata property.
         * 
         * @return
         *     possible object is
         *     {@link Metadata }
         *     
         */
        public Metadata getMetadata() {
            return metadata;
        }

        /**
         * Sets the value of the metadata property.
         * 
         * @param value
         *     allowed object is
         *     {@link Metadata }
         *     
         */
        public void setMetadata(Metadata value) {
            this.metadata = value;
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
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;choice>
         *         &lt;element ref="{http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/INT/IdmStrategy/Response}response"/>
         *         &lt;element ref="{http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/KEN/IdmCb5/Company/GetCustomReport/Response}response"/>
         *         &lt;element ref="{http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/KEN/IdmCb5/Individual/GetCustomReport/Response}response"/>
         *       &lt;/choice>
         *       &lt;attribute name="id" use="required" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Response}guid" />
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
                @XmlElementRef(name = "response", namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/KEN/IdmCb5/Individual/GetCustomReport/Response", type = com.creditinfo.schemas._2012._09.multiconnector.connectors.ken.idmcb5.individual.getcustomreport.response.Response.class, required = false),
                @XmlElementRef(name = "response", namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/INT/IdmStrategy/Response", type = com.creditinfo.schemas._2012._09.multiconnector.connectors._int.idmstrategy.response.Response.class, required = false),
                @XmlElementRef(name = "response", namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector/Connectors/KEN/IdmCb5/Company/GetCustomReport/Response", type = com.creditinfo.schemas._2012._09.multiconnector.connectors.ken.idmcb5.company.getcustomreport.response.Response.class, required = false)
            })
            protected List<Object> content;
            @XmlAttribute(name = "id", required = true)
            protected String id;

            /**
             * Gets the rest of the content model. 
             * 
             * <p>
             * You are getting this "catch-all" property because of the following reason: 
             * The field name "Response" is used by two different parts of a schema. See: 
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
             * {@link com.creditinfo.schemas._2012._09.multiconnector.connectors.ken.idmcb5.individual.getcustomreport.response.Response }
             * {@link com.creditinfo.schemas._2012._09.multiconnector.connectors._int.idmstrategy.response.Response }
             * {@link com.creditinfo.schemas._2012._09.multiconnector.connectors.ken.idmcb5.company.getcustomreport.response.Response }
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
         *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="stackTrace" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *       &lt;attribute name="id" use="required" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Response}guid" />
         *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "message",
            "stackTrace"
        })
        public static class Error {

            @XmlElement(required = true)
            protected String message;
            @XmlElement(required = true)
            protected String stackTrace;
            @XmlAttribute(name = "id", required = true)
            protected String id;
            @XmlAttribute(name = "type", required = true)
            protected String type;

            /**
             * Gets the value of the message property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMessage() {
                return message;
            }

            /**
             * Sets the value of the message property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMessage(String value) {
                this.message = value;
            }

            /**
             * Gets the value of the stackTrace property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStackTrace() {
                return stackTrace;
            }

            /**
             * Sets the value of the stackTrace property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStackTrace(String value) {
                this.stackTrace = value;
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
             * Gets the value of the type property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getType() {
                return type;
            }

            /**
             * Sets the value of the type property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setType(String value) {
                this.type = value;
            }

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
         *       &lt;attribute name="isCacheUsed" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *       &lt;attribute name="requestCreated" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
         *       &lt;attribute name="elapsedMilliseconds" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
         *       &lt;attribute name="connectorAttemptsCount" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
         *       &lt;attribute name="isBusinessTimeouted" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *       &lt;attribute name="priority" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
         *       &lt;attribute name="userName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="inserted" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
         *       &lt;attribute name="validTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Metadata {

            @XmlAttribute(name = "isCacheUsed")
            protected Boolean isCacheUsed;
            @XmlAttribute(name = "requestCreated", required = true)
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar requestCreated;
            @XmlAttribute(name = "elapsedMilliseconds", required = true)
            protected long elapsedMilliseconds;
            @XmlAttribute(name = "connectorAttemptsCount", required = true)
            protected int connectorAttemptsCount;
            @XmlAttribute(name = "isBusinessTimeouted", required = true)
            protected boolean isBusinessTimeouted;
            @XmlAttribute(name = "priority", required = true)
            protected int priority;
            @XmlAttribute(name = "userName", required = true)
            protected String userName;
            @XmlAttribute(name = "inserted")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar inserted;
            @XmlAttribute(name = "validTo")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar validTo;

            /**
             * Gets the value of the isCacheUsed property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isIsCacheUsed() {
                return isCacheUsed;
            }

            /**
             * Sets the value of the isCacheUsed property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setIsCacheUsed(Boolean value) {
                this.isCacheUsed = value;
            }

            /**
             * Gets the value of the requestCreated property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getRequestCreated() {
                return requestCreated;
            }

            /**
             * Sets the value of the requestCreated property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setRequestCreated(XMLGregorianCalendar value) {
                this.requestCreated = value;
            }

            /**
             * Gets the value of the elapsedMilliseconds property.
             * 
             */
            public long getElapsedMilliseconds() {
                return elapsedMilliseconds;
            }

            /**
             * Sets the value of the elapsedMilliseconds property.
             * 
             */
            public void setElapsedMilliseconds(long value) {
                this.elapsedMilliseconds = value;
            }

            /**
             * Gets the value of the connectorAttemptsCount property.
             * 
             */
            public int getConnectorAttemptsCount() {
                return connectorAttemptsCount;
            }

            /**
             * Sets the value of the connectorAttemptsCount property.
             * 
             */
            public void setConnectorAttemptsCount(int value) {
                this.connectorAttemptsCount = value;
            }

            /**
             * Gets the value of the isBusinessTimeouted property.
             * 
             */
            public boolean isIsBusinessTimeouted() {
                return isBusinessTimeouted;
            }

            /**
             * Sets the value of the isBusinessTimeouted property.
             * 
             */
            public void setIsBusinessTimeouted(boolean value) {
                this.isBusinessTimeouted = value;
            }

            /**
             * Gets the value of the priority property.
             * 
             */
            public int getPriority() {
                return priority;
            }

            /**
             * Sets the value of the priority property.
             * 
             */
            public void setPriority(int value) {
                this.priority = value;
            }

            /**
             * Gets the value of the userName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUserName() {
                return userName;
            }

            /**
             * Sets the value of the userName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUserName(String value) {
                this.userName = value;
            }

            /**
             * Gets the value of the inserted property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getInserted() {
                return inserted;
            }

            /**
             * Sets the value of the inserted property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setInserted(XMLGregorianCalendar value) {
                this.inserted = value;
            }

            /**
             * Gets the value of the validTo property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getValidTo() {
                return validTo;
            }

            /**
             * Sets the value of the validTo property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setValidTo(XMLGregorianCalendar value) {
                this.validTo = value;
            }

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
         *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *       &lt;attribute name="id" use="required" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Response}guid" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "message"
        })
        public static class NotFound {

            @XmlElement(required = true)
            protected String message;
            @XmlAttribute(name = "id", required = true)
            protected String id;

            /**
             * Gets the value of the message property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMessage() {
                return message;
            }

            /**
             * Sets the value of the message property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMessage(String value) {
                this.message = value;
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


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="id" use="required" type="{http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Response}guid" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Timeout {

            @XmlAttribute(name = "id", required = true)
            protected String id;

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
