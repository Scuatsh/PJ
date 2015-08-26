package org.dgac.app.web.bean;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.dgac.app.logic.ejb.mantenedores.DescriptorEJBLocal;
import org.dgac.app.logic.ejb.mantenedores.DestinatarioEJBLocal;
import org.dgac.app.logic.ejb.mantenedores.TipoDocumentoEJBLocal;
import org.dgac.app.logic.ejb.sistema.SistemaEJBLocal;
import org.dgac.common.dto.DescriptorDTO;
import org.dgac.common.dto.DestinatarioDTO;
import org.dgac.common.dto.FuncionarioDTO;
import org.dgac.common.dto.LogDTO;
import org.dgac.common.dto.TipoDocumentoDTO;
import org.dgac.common.enums.LevelEnum;
import org.dgac.common.enums.UsrNameDefaultEnum;
import org.dgac.common.util.ServiceLocator;
import org.primefaces.context.RequestContext;

import com.google.gson.Gson;


// TODO: Auto-generated Javadoc
/**
 * The Class MantenedorBean.
 */
@ManagedBean(name="mantenedorBean")
@ViewScoped
public class MantenedorBean extends BeanGenerico implements Serializable{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The descriptor service. */
	private DescriptorEJBLocal descriptorService;

	/** The destinatario service. */
	private DestinatarioEJBLocal destinatarioService;

	/** The tipo documento service. */
	private TipoDocumentoEJBLocal tipoDocumentoService;

	/** The descriptor list. */
	private List<DescriptorDTO> descriptorList;

	/** The destinatario list. */
	private List<DestinatarioDTO> destinatarioList;

	/** The tipo documento list. */
	private List<TipoDocumentoDTO> tipoDocumentoList;

	/** The funcionario. */
	private FuncionarioDTO funcionario;

	/** The usuario. */
	private String usuario;

	/** The obj descriptor dto. */
	private DescriptorDTO objDescriptorDTO;

	/** The obj tipo documento dto. */
	private TipoDocumentoDTO objTipoDocumentoDTO;

	/** The obj destinatario dto. */
	private DestinatarioDTO objDestinatarioDTO;

	/** The estado. */
	private boolean estado = false;

	/** The estado dos. */
	private boolean estadoDos = false;
	private SistemaEJBLocal sistemaService;



	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init(){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("busquedaBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cargaBean", null);
		descriptorService=(DescriptorEJBLocal) ServiceLocator.getInstance().getEjb(DescriptorEJBLocal.class);
		destinatarioService=(DestinatarioEJBLocal) ServiceLocator.getInstance().getEjb(DestinatarioEJBLocal.class);
		tipoDocumentoService=(TipoDocumentoEJBLocal) ServiceLocator.getInstance().getEjb(TipoDocumentoEJBLocal.class);
		sistemaService=(SistemaEJBLocal)ServiceLocator.getInstance().getEjb(SistemaEJBLocal.class);
		usuario = UsrNameDefaultEnum.DGAC.getId();
		descriptorList = descriptorService.selectDescriptores(usuario);		
		destinatarioList = destinatarioService.selectDestinatarios(usuario);		
		tipoDocumentoList = tipoDocumentoService.selectTiposDocumentos(usuario);	
		objDescriptorDTO = new DescriptorDTO();		
		objTipoDocumentoDTO = new TipoDocumentoDTO();
		objDestinatarioDTO = new DestinatarioDTO();
	}

