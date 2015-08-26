/*
 * 
 */
package org.dgac.common.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class RolDTO.
 */
public class RolDTO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The rol_id. */
	private long rol_id;
	
	/** The rol_nombre. */
	private String rol_nombre;
	
	/** The rol_prioridad. */
	private int rol_prioridad;
	
	/** The rol_admin. */
	private int rol_admin;
	
	/** The rol_super_admin. */
	private int rol_super_admin;
	
	/** The selected. */
	private boolean selected;
	
	private int rol_estado;
	
	/**
	 * Gets the rol_id.
	 *
	 * @return the rol_id
	 */
	public long getRol_id() {
		return rol_id;
	}
	
	/**
	 * Sets the rol_id.
	 *
	 * @param rol_id the new rol_id
	 */
	public void setRol_id(long rol_id) {
		this.rol_id = rol_id;
	}
	
	/**
	 * Gets the rol_nombre.
	 *
	 * @return the rol_nombre
	 */
	public String getRol_nombre() {
		return rol_nombre;
	}
	
	/**
	 * Sets the rol_nombre.
	 *
	 * @param rol_nombre the new rol_nombre
	 */
	public void setRol_nombre(String rol_nombre) {
		this.rol_nombre = rol_nombre;
	}
	
	/**
	 * Gets the rol_prioridad.
	 *
	 * @return the rol_prioridad
	 */
	public int getRol_prioridad() {
		return rol_prioridad;
	}
	
	/**
	 * Sets the rol_prioridad.
	 *
	 * @param rol_prioridad the new rol_prioridad
	 */
	public void setRol_prioridad(int rol_prioridad) {
		this.rol_prioridad = rol_prioridad;
	}

	/**
	 * Gets the rol_admin.
	 *
	 * @return the rol_admin
	 */
	public int getRol_admin() {
		return rol_admin;
	}

	/**
	 * Sets the rol_admin.
	 *
	 * @param rol_admin the new rol_admin
	 */
	public void setRol_admin(int rol_admin) {
		this.rol_admin = rol_admin;
	}

	/**
	 * Gets the rol_super_admin.
	 *
	 * @return the rol_super_admin
	 */
	public int getRol_super_admin() {
		return rol_super_admin;
	}

	/**
	 * Sets the rol_super_admin.
	 *
	 * @param rol_super_admin the new rol_super_admin
	 */
	public void setRol_super_admin(int rol_super_admin) {
		this.rol_super_admin = rol_super_admin;
	}

	/**
	 * Gets the selected.
	 *
	 * @return the selected
	 */
	public boolean getSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected the new selected
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getRol_estado() {
		return rol_estado;
	}

	public void setRol_estado(int rol_estado) {
		this.rol_estado = rol_estado;
	}
}
