
package com.creditinfo.schemas._2012._09.multiconnector.connectors._int.idmstrategy.request;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.creditinfo.schemas._2012._09.multiconnector.connectors._int.idmstrategy.request package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.creditinfo.schemas._2012._09.multiconnector.connectors._int.idmstrategy.request
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Request }
     * 
     */
    public Request createRequest() {
        return new Request();
    }

    /**
     * Create an instance of {@link Request.Strategy }
     * 
     */
    public Request.Strategy createRequestStrategy() {
        return new Request.Strategy();
    }

    /**
     * Create an instance of {@link Request.Cb5SearchParameters }
     * 
     */
    public Request.Cb5SearchParameters createRequestCb5SearchParameters() {
        return new Request.Cb5SearchParameters();
    }

    /**
     * Create an instance of {@link Request.CustomFields }
     * 
     */
    public Request.CustomFields createRequestCustomFields() {
        return new Request.CustomFields();
    }

}