	/**
	 * Eliminar descriptor.
	 *
	 * @param id the id
	 */
	public void eliminarDescriptor(long id)
	{
		try
		{
			objDescriptorDTO.setDesc_id(id);
			if(descriptorService.tomarDependenciasDeId(usuario, objDescriptorDTO)>0 && objDescriptorDTO.getDesc_estado()== 0)
			{
				//TODO: no se puede act ya que existen dep en documentos.

				for(int indice = 0; indice<descriptorList.size(); indice ++)
				{
					if(objDescriptorDTO.getDesc_nombre_large().equalsIgnoreCase(descriptorList.get(indice).getDesc_nombre_large()))
					{
						descriptorList.get(indice).setDesc_estado(1);
					}
				}
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "Estimado usuario, existen documentos asociados a este descriptor, por lo cual, no podrá dejar inactivo el componente. Ante una posible inconsistencia  de datos por favor comuníquese con el administrador. De ante mano muchas gracias. ");

				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}
			else if(descriptorService.deleteDescriptor(usuario, objDescriptorDTO))
			{
				descriptorList = descriptorService.selectDescriptores(usuario);	
				objDescriptorDTO = new DescriptorDTO();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notificación de Información", "Se ha desactivado correctamente el descriptor.");

				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}
			else
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "Estimado usuario, existen documentos asociados a este descriptor, por lo cual, no podrá dejar inactivo el componente. Ante una posible inconsistencia  de datos por favor comuníquese con el administrador. De ante mano muchas gracias. ");

				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}
		}
		catch(Exception error)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "No ha sido posible deshabilitar correctamente el descriptor.");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			LogDTO log= new LogDTO(usuario,MantenedorBean.class.getName(),LevelEnum.ERR.getId(),error.getMessage());
			sistemaService.registroLog(log);
		}
	}

	public void eliminarDestinatario(long id)
	{
		try
		{
			objDestinatarioDTO.setDes_id(id);
			if(destinatarioService.tomarDependenciasDeId(usuario, objDestinatarioDTO)>0 && objDestinatarioDTO.getDes_estado()== 0)
			{
				for(int indice = 0; indice<destinatarioList.size(); indice ++)
				{
					if(objDestinatarioDTO.getDes_nombre_large().equalsIgnoreCase(destinatarioList.get(indice).getDes_nombre_large()))
					{
						destinatarioList.get(indice).setDes_estado(1);
					}
				}
				//TODO: no se puede act ya que existen dep en documentos.
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "Estimado usuario, existen documentos asociados a este destinatario, por lo cual, no podrá dejar inactivo el componente. Ante una posible inconsistencia  de datos por favor comuníquese con el administrador. De ante mano muchas gracias. ");

				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}
			else if(destinatarioService.deleteDestinatario(usuario, objDestinatarioDTO))
			{
				destinatarioList = destinatarioService.selectDestinatarios(usuario);		
				objDestinatarioDTO = new DestinatarioDTO();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notificación de Información", "Se ha desactivado correctamente el destinatario.");

				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}
			else
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "No fue posible deshabilitar el destinatario.");

				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}
		}
		catch(Exception error)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "Estimado usuario, existen documentos asociados a este destinatario, por lo cual, no podrá dejar inactivo el componente. Ante una posible inconsistencia  de datos por favor comuníquese con el administrador. De ante mano muchas gracias. ");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			LogDTO log= new LogDTO(usuario,MantenedorBean.class.getName(),LevelEnum.ERR.getId(),error.getMessage());
			sistemaService.registroLog(log);
		}
	}

	/**
	 * Eliminar tipo doc.
	 *
	 * @param id the id
	 */
	public void eliminarTipoDoc(long id)
	{
		try
		{
			objTipoDocumentoDTO.setTpo_id(id);
			if(tipoDocumentoService.tomarDependenciasDeId(usuario, objTipoDocumentoDTO)>0 && objTipoDocumentoDTO.getTpo_estado() == 0)
			{
				//TODO: no se puede act ya que existen dep en documentos.
				for(int indice = 0; indice<tipoDocumentoList.size(); indice ++)
				{
					if(objTipoDocumentoDTO.getTpo_nombre_large().equalsIgnoreCase(tipoDocumentoList.get(indice).getTpo_nombre_large()))
					{
						tipoDocumentoList.get(indice).setTpo_estado(1);
					}
				}
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "Estimado usuario, existen documentos asociados a este tipo de documento, por lo cual, no podrá dejar inactivo el componente. Ante una posible inconsistencia  de datos por favor comuníquese con el administrador. De ante mano muchas gracias. ");

				RequestContext.getCurrentInstance().showMessageInDialog(message);

			}
			else if(tipoDocumentoService.deleteTipoDocumento(usuario, objTipoDocumentoDTO))
			{
				tipoDocumentoList = tipoDocumentoService.selectTiposDocumentos(usuario);		
				objTipoDocumentoDTO = new TipoDocumentoDTO();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notificación de Información", "Se ha desactivado correctamente el tipo de documento.");

				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}
			else
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "No fue posible deshabilitar el tipo de documento.");

				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}
		}
		catch(Exception error)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "Estimado usuario, existen documentos asociados a este tipo de documento, por lo cual, no podrá dejar inactivo el componente. Ante una posible inconsistencia  de datos por favor comuníquese con el administrador. De ante mano muchas gracias. ");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			LogDTO log= new LogDTO(usuario,MantenedorBean.class.getName(),LevelEnum.ERR.getId(),error.getMessage());
			sistemaService.registroLog(log);
		}
	}



	/**
	 * Tomar update descriptor.
	 *
	 * @param id the id
	 */
	public void tomarUpdateDestinatario(long id)
	{
		try
		{
			for(int indice = 0; indice<destinatarioList.size(); indice ++ )
			{
				if(destinatarioList.get(indice).getDes_id() == id)
				{
					objDestinatarioDTO = destinatarioList.get(indice);
					cambiarEstadoDos();
				}
			}
		}
		catch(Exception error)
		{
			LogDTO log= new LogDTO(usuario,MantenedorBean.class.getName(),LevelEnum.ERR.getId(),error.getMessage());
			sistemaService.registroLog(log);
		}
	}

	public void tomarUpdateDescriptor(long id)
	{
		try
		{
			for(int indice = 0; indice<descriptorList.size(); indice ++ )
			{
				if(descriptorList.get(indice).getDesc_id() == id)
				{
					objDescriptorDTO = descriptorList.get(indice);
					cambiarEstadoDos();
				}
			}
		}
		catch(Exception error)
		{
			LogDTO log= new LogDTO(usuario,MantenedorBean.class.getName(),LevelEnum.ERR.getId(),error.getMessage());
			sistemaService.registroLog(log);
		}
	}

	/**
	 * Tomar update tipo doc.
	 *
	 * @param id the id
	 */
	public void tomarUpdateTipoDoc(long id)
	{
		try
		{
			for(int indice = 0; indice<tipoDocumentoList.size(); indice ++ )
			{
				if(tipoDocumentoList.get(indice).getTpo_id() == id)
				{
					objTipoDocumentoDTO = tipoDocumentoList.get(indice);
					cambiarEstadoDos();
				}
			}
		}
		catch(Exception error)
		{
			LogDTO log= new LogDTO(usuario,MantenedorBean.class.getName(),LevelEnum.ERR.getId(),error.getMessage());
			sistemaService.registroLog(log);
		}
	}

	/**
	 * Update descriptor.
	 */
	public void updateDescriptor()
	{
		try
		{
			if(objDescriptorDTO != null)
			{

				if(descriptorService.tomarDependenciasDeId(usuario, objDescriptorDTO)>0 && objDescriptorDTO.getDesc_estado()== 0)
				{
					//TODO: no se puede act ya que existen dep en documentos.

					for(int indice = 0; indice<descriptorList.size(); indice ++)
					{
						if(objDescriptorDTO.getDesc_nombre_large().equalsIgnoreCase(descriptorList.get(indice).getDesc_nombre_large()))
						{
							descriptorList.get(indice).setDesc_estado(1);
						}
					}
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "Estimado usuario, existen documentos asociados a este descriptor, por lo cual, no podrá dejar inactivo el componente. Ante una posible inconsistencia  de datos por favor comuníquese con el administrador. De ante mano muchas gracias. ");

					RequestContext.getCurrentInstance().showMessageInDialog(message);
				}
				else{
					if(descriptorService.updateDescriptor(usuario, objDescriptorDTO));
					{
						objDescriptorDTO = new DescriptorDTO();
						tipoDocumentoList = tipoDocumentoService.selectTiposDocumentos(usuario);
						cambiarEstadoDos();
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notificación de Información", "Se ha actualizado correctamente el descriptor.");

						RequestContext.getCurrentInstance().showMessageInDialog(message);
					}
				}

			}
		}
		catch(Exception error)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "No ha sido posible actualizar correctamente el descriptor.");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			LogDTO log= new LogDTO(usuario,MantenedorBean.class.getName(),LevelEnum.ERR.getId(),error.getMessage());
			sistemaService.registroLog(log);
		}

	}

	public void updateDestinatario()
	{
		try
		{
			if(objDestinatarioDTO != null)
			{
				if(destinatarioService.tomarDependenciasDeId(usuario, objDestinatarioDTO)>0 && objDestinatarioDTO.getDes_estado()== 0)
				{
					for(int indice = 0; indice<destinatarioList.size(); indice ++)
					{
						if(objDestinatarioDTO.getDes_nombre_large().equalsIgnoreCase(destinatarioList.get(indice).getDes_nombre_large()))
						{
							destinatarioList.get(indice).setDes_estado(1);
						}
					}
					//TODO: no se puede act ya que existen dep en documentos.
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "Estimado usuario, existen documentos asociados a este destinatario, por lo cual, no podrá dejar inactivo el componente. Ante una posible inconsistencia  de datos por favor comuníquese con el administrador. De ante mano muchas gracias. ");

					RequestContext.getCurrentInstance().showMessageInDialog(message);
				}
				else
				{
					if(destinatarioService.updateDestinatario(usuario, objDestinatarioDTO));
					{
						objDestinatarioDTO = new DestinatarioDTO();
						destinatarioList = destinatarioService.selectDestinatarios(usuario);					
						cambiarEstadoDos();
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notificación de Información", "Se ha actualizado correctamente el destinatario.");

						RequestContext.getCurrentInstance().showMessageInDialog(message);
					}
				}

			}
		}
		catch(Exception error)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "No ha sido posible actualizar correctamente el destinatario.");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			LogDTO log= new LogDTO(usuario,MantenedorBean.class.getName(),LevelEnum.ERR.getId(),error.getMessage());
			sistemaService.registroLog(log);
		}

	}

	/**
	 * Update tipo doc.
	 */
	public void updateTipoDoc()
	{
		try
		{
			if(objTipoDocumentoDTO != null)
			{
				if(tipoDocumentoService.tomarDependenciasDeId(usuario, objTipoDocumentoDTO)>0 && objTipoDocumentoDTO.getTpo_estado() == 0)
				{
					//TODO: no se puede act ya que existen dep en documentos.
					for(int indice = 0; indice<tipoDocumentoList.size(); indice ++)
					{
						if(objTipoDocumentoDTO.getTpo_nombre_large().equalsIgnoreCase(tipoDocumentoList.get(indice).getTpo_nombre_large()))
						{
							tipoDocumentoList.get(indice).setTpo_estado(1);
						}
					}
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "Estimado usuario, existen documentos asociados a este tipo de documento, por lo cual, no podrá dejar inactivo el componente. Ante una posible inconsistencia  de datos por favor comuníquese con el administrador. De ante mano muchas gracias. ");

					RequestContext.getCurrentInstance().showMessageInDialog(message);

				}
				else
				{
					if(tipoDocumentoService.updateTipoDocumento(usuario, objTipoDocumentoDTO));
					{
						objTipoDocumentoDTO = new TipoDocumentoDTO();
						tipoDocumentoList = tipoDocumentoService.selectTiposDocumentos(usuario);
						cambiarEstadoDos();
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notificación de Información", "Se ha actualizado correctamente el tipo de documento.");

						RequestContext.getCurrentInstance().showMessageInDialog(message);
					}
				}

			}
		}
		catch(Exception error)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "No ha sido posible actualizar correctamente el tipo de documento.");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			LogDTO log= new LogDTO(usuario,MantenedorBean.class.getName(),LevelEnum.ERR.getId(),error.getMessage());
			sistemaService.registroLog(log);
		}

	}

	/**
	 * Tomar formulario descriptor.
	 */
	public void tomarFormularioDescriptor()
	{
		try
		{
			if(objDescriptorDTO != null)
			{
				if(descriptorService.insertDescriptor(usuario, objDescriptorDTO))
				{
					descriptorList = descriptorService.selectDescriptores(usuario);	
					cambiarEstado();
					objDescriptorDTO = new DescriptorDTO();
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notificación de Información", "Se ha ingresado correctamente el descriptor.");

					RequestContext.getCurrentInstance().showMessageInDialog(message);
				}
				else
				{
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "No ha sido posible ingresar correctamente el descriptor.");

					RequestContext.getCurrentInstance().showMessageInDialog(message);
				}
			}
			else
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "No ha sido posible ingresar correctamente el descriptor.");

				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}
		}
		catch(Exception error)
		{
			LogDTO log= new LogDTO(usuario,MantenedorBean.class.getName(),LevelEnum.ERR.getId(),error.getMessage());
			sistemaService.registroLog(log);
		}
	}

	/**
	 * Tomar formulario tipo doc.
	 */
	public void tomarFormularioDestinatario()
	{
		try
		{
			if(objDestinatarioDTO != null)
			{			
				if(destinatarioService.insertDestinatario(usuario, objDestinatarioDTO))
				{
					destinatarioList = destinatarioService.selectDestinatarios(usuario);
					cambiarEstado();
					objDestinatarioDTO = new DestinatarioDTO();
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notificación de Información", "Se ha ingresado correctamente el destinatario.");

					RequestContext.getCurrentInstance().showMessageInDialog(message);
				}
				else
				{
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "No ha sido posible ingresar correctamente el destinatario.");

					RequestContext.getCurrentInstance().showMessageInDialog(message);
				}
			}
			else
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "No ha sido posible ingresar correctamente el destinatario.");

				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}
		}
		catch(Exception error)
		{
			LogDTO log= new LogDTO(usuario,MantenedorBean.class.getName(),LevelEnum.ERR.getId(),error.getMessage());
			sistemaService.registroLog(log);
		}
	}

	public void tomarFormularioTipoDoc()
	{
		try
		{
			if(objTipoDocumentoDTO != null)
			{
				if(tipoDocumentoService.insertTipoDocumento(usuario, objTipoDocumentoDTO))
				{
					tipoDocumentoList = tipoDocumentoService.selectTiposDocumentos(usuario);
					cambiarEstado();
					objTipoDocumentoDTO = new TipoDocumentoDTO();
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notificación de Información", "Se ha ingresado correctamente el tipo de documento.");

					RequestContext.getCurrentInstance().showMessageInDialog(message);
				}
				else
				{
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "No ha sido posible ingresar correctamente el tipo de documento.");

					RequestContext.getCurrentInstance().showMessageInDialog(message);
				}
			}
			else
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "No ha sido posible ingresar correctamente el tipo de documento.");

				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}
		}
		catch(Exception error)
		{
			LogDTO log= new LogDTO(usuario,MantenedorBean.class.getName(),LevelEnum.ERR.getId(),error.getMessage());
			sistemaService.registroLog(log);
		}
	}


	/**
	 * Cambiar estado.
	 */
	public void cambiarEstado()
	{

		if(estado)
		{
			estado = false;
		}
		else
		{
			estado = true;
			estadoDos = false;
		}
	}

	public void cambiarEstadonew()
	{
		objDescriptorDTO = new DescriptorDTO();
		objDestinatarioDTO = new DestinatarioDTO();
		objTipoDocumentoDTO = new TipoDocumentoDTO();
		if(estado)
		{
			estado = false;
		}
		else
		{
			estado = true;
			estadoDos = false;
		}
	}

	/**
	 * Cambiar estado dos.
	 */
	public void cambiarEstadoDos()
	{
		if(estadoDos)
		{
			estadoDos = false;
		}
		else
		{
			estadoDos = true;
			estado = false;
		}
	}

	/**
	 * Gets the descriptor list.
	 *
	 * @return the descriptor list
	 */
	public List<DescriptorDTO> getDescriptorList() {
		return descriptorList;
	}

	/**
	 * Sets the descriptor list.
	 *
	 * @param descriptorList the new descriptor list
	 */
	public void setDescriptorList(List<DescriptorDTO> descriptorList) {
		this.descriptorList = descriptorList;
	}

	/**
	 * Gets the destinatario list.
	 *
	 * @return the destinatario list
	 */
	public List<DestinatarioDTO> getDestinatarioList() {
		return destinatarioList;
	}

	/**
	 * Sets the destinatario list.
	 *
	 * @param destinatarioList the new destinatario list
	 */
	public void setDestinatarioList(List<DestinatarioDTO> destinatarioList) {
		this.destinatarioList = destinatarioList;
	}

	/**
	 * Gets the tipo documento list.
	 *
	 * @return the tipo documento list
	 */
	public List<TipoDocumentoDTO> getTipoDocumentoList() {
		return tipoDocumentoList;
	}

	/**
	 * Sets the tipo documento list.
	 *
	 * @param tipoDocumentoList the new tipo documento list
	 */
	public void setTipoDocumentoList(List<TipoDocumentoDTO> tipoDocumentoList) {
		this.tipoDocumentoList = tipoDocumentoList;
	}

	/**
	 * Gets the obj descriptor dto.
	 *
	 * @return the obj descriptor dto
	 */
	public DescriptorDTO getObjDescriptorDTO() {
		return objDescriptorDTO;
	}

	/**
	 * Sets the obj descriptor dto.
	 *
	 * @param objDescriptorDTO the new obj descriptor dto
	 */
	public void setObjDescriptorDTO(DescriptorDTO objDescriptorDTO) {
		this.objDescriptorDTO = objDescriptorDTO;
	}

	/**
	 * Checks if is estado.
	 *
	 * @return true, if is estado
	 */
	public boolean isEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	/**
	 * Checks if is estado dos.
	 *
	 * @return true, if is estado dos
	 */
	public boolean isEstadoDos() {
		return estadoDos;
	}

	/**
	 * Sets the estado dos.
	 *
	 * @param estadoDos the new estado dos
	 */
	public void setEstadoDos(boolean estadoDos) {
		this.estadoDos = estadoDos;
	}

	/**
	 * Gets the obj tipo documento dto.
	 *
	 * @return the obj tipo documento dto
	 */
	public TipoDocumentoDTO getObjTipoDocumentoDTO() {
		return objTipoDocumentoDTO;
	}

	/**
	 * Sets the obj tipo documento dto.
	 *
	 * @param objTipoDocumentoDTO the new obj tipo documento dto
	 */
	public void setObjTipoDocumentoDTO(TipoDocumentoDTO objTipoDocumentoDTO) {
		this.objTipoDocumentoDTO = objTipoDocumentoDTO;
	}

	/**
	 * Gets the obj destinatario dto.
	 *
	 * @return the obj destinatario dto
	 */
	public DestinatarioDTO getObjDestinatarioDTO() {
		return objDestinatarioDTO;
	}

	/**
	 * Sets the obj destinatario dto.
	 *
	 * @param objDestinatarioDTO the new obj destinatario dto
	 */
	public void setObjDestinatarioDTO(DestinatarioDTO objDestinatarioDTO) {
		this.objDestinatarioDTO = objDestinatarioDTO;
	}



}
