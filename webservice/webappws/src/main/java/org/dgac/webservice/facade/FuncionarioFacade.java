package org.dgac.webservice.facade;

import java.util.List;

import org.dgac.common.dto.FuncionarioDTO;

public interface FuncionarioFacade {

	public FuncionarioDTO obtenerFuncionario(String p_email) throws Exception;
	public List<FuncionarioDTO> obtenerFuncionarioList() throws Exception;
	public FuncionarioDTO obtenerFuncionario(int p_rut) throws Exception;
}
