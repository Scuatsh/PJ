package org.dgac.webservice.implement;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.dgac.common.constantes.ConstantesActiveDirectory;
import org.dgac.common.dto.Grupo;
import org.dgac.common.dto.ResultDTO;
import org.dgac.webservice.excepcion.WebServiceException;
import org.dgac.webservice.excepcion.WebServicesEnum;
import org.dgac.webservice.facade.WebServiceFacade;

public class WebServiceImpl implements WebServiceFacade {

	public ResultDTO login(String scus_usnm, String scus_pass)
			throws WebServiceException {
		// TODO Auto-generated method stub
		ResultDTO resultado=new ResultDTO();
		OracleFuncionarioDAO daoOracle=new OracleFuncionarioDAO();
		
		 Hashtable<String, String> env = new Hashtable<String, String>(11);  
		 env.put(Context.INITIAL_CONTEXT_FACTORY, ConstantesActiveDirectory.INITIAL_CONTEXT_FACTORY);   
		 env.put(Context.PROVIDER_URL, ConstantesActiveDirectory.PROVIDER_URL);
		 env.put(Context.SECURITY_AUTHENTICATION, ConstantesActiveDirectory.SECURITY_AUTHENTICATION); 
		 env.put(Context.SECURITY_PRINCIPAL, scus_usnm + "@dgac.gob.cl");     
		 env.put(Context.SECURITY_CREDENTIALS, scus_pass);  
		 
		 DirContext ctx;
		 
		 
		 try
		 {
			 ctx = new InitialDirContext(env);
			 
			 String searchBase = "DC=dgac,DC=gob,DC=cl";
			 StringBuilder searchFilter = new StringBuilder("(&");
			 searchFilter.append("(objectClass=person)");
			 searchFilter.append("(userPrincipalName="+scus_usnm+"@dgac.gob.cl)");
			 searchFilter.append(")");
			 String [] returnAttrs={"memberOf","mail"};
			 SearchControls sCtrl = new SearchControls();
			 sCtrl.setSearchScope(SearchControls.SUBTREE_SCOPE);
			 sCtrl.setReturningAttributes(returnAttrs);
			 
			 
			 NamingEnumeration<SearchResult> answer = ctx.search(searchBase, searchFilter.toString(),sCtrl);
			 
			 
			 while(answer.hasMoreElements())
			 {
				 String memberOfAttrValue = null;
				 
				 SearchResult sr = answer.next();
				 if(sr.getAttributes().get("memberOf") != null)
				 {
					 
					 memberOfAttrValue = sr.getAttributes().get("memberOf").toString();
					 
					 if(memberOfAttrValue!=null){
						 String mail=null;
						 String[] listaDeGrupos =  memberOfAttrValue.split(",");
						 List<Grupo>lista=new ArrayList<Grupo>();
						 
						 for(int indice =0; indice<listaDeGrupos.length; indice++)
						 {
							 Grupo grp=new Grupo();
							 grp.setNombre(listaDeGrupos[indice].substring(listaDeGrupos[indice].lastIndexOf("=")+1, listaDeGrupos[indice].length()));
							 lista.add(indice, grp);
						 }
						 
						 resultado.setCodigo(WebServicesEnum.SIS_0000.getId());
						 resultado.setMensaje("Se han encontrado grupos para Usuario: " + scus_usnm);
						 resultado.setGrupos(lista);
						 resultado.setStatus(true);
						 mail = sr.getAttributes().get("mail").toString().split(" ")[1];
						 try {
							resultado.setFuncionario(daoOracle.obtenerFuncionario(mail));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							resultado.setFuncionario(null);
						}
					 }
					 else
					 {
						 resultado.setCodigo(WebServicesEnum.INT_0001.getId());
						 resultado.setMensaje("No se han encontrado grupos para Usuario: " + scus_usnm);
						 resultado.setStatus(false);
					 }
				 }
				 
			 }
			 
		 }
		 catch(NamingException ex)
		 {
			 resultado.setCodigo(WebServicesEnum.INT_0001.getId());
			 resultado.setMensaje("No se han encontrado grupos para Usuario: " + scus_usnm);
			 resultado.setStatus(false);
			 throw new  WebServiceException(WebServicesEnum.INT_0001,ex);
		 }

		return resultado;
	}

	

}
