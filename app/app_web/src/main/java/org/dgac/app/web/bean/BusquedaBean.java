package org.dgac.app.web.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.dgac.app.logic.ejb.busqueda.BusquedaEJBLocal;
import org.dgac.app.logic.ejb.mantenedores.DescriptorEJBLocal;
import org.dgac.app.logic.ejb.mantenedores.DestinatarioEJBLocal;
import org.dgac.app.logic.ejb.mantenedores.DocumentoEJBLocal;
import org.dgac.app.logic.ejb.mantenedores.TipoDocumentoEJBLocal;
import org.dgac.app.logic.ejb.sistema.SistemaEJBLocal;
import org.dgac.app.web.util.UtilSession;
import org.dgac.common.dto.DescriptorDTO;
import org.dgac.common.dto.DestinatarioDTO;
import org.dgac.common.dto.DocumentoDTO;
import org.dgac.common.dto.FileUpload;
import org.dgac.common.dto.FuncionarioDTO;
import org.dgac.common.dto.TipoDocumentoDTO;
import org.dgac.common.enums.UsrNameDefaultEnum;
import org.dgac.common.util.ServiceLocator;
import org.dgac.common.util.UtilidadesAll;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.google.gson.Gson;


// TODO: Auto-generated Javadoc
/**
 * The Class SistemaBean.
 */
