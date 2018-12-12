
package com.creditinfo.schemas._2012._09.multiconnector.messages.request;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.creditinfo.schemas._2012._09.multiconnector.messages.request package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RequestXml_QNAME = new QName("http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Request", "RequestXml");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.creditinfo.schemas._2012._09.multiconnector.messages.request
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RequestXml }
     * 
     */
    public RequestXml createRequestXml() {
        return new RequestXml();
    }

    /**
     * Create an instance of {@link RequestXml.Connector }
     * 
     */
    public RequestXml.Connector createRequestXmlConnector() {
        return new RequestXml.Connector();
    }

    /**
     * Create an instance of {@link RequestXml.Connector.Data }
     * 
     */
    public RequestXml.Connector.Data createRequestXmlConnectorData() {
        return new RequestXml.Connector.Data();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestXml }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://creditinfo.com/schemas/2012/09/MultiConnector/Messages/Request", name = "RequestXml")
    public JAXBElement<RequestXml> createRequestXml(RequestXml value) {
        return new JAXBElement<RequestXml>(_RequestXml_QNAME, RequestXml.class, null, value);
    }

}
