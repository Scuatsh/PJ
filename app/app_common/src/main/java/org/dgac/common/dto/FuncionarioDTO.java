package org.dgac.common.dto;

import org.dgac.common.dto.UnidadAdicionalDTO;

import java.io.Serializable;
import java.util.List;

public class FuncionarioDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rut;
	private String digito_verificador;
	private String nombres;
	private String apellido_materno;
	private String apellido_paterno;
	private String unidad;
	private String unidadNemo;
	private int unidadId;
	private int unidadNemoId;
	private String mail;
	private List<UnidadAdicionalDTO> unidadAdicionalList;
	public int getRut() {
		return rut;
	}
	public void setRut(int rut) {
		this.rut = rut;
	}
	public String getDigito_verificador() {
		return digito_verificador;
	}
	public void setDigito_verificador(String digito_verificador) {
		this.digito_verificador = digito_verificador;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellido_materno() {
		return apellido_materno;
	}
	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}
	public String getApellido_paterno() {
		return apellido_paterno;
	}
	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getUnidadId() {
		return unidadId;
	}
	public void setUnidadId(int unidadId) {
		this.unidadId = unidadId;
	}
	public int getUnidadNemoId() {
		return unidadNemoId;
	}
	public void setUnidadNemoId(int unidadNemoId) {
		this.unidadNemoId = unidadNemoId;
	}
	public String getUnidadNemo() {
		return unidadNemo;
	}
	public void setUnidadNemo(String unidadNemo) {
		this.unidadNemo = unidadNemo;
	}
	public List<UnidadAdicionalDTO> getUnidadAdicionalList() {
		return unidadAdicionalList;
	}
	public void setUnidadAdicionalList(List<UnidadAdicionalDTO> unidadAdicionalList) {
		this.unidadAdicionalList = unidadAdicionalList;
	}
}
