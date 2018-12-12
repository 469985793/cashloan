
package com.creditinfo.schemas._2012._09.multiconnector.messages.response;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.creditinfo.schemas._2012._09.multiconnector.messages.response package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.creditinfo.schemas._2012._09.multiconnector.messages.response
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link Response.Connector }
     * 
     */
    public Response.Connector createResponseConnector() {
        return new Response.Connector();
    }

    /**
     * Create an instance of {@link Response.Connector.Timeout }
     * 
     */
    public Response.Connector.Timeout createResponseConnectorTimeout() {
        return new Response.Connector.Timeout();
    }

    /**
     * Create an instance of {@link Response.Connector.NotFound }
     * 
     */
    public Response.Connector.NotFound createResponseConnectorNotFound() {
        return new Response.Connector.NotFound();
    }

    /**
     * Create an instance of {@link Response.Connector.Error }
     * 
     */
    public Response.Connector.Error createResponseConnectorError() {
        return new Response.Connector.Error();
    }

    /**
     * Create an instance of {@link Response.Connector.Data }
     * 
     */
    public Response.Connector.Data createResponseConnectorData() {
        return new Response.Connector.Data();
    }

    /**
     * Create an instance of {@link Response.Connector.Metadata }
     * 
     */
    public Response.Connector.Metadata createResponseConnectorMetadata() {
        return new Response.Connector.Metadata();
    }

}
