
package org.dgac.ws.integration.server.alfresco;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="documentos" type="{http://www.exe.cl/almacenardocumentos}documentoAlfrescoResponseArray"/>
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
    "documentos"
})
@XmlRootElement(name = "almacenarDocumentosResponse")
public class AlmacenarDocumentosResponse {

    @XmlElement(required = true)
    protected DocumentoAlfrescoResponseArray documentos;

    /**
     * Gets the value of the documentos property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentoAlfrescoResponseArray }
     *     
     */
    public DocumentoAlfrescoResponseArray getDocumentos() {
        return documentos;
    }

    /**
     * Sets the value of the documentos property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentoAlfrescoResponseArray }
     *     
     */
    public void setDocumentos(DocumentoAlfrescoResponseArray value) {
        this.documentos = value;
    }

}
