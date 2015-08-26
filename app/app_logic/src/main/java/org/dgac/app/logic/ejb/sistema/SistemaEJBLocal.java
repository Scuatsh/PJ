package org.dgac.app.logic.ejb.sistema;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import org.dgac.app.exception.DgacBusinessException;
import org.dgac.common.dto.LogDTO;
import org.dgac.common.dto.ResultActiveDirectoryDTO;
import org.dgac.common.dto.RolDTO;


// TODO: Auto-generated Javadoc
/**
 * The Interface SistemaEJBLocal.
 */
@Local
public interface SistemaEJBLocal {

	/**
	 * Login active directory.
	 *
	 * @param usrName the usr name
	 * @param password the password
	 * @return the result active directory dto
	 * @throws DgacBusinessException the dgac business exception
	 */
	public ResultActiveDirectoryDTO loginActiveDirectory(String usrName,String password) throws DgacBusinessException;
	/**
	 * Registro log.
	 *
	 * @param log the log
	 */
	public void registroLog(LogDTO log);
	public List<RolDTO> returnRolesTramites(String usuario);
}
