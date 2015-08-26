package org.dgac.app.logic.service.mantenedores;

import java.util.List;

import org.dgac.common.dto.DestinatarioDTO;

public interface DestinatarioService {

	public boolean insertDestinatario(String usuario,DestinatarioDTO des);
	public boolean updateDestinatario(String usuario,DestinatarioDTO des);
	public boolean deleteDestinatario(String usuario,DestinatarioDTO des);
	public List<DestinatarioDTO> selectDestinatarios(String usuario);
	public long tomarDependenciasDeId(String usuario, DestinatarioDTO desc);
}
