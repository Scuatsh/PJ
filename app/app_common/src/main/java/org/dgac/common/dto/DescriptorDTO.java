package org.dgac.common.dto;

import java.io.Serializable;

public class DescriptorDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long desc_id;
	private String desc_nombre_small;
	private String desc_nombre_large;
	private int desc_estado;
	
	public long getDesc_id() {
		return desc_id;
	}
	public void setDesc_id(long desc_id) {
		this.desc_id = desc_id;
	}
	public String getDesc_nombre_small() {
		return desc_nombre_small;
	}
	public void setDesc_nombre_small(String desc_nombre_small) {
		this.desc_nombre_small = desc_nombre_small;
	}
	public String getDesc_nombre_large() {
		return desc_nombre_large;
	}
	public void setDesc_nombre_large(String desc_nombre_large) {
		this.desc_nombre_large = desc_nombre_large;
	}
	public int getDesc_estado() {
		return desc_estado;
	}
	public void setDesc_estado(int desc_estado) {
		this.desc_estado = desc_estado;
	}
}
