package org.dgac.ws.integration.client.alfresco.buscar;

import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.dgac.app.exception.DgacException;
import org.dgac.ws.integration.server.alfresco.buscar.ObtenerDocumentoRequest;
import org.dgac.ws.integration.server.alfresco.buscar.ObtenerDocumentoResponse;
import org.dgac.ws.integration.server.alfresco.buscar.ObtenerDocumentoService;
import org.dgac.ws.integration.util.ClienteWS;
import org.dgac.ws.integration.util.IntegrationProperties;

public class ObtenerAlfrescoClient extends ClienteWS{

	private static final Logger LOGGER = Logger.getLogger(ObtenerAlfrescoClient.class);

	public ObtenerAlfrescoClient(IntegrationProperties integrationProperties) {
		this.integrationProperties = integrationProperties;
	}

	// Declaración
	private static ObtenerDocumentoService service;

	public ObtenerDocumentoResponse executeBuscar(ObtenerDocumentoRequest rq){

		try {
			service = getService(integrationProperties.getProp()
					.get("alfrescoObtener.wsdl.file").toString());
		} catch (Exception e) {
			LOGGER.error("DgacException getService -->",e);
		}
		ObtenerDocumentoResponse rs = null;
		try {
			WsBuscarAlfrescoIF serviceImpl = service.getWsObtenerAlfrescoPort();
			rs = serviceImpl.ObtenerDocumentoOp(rq);

		} catch (DgacException ie) {
			LOGGER.error("DgacException --> ",ie);
		} catch (Exception e) {
			LOGGER.error("Exception --> ",e);
		}
		return rs;

	}

	public static ObtenerDocumentoService getService(String url) throws Exception {
		if (service == null) {
			service = new ObtenerDocumentoService(new URL(url), new QName(
					"http://soa.jboss.org/obtenerdocumento", "ObtenerDocumentoService"));
		}
		return service;
	}
}
