package org.dgac.common.dto;

import java.io.Serializable;

public class TipoDocumentoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long tpo_id;
	private String tpo_nombre_small;
	private String tpo_nombre_large;
	private  int tpo_estado;
	
	public long getTpo_id() {
		return tpo_id;
	}
	public void setTpo_id(long tpo_id) {
		this.tpo_id = tpo_id;
	}
	public String getTpo_nombre_small() {
		return tpo_nombre_small;
	}
	public void setTpo_nombre_small(String tpo_nombre_small) {
		this.tpo_nombre_small = tpo_nombre_small;
	}
	public String getTpo_nombre_large() {
		return tpo_nombre_large;
	}
	public void setTpo_nombre_large(String tpo_nombre_large) {
		this.tpo_nombre_large = tpo_nombre_large;
	}
	public int getTpo_estado() {
		return tpo_estado;
	}
	public void setTpo_estado(int tpo_estado) {
		this.tpo_estado = tpo_estado;
	}
}
