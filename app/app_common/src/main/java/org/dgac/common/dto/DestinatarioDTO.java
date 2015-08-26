package org.dgac.common.dto;

import java.io.Serializable;

public class DestinatarioDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long des_id;
	private String des_nombre_small;
	private String des_nombre_large;
	private  int des_estado;
	
	public long getDes_id() {
		return des_id;
	}
	public void setDes_id(long des_id) {
		this.des_id = des_id;
	}
	public String getDes_nombre_small() {
		return des_nombre_small;
	}
	public void setDes_nombre_small(String des_nombre_small) {
		this.des_nombre_small = des_nombre_small;
	}
	public String getDes_nombre_large() {
		return des_nombre_large;
	}
	public void setDes_nombre_large(String des_nombre_large) {
		this.des_nombre_large = des_nombre_large;
	}
	public int getDes_estado() {
		return des_estado;
	}
	public void setDes_estado(int des_estado) {
		this.des_estado = des_estado;
	}
}
