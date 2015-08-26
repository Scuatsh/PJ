package org.dgac.common.dto;

import java.io.Serializable;

public class UnidadAdicionalDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tabla_id;
	private int rut_funcionario;
	private int unidad_nemo;
	private String unidad_nemo_gl;
	
	public int getTabla_id() {
		return tabla_id;
	}
	public void setTabla_id(int tabla_id) {
		this.tabla_id = tabla_id;
	}
	public int getRut_funcionario() {
		return rut_funcionario;
	}
	public void setRut_funcionario(int rut_funcionario) {
		this.rut_funcionario = rut_funcionario;
	}
	public int getUnidad_nemo() {
		return unidad_nemo;
	}
	public void setUnidad_nemo(int unidad_nemo) {
		this.unidad_nemo = unidad_nemo;
	}
	public String getUnidad_nemo_gl() {
		return unidad_nemo_gl;
	}
	public void setUnidad_nemo_gl(String unidad_nemo_gl) {
		this.unidad_nemo_gl = unidad_nemo_gl;
	}
}
