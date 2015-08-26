package org.dgac.common.dto;

import java.io.Serializable;

public class UnidadAdicional implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int unidadNemoID;
	public String unidadNemo;
	
	public int getUnidadNemoID() {
		return unidadNemoID;
	}
	public void setUnidadNemoID(int unidadNemoID) {
		this.unidadNemoID = unidadNemoID;
	}
	public String getUnidadNemo() {
		return unidadNemo;
	}
	public void setUnidadNemo(String unidadNemo) {
		this.unidadNemo = unidadNemo;
	}
}
