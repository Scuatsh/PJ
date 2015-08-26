package org.dgac.webservice.facade;

import org.dgac.webservice.excepcion.WebServiceException;
import org.dgac.common.dto.*;

public interface WebServiceFacade {

	public ResultDTO login(String scus_usnm, String scus_pass) throws WebServiceException;
}
