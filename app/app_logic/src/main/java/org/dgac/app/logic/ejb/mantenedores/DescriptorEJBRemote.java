package org.dgac.app.logic.ejb.mantenedores;

import java.util.List;

import javax.ejb.Remote;

import org.dgac.common.dto.DescriptorDTO;

@Remote
public interface DescriptorEJBRemote {

	public boolean insertDescriptor(String usuario,DescriptorDTO desc);
	public boolean updateDescriptor(String usuario,DescriptorDTO desc);
	public boolean deleteDescriptor(String usuario,DescriptorDTO desc);
	public List<DescriptorDTO> selectDescriptores(String usuario);
	public long tomarDependenciasDeId(String usuario, DescriptorDTO desc);
}
