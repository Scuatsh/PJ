package org.dgac.app.logic.ejb.mantenedores;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.dgac.app.logic.service.mantenedores.DocumentoService;
import org.dgac.common.dto.DocumentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

/**
 * Session Bean implementation class DocumentoEJB
 */
@Interceptors({SpringBeanAutowiringInterceptor.class})
@Stateless(mappedName = "DocumentoEJB")
public class DocumentoEJB implements DocumentoEJBRemote, DocumentoEJBLocal {

	@Autowired
	DocumentoService service;
    /**
     * Default constructor. 
     */
    public DocumentoEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean insertDocumento(String usuario, DocumentoDTO doc) {
		// TODO Auto-generated method stub
		return service.insertDocumento(usuario, doc);
	}

	@Override
	public boolean updateDocumento(String usuario, DocumentoDTO doc) {
		// TODO Auto-generated method stub
		return service.updateDocumento(usuario, doc);
	}

	@Override
	public boolean deleteDocumento(String usuario, DocumentoDTO doc) {
		// TODO Auto-generated method stub
		return service.deleteDocumento(usuario, doc);
	}

	@Override
	public List<DocumentoDTO> selectDocumentos(String usuario, long rol_id) {
		// TODO Auto-generated method stub
		return service.selectDocumentos(usuario, rol_id);
	}

	@Override
	public long validarDocumento(String usuario, DocumentoDTO doc) {
		// TODO Auto-generated method stub
		return service.validarDocumento(usuario, doc);
	}

	@Override
	public boolean publicarDocumento(String usuario, DocumentoDTO doc) {
		// TODO Auto-generated method stub
		return service.publicarDocumento(usuario, doc);
	}

}
