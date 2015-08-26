
package org.dgac.ws.integration.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for unidadAdicionalDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="unidadAdicionalDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rut_funcionario" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tabla_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="unidad_nemo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="unidad_nemo_gl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "unidadAdicionalDTO", propOrder = {
    "rutFuncionario",
    "tablaId",
    "unidadNemo",
    "unidadNemoGl"
})
public class UnidadAdicionalDTO {

    @XmlElement(name = "rut_funcionario")
    protected int rutFuncionario;
    @XmlElement(name = "tabla_id")
    protected int tablaId;
    @XmlElement(name = "unidad_nemo")
    protected int unidadNemo;
    @XmlElement(name = "unidad_nemo_gl")
    protected String unidadNemoGl;

    /**
     * Gets the value of the rutFuncionario property.
     * 
     */
    public int getRutFuncionario() {
        return rutFuncionario;
    }

    /**
     * Sets the value of the rutFuncionario property.
     * 
     */
    public void setRutFuncionario(int value) {
        this.rutFuncionario = value;
    }

    /**
     * Gets the value of the tablaId property.
     * 
     */
    public int getTablaId() {
        return tablaId;
    }

    /**
     * Sets the value of the tablaId property.
     * 
     */
    public void setTablaId(int value) {
        this.tablaId = value;
    }

    /**
     * Gets the value of the unidadNemo property.
     * 
     */
    public int getUnidadNemo() {
        return unidadNemo;
    }

    /**
     * Sets the value of the unidadNemo property.
     * 
     */
    public void setUnidadNemo(int value) {
        this.unidadNemo = value;
    }

    /**
     * Gets the value of the unidadNemoGl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidadNemoGl() {
        return unidadNemoGl;
    }

    /**
     * Sets the value of the unidadNemoGl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidadNemoGl(String value) {
        this.unidadNemoGl = value;
    }

}
