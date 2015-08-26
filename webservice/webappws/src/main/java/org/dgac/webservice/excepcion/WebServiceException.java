package org.dgac.webservice.excepcion;

public class WebServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WebServicesEnum webservicesExceptionEnum;
	private Object[] parameters;

	
	public WebServiceException(WebServicesEnum webservicesExceptionEnum, String mensaje) {
		super(mensaje);
		this.webservicesExceptionEnum = webservicesExceptionEnum; 
	}
	
	
	public WebServiceException(WebServicesEnum webservicesExceptionEnum, String mensaje, String... parameters) {
		super();
		this.webservicesExceptionEnum = webservicesExceptionEnum;
		this.parameters = parameters;
	}

	public WebServiceException(WebServicesEnum webservicesExceptionEnum, Throwable cause) {
		super(cause);
		this.webservicesExceptionEnum = webservicesExceptionEnum; 
	}
	
	
	public WebServiceException(WebServicesEnum webservicesExceptionEnum, Throwable cause, String... parameters) {
		super(cause);
		this.webservicesExceptionEnum = webservicesExceptionEnum; 
		this.parameters = parameters;
	}
	
	
	public WebServicesEnum getWebservicesExceptionEnum() {
		return webservicesExceptionEnum;
	}


	public Object[] getParameters() {
		return parameters;
	}
}
