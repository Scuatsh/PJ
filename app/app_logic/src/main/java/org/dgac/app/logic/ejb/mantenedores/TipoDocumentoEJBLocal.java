package org.dgac.app.logic.ejb.mantenedores;

import java.util.List;

import javax.ejb.Local;

import org.dgac.common.dto.TipoDocumentoDTO;

@Local
public interface TipoDocumentoEJBLocal {

	public boolean insertTipoDocumento(String usuario,TipoDocumentoDTO tpoDocumento);
	public boolean updateTipoDocumento(String usuario,TipoDocumentoDTO tpoDocumento);
	public boolean deleteTipoDocumento(String usuario,TipoDocumentoDTO tpoDocumento);
	public List<TipoDocumentoDTO> selectTiposDocumentos(String usuario);
	public long tomarDependenciasDeId(String usuario, TipoDocumentoDTO desc);
}
