package org.dgac.app.exception;

import java.io.Serializable;

public class DgacBusinessException extends Exception implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String descripcion;
	public DgacBusinessException(String mensaje)
    {
        super(mensaje);
        this.descripcion = "";
    }
   
    public DgacBusinessException(String mensaje, String descripcion)
    {
        super(mensaje);
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }    

}
