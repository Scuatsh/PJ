package org.dgac.app.logic.service.mantenedores;

import java.util.List;

import org.dgac.common.dto.DescriptorDTO;

public interface DescriptorService {

	public boolean insertDescriptor(String usuario,DescriptorDTO desc);
	public boolean updateDescriptor(String usuario,DescriptorDTO desc);
	public boolean deleteDescriptor(String usuario,DescriptorDTO desc);
	public List<DescriptorDTO> selectDescriptores(String usuario);
	public long tomarDependenciasDeId(String usuario, DescriptorDTO desc);
}
