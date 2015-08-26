
package org.dgac.ws.integration.server;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for funcionarioDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="funcionarioDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apellido_materno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="apellido_paterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="digito_verificador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rut" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="unidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unidadAdicionalList" type="{http://server.webservices.dgac.org/}unidadAdicionalDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="unidadId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="unidadNemo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unidadNemoId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "funcionarioDTO", propOrder = {
    "apellidoMaterno",
    "apellidoPaterno",
    "digitoVerificador",
    "mail",
    "nombres",
    "rut",
    "unidad",
    "unidadAdicionalList",
    "unidadId",
    "unidadNemo",
    "unidadNemoId"
})
public class FuncionarioDTO {

    @XmlElement(name = "apellido_materno")
    protected String apellidoMaterno;
    @XmlElement(name = "apellido_paterno")
    protected String apellidoPaterno;
    @XmlElement(name = "digito_verificador")
    protected String digitoVerificador;
    protected String mail;
    protected String nombres;
    protected int rut;
    protected String unidad;
    @XmlElement(nillable = true)
    protected List<UnidadAdicionalDTO> unidadAdicionalList;
    protected int unidadId;
    protected String unidadNemo;
    protected int unidadNemoId;

    /**
     * Gets the value of the apellidoMaterno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Sets the value of the apellidoMaterno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoMaterno(String value) {
        this.apellidoMaterno = value;
    }

    /**
     * Gets the value of the apellidoPaterno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Sets the value of the apellidoPaterno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoPaterno(String value) {
        this.apellidoPaterno = value;
    }

    /**
     * Gets the value of the digitoVerificador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDigitoVerificador() {
        return digitoVerificador;
    }

    /**
     * Sets the value of the digitoVerificador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDigitoVerificador(String value) {
        this.digitoVerificador = value;
    }

    /**
     * Gets the value of the mail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMail() {
        return mail;
    }

    /**
     * Sets the value of the mail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMail(String value) {
        this.mail = value;
    }

    /**
     * Gets the value of the nombres property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Sets the value of the nombres property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombres(String value) {
        this.nombres = value;
    }

    /**
     * Gets the value of the rut property.
     * 
     */
    public int getRut() {
        return rut;
    }

    /**
     * Sets the value of the rut property.
     * 
     */
    public void setRut(int value) {
        this.rut = value;
    }

    /**
     * Gets the value of the unidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidad() {
        return unidad;
    }

    /**
     * Sets the value of the unidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidad(String value) {
        this.unidad = value;
    }

    /**
     * Gets the value of the unidadAdicionalList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the unidadAdicionalList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnidadAdicionalList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UnidadAdicionalDTO }
     * 
     * 
     */
    public List<UnidadAdicionalDTO> getUnidadAdicionalList() {
        if (unidadAdicionalList == null) {
            unidadAdicionalList = new ArrayList<UnidadAdicionalDTO>();
        }
        return this.unidadAdicionalList;
    }

    /**
     * Gets the value of the unidadId property.
     * 
     */
    public int getUnidadId() {
        return unidadId;
    }

    /**
     * Sets the value of the unidadId property.
     * 
     */
    public void setUnidadId(int value) {
        this.unidadId = value;
    }

    /**
     * Gets the value of the unidadNemo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidadNemo() {
        return unidadNemo;
    }

    /**
     * Sets the value of the unidadNemo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidadNemo(String value) {
        this.unidadNemo = value;
    }

    /**
     * Gets the value of the unidadNemoId property.
     * 
     */
    public int getUnidadNemoId() {
        return unidadNemoId;
    }

    /**
     * Sets the value of the unidadNemoId property.
     * 
     */
    public void setUnidadNemoId(int value) {
        this.unidadNemoId = value;
    }

}
