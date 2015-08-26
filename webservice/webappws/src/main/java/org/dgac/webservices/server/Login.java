package org.dgac.webservices.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.dgac.common.dto.ResultDTO;
import org.dgac.webservice.excepcion.WebServicesEnum;
import org.dgac.webservice.implement.WebServiceImpl;


@WebService(serviceName = "Login")
public class Login {


	@WebMethod(operationName = "login")
	public @WebResult ResultDTO login(@WebParam(name = "usrname") String usrname,@WebParam(name = "password")String password) {
		ResultDTO resultado=new ResultDTO();
		try
		{
			WebServiceImpl impl=new WebServiceImpl();
			resultado=impl.login(usrname, password);
		}
		catch(Exception e)
		{
			resultado.setCodigo(WebServicesEnum.INT_0001.getId());
			resultado.setMensaje("No existe el usuario: " + usrname );
			resultado.setStatus(false);
		}

		return resultado;
	}
}
