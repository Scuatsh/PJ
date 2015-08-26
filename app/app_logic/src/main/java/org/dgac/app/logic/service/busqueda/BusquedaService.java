package org.dgac.app.logic.service.busqueda;

import java.util.ArrayList;
import java.util.Date;

import org.dgac.common.dto.DocumentoDTO;

public interface BusquedaService {
	
	
	public ArrayList<DocumentoDTO> buscarDoc(DocumentoDTO objDocumentoDTO,Date fechaDesde, Date fechaHasta);
	public ArrayList<DocumentoDTO> buscarUsuario(DocumentoDTO objDocumentoDTO,Date fechaDesde, Date fechaHasta);
	public byte[] getDocumento(String id_alfreso);

}
