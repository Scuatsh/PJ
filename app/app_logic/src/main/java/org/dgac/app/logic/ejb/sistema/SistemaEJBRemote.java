package org.dgac.app.logic.ejb.sistema;

import java.util.List;

import javax.ejb.Remote;

import org.dgac.app.exception.DgacBusinessException;
import org.dgac.common.dto.LogDTO;
import org.dgac.common.dto.ResultActiveDirectoryDTO;
import org.dgac.common.dto.RolDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface SistemaEJBRemote.
 */
@Remote
public interface SistemaEJBRemote {

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
