package or.dgac.app.exception.dto;

import java.io.Serializable;


// TODO: Auto-generated Javadoc
/**
 * The Class ErrorLogger.
 */
public class ErrorLogger implements Serializable 
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The err clase. */
	private String errClase;

	/** The err metodo. */
	private String errMetodo;

	/** The err error. */
	private String errError;



	/**
	 * Instantiates a new error logger.
	 */
	public ErrorLogger() {
		super();
	}

	/**
	 * Instantiates a new error logger.
	 *
	 * @param errClase the err clase
	 * @param errMetodo the err metodo
	 * @param errError the err error
	 */
	public ErrorLogger(String errClase, String errMetodo, String errError) {
		super();
		this.errClase = errClase;
		this.errMetodo = errMetodo;
		this.errError = errError;
	}

	/**
	 * Gets the err clase.
	 *
	 * @return the err clase
	 */
	public String getErrClase() {
		return errClase;
	}

	/**
	 * Sets the err clase.
	 *
	 * @param errClase the new err clase
	 */
	public void setErrClase(String errClase) {
		this.errClase = errClase;
	}

	/**
	 * Gets the err metodo.
	 *
	 * @return the err metodo
	 */
	public String getErrMetodo() {
		return errMetodo;
	}

	/**
	 * Sets the err metodo.
	 *
	 * @param errMetodo the new err metodo
	 */
	public void setErrMetodo(String errMetodo) {
		this.errMetodo = errMetodo;
	}

	/**
	 * Gets the err error.
	 *
	 * @return the err error
	 */
	public String getErrError() {
		return errError;
	}

	/**
	 * Sets the err error.
	 *
	 * @param errError the new err error
	 */
	public void setErrError(String errError) {
		this.errError = errError;
	}







}
