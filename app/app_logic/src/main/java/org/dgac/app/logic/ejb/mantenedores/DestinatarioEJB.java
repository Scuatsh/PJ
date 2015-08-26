package org.dgac.app.logic.ejb.mantenedores;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.dgac.app.logic.service.mantenedores.DestinatarioService;
import org.dgac.common.dto.DestinatarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

/**
 * Session Bean implementation class DestinatarioEJB
 */
@Interceptors({SpringBeanAutowiringInterceptor.class})
@Stateless(mappedName = "DestinatarioEJB")
public class DestinatarioEJB implements DestinatarioEJBRemote, DestinatarioEJBLocal {

	@Autowired
	DestinatarioService service;
    /**
     * Default constructor. 
     */
    public DestinatarioEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean insertDestinatario(String usuario, DestinatarioDTO des) {
		// TODO Auto-generated method stub
		return service.insertDestinatario(usuario, des);
	}

	@Override
	public boolean updateDestinatario(String usuario, DestinatarioDTO des) {
		// TODO Auto-generated method stub
		return service.updateDestinatario(usuario, des);
	}

	@Override
	public boolean deleteDestinatario(String usuario, DestinatarioDTO des) {
		// TODO Auto-generated method stub
		return service.deleteDestinatario(usuario, des);
	}

	@Override
	public List<DestinatarioDTO> selectDestinatarios(String usuario) {
		// TODO Auto-generated method stub
		return service.selectDestinatarios(usuario);
	}

	@Override
	public long tomarDependenciasDeId(String usuario, DestinatarioDTO desc) {
		// TODO Auto-generated method stub
		return service.tomarDependenciasDeId(usuario, desc);
	}

}
