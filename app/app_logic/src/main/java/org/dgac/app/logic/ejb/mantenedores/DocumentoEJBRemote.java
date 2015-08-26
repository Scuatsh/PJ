package org.dgac.app.logic.ejb.mantenedores;

import java.util.List;

import javax.ejb.Remote;

import org.dgac.common.dto.DocumentoDTO;

@Remote
public interface DocumentoEJBRemote {

	public boolean insertDocumento(String usuario,DocumentoDTO doc);
	public boolean updateDocumento(String usuario,DocumentoDTO doc);
	public boolean deleteDocumento(String usuario,DocumentoDTO doc);
	public List<DocumentoDTO> selectDocumentos(String usuario,long rol_id);
	public long validarDocumento(String usuario,DocumentoDTO doc);
	public boolean publicarDocumento(String usuario,DocumentoDTO doc);
}
