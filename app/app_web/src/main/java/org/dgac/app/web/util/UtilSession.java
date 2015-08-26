package org.dgac.app.web.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dgac.common.dto.FuncionarioDTO;

public class UtilSession {

	public static HttpSession getSession() {
        return (HttpSession)
          FacesContext.
          getCurrentInstance().
          getExternalContext().
          getSession(false);
      }
       
      public static HttpServletRequest getRequest() {
       return (HttpServletRequest) FacesContext.
          getCurrentInstance().
          getExternalContext().getRequest();
      }
 
      
      
      public static String getUsuarioSession()
      {
    	  HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
          return  (String)session.getAttribute("usuario");
      }
      
      public static FuncionarioDTO getFuncionario()
      {
    	  HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
          return  (FuncionarioDTO)session.getAttribute("funcionario");
      }
      
      public static String getRol()
      {
    	  HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
          return  (String)session.getAttribute("rol");
      }
}
