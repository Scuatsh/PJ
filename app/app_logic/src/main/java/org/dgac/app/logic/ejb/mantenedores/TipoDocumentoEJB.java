package org.dgac.app.logic.ejb.mantenedores;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.dgac.app.logic.service.mantenedores.TipoDocumentoService;
import org.dgac.common.dto.TipoDocumentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

/**
 * Session Bean implementation class TipoDocumentoEJB
 */
@Interceptors({SpringBeanAutowiringInterceptor.class})
@Stateless(mappedName = "TipoDocumentoEJB")
public class TipoDocumentoEJB implements TipoDocumentoEJBRemote, TipoDocumentoEJBLocal {

	@Autowired
	TipoDocumentoService service;
    /**
     * Default constructor. 
     */
    public TipoDocumentoEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean insertTipoDocumento(String usuario,
			TipoDocumentoDTO tpoDocumento) {
		// TODO Auto-generated method stub
		return service.insertTipoDocumento(usuario, tpoDocumento);
	}

	@Override
	public boolean updateTipoDocumento(String usuario,
			TipoDocumentoDTO tpoDocumento) {
		// TODO Auto-generated method stub
		return service.updateTipoDocumento(usuario, tpoDocumento);
	}

	@Override
	public boolean deleteTipoDocumento(String usuario,
			TipoDocumentoDTO tpoDocumento) {
		// TODO Auto-generated method stub
		return service.deleteTipoDocumento(usuario, tpoDocumento);
	}

	@Override
	public List<TipoDocumentoDTO> selectTiposDocumentos(String usuario) {
		// TODO Auto-generated method stub
		return service.selectTiposDocumentos(usuario);
	}

	@Override
	public long tomarDependenciasDeId(String usuario, TipoDocumentoDTO desc) {
		// TODO Auto-generated method stub
		return service.tomarDependenciasDeId(usuario, desc);
	}

}
