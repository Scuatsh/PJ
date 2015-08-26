package org.dgac.app.web.bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.dgac.app.exception.DgacException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dgac.app.logic.ejb.sistema.SistemaEJBLocal;
import org.dgac.app.web.security.LoginBean;
import org.dgac.app.web.util.FacesAccesor;
import org.dgac.app.web.util.UtilSession;
import org.dgac.common.dto.LogDTO;
import org.dgac.common.dto.ResultActiveDirectoryDTO;
import org.dgac.common.dto.RolDTO;
import org.dgac.common.enums.LevelEnum;
import org.dgac.common.enums.RolEnum;
import org.dgac.common.enums.UsrNameDefaultEnum;
import org.dgac.common.util.ServiceLocator;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


// TODO: Auto-generated Javadoc
/**
 * The Class SistemaBean.
 */
@ManagedBean(name = "sistemaBean")
@ViewScoped
public class SistemaBean extends BeanGenerico implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private static final Log LOGGER = LogFactory.getLog(SistemaBean.class);

	/** The sistema ejb. */
	private SistemaEJBLocal sistemaEJB;	

	/** The txt password. */
	private String txtPassword;
	
	/** The txt usr name. */
	private String txtUsrName;
	
	private List<RolDTO>listaRoles;
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		sistemaEJB = (SistemaEJBLocal) ServiceLocator.getInstance().getEjb(SistemaEJBLocal.class);
		listaRoles=new ArrayList<RolDTO>();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("busquedaBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cargaBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("mantenedorBean", null);
	}

	/**
	 * Redirect bandeja.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void login() throws IOException {
		
		try
		{
			ResultActiveDirectoryDTO adirectory=new ResultActiveDirectoryDTO();
			adirectory=sistemaEJB.loginActiveDirectory(txtUsrName.trim(), txtPassword.trim());
			List<RolDTO>rolesConsulta=new ArrayList<RolDTO>();
			listaRoles=sistemaEJB.returnRolesTramites(txtUsrName);
			if(adirectory.isStatus())
			{
				for(String grupo:adirectory.getNombreGrupos())
				{
					
					for(RolDTO rol:listaRoles)
					{
						if(grupo.equals(rol.getRol_nombre()))
						{
							rolesConsulta.add(rol);
						}
					}
					

				}
				
				
				if(rolesConsulta.size()>0)
				{
					LoginBean loginBean = (LoginBean) FacesAccesor.getManagedBean("loginBean");

					for(RolDTO r:rolesConsulta)
					{
						if(r.getRol_nombre().equals(RolEnum.ADMIN.getId()))
						{
							HttpSession session = UtilSession.getSession();
							session.setAttribute("rol", String.valueOf(r.getRol_nombre()));
							loginBean.setToViewMenu("menu_admin.xhtml");
							loginBean.navigateTo("/portal/inicio.xhtml");
							FacesContext.getCurrentInstance().getExternalContext().redirect("common.jsf");
						}
						else
						{
							if(r.getRol_nombre().equals(RolEnum.SUPERADMIN.getId()))
							{
								HttpSession session = UtilSession.getSession();
								session.setAttribute("rol", String.valueOf(r.getRol_id()));
								loginBean.setToViewMenu("menu_admin.xhtml");
								loginBean.navigateTo("/portal/inicio.xhtml");
								FacesContext.getCurrentInstance().getExternalContext().redirect("common.jsf");
							}
							else
							{
								FacesContext.getCurrentInstance().getExternalContext().redirect("sinAcceso.jsf");
							}
							
						}
					}
				}
				
				HttpSession session = UtilSession.getSession();
				session.setAttribute("usuario", txtUsrName);
				HttpSession sessionFuncionario = UtilSession.getSession();
				sessionFuncionario.setAttribute("funcionario", adirectory.getFuncionario());				
			}
			else
			{
				FacesContext.getCurrentInstance().getExternalContext().redirect("sinAcceso.jsf");
			}

		}
		catch(Exception e)
		{
			sistemaEJB.registroLog(new LogDTO(txtUsrName, SistemaBean.class.getSimpleName()+"[CatchLogin]", LevelEnum.ERR.getId(), e.getMessage()));
			LOGGER.error("Error al ejecutar el loginActiveDirectory [redirectBandeja]",e);
		}
	}


	public void redirectNoLogin()
	{
		try
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Haz ingresado al sistema como usuario invitado. Solo tienes privilegios para visualizar los documentos.");
	        RequestContext.getCurrentInstance().showMessageInDialog(message);
			LoginBean loginBean = (LoginBean) FacesAccesor.getManagedBean("loginBean");
			HttpSession session = UtilSession.getSession();
			session.setAttribute("rol", "USUARIO");
			loginBean.setToViewMenu("menu_usuario.xhtml");
			loginBean.navigateTo("/portal/consultaUsuario.xhtml");
			FacesContext.getCurrentInstance().getExternalContext().redirect("common.jsf");
			
		}
		catch(Exception e)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ha ocurrido un error al ingresar al sistema.");
	         
	        RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
		
	}
	public void redirectTo(String url)
	{
		try
		{
			LoginBean loginBean = (LoginBean) FacesAccesor.getManagedBean("loginBean");
			loginBean.setToViewMenu("menu_admin.xhtml");
			loginBean.navigateTo(url);
			FacesContext.getCurrentInstance().getExternalContext().redirect("common.jsf");
		}
		catch(Exception e)
		{
			sistemaEJB.registroLog(new LogDTO(txtUsrName, SistemaBean.class.getSimpleName()+"[redirectTo]", LevelEnum.ERR.getId(), e.getMessage()));

		}
	}
	
	public void redirectToNoLogin(String url)
	{
		try
		{
			LoginBean loginBean = (LoginBean) FacesAccesor.getManagedBean("loginBean");
			loginBean.setToViewMenu("menu_usuario.xhtml");
			loginBean.navigateTo(url);
			FacesContext.getCurrentInstance().getExternalContext().redirect("common.jsf");
		}
		catch(Exception e)
		{
			sistemaEJB.registroLog(new LogDTO(txtUsrName, SistemaBean.class.getSimpleName()+"[redirectTo]", LevelEnum.ERR.getId(), e.getMessage()));

		}
	}
	
	public void cerrarSession()
	{
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if(session!=null){
			session.invalidate();
		}
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");	
		} catch (IOException e) {
			
		}
	}

	/**
	 * Gets the txt password.
	 *
	 * @return the txt password
	 */
	public String getTxtPassword() {
		return txtPassword;
	}

	/**
	 * Sets the txt password.
	 *
	 * @param txtPassword the new txt password
	 */
	public void setTxtPassword(String txtPassword) {
		this.txtPassword = txtPassword;
	}

	/**
	 * Gets the txt usr name.
	 *
	 * @return the txt usr name
	 */
	public String getTxtUsrName() {
		return txtUsrName;
	}

	/**
	 * Sets the txt usr name.
	 *
	 * @param txtUsrName the new txt usr name
	 */
	public void setTxtUsrName(String txtUsrName) {
		this.txtUsrName = txtUsrName;
	}
}