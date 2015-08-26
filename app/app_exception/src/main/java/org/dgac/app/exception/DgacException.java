package org.dgac.app.exception;

import java.io.Serializable;

import or.dgac.app.exception.dto.ErrorLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class DgacException.
 */
public class DgacException extends Exception implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The clase. */
	private String clase;
	
	/** The mensaje. */
	private String mensaje;
	
	/** The metodo. */
	private String metodo;
	
	/** The obj error logger. */
	private ErrorLogger objErrorLogger;
	
	/**
	 * Instantiates a new dgac exception.
	 *
	 * @param clase the clase
	 * @param mensaje the mensaje
	 * @param metodo the metodo
	 */
	public DgacException(String clase, String mensaje, String metodo
			) {
		super(mensaje);
		this.clase = clase;
		this.mensaje = mensaje;
		this.metodo = metodo;
		
	}
	
	

}
