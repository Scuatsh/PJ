package org.dgac.common.enums;

// TODO: Auto-generated Javadoc
/**
 * The Enum LevelEnum.
 */
public enum LevelEnum {

	/** The err. */
	ERR("ERR"),
	
	/** The inf. */
	INF("INF"),
	
	/** The warn. */
	WARN("WARN");
	
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
	private LevelEnum(String id) {
		this.id = id;
	}
}
