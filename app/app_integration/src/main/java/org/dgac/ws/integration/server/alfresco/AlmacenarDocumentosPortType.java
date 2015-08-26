
package org.dgac.ws.integration.server.alfresco;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import org.dgac.ws.integration.server.alfresco.AlmacenarDocumentosRequest;
import org.dgac.ws.integration.server.alfresco.AlmacenarDocumentosResponse;
import org.dgac.ws.integration.server.alfresco.ObjectFactory;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "AlmacenarDocumentosPortType", targetNamespace = "http://soa.jboss.org/almacenardocumentos")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface AlmacenarDocumentosPortType {


    /**
     * 
     * @param in
     * @return
     *     returns cl.exe.almacenardocumentos.AlmacenarDocumentosResponse
     * @throws AlmacenarDocumentosFault1
     */
    @WebMethod(operationName = "AlmacenarDocumentosOp", action = "http://soa.jboss.org/almacenardocumentos/AlmacenarDocumentosOp")
    @WebResult(name = "almacenarDocumentosResponse", targetNamespace = "http://www.exe.cl/almacenardocumentos", partName = "out")
    public AlmacenarDocumentosResponse almacenarDocumentosOp(
        @WebParam(name = "almacenarDocumentosRequest", targetNamespace = "http://www.exe.cl/almacenardocumentos", partName = "in")
        AlmacenarDocumentosRequest in)
        throws AlmacenarDocumentosFault1
    ;

}