package org.dgac.app.logic.service.mantenedores;

import java.util.List;

import org.dgac.common.dto.TipoDocumentoDTO;

public interface TipoDocumentoService {

	public boolean insertTipoDocumento(String usuario,TipoDocumentoDTO tpoDocumento);
	public boolean updateTipoDocumento(String usuario,TipoDocumentoDTO tpoDocumento);
	public boolean deleteTipoDocumento(String usuario,TipoDocumentoDTO tpoDocumento);
	public List<TipoDocumentoDTO> selectTiposDocumentos(String usuario);
	public long tomarDependenciasDeId(String usuario, TipoDocumentoDTO desc);
}
