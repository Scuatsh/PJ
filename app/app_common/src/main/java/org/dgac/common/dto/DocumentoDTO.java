package org.dgac.common.dto;

import java.io.Serializable;
import java.sql.Date;

public class DocumentoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long doc_id;
	private int doc_ano;
	private long des_id;
	private long desc_id;
	private String doc_materia;
	private Date doc_fecha_ingreso;
	private String doc_numero;
	private long tpo_id;
	private String doc_resumen;
	private String doc_abogado;
	private String doc_nombre_archivo;
	private String doc_id_alfresco;
	private String doc_usr_insert;
	private String doc_usr_mod;
	private Date doc_fecha_mod;
	private int doc_estado;
	
	private String nombreDestinatario;
	private String nombreDescriptor;
	private String nombreTipoDoc;
	private long size;
	private byte[] byteArray;
	
	public long getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(long doc_id) {
		this.doc_id = doc_id;
	}
	public int getDoc_ano() {
		return doc_ano;
	}
	public void setDoc_ano(int doc_ano) {
		this.doc_ano = doc_ano;
	}
	public long getDes_id() {
		return des_id;
	}
	public void setDes_id(long des_id) {
		this.des_id = des_id;
	}
	public long getDesc_id() {
		return desc_id;
	}
	public void setDesc_id(long desc_id) {
		this.desc_id = desc_id;
	}
	public String getDoc_materia() {
		return doc_materia;
	}
	public void setDoc_materia(String doc_materia) {
		this.doc_materia = doc_materia;
	}
	public Date getDoc_fecha_ingreso() {
		return doc_fecha_ingreso;
	}
	public void setDoc_fecha_ingreso(Date doc_fecha_ingreso) {
		this.doc_fecha_ingreso = doc_fecha_ingreso;
	}
	public String getDoc_numero() {
		return doc_numero;
	}
	public void setDoc_numero(String doc_numero) {
		this.doc_numero = doc_numero;
	}
	public long getTpo_id() {
		return tpo_id;
	}
	public void setTpo_id(long tpo_id) {
		this.tpo_id = tpo_id;
	}
	public String getDoc_resumen() {
		return doc_resumen;
	}
	public void setDoc_resumen(String doc_resumen) {
		this.doc_resumen = doc_resumen;
	}
	public String getDoc_abogado() {
		return doc_abogado;
	}
	public void setDoc_abogado(String doc_abogado) {
		this.doc_abogado = doc_abogado;
	}
	public String getDoc_nombre_archivo() {
		return doc_nombre_archivo;
	}
	public void setDoc_nombre_archivo(String doc_nombre_archivo) {
		this.doc_nombre_archivo = doc_nombre_archivo;
	}
	public String getDoc_id_alfresco() {
		return doc_id_alfresco;
	}
	public void setDoc_id_alfresco(String doc_id_alfresco) {
		this.doc_id_alfresco = doc_id_alfresco;
	}
	public String getDoc_usr_insert() {
		return doc_usr_insert;
	}
	public void setDoc_usr_insert(String doc_usr_insert) {
		this.doc_usr_insert = doc_usr_insert;
	}
	public String getDoc_usr_mod() {
		return doc_usr_mod;
	}
	public void setDoc_usr_mod(String doc_usr_mod) {
		this.doc_usr_mod = doc_usr_mod;
	}
	public Date getDoc_fecha_mod() {
		return doc_fecha_mod;
	}
	public void setDoc_fecha_mod(Date doc_fecha_mod) {
		this.doc_fecha_mod = doc_fecha_mod;
	}
	public int getDoc_estado() {
		return doc_estado;
	}
	public void setDoc_estado(int doc_estado) {
		this.doc_estado = doc_estado;
	}
	public String getNombreDestinatario() {
		return nombreDestinatario;
	}
	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}
	public String getNombreDescriptor() {
		return nombreDescriptor;
	}
	public void setNombreDescriptor(String nombreDescriptor) {
		this.nombreDescriptor = nombreDescriptor;
	}
	public String getNombreTipoDoc() {
		return nombreTipoDoc;
	}
	public void setNombreTipoDoc(String nombreTipoDoc) {
		this.nombreTipoDoc = nombreTipoDoc;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public byte[] getByteArray() {
		return byteArray;
	}
	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}
}
