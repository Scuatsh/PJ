package org.dgac.app.web.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;


// TODO: Auto-generated Javadoc
/**
 * The Class SistemaBean.
 */
@ManagedBean(name = "cargaBean")
@SessionScoped
public class CargarDocumentoBean extends BeanGenerico implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    
	private DescriptorEJBLocal descriptorService;
	private DestinatarioEJBLocal destinatarioService;
	private TipoDocumentoEJBLocal tpoDocumentoService;
	private DocumentoEJBLocal documentoService;
	private SistemaEJBLocal sistemaService;

	private DestinatarioDTO destinatario;
	private DescriptorDTO descriptor;
	private TipoDocumentoDTO tpoDocumento;
	private DocumentoDTO objDocumento;
	
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
	private boolean colum;

	private String extension;
	private String nombreDestinatario;
	private String nombreDescriptor;
	private String nombreTpoDocumento;
	
	private boolean showpopup;

	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("busquedaBean", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("mantenedorBean", null);
		objDocumento=new DocumentoDTO();
		descriptorService=(DescriptorEJBLocal) ServiceLocator.getInstance().getEjb(DescriptorEJBLocal.class);
		destinatarioService=(DestinatarioEJBLocal)ServiceLocator.getInstance().getEjb(DestinatarioEJBLocal.class);
		tpoDocumentoService=(TipoDocumentoEJBLocal)ServiceLocator.getInstance().getEjb(TipoDocumentoEJBLocal.class);
		documentoService=(DocumentoEJBLocal)ServiceLocator.getInstance().getEjb(DocumentoEJBLocal.class);
		sistemaService=(SistemaEJBLocal)ServiceLocator.getInstance().getEjb(SistemaEJBLocal.class);
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
		colum=false;
	}
	
	
	/**
	 * Upload.
	 *
	 * @param event the event
	 */
	public void upload(FileUploadEvent event) {  

		try {

			objFileUpload = new FileUpload(event.getFile().getContents(),event.getFile().getFileName());
			lstFileUpload.add(objFileUpload);
		      
		} catch (Exception e) {
			//e.printStackTrace();
		}

	}
	
	public void onPreRender()
	{
		try 
		{
			setVariables();
			if(objFileUpload.getFileName()!=null||objFileUpload.getFileName()!="")
			{
				extension=objFileUpload.getFileName().substring(objFileUpload.getFileName().lastIndexOf('.'), objFileUpload.getFileName().length());
			}
			
			ByteArrayOutputStream out = new ByteArrayOutputStream(objFileUpload.getFile().length);
            out.write(objFileUpload.getFile(),0,objFileUpload.getFile().length);
            if(extension.equals(".pdf"))
            {
            	content = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()), "application/pdf"); 
            }
            
		      
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	public void removeDocumento()
	{
		lstFileUpload.remove(objFileUpload);
	}
	
	public void submitForm()
	{
		objDocumento.setDoc_nombre_archivo(objFileUpload.getFileName().toUpperCase());
		objDocumento.setDoc_usr_insert(UsrNameDefaultEnum.DGAC.getId());
		objDocumento.setDoc_estado(1);
		objDocumento.setByteArray(objFileUpload.getFile());
		objDocumento.setSize(objFileUpload.getFile().length);
		
		try
		{
			if(documentoService.validarDocumento(UsrNameDefaultEnum.DGAC.getId(), objDocumento)==0)
			{
				if(documentoService.insertDocumento(UsrNameDefaultEnum.DGAC.getId(), objDocumento))
				{
					showpopup=true;
					colum=true;
					setVariables();
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notificación de Registro", "Se ha creado un nuevo documento.");
			         
			        RequestContext.getCurrentInstance().showMessageInDialog(message);
			        
				}
				else
				{
					showpopup=false;
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "No se ha podido generar el documento. Favor intentar mas tarde");
			         
			        RequestContext.getCurrentInstance().showMessageInDialog(message);
				}
			}
			else
			{
				showpopup=false;
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "El documento ya se encuentra en el repositorio. Favor intentar nuevamente. O bien cambiar el nombre del documento.");
		         
		        RequestContext.getCurrentInstance().showMessageInDialog(message);
			}
			
		}
		catch(Exception e)
		{
			showpopup=false;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "No se ha podido generar el documento. Favor intentar mas tarde");
	         
	        RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}
	
	public void publicar()
	{
		objDocumento.setDoc_nombre_archivo(objFileUpload.getFileName());
		objDocumento.setDoc_usr_insert(UsrNameDefaultEnum.DGAC.getId());
		objDocumento.setDoc_estado(1);
		objDocumento.setDoc_id(documentoService.validarDocumento(UsrNameDefaultEnum.DGAC.getId(), objDocumento));
		
		try
		{
			if(documentoService.publicarDocumento(UsrNameDefaultEnum.DGAC.getId(), objDocumento))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notificación de Publicación", "Se ha publicado el documento.");
		         
		        RequestContext.getCurrentInstance().showMessageInDialog(message);
		        resetForm();
			}
			else
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "No se ha podido publicar el documento. Favor intentar mas tarde");
		         
		        RequestContext.getCurrentInstance().showMessageInDialog(message);
			}
			
		}
		catch(Exception e)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notificación de Error", "No se ha podido publicar el documento. Favor intentar mas tarde");
	         
	        RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}
	
	public void cerrar()
	{
		objDocumento.setDoc_nombre_archivo(objFileUpload.getFileName());
		objDocumento.setDoc_usr_insert(UsrNameDefaultEnum.DGAC.getId());
		objDocumento.setDoc_estado(0);
		objDocumento.setDoc_id(documentoService.validarDocumento(UsrNameDefaultEnum.DGAC.getId(), objDocumento));
		
		try
		{
			if(documentoService.deleteDocumento(UsrNameDefaultEnum.DGAC.getId(), objDocumento))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notificación de Publicación", "Se ha cerrado el documento.");
		         
		        RequestContext.getCurrentInstance().showMessageInDialog(message);
		        resetForm();
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
	
	public void resetForm()
	{
		setVariables();
		objDocumento=new DocumentoDTO();
		lstFileUpload.remove(objFileUpload);
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


	public boolean isColum() {
		return colum;
	}

	public void setColum(boolean colum) {
		this.colum = colum;
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
	
	public boolean isShowpopup() {
		return showpopup;
	}


	public void setShowpopup(boolean showpopup) {
		this.showpopup = showpopup;
	}
}