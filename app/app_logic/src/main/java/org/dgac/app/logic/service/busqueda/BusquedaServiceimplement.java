package org.dgac.app.logic.service.busqueda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.dgac.app.logic.service.ServiceGeneric;
import org.dgac.app.logic.service.sistema.SistemaServiceImpl;
import org.dgac.common.constantes.ConstantesBd;
import org.dgac.common.dto.DocumentoDTO;
import org.dgac.common.dto.LogDTO;
import org.dgac.common.enums.LevelEnum;
import org.dgac.ws.integration.client.alfresco.buscar.ObtenerAlfrescoClient;
import org.dgac.ws.integration.server.alfresco.buscar.ObtenerDocumentoRequest;
import org.dgac.ws.integration.server.alfresco.buscar.ObtenerDocumentoResponse;
import org.dgac.ws.integration.util.IntegrationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusquedaServiceimplement extends ServiceGeneric implements BusquedaService {
	
	/** The conn. */
	private Connection conn = null;

	/** The pstmt. */
	private PreparedStatement pstmt = null;

	/** The rset. */
	private ResultSet rset = null;
	private String query;
	private boolean estadoQuery;
	private ArrayList<DocumentoDTO> lstDoc;
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	IntegrationProperties integrationProperties;

	@Override
	public ArrayList<DocumentoDTO> buscarDoc(DocumentoDTO objDocumentoDTO,Date fechaDesde, Date fechaHasta) {
	
		lstDoc = new ArrayList<DocumentoDTO>();
		try
		{
			
			conn = super.getConnection();
			pstmt = conn.prepareStatement(armarQuery(objDocumentoDTO,fechaDesde,fechaHasta));			
			rset = (ResultSet) pstmt.executeQuery();
			while (rset.next()) {
				DocumentoDTO objDoc = new DocumentoDTO();
				
				objDoc.setDoc_id(rset.getLong(ConstantesBd.DOC_ID.substring(ConstantesBd.DOC_ID.indexOf('.')+1,ConstantesBd.DOC_ID.length())));
				objDoc.setDoc_ano(rset.getInt(ConstantesBd.DOC_ANO.substring(ConstantesBd.DOC_ANO.indexOf('.')+1,ConstantesBd.DOC_ANO.length())));
				objDoc.setDes_id(rset.getLong(ConstantesBd.DOC_DES_ID.substring(ConstantesBd.DOC_DES_ID.indexOf('.')+1,ConstantesBd.DOC_DES_ID.length())));
				objDoc.setDesc_id(rset.getLong(ConstantesBd.DOC_DESC_ID.substring(ConstantesBd.DOC_DESC_ID.indexOf('.')+1,ConstantesBd.DOC_DESC_ID.length())));
				objDoc.setDoc_materia(rset.getString(ConstantesBd.DOC_MATERIA.substring(ConstantesBd.DOC_MATERIA.indexOf('.')+1,ConstantesBd.DOC_MATERIA.length())));
				objDoc.setDoc_fecha_ingreso(rset.getDate(ConstantesBd.DOC_FECHA_INGRESO.substring(ConstantesBd.DOC_FECHA_INGRESO.indexOf('.')+1,ConstantesBd.DOC_FECHA_INGRESO.length())));
				objDoc.setDoc_numero(rset.getString(ConstantesBd.DOC_NUMERO.substring(ConstantesBd.DOC_NUMERO.indexOf('.')+1,ConstantesBd.DOC_NUMERO.length())));
				objDoc.setTpo_id(rset.getLong(ConstantesBd.DOC_TPO_ID.substring(ConstantesBd.DOC_TPO_ID.indexOf('.')+1,ConstantesBd.DOC_TPO_ID.length())));
				objDoc.setDoc_resumen(rset.getString(ConstantesBd.DOC_RESUMEN.substring(ConstantesBd.DOC_RESUMEN.indexOf('.')+1,ConstantesBd.DOC_RESUMEN.length())));
				objDoc.setDoc_abogado(rset.getString(ConstantesBd.DOC_ABOGADO.substring(ConstantesBd.DOC_ABOGADO.indexOf('.')+1,ConstantesBd.DOC_ABOGADO.length())));
				objDoc.setDoc_nombre_archivo(rset.getString(ConstantesBd.DOC_NOMBRE_ARCHIVO.substring(ConstantesBd.DOC_NOMBRE_ARCHIVO.indexOf('.')+1,ConstantesBd.DOC_NOMBRE_ARCHIVO.length())));
				objDoc.setDoc_id_alfresco(rset.getString(ConstantesBd.DOC_ID_ALFRESCO.substring(ConstantesBd.DOC_ID_ALFRESCO.indexOf('.')+1,ConstantesBd.DOC_ID_ALFRESCO.length())));
				objDoc.setDoc_usr_insert(rset.getString(ConstantesBd.DOC_USR_INSERT.substring(ConstantesBd.DOC_USR_INSERT.indexOf('.')+1,ConstantesBd.DOC_USR_INSERT.length())));
				objDoc.setDoc_usr_mod(rset.getString(ConstantesBd.DOC_USR_MOD.substring(ConstantesBd.DOC_USR_MOD.indexOf('.')+1,ConstantesBd.DOC_USR_MOD.length())));
				objDoc.setDoc_fecha_mod(rset.getDate(ConstantesBd.DOC_FECHA_MOD.substring(ConstantesBd.DOC_FECHA_MOD.indexOf('.')+1,ConstantesBd.DOC_FECHA_MOD.length())));
				objDoc.setDoc_estado(rset.getInt(ConstantesBd.DOC_ESTADO.substring(ConstantesBd.DOC_ESTADO.indexOf('.')+1,ConstantesBd.DOC_ESTADO.length())));
				objDoc.setNombreDescriptor(rset.getString(ConstantesBd.DESC_NOMBRE_SMALL));
				objDoc.setNombreDestinatario(rset.getString(ConstantesBd.DES_NOMBRE_SMALL));
				objDoc.setNombreTipoDoc(rset.getString(ConstantesBd.TPO_NOMBRE_SMALL));
				
				lstDoc.add(objDoc);
			}
			
			return lstDoc;
			
		}
		catch(Exception error)
		{
			error.getMessage();
			return null;
		}
		finally
		{
			super.closePreparedStatement(pstmt);
			super.closeConnection(conn);
			super.closeResultSet(rset);
		}
		
	}
	
	
	@Override
	public ArrayList<DocumentoDTO> buscarUsuario(DocumentoDTO objDocumentoDTO,Date fechaDesde, Date fechaHasta) {
	
		lstDoc = new ArrayList<DocumentoDTO>();
		try
		{
			
			conn = super.getConnection();
			pstmt = conn.prepareStatement(armarQueryUsuario(objDocumentoDTO,fechaDesde,fechaHasta));			
			rset = (ResultSet) pstmt.executeQuery();
			while (rset.next()) {
				DocumentoDTO objDoc = new DocumentoDTO();
				
				objDoc.setDoc_id(rset.getLong(ConstantesBd.DOC_ID.substring(ConstantesBd.DOC_ID.indexOf('.')+1,ConstantesBd.DOC_ID.length())));
				objDoc.setDoc_ano(rset.getInt(ConstantesBd.DOC_ANO.substring(ConstantesBd.DOC_ANO.indexOf('.')+1,ConstantesBd.DOC_ANO.length())));
				objDoc.setDes_id(rset.getLong(ConstantesBd.DOC_DES_ID.substring(ConstantesBd.DOC_DES_ID.indexOf('.')+1,ConstantesBd.DOC_DES_ID.length())));
				objDoc.setDesc_id(rset.getLong(ConstantesBd.DOC_DESC_ID.substring(ConstantesBd.DOC_DESC_ID.indexOf('.')+1,ConstantesBd.DOC_DESC_ID.length())));
				objDoc.setDoc_materia(rset.getString(ConstantesBd.DOC_MATERIA.substring(ConstantesBd.DOC_MATERIA.indexOf('.')+1,ConstantesBd.DOC_MATERIA.length())));
				objDoc.setDoc_fecha_ingreso(rset.getDate(ConstantesBd.DOC_FECHA_INGRESO.substring(ConstantesBd.DOC_FECHA_INGRESO.indexOf('.')+1,ConstantesBd.DOC_FECHA_INGRESO.length())));
				objDoc.setDoc_numero(rset.getString(ConstantesBd.DOC_NUMERO.substring(ConstantesBd.DOC_NUMERO.indexOf('.')+1,ConstantesBd.DOC_NUMERO.length())));
				objDoc.setTpo_id(rset.getLong(ConstantesBd.DOC_TPO_ID.substring(ConstantesBd.DOC_TPO_ID.indexOf('.')+1,ConstantesBd.DOC_TPO_ID.length())));
				objDoc.setDoc_resumen(rset.getString(ConstantesBd.DOC_RESUMEN.substring(ConstantesBd.DOC_RESUMEN.indexOf('.')+1,ConstantesBd.DOC_RESUMEN.length())));
				objDoc.setDoc_abogado(rset.getString(ConstantesBd.DOC_ABOGADO.substring(ConstantesBd.DOC_ABOGADO.indexOf('.')+1,ConstantesBd.DOC_ABOGADO.length())));
				objDoc.setDoc_nombre_archivo(rset.getString(ConstantesBd.DOC_NOMBRE_ARCHIVO.substring(ConstantesBd.DOC_NOMBRE_ARCHIVO.indexOf('.')+1,ConstantesBd.DOC_NOMBRE_ARCHIVO.length())));
				objDoc.setDoc_id_alfresco(rset.getString(ConstantesBd.DOC_ID_ALFRESCO.substring(ConstantesBd.DOC_ID_ALFRESCO.indexOf('.')+1,ConstantesBd.DOC_ID_ALFRESCO.length())));
				objDoc.setDoc_usr_insert(rset.getString(ConstantesBd.DOC_USR_INSERT.substring(ConstantesBd.DOC_USR_INSERT.indexOf('.')+1,ConstantesBd.DOC_USR_INSERT.length())));
				objDoc.setDoc_usr_mod(rset.getString(ConstantesBd.DOC_USR_MOD.substring(ConstantesBd.DOC_USR_MOD.indexOf('.')+1,ConstantesBd.DOC_USR_MOD.length())));
				objDoc.setDoc_fecha_mod(rset.getDate(ConstantesBd.DOC_FECHA_MOD.substring(ConstantesBd.DOC_FECHA_MOD.indexOf('.')+1,ConstantesBd.DOC_FECHA_MOD.length())));
				objDoc.setDoc_estado(rset.getInt(ConstantesBd.DOC_ESTADO.substring(ConstantesBd.DOC_ESTADO.indexOf('.')+1,ConstantesBd.DOC_ESTADO.length())));
				objDoc.setNombreDescriptor(rset.getString(ConstantesBd.DESC_NOMBRE_SMALL));
				objDoc.setNombreDestinatario(rset.getString(ConstantesBd.DES_NOMBRE_SMALL));
				objDoc.setNombreTipoDoc(rset.getString(ConstantesBd.TPO_NOMBRE_SMALL));
				
				lstDoc.add(objDoc);
			}
			
			return lstDoc;
			
		}
		catch(Exception error)
		{
			error.getMessage();
			return null;
		}
		finally
		{
			super.closePreparedStatement(pstmt);
			super.closeConnection(conn);
			super.closeResultSet(rset);
		}
		
	}
	
	public String armarQuery(DocumentoDTO objDocumentoDTO,Date fechaDesde, Date fechaHasta)
	{
		try
		{
			query = null;
			query = ConstantesBd.QUERYDINAMICADOCUNO;
			if(objDocumentoDTO.getDoc_abogado() != null)
			{
				if(objDocumentoDTO.getDoc_abogado().trim().length()>0)
				{
						if(estadoQuery)
						{
							query += ConstantesBd.QUERYDINAMICADOCAND;
							query += ConstantesBd.DOC_ABOGADO;
							query += ConstantesBd.QUERYDINAMICODOCLIKE+" '%"+objDocumentoDTO.getDoc_abogado()+"%' ";
							
						}
						else
						{
							estadoQuery = true;
							query += ConstantesBd.QUERYDINAMICADOCWHERE;
							query += ConstantesBd.DOC_ABOGADO;
							query += ConstantesBd.QUERYDINAMICODOCLIKE+" '%"+objDocumentoDTO.getDoc_abogado()+"%' ";
						}
				}
			}
			if(objDocumentoDTO.getDoc_nombre_archivo() != null)
			{
				 if(objDocumentoDTO.getDoc_nombre_archivo().trim().length()>0)
				 {
					 if(estadoQuery)
						{
							query += ConstantesBd.QUERYDINAMICADOCAND;
							query += ConstantesBd.DOC_NOMBRE_ARCHIVO;
							query += ConstantesBd.QUERYDINAMICODOCLIKE+" '%"+objDocumentoDTO.getDoc_nombre_archivo()+"%' ";
							
						}
						else
						{
							estadoQuery = true;
							query += ConstantesBd.QUERYDINAMICADOCWHERE;
							query += ConstantesBd.DOC_NOMBRE_ARCHIVO;
							query += ConstantesBd.QUERYDINAMICODOCLIKE+" '%"+objDocumentoDTO.getDoc_nombre_archivo()+"%' ";
						}
				 }
			}
			
			if(objDocumentoDTO.getDoc_resumen() != null)
			{
				 if(objDocumentoDTO.getDoc_resumen().trim().length()>0)
				 {
					 if(estadoQuery)
						{
							query += ConstantesBd.QUERYDINAMICADOCAND;
							query += ConstantesBd.DOC_RESUMEN;
							query += ConstantesBd.QUERYDINAMICODOCLIKE+" '%"+objDocumentoDTO.getDoc_resumen()+" %'";
							
						}
						else
						{
							estadoQuery = true;
							query += ConstantesBd.QUERYDINAMICADOCWHERE;
							query += ConstantesBd.DOC_RESUMEN;
							query += ConstantesBd.QUERYDINAMICODOCLIKE+" '%"+objDocumentoDTO.getDoc_resumen()+"%' ";
						}
				 }
			}
			
			if(objDocumentoDTO.getDoc_numero() != null)
			{
				if(objDocumentoDTO.getDoc_numero().trim().length()>0 )
				 {
					 if(estadoQuery)
						{
							query += ConstantesBd.QUERYDINAMICADOCAND;
							query += ConstantesBd.DOC_NUMERO;
							query += ConstantesBd.QUERYDINAMICODOCLIKE+" '%"+objDocumentoDTO.getDoc_numero()+"%' ";
							
						}
						else
						{
							estadoQuery = true;
							query += ConstantesBd.QUERYDINAMICADOCWHERE;
							query += ConstantesBd.DOC_NUMERO;
							query += ConstantesBd.QUERYDINAMICODOCLIKE+" '%"+objDocumentoDTO.getDoc_numero()+"%' ";
						}
				 }
			}
			
			if(objDocumentoDTO.getDoc_materia() != null)
			{
				if(objDocumentoDTO.getDoc_materia().trim().length()>0 )
				 {
					 if(estadoQuery)
						{
							query += ConstantesBd.QUERYDINAMICADOCAND;
							query += ConstantesBd.DOC_MATERIA;
							query += ConstantesBd.QUERYDINAMICODOCLIKE+" '%"+objDocumentoDTO.getDoc_materia()+"%' ";
							
						}
						else
						{
							estadoQuery = true;
							query += ConstantesBd.QUERYDINAMICADOCWHERE;
							query += ConstantesBd.DOC_MATERIA;
							query += ConstantesBd.QUERYDINAMICODOCLIKE+" '%"+objDocumentoDTO.getDoc_materia()+"%' ";
						}
				 }
			}
			
			
			 
			 if(objDocumentoDTO.getDoc_ano()>0 )
			 {
				 if(estadoQuery)
					{
						query += ConstantesBd.QUERYDINAMICADOCAND;
						query += ConstantesBd.DOC_ANO;
						query += "=" + objDocumentoDTO.getDoc_ano();
						
					}
					else
					{
						estadoQuery = true;
						query += ConstantesBd.QUERYDINAMICADOCWHERE;
						query += ConstantesBd.DOC_ANO;
						query += "=" + objDocumentoDTO.getDoc_ano();
					}
			 }
			 
			 if(objDocumentoDTO.getDes_id()>0)
			 {
				 if(estadoQuery)
					{
						query += ConstantesBd.QUERYDINAMICADOCAND;
						query += ConstantesBd.DOC_DES_ID;
						query += "=" + objDocumentoDTO.getDes_id();
						
					}
					else
					{
						estadoQuery = true;
						query += ConstantesBd.QUERYDINAMICADOCWHERE;
						query += ConstantesBd.DOC_DES_ID;
						query += "=" + objDocumentoDTO.getDes_id();
					}
			 }
			 
			 if(objDocumentoDTO.getDesc_id()>0)
			 {
				 if(estadoQuery)
					{
						query += ConstantesBd.QUERYDINAMICADOCAND;
						query += ConstantesBd.DOC_DESC_ID;
						query += "=" + objDocumentoDTO.getDesc_id();
						
					}
					else
					{
						estadoQuery = true;
						query += ConstantesBd.QUERYDINAMICADOCWHERE;
						query += ConstantesBd.DOC_DESC_ID;
						query += "=" + objDocumentoDTO.getDesc_id();
					}
			 }
			 
			 if(objDocumentoDTO.getTpo_id()>0)
			 {
				 if(estadoQuery)
					{
						query += ConstantesBd.QUERYDINAMICADOCAND;
						query += ConstantesBd.DOC_TPO_ID;
						query += "=" + objDocumentoDTO.getTpo_id();
						
					}
					else
					{
						estadoQuery = true;
						query += ConstantesBd.QUERYDINAMICADOCWHERE;
						query += ConstantesBd.DOC_TPO_ID;
						query += "=" + objDocumentoDTO.getTpo_id();
					}
			 }
			 if(fechaDesde!= null && fechaHasta != null)
			 {
				 
				 if(estadoQuery)
					{
						query += ConstantesBd.QUERYDINAMICADOCAND;
						query += ConstantesBd.DOC_FECHA_INGRESO;
						query += " between '" + formatter.format(fechaDesde) +"' and '"+ formatter.format(fechaHasta)+"'" ;
						//query += "=" + objDocumentoDTO.getDoc_ano();
						
					}
					else
					{
						estadoQuery = true;
						query += ConstantesBd.QUERYDINAMICADOCWHERE;
						query += ConstantesBd.DOC_FECHA_INGRESO;
						query += " between '" + formatter.format(fechaDesde) +"' and '"+ formatter.format(fechaHasta)+"'" ;
					}
			 }
			 else
			 {
				 if(fechaDesde != null)
				 {
					 if(estadoQuery)
						{
							query += ConstantesBd.QUERYDINAMICADOCAND;
							query += ConstantesBd.DOC_FECHA_INGRESO;
							query += "= '" + formatter.format(fechaDesde)+"'";
							
						}
						else
						{
							estadoQuery = true;
							query += ConstantesBd.QUERYDINAMICADOCWHERE;
							query += ConstantesBd.DOC_FECHA_INGRESO;
							query += "= '" + formatter.format(fechaDesde)+"'";
						}
				 }
				 
				 if(fechaHasta != null)
				 {
					 if(estadoQuery)
						{
							query += ConstantesBd.QUERYDINAMICADOCAND;
							query += ConstantesBd.DOC_FECHA_INGRESO;
							query += "= '"  + formatter.format(fechaHasta)+"'";
							
						}
						else
						{
							estadoQuery = true;
							query += ConstantesBd.QUERYDINAMICADOCWHERE;
							query += ConstantesBd.DOC_FECHA_INGRESO;
							query += "= '"  + formatter.format(fechaHasta)+"'";
						}
				 }
				 
				
			 }
			 if(estadoQuery)
			 {
					query += ConstantesBd.QUERYDINAMICADOCAND;
					query += ConstantesBd.QUERYDINAMICADOCDOS;
			 }
			 else
			 {
					estadoQuery = true;
					query += ConstantesBd.QUERYDINAMICADOCWHERE;
					query += ConstantesBd.QUERYDINAMICADOCDOS;
			 }
			 
			
			 estadoQuery = false;			
			 return query;
		}
		catch(Exception error)
		{
//			this.registroLog(new LogDTO(usrName, SistemaServiceImpl.class.getSimpleName()+"[BusquedaServiceimplement]", LevelEnum.ERR.getId(), error.getMessage()));
			error.getMessage();
			return null;
		}
		
	}

	
	
	public String armarQueryUsuario(DocumentoDTO objDocumentoDTO,Date fechaDesde, Date fechaHasta)
	{
		try
		{
			query = null;
			query = ConstantesBd.QUERYDINAMICADOCUNO;
			if(objDocumentoDTO.getDoc_abogado() != null)
			{
				if(objDocumentoDTO.getDoc_abogado().trim().length()>0)
				{
						if(estadoQuery)
						{
							query += ConstantesBd.QUERYDINAMICADOCAND;
							query += ConstantesBd.DOC_ABOGADO;
							query += ConstantesBd.QUERYDINAMICODOCLIKE+" '%"+objDocumentoDTO.getDoc_abogado()+"%' ";
							
						}
						else
						{
							estadoQuery = true;
							query += ConstantesBd.QUERYDINAMICADOCWHERE;
							query += ConstantesBd.DOC_ABOGADO;
							query += ConstantesBd.QUERYDINAMICODOCLIKE+" '%"+objDocumentoDTO.getDoc_abogado()+"%' ";
						}
				}
			}
			if(objDocumentoDTO.getDoc_nombre_archivo() != null)
			{
				 if(objDocumentoDTO.getDoc_nombre_archivo().trim().length()>0)
				 {
					 if(estadoQuery)
						{
							query += ConstantesBd.QUERYDINAMICADOCAND;
							query += ConstantesBd.DOC_NOMBRE_ARCHIVO;
							query += ConstantesBd.QUERYDINAMICODOCLIKE+" '%"+objDocumentoDTO.getDoc_nombre_archivo()+"%' ";
							
						}
						else
						{
							estadoQuery = true;
							query += ConstantesBd.QUERYDINAMICADOCWHERE;
							query += ConstantesBd.DOC_NOMBRE_ARCHIVO;
							query += ConstantesBd.QUERYDINAMICODOCLIKE+" '%"+objDocumentoDTO.getDoc_nombre_archivo()+"%' ";
						}
				 }
			}
			
			if(objDocumentoDTO.getDoc_resumen() != null)
			{
				 if(objDocumentoDTO.getDoc_resumen().trim().length()>0)
				 {
					 if(estadoQuery)
						{
							query += ConstantesBd.QUERYDINAMICADOCAND;
							query += ConstantesBd.DOC_RESUMEN;
							query += ConstantesBd.QUERYDINAMICODOCLIKE+" '%"+objDocumentoDTO.getDoc_resumen()+" %'";
							
						}
						else
						{
							estadoQuery = true;
							query += ConstantesBd.QUERYDINAMICADOCWHERE;
							query += ConstantesBd.DOC_RESUMEN;
							query += ConstantesBd.QUERYDINAMICODOCLIKE+" '%"+objDocumentoDTO.getDoc_resumen()+"%' ";
						}
				 }
			}
			
			if(objDocumentoDTO.getDoc_numero() != null)
			{
				if(objDocumentoDTO.getDoc_numero().trim().length()>0 )
				 {
					 if(estadoQuery)
						{
							query += ConstantesBd.QUERYDINAMICADOCAND;
							query += ConstantesBd.DOC_NUMERO;
							query += ConstantesBd.QUERYDINAMICODOCLIKE+" '%"+objDocumentoDTO.getDoc_numero()+"%' ";
							
						}
						else
						{
							estadoQuery = true;
							query += ConstantesBd.QUERYDINAMICADOCWHERE;
							query += ConstantesBd.DOC_NUMERO;
							query += ConstantesBd.QUERYDINAMICODOCLIKE+" '%"+objDocumentoDTO.getDoc_numero()+"%' ";
						}
				 }
			}
			
			if(objDocumentoDTO.getDoc_materia() != null)
			{
				if(objDocumentoDTO.getDoc_materia().trim().length()>0 )
				 {
					 if(estadoQuery)
						{
							query += ConstantesBd.QUERYDINAMICADOCAND;
							query += ConstantesBd.DOC_MATERIA;
							query += ConstantesBd.QUERYDINAMICODOCLIKE+" '%"+objDocumentoDTO.getDoc_materia()+"%' ";
							
						}
						else
						{
							estadoQuery = true;
							query += ConstantesBd.QUERYDINAMICADOCWHERE;
							query += ConstantesBd.DOC_MATERIA;
							query += ConstantesBd.QUERYDINAMICODOCLIKE+" '%"+objDocumentoDTO.getDoc_materia()+"%' ";
						}
				 }
			}
			
			
			 
			 if(objDocumentoDTO.getDoc_ano()>0 )
			 {
				 if(estadoQuery)
					{
						query += ConstantesBd.QUERYDINAMICADOCAND;
						query += ConstantesBd.DOC_ANO;
						query += "=" + objDocumentoDTO.getDoc_ano();
						
					}
					else
					{
						estadoQuery = true;
						query += ConstantesBd.QUERYDINAMICADOCWHERE;
						query += ConstantesBd.DOC_ANO;
						query += "=" + objDocumentoDTO.getDoc_ano();
					}
			 }
			 
			 if(objDocumentoDTO.getDes_id()>0)
			 {
				 if(estadoQuery)
					{
						query += ConstantesBd.QUERYDINAMICADOCAND;
						query += ConstantesBd.DOC_DES_ID;
						query += "=" + objDocumentoDTO.getDes_id();
						
					}
					else
					{
						estadoQuery = true;
						query += ConstantesBd.QUERYDINAMICADOCWHERE;
						query += ConstantesBd.DOC_DES_ID;
						query += "=" + objDocumentoDTO.getDes_id();
					}
			 }
			 
			 if(objDocumentoDTO.getDesc_id()>0)
			 {
				 if(estadoQuery)
					{
						query += ConstantesBd.QUERYDINAMICADOCAND;
						query += ConstantesBd.DOC_DESC_ID;
						query += "=" + objDocumentoDTO.getDesc_id();
						
					}
					else
					{
						estadoQuery = true;
						query += ConstantesBd.QUERYDINAMICADOCWHERE;
						query += ConstantesBd.DOC_DESC_ID;
						query += "=" + objDocumentoDTO.getDesc_id();
					}
			 }
			 
			 if(objDocumentoDTO.getTpo_id()>0)
			 {
				 if(estadoQuery)
					{
						query += ConstantesBd.QUERYDINAMICADOCAND;
						query += ConstantesBd.DOC_TPO_ID;
						query += "=" + objDocumentoDTO.getTpo_id();
						
					}
					else
					{
						estadoQuery = true;
						query += ConstantesBd.QUERYDINAMICADOCWHERE;
						query += ConstantesBd.DOC_TPO_ID;
						query += "=" + objDocumentoDTO.getTpo_id();
					}
			 }
			 if(fechaDesde!= null && fechaHasta != null)
			 {
				 
				 if(estadoQuery)
					{
						query += ConstantesBd.QUERYDINAMICADOCAND;
						query += ConstantesBd.DOC_FECHA_INGRESO;
						query += " between '" + formatter.format(fechaDesde) +"' and '"+ formatter.format(fechaHasta)+"'" ;
						//query += "=" + objDocumentoDTO.getDoc_ano();
						
					}
					else
					{
						estadoQuery = true;
						query += ConstantesBd.QUERYDINAMICADOCWHERE;
						query += ConstantesBd.DOC_FECHA_INGRESO;
						query += " between '" + formatter.format(fechaDesde) +"' and '"+ formatter.format(fechaHasta)+"'" ;
					}
			 }
			 else
			 {
				 if(fechaDesde != null)
				 {
					 if(estadoQuery)
						{
							query += ConstantesBd.QUERYDINAMICADOCAND;
							query += ConstantesBd.DOC_FECHA_INGRESO;
							query += "= '" + formatter.format(fechaDesde)+"'";
							
						}
						else
						{
							estadoQuery = true;
							query += ConstantesBd.QUERYDINAMICADOCWHERE;
							query += ConstantesBd.DOC_FECHA_INGRESO;
							query += "= '" + formatter.format(fechaDesde)+"'";
						}
				 }
				 
				 if(fechaHasta != null)
				 {
					 if(estadoQuery)
						{
							query += ConstantesBd.QUERYDINAMICADOCAND;
							query += ConstantesBd.DOC_FECHA_INGRESO;
							query += "= '"  + formatter.format(fechaHasta)+"'";
							
						}
						else
						{
							estadoQuery = true;
							query += ConstantesBd.QUERYDINAMICADOCWHERE;
							query += ConstantesBd.DOC_FECHA_INGRESO;
							query += "= '"  + formatter.format(fechaHasta)+"'";
						}
				 }
				 
				
			 }
			 if(estadoQuery)
			 {
					query += ConstantesBd.QUERYDINAMICADOCAND;
					query += ConstantesBd.QUERYDINAMICADOCDOS;
					query += ConstantesBd.QUERYDINAMICADOCESTADO;
			 }
			 else
			 {
					estadoQuery = true;
					query += ConstantesBd.QUERYDINAMICADOCWHERE;
					query += ConstantesBd.QUERYDINAMICADOCDOS;
					query += ConstantesBd.QUERYDINAMICADOCESTADO;
			 }
			 
			
			 estadoQuery = false;			
			 return query;
		}
		catch(Exception error)
		{
//			this.registroLog(new LogDTO(usrName, SistemaServiceImpl.class.getSimpleName()+"[BusquedaServiceimplement]", LevelEnum.ERR.getId(), error.getMessage()));
			error.getMessage();
			return null;
		}
		
	}
	
	@Override
	public byte[] getDocumento(String id_alfreso) {
		// TODO Auto-generated method stub
		byte[] decoded=null;
		try
		{
			ObtenerAlfrescoClient cliente=new ObtenerAlfrescoClient(integrationProperties);
			
			ObtenerDocumentoRequest rq= new ObtenerDocumentoRequest();
			rq.setId(id_alfreso);
			
			ObtenerDocumentoResponse response=new ObtenerDocumentoResponse();
			response=cliente.executeBuscar(rq);
			decoded=response.getDocumento().getData();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//byte[] decoded = org.apache.commons.codec.binary.Base64.decodeBase64();
		return decoded;
	}

}
