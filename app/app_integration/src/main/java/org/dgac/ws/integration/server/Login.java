
package org.dgac.ws.integration.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Login", targetNamespace = "http://server.webservices.dgac.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Login {


    /**
     * 
     * @param password
     * @param usrname
     * @return
     *     returns org.dgac.webservices.server.ResultDTO
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "login", targetNamespace = "http://server.webservices.dgac.org/", className = "org.dgac.webservices.server.Login_Type")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://server.webservices.dgac.org/", className = "org.dgac.webservices.server.LoginResponse")
    public ResultDTO login(
        @WebParam(name = "usrname", targetNamespace = "")
        String usrname,
        @WebParam(name = "password", targetNamespace = "")
        String password);

}
