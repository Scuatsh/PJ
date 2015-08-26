package org.dgac.app.logic.ejb.sistema;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.dgac.app.exception.DgacBusinessException;
import org.dgac.app.logic.service.sistema.SistemaService;
import org.dgac.common.dto.LogDTO;
import org.dgac.common.dto.ResultActiveDirectoryDTO;
import org.dgac.common.dto.RolDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

// TODO: Auto-generated Javadoc
/**
 * Session Bean implementation class SistemaEJB.
 */
@Interceptors({SpringBeanAutowiringInterceptor.class})
@Stateless(mappedName = "SistemaEJB")
public class SistemaEJB implements SistemaEJBRemote, SistemaEJBLocal {

    /**
     * Default constructor. 
     */
	
	@Autowired
	private SistemaService service;
	
    /**
     * Instantiates a new sistema ejb.
     */
    public SistemaEJB() {
        // TODO Auto-generated constructor stub
    }


	/* (non-Javadoc)
	 * @see org.dgac.app.logic.ejb.sistema.SistemaEJBRemote#loginActiveDirectory(java.lang.String, java.lang.String)
	 */
	@Override
	public ResultActiveDirectoryDTO loginActiveDirectory(String usrName, String password) throws DgacBusinessException {
		// TODO Auto-generated method stub
		return service.loginActiveDirectory(usrName, password);
	}

	/* (non-Javadoc)
	 * @see org.dgac.app.logic.ejb.sistema.SistemaEJBRemote#registroLog(org.dgac.common.dto.LogDTO)
	 */
	@Override
	public void registroLog(LogDTO log) {
		// TODO Auto-generated method stub
		service.registroLog(log);
	}


	@Override
	public List<RolDTO> returnRolesTramites(String usuario) {
		// TODO Auto-generated method stub
		return service.returnRolesTramites(usuario);
	}
}
