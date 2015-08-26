
package org.dgac.ws.integration.server.alfresco.buscar;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cl.exe.obtenerdocumento package. 
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

    private final static QName _ObtenerDocumentoFault_QNAME = new QName("http://www.exe.cl/obtenerdocumento", "obtenerDocumentoFault");
    private final static QName _ObtenerDocumentoRequest_QNAME = new QName("http://www.exe.cl/obtenerdocumento", "obtenerDocumentoRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cl.exe.obtenerdocumento
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ObtenerDocumentoFault }
     * 
     */
    public ObtenerDocumentoFault createObtenerDocumentoFault() {
        return new ObtenerDocumentoFault();
    }

    /**
     * Create an instance of {@link ObtenerDocumentoRequest }
     * 
     */
    public ObtenerDocumentoRequest createObtenerDocumentoRequest() {
        return new ObtenerDocumentoRequest();
    }

    /**
     * Create an instance of {@link ObtenerDocumentoResponse }
     * 
     */
    public ObtenerDocumentoResponse createObtenerDocumentoResponse() {
        return new ObtenerDocumentoResponse();
    }

    /**
     * Create an instance of {@link DocumentoAlfresco }
     * 
     */
    public DocumentoAlfresco createDocumentoAlfresco() {
        return new DocumentoAlfresco();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerDocumentoFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.exe.cl/obtenerdocumento", name = "obtenerDocumentoFault")
    public JAXBElement<ObtenerDocumentoFault> createObtenerDocumentoFault(ObtenerDocumentoFault value) {
        return new JAXBElement<ObtenerDocumentoFault>(_ObtenerDocumentoFault_QNAME, ObtenerDocumentoFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerDocumentoRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.exe.cl/obtenerdocumento", name = "obtenerDocumentoRequest")
    public JAXBElement<ObtenerDocumentoRequest> createObtenerDocumentoRequest(ObtenerDocumentoRequest value) {
        return new JAXBElement<ObtenerDocumentoRequest>(_ObtenerDocumentoRequest_QNAME, ObtenerDocumentoRequest.class, null, value);
    }

}
