package org.dgac.common.enums;

public enum RolEnum {

	/** The err. */
	USUARIO("USR"),
	
	/** The inf. */
	ADMIN("GG_PORTAL_JURIDICA_ADMINISTRADOR"),
	
	/** The warn. */
	SUPERADMIN("GG_PORTAL_JURIDICA_SUPERADMINISTRADOR");
	
	/** The id. */
	private String id;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Instantiates a new enum version.
	 *
	 * @param id the id
	 */
	private RolEnum(String id) {
		this.id = id;
	}
}
