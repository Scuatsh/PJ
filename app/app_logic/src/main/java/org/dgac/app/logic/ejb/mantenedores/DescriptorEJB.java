package org.dgac.app.logic.ejb.mantenedores;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.dgac.app.logic.service.mantenedores.DescriptorService;
import org.dgac.common.dto.DescriptorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

/**
 * Session Bean implementation class DescriptorEJB
 */
@Interceptors({SpringBeanAutowiringInterceptor.class})
@Stateless(mappedName = "DescriptorEJB")
public class DescriptorEJB implements DescriptorEJBRemote, DescriptorEJBLocal {

	@Autowired
	DescriptorService service;
    /**
     * Default constructor. 
     */
    public DescriptorEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean insertDescriptor(String usuario, DescriptorDTO desc) {
		// TODO Auto-generated method stub
		return service.insertDescriptor(usuario, desc);
	}

	@Override
	public boolean updateDescriptor(String usuario, DescriptorDTO desc) {
		// TODO Auto-generated method stub
		return service.updateDescriptor(usuario, desc);
	}

	@Override
	public boolean deleteDescriptor(String usuario, DescriptorDTO desc) {
		// TODO Auto-generated method stub
		return service.deleteDescriptor(usuario, desc);
	}

	@Override
	public List<DescriptorDTO> selectDescriptores(String usuario) {
		// TODO Auto-generated method stub
		return service.selectDescriptores(usuario);
	}

	@Override
	public long tomarDependenciasDeId(String usuario, DescriptorDTO desc) {
		// TODO Auto-generated method stub
		return service.tomarDependenciasDeId(usuario, desc);
	}

}
