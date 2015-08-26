/*
 * 
 */
package org.dgac.common.dto;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ResultActiveDirectoryDTO.
 */
public class ResultActiveDirectoryDTO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The codigo. */
	public int codigo;
	
	/** The mensaje. */
	public String mensaje;
	
	/** The status. */
	public boolean status;
	
	/** The nombre grupos. */
	public List<String> nombreGrupos;
	
	public FuncionarioDTO funcionario;
	
	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	
	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	/**
	 * Checks if is status.
	 *
	 * @return true, if is status
	 */
	public boolean isStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	/**
	 * Gets the nombre grupos.
	 *
	 * @return the nombre grupos
	 */
	public List<String> getNombreGrupos() {
		return nombreGrupos;
	}
	
	/**
	 * Sets the nombre grupos.
	 *
	 * @param nombreGrupos the new nombre grupos
	 */
	public void setNombreGrupos(List<String> nombreGrupos) {
		this.nombreGrupos = nombreGrupos;
	}

	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioDTO funcionario) {
		this.funcionario = funcionario;
	}
}
