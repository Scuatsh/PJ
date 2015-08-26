package org.dgac.ws.integration.client.alfresco;

import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.dgac.app.exception.DgacException;
import org.dgac.ws.integration.client.LoginClient;
import org.dgac.ws.integration.client.WSLoginIF;
import org.dgac.ws.integration.server.LoginResponse;
import org.dgac.ws.integration.server.Login_Service;
import org.dgac.ws.integration.server.Login_Type;
import org.dgac.ws.integration.server.alfresco.AlmacenarDocumentosRequest;
import org.dgac.ws.integration.server.alfresco.AlmacenarDocumentosResponse;
import org.dgac.ws.integration.server.alfresco.AlmacenarDocumentosService;
import org.dgac.ws.integration.server.alfresco.DocumentoAlfresco;
import org.dgac.ws.integration.server.alfresco.DocumentoAlfrescoResponse;
import org.dgac.ws.integration.util.ClienteWS;
import org.dgac.ws.integration.util.IntegrationProperties;

public class AlmacenarAlfrescoClient extends ClienteWS{

	private static final Logger LOGGER = Logger.getLogger(AlmacenarAlfrescoClient.class);

	public AlmacenarAlfrescoClient(IntegrationProperties integrationProperties) {
		this.integrationProperties = integrationProperties;
	}

	// Declaración
	private static AlmacenarDocumentosService service;

	public AlmacenarDocumentosResponse executeAlmacenar(AlmacenarDocumentosRequest rq){

		try {
			service = getService(integrationProperties.getProp()
					.get("alfrescoInsert.wsdl.file").toString());
		} catch (Exception e) {
			LOGGER.error("DgacException getService -->",e);
		}
		AlmacenarDocumentosResponse rs = null;
		try {
			WsAlmacenarAlfrescoIF serviceImpl = service.getWsAlmacenarAlfrescoPort();
			rs = serviceImpl.AlmacenarDocumentosOp(rq);

		} catch (DgacException ie) {
			LOGGER.error("DgacException --> ",ie);
		} catch (Exception e) {
			LOGGER.error("Exception --> ",e);
		}
		return rs;

	}

	public static AlmacenarDocumentosService getService(String url) throws Exception {
		if (service == null) {
			service = new AlmacenarDocumentosService(new URL(url), new QName(
					"http://soa.jboss.org/almacenardocumentos", "AlmacenarDocumentosService"));
		}
		return service;
	}
}