@ManagedBean(name = "busquedaBean")
@SessionScoped
public class BusquedaBean extends BeanGenerico implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private DescriptorEJBLocal descriptorService;
	private DestinatarioEJBLocal destinatarioService;
	private TipoDocumentoEJBLocal tpoDocumentoService;
	private DocumentoEJBLocal documentoService;
	private SistemaEJBLocal sistemaService;
	private BusquedaEJBLocal objBusquedaEJBLocal;
	private DestinatarioDTO destinatario;
	private DescriptorDTO descriptor;
	private TipoDocumentoDTO tpoDocumento;
	private DocumentoDTO objDocumento;
	private DocumentoDTO objBuscar;
	private List<Integer> listYears;
	private List<DestinatarioDTO> listDestinatario;
	private List<DescriptorDTO> listDescriptor;
	private List<TipoDocumentoDTO> listTipoDocumento;
	private List<DocumentoDTO> listDocumento;
	private byte[] documento;
	private FileUpload objFileUpload;
	private List<FileUpload> lstFileUpload;
	private StreamedContent content;
	private FuncionarioDTO funcionario;
	private int page;
	private Date fechaDesde;
	private Date fechaHasta;
	private Gson objGson;
	private String nombreDestinatario;
	private String nombreDescriptor;
	private String nombreTpoDocumento;
	private String strDestinatario;
	private String strDescriptor;
	private String strTipoDocumento;
	private int ano;
	private boolean mostrar;
	private String anoTomado;
	private String anoTomadoUp;
	private String errorAlfresco;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cargaBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("mantenedorBean", null);
		objDocumento=new DocumentoDTO();
		objBuscar = new DocumentoDTO();
		descriptorService=(DescriptorEJBLocal) ServiceLocator.getInstance().getEjb(DescriptorEJBLocal.class);
		destinatarioService=(DestinatarioEJBLocal)ServiceLocator.getInstance().getEjb(DestinatarioEJBLocal.class);
		tpoDocumentoService=(TipoDocumentoEJBLocal)ServiceLocator.getInstance().getEjb(TipoDocumentoEJBLocal.class);
		documentoService=(DocumentoEJBLocal)ServiceLocator.getInstance().getEjb(DocumentoEJBLocal.class);
		sistemaService=(SistemaEJBLocal)ServiceLocator.getInstance().getEjb(SistemaEJBLocal.class);
		objBusquedaEJBLocal = (BusquedaEJBLocal)ServiceLocator.getInstance().getEjb(BusquedaEJBLocal.class);
		funcionario=UtilSession.getFuncionario();
		//String usuario=funcionario.getNombres()+" "+funcionario.getApellido_paterno()+" "+funcionario.getApellido_materno();
		lstFileUpload = new ArrayList<FileUpload>();
		listDestinatario=new ArrayList<DestinatarioDTO>();
		listDestinatario=destinatarioService.selectDestinatarios(UsrNameDefaultEnum.DGAC.getId());
		for(int i=0;i<listDestinatario.size();i++)
		{
			if(listDestinatario.get(i).getDes_estado()==0)
			{
				listDestinatario.remove(i);
			}
		}
		listDescriptor=new ArrayList<DescriptorDTO>();
		listDescriptor=descriptorService.selectDescriptores(UsrNameDefaultEnum.DGAC.getId());
		for(int i=0;i<listDescriptor.size();i++)
		{
			if(listDescriptor.get(i).getDesc_estado()==0)
			{
				listDescriptor.remove(i);
			}
		}
		listTipoDocumento=new ArrayList<TipoDocumentoDTO>();
		listTipoDocumento=tpoDocumentoService.selectTiposDocumentos(UsrNameDefaultEnum.DGAC.getId());
		for(int i=0;i<listTipoDocumento.size();i++)
		{
			if(listTipoDocumento.get(i).getTpo_estado()==0)
			{
				listTipoDocumento.remove(i);
			}
		}
		listYears=new ArrayList<Integer>();
		int objAno=Calendar.getInstance().get(Calendar.YEAR); 
		listYears.add(Calendar.getInstance().get(Calendar.YEAR));
		while(objAno>=2008)
		{
			objAno--;
			if(objAno>=2008)
			{
				listYears.add(objAno);
			}

		}
		listDocumento=new ArrayList<DocumentoDTO>();
		//listDocumento=documentoService.selectDocumentos(UsrNameDefaultEnum.DGAC.getId(), Long.parseLong(String.valueOf(UtilSession.getRol())));
		//page=listDocumento.size();
	}

	public void onPreRender()
	{
		try 
		{

			if(objDocumento.getDoc_nombre_archivo()!=null)
			{
				
				setVariables();
				byte[] array=objBusquedaEJBLocal.getDocumento(objDocumento.getDoc_id_alfresco());
				if(array != null)
				{
					ByteArrayOutputStream out = new ByteArrayOutputStream(array.length);
					out.write(array,0,array.length);
					content = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()), "application/pdf"); 
				}
				else
				{

					ClassLoader classLoader = getClass().getClassLoader();
					File file = new File(classLoader.getResource("file/pru.pdf").getFile());
					byte[] array2 = UtilidadesAll.leerFileToBytesXD(file);
					ByteArrayOutputStream out = new ByteArrayOutputStream(array2.length);
					out.write(array2,0,array2.length);
					content = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()), "application/pdf"); 
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Problema al tomar el documento desde alfresco");
					RequestContext.getCurrentInstance().showMessageInDialog(message);
				}



			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Integer> autoCompleAno(String ano)
	{
		List<Integer> lstAno = new ArrayList<Integer>();
		try
		{
			for(int indice = 0; indice<listYears.size(); indice ++)
			{
				if(String.valueOf(listYears.get(indice)).contains(ano))
				{
					lstAno.add(listYears.get(indice));
				}
			}
		}
		catch(Exception error)
		{
			error.getMessage();
		}
		return lstAno;
	}

	public void removeDocumento()
	{
		objDocumento.setDoc_usr_mod(UsrNameDefaultEnum.DGAC.getId());
		objDocumento.setDoc_estado(0);
		objDocumento.setDoc_id(documentoService.validarDocumento(UsrNameDefaultEnum.DGAC.getId(), objDocumento));

		try
		{
			if(documentoService.deleteDocumento(UsrNameDefaultEnum.DGAC.getId(), objDocumento))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notificación de Publicación", "Se ha cerrado el documento.");

				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}
			else
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "No se ha podido cerrar el documento. Favor intentar mas tarde");

				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}

		}
		catch(Exception e)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "No se ha podido cerrar el documento. Favor intentar mas tarde");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}


	public void actualizarDocumento()
	{
		objDocumento.setDoc_usr_mod(UsrNameDefaultEnum.DGAC.getId());
		objDocumento.setDoc_estado(1);
		objDocumento.setDoc_id(documentoService.validarDocumento(UsrNameDefaultEnum.DGAC.getId(), objDocumento));
		objDocumento.setDoc_ano(Integer.parseInt(anoTomadoUp));

		try
		{
			if(documentoService.updateDocumento(UsrNameDefaultEnum.DGAC.getId(), objDocumento))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notificación de Actualización", "Se ha actualizado el documento.");

				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}
			else
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "No se ha podido actualizar el documento. Favor intentar mas tarde");

				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}

		}
		catch(Exception e)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "No se ha podido cerrar el documento. Favor intentar mas tarde");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}

	public void resetForm()
	{
		listDocumento=new ArrayList<DocumentoDTO>();
		objBuscar = new DocumentoDTO();
	}

	public void setVariables()
	{
		String destin=null;
		for(DestinatarioDTO des:listDestinatario)
		{
			if(objDocumento.getDes_id()==des.getDes_id())
			{
				destin=des.getDes_nombre_large();
				nombreDestinatario=destin;
			}
		}


		String descr=null;
		for(DescriptorDTO desc:listDescriptor)
		{
			if(objDocumento.getDesc_id()==desc.getDesc_id())
			{
				descr=desc.getDesc_nombre_large();
				nombreDescriptor=descr;
			}
		}

		String tpo=null;
		for(TipoDocumentoDTO doc:listTipoDocumento)
		{
			if(objDocumento.getTpo_id()==doc.getTpo_id())
			{
				tpo=doc.getTpo_nombre_large();
				nombreTpoDocumento=tpo;
			}
		}
	}

	public void tomarAno(String ano)
	{
		anoTomadoUp = ano;
	}
	public void tomarFormulario()
	{
		try
		{
			objBuscar.setDesc_id(0);
			objBuscar.setTpo_id(0);
			objBuscar.setDes_id(0);
			objBuscar.setDoc_ano(0);

			if(objBuscar != null)
			{

				if(anoTomado != null)
				{
					objBuscar.setDoc_ano(Integer.parseInt(anoTomado));
					anoTomado = null;
				}

				if(strDestinatario != null)
				{											
					objBuscar.setDes_id(Long.parseLong(strDestinatario));

				}

				if(strDescriptor != null)
				{

					objBuscar.setDesc_id(Long.parseLong(strDescriptor));

				}

				if(strTipoDocumento != null)
				{

					objBuscar.setTpo_id(Long.parseLong(strTipoDocumento));

				}
				limpiarFiltros();
				listDocumento =	objBusquedaEJBLocal.buscarDoc(objBuscar,fechaDesde,fechaHasta);
				if(listDocumento!=null)
				{
					page=listDocumento.size();
				}
				else
				{
					page = 0;
				}

				String rolSession=UtilSession.getRol();
				if(rolSession.equals("GG_PORTAL_JURIDICA_ADMINISTRADOR"))
				{
					mostrar=false;
				}
				else
				{
					mostrar=true;
				}
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
		}
	}


	public void tomarFormularioUsuario()
	{
		try
		{
			objBuscar.setDesc_id(0);
			objBuscar.setTpo_id(0);
			objBuscar.setDes_id(0);
			objBuscar.setDoc_ano(0);
			if(objBuscar != null)
			{
				if(anoTomado != null)
				{
					objBuscar.setDoc_ano(Integer.parseInt(anoTomado));
					anoTomado = null;
				}

				if(strDestinatario != null)
				{											
					objBuscar.setDes_id(Long.parseLong(strDestinatario));

				}

				if(strDescriptor != null)
				{

					objBuscar.setDesc_id(Long.parseLong(strDescriptor));

				}

				if(strTipoDocumento != null)
				{

					objBuscar.setTpo_id(Long.parseLong(strTipoDocumento));

				}
				limpiarFiltros();
				listDocumento =	objBusquedaEJBLocal.buscarUsuario(objBuscar,fechaDesde,fechaHasta);
				if(listDocumento!=null)
				{
					page=listDocumento.size();
				}
				else
				{
					page = 0;
				}

				String rolSession=UtilSession.getRol();
				if(rolSession.equals("GG_PORTAL_JURIDICA_SUPERADMINISTRADOR"))
				{
					mostrar=true;
				}
				else
				{
					mostrar=false;
				}
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
		}
	}

	public void limpiarFiltros()
	{
		this.setAnoTomado(null); 
		this.setStrDestinatario(null); 
		this.setStrDescriptor(null); 
		this.setStrTipoDocumento(null);
	}

	public void buscarDocumentoFiltros()
	{

		try{
			objGson = new Gson();
		}
		catch(Exception error)
		{
			error.printStackTrace();
		}
	}

	public List<Integer> getListYears() {
		return listYears;
	}

	public void setListYears(List<Integer> listYears) {
		this.listYears = listYears;
	}

	public List<DestinatarioDTO> getListDestinatario() {
		return listDestinatario;
	}

	public void setListDestinatario(List<DestinatarioDTO> listDestinatario) {
		this.listDestinatario = listDestinatario;
	}

	public List<DescriptorDTO> getListDescriptor() {
		return listDescriptor;
	}

	public void setListDescriptor(List<DescriptorDTO> listDescriptor) {
		this.listDescriptor = listDescriptor;
	}

	public List<DocumentoDTO> getListDocumento() {
		return listDocumento;
	}

	public void setListDocumento(List<DocumentoDTO> listDocumento) {
		this.listDocumento = listDocumento;
	}

	public List<TipoDocumentoDTO> getListTipoDocumento() {
		return listTipoDocumento;
	}

	public void setListTipoDocumento(List<TipoDocumentoDTO> listTipoDocumento) {
		this.listTipoDocumento = listTipoDocumento;
	}


	public byte[] getDocumento() {
		return documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}


	public FileUpload getObjFileUpload() {
		return objFileUpload;
	}


	public void setObjFileUpload(FileUpload objFileUpload) {
		this.objFileUpload = objFileUpload;
	}


	public List<FileUpload> getLstFileUpload() {
		return lstFileUpload;
	}

	public void setLstFileUpload(List<FileUpload> lstFileUpload) {
		this.lstFileUpload = lstFileUpload;
	}

	public StreamedContent getContent() {
		return content;
	}

	public void setContent(StreamedContent content) {
		this.content = content;
	}


	public DestinatarioDTO getDestinatario() {
		return destinatario;
	}


	public void setDestinatario(DestinatarioDTO destinatario) {
		this.destinatario = destinatario;
	}


	public DescriptorDTO getDescriptor() {
		return descriptor;
	}


	public void setDescriptor(DescriptorDTO descriptor) {
		this.descriptor = descriptor;
	}


	public TipoDocumentoDTO getTpoDocumento() {
		return tpoDocumento;
	}


	public void setTpoDocumento(TipoDocumentoDTO tpoDocumento) {
		this.tpoDocumento = tpoDocumento;
	}


	public DocumentoDTO getObjDocumento() {
		return objDocumento;
	}


	public void setObjDocumento(DocumentoDTO objDocumento) {
		this.objDocumento = objDocumento;
	}


	public int getAno() {
		return ano;
	}


	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public DocumentoDTO getObjBuscar() {
		return objBuscar;
	}

	public void setObjBuscar(DocumentoDTO objBuscar) {
		this.objBuscar = objBuscar;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getNombreDestinatario() {
		return nombreDestinatario;
	}

	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}

	public String getNombreDescriptor() {
		return nombreDescriptor;
	}

	public void setNombreDescriptor(String nombreDescriptor) {
		this.nombreDescriptor = nombreDescriptor;
	}

	public String getNombreTpoDocumento() {
		return nombreTpoDocumento;
	}

	public void setNombreTpoDocumento(String nombreTpoDocumento) {
		this.nombreTpoDocumento = nombreTpoDocumento;
	}

	public String getStrDestinatario() {
		return strDestinatario;
	}

	public void setStrDestinatario(String strDestinatario) {
		this.strDestinatario = strDestinatario;
	}

	public String getStrDescriptor() {
		return strDescriptor;
	}

	public void setStrDescriptor(String strDescriptor) {
		this.strDescriptor = strDescriptor;
	}

	public String getStrTipoDocumento() {
		return strTipoDocumento;
	}

	public void setStrTipoDocumento(String strTipoDocumento) {
		this.strTipoDocumento = strTipoDocumento;
	}

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}

	public String getAnoTomado() {
		return anoTomado;
	}

	public void setAnoTomado(String anoTomado) {
		this.anoTomado = anoTomado;
	}

	public String getAnoTomadoUp() {
		return anoTomadoUp;
	}

	public void setAnoTomadoUp(String anoTomadoUp) {
		this.anoTomadoUp = anoTomadoUp;
	}

}