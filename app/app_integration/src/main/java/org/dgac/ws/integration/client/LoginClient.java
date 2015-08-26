package org.dgac.ws.integration.client;

import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.dgac.app.exception.DgacException;
import org.dgac.ws.integration.server.Login;
import org.dgac.ws.integration.server.LoginResponse;
import org.dgac.ws.integration.server.Login_Service;
import org.dgac.ws.integration.server.Login_Type;
import org.dgac.ws.integration.util.ClienteWS;
import org.dgac.ws.integration.util.IntegrationProperties;

public class LoginClient extends ClienteWS {

	private static final Logger LOGGER = Logger.getLogger(LoginClient.class);

	public LoginClient(IntegrationProperties integrationProperties) {
		this.integrationProperties = integrationProperties;
	}

	// Declaración
	private static Login_Service service;

	public LoginResponse executeLogin(String usuario, String password){

		try {
			service = getService(integrationProperties.getProp()
					.get("login.wsdl.file").toString());
		} catch (Exception e) {
			LOGGER.error("DgacException getService -->",e);
		}
		LoginResponse rs = null;
		try {
			WSLoginIF serviceImpl = service.getWSLoginPort();
			Login_Type rq = new Login_Type();
			rq.setUsrname(usuario);
			rq.setPassword(password);
			rs = serviceImpl.login(rq);

		} catch (DgacException ie) {
			LOGGER.error("DgacException --> ",ie);
		} catch (Exception e) {
			LOGGER.error("Exception --> ",e);
		}
		return rs;

	}

	public static Login_Service getService(String url) throws Exception {
		if (service == null) {
			service = new Login_Service(new URL(url), new QName(
					"http://server.webservices.dgac.org/", "Login"));
		}
		return service;
	}

}
