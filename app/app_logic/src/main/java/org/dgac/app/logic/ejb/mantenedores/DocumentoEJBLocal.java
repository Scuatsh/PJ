package org.dgac.app.logic.ejb.mantenedores;

import java.util.List;

import javax.ejb.Local;

import org.dgac.common.dto.DocumentoDTO;

@Local
public interface DocumentoEJBLocal {

	public boolean insertDocumento(String usuario,DocumentoDTO doc);
	public boolean updateDocumento(String usuario,DocumentoDTO doc);
	public boolean deleteDocumento(String usuario,DocumentoDTO doc);
	public List<DocumentoDTO> selectDocumentos(String usuario,long rol_id);
	public long validarDocumento(String usuario,DocumentoDTO doc);
	public boolean publicarDocumento(String usuario,DocumentoDTO doc);
}
