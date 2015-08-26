
package org.dgac.ws.integration.server.alfresco;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cl.exe.almacenardocumentos package. 
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

    private final static QName _AlmacenarDocumentosRequest_QNAME = new QName("http://www.exe.cl/almacenardocumentos", "almacenarDocumentosRequest");
    private final static QName _AlmacenarDocumentosFault_QNAME = new QName("http://www.exe.cl/almacenardocumentos", "almacenarDocumentosFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cl.exe.almacenardocumentos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AlmacenarDocumentosResponse }
     * 
     */
    public AlmacenarDocumentosResponse createAlmacenarDocumentosResponse() {
        return new AlmacenarDocumentosResponse();
    }

    /**
     * Create an instance of {@link DocumentoAlfrescoResponseArray }
     * 
     */
    public DocumentoAlfrescoResponseArray createDocumentoAlfrescoResponseArray() {
        return new DocumentoAlfrescoResponseArray();
    }

    /**
     * Create an instance of {@link AlmacenarDocumentosRequest }
     * 
     */
    public AlmacenarDocumentosRequest createAlmacenarDocumentosRequest() {
        return new AlmacenarDocumentosRequest();
    }

    /**
     * Create an instance of {@link AlmacenarDocumentosFault }
     * 
     */
    public AlmacenarDocumentosFault createAlmacenarDocumentosFault() {
        return new AlmacenarDocumentosFault();
    }

    /**
     * Create an instance of {@link DocumentoAlfrescoResponse }
     * 
     */
    public DocumentoAlfrescoResponse createDocumentoAlfrescoResponse() {
        return new DocumentoAlfrescoResponse();
    }

    /**
     * Create an instance of {@link DocumentoAlfresco }
     * 
     */
    public DocumentoAlfresco createDocumentoAlfresco() {
        return new DocumentoAlfresco();
    }

    /**
     * Create an instance of {@link DocumentoAlfrescoArray }
     * 
     */
    public DocumentoAlfrescoArray createDocumentoAlfrescoArray() {
        return new DocumentoAlfrescoArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlmacenarDocumentosRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.exe.cl/almacenardocumentos", name = "almacenarDocumentosRequest")
    public JAXBElement<AlmacenarDocumentosRequest> createAlmacenarDocumentosRequest(AlmacenarDocumentosRequest value) {
        return new JAXBElement<AlmacenarDocumentosRequest>(_AlmacenarDocumentosRequest_QNAME, AlmacenarDocumentosRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlmacenarDocumentosFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.exe.cl/almacenardocumentos", name = "almacenarDocumentosFault")
    public JAXBElement<AlmacenarDocumentosFault> createAlmacenarDocumentosFault(AlmacenarDocumentosFault value) {
        return new JAXBElement<AlmacenarDocumentosFault>(_AlmacenarDocumentosFault_QNAME, AlmacenarDocumentosFault.class, null, value);
    }

}
