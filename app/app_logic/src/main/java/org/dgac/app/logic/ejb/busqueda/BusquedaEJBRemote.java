package org.dgac.app.logic.ejb.busqueda;

import java.util.ArrayList;
import java.util.Date;

import javax.ejb.Remote;

import org.dgac.common.dto.DocumentoDTO;

@Remote
public interface BusquedaEJBRemote {

	public ArrayList<DocumentoDTO> buscarDoc(DocumentoDTO objDocumentoDTO,Date fechaDesde, Date fechaHasta);
	public ArrayList<DocumentoDTO> buscarUsuario(DocumentoDTO objDocumentoDTO,Date fechaDesde, Date fechaHasta);
	public byte[] getDocumento(String id_alfreso);
}
