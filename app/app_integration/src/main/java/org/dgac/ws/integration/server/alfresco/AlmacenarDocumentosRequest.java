
package org.dgac.ws.integration.server.alfresco;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for almacenarDocumentosRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="almacenarDocumentosRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="documentos" type="{http://www.exe.cl/almacenardocumentos}documentoAlfrescoArray"/>
 *         &lt;element name="carpeta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "almacenarDocumentosRequest", propOrder = {
    "documentos",
    "carpeta"
})
public class AlmacenarDocumentosRequest {

    @XmlElement(required = true)
    protected DocumentoAlfrescoArray documentos;
    @XmlElement(required = true)
    protected String carpeta;

    /**
     * Gets the value of the documentos property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentoAlfrescoArray }
     *     
     */
    public DocumentoAlfrescoArray getDocumentos() {
        return documentos;
    }

    /**
     * Sets the value of the documentos property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentoAlfrescoArray }
     *     
     */
    public void setDocumentos(DocumentoAlfrescoArray value) {
        this.documentos = value;
    }

    /**
     * Gets the value of the carpeta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarpeta() {
        return carpeta;
    }

    /**
     * Sets the value of the carpeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarpeta(String value) {
        this.carpeta = value;
    }

}
