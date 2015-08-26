package org.dgac.app.logic.ejb.busqueda;

import java.util.ArrayList;
import java.util.Date;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.dgac.app.logic.service.busqueda.BusquedaService;
import org.dgac.common.dto.DocumentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import sun.print.resources.serviceui;

/**
 * Session Bean implementation class BusquedaEJB
 */
@Interceptors({SpringBeanAutowiringInterceptor.class})
@Stateless(mappedName = "BusquedaEJB")
public class BusquedaEJB implements BusquedaEJBRemote, BusquedaEJBLocal {

	@Autowired
	private BusquedaService objBusquedaService;
    /**
     * Default constructor. 
     */
    public BusquedaEJB() {
        // TODO Auto-generated constructor stub
    }
	@Override
	public ArrayList<DocumentoDTO> buscarDoc(DocumentoDTO objDocumentoDTO,Date fechaDesde, Date fechaHasta) {
		// TODO Auto-generated method stub
		return objBusquedaService.buscarDoc(objDocumentoDTO, fechaDesde,  fechaHasta);
	}
	@Override
	public byte[] getDocumento(String id_alfreso) {
		// TODO Auto-generated method stub
		return objBusquedaService.getDocumento(id_alfreso);
	}
	@Override
	public ArrayList<DocumentoDTO> buscarUsuario(DocumentoDTO objDocumentoDTO,
			Date fechaDesde, Date fechaHasta) {
		// TODO Auto-generated method stub
		return objBusquedaService.buscarUsuario(objDocumentoDTO, fechaDesde, fechaHasta);
	}
	
	
	

}
