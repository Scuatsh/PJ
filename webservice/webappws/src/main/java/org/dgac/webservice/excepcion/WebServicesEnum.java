package org.dgac.webservice.excepcion;

public enum WebServicesEnum {

	SIS_0000(0),
	/**
	 * Se ha producido un error de sistema.
	 */
	SIS_0001(10),
	/**
	 * Se ha producido un error desconocido.
	 */
	INT_0001(1),
	/**
	 * Se ha producido un error al tratar de obtener los datos de configuracion del WS.
	 */
	INT_0002(2),
	/**
	 * Se ha producido un error con la comunicación con el WS.
	 */
	INT_0003(3),
	/**
	 * El WS retorno un Codigo de Error.
	 */
	INT_0004(4);

	private int id;
	public int getId(){
		return this.id;
	}
	private WebServicesEnum(int id){
		this.id = id;
	}
}
