package org.dgac.app.logic.ejb.mantenedores;

import java.util.List;

import javax.ejb.Remote;

import org.dgac.common.dto.DestinatarioDTO;

@Remote
public interface DestinatarioEJBRemote {

	public boolean insertDestinatario(String usuario,DestinatarioDTO des);
	public boolean updateDestinatario(String usuario,DestinatarioDTO des);
	public boolean deleteDestinatario(String usuario,DestinatarioDTO des);
	public List<DestinatarioDTO> selectDestinatarios(String usuario);
	public long tomarDependenciasDeId(String usuario, DestinatarioDTO desc);
}
