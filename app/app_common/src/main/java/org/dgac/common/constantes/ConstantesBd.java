/*
 * 
 */
package org.dgac.common.constantes;

// TODO: Auto-generated Javadoc
/**
 * The Class ConstantesBd.
 */
public class ConstantesBd {



	/*
	 * Consulta tabla rol
	 */

	/** The Constant SELECCIONAR_ROL. */
	public static final String SELECCIONAR_ROL = "SELECT rol_id, "+
			" rol_nombre, "+
			" rol_prioridad, "+
			" rol.rol_estado "+
			" FROM   rol "+
			" WHERE  rol.rol_estado=1";
	
	/*INSERT LOG*/

	/** The Constant INSERT_LOG. */
	public static final String INSERT_LOG="insert into log(log_usr,log_date,log_location,log_level,log_message)"+
			"values(?,?,?,?,?)";

	
	
	/*TIPO DE DOCUMENTO*/
	
	public static final String INSERT_TPO_DOCUMENTO="insert into tipo_documento(tpo_nombre_small,tpo_nombre_large,tpo_estado) values(?,?,?)";
	public static final String DELETE_TPO_DOCUMENTO="update tipo_documento set tpo_estado=0 where tpo_id=?";
	public static final String UPDATE_TPO_DOCUMENTO="update tipo_documento set tpo_nombre_small=?,tpo_nombre_large=?,tpo_estado=? where tpo_id=?";
	public static final String SELECT_TPO_DOCUMENTO="select tpo_id,tpo_nombre_small,tpo_nombre_large,tpo_estado from tipo_documento  order by tpo_nombre_small asc";
	public static final String SELECTDEPIDTIPDOC = "select count(*) as contador from documento WHERE tpo_id = ?";
	
	/*DESCRIPTOR*/
	
	public static final String INSERT_DESCRIPTOR="insert into descriptor(desc_nombre_small,desc_nombre_large,desc_estado) values(?,?,?)";
	public static final String DELETE_DESCRIPTOR="update descriptor set desc_estado=0 where desc_id=?";
	public static final String UPDATE_DESCRIPTOR="update descriptor set desc_nombre_small=?,desc_nombre_large=?,desc_estado=? where desc_id=?";
	public static final String SELECT_DESCRIPTOR="select desc_id,desc_nombre_small,desc_nombre_large,desc_estado from descriptor  order by desc_nombre_small asc";
	public static final String SELECTDEPIDDESCR = "select count(*) as contador from documento WHERE desc_id = ?";
	
	/*DESTINATARIO*/
	
	public static final String INSERT_DESTINATARIO="insert into destinatario(des_nombre_small,des_nombre_large,des_estado) values(?,?,?)";
	public static final String DELETE_DESTINATARIO="update destinatario set des_estado=0 where des_id=?";
	public static final String UPDATE_DESTINATARIO="update destinatario set des_nombre_small=?,des_nombre_large=?,des_estado=? where des_id=?";
	public static final String SELECT_DESTINATARIO="select des_id,des_nombre_small,des_nombre_large,des_estado from destinatario  order by des_nombre_small asc";
	public static final String SELECT_DESTINATARIO_BYID="select des_id,des_nombre_small,des_nombre_large,des_estado from destinatario where des_id=?";
	public static final String SELECTDEPIDDESTINA = "select count(*) as contador from documento WHERE des_id = ?";

	/*DOCUMENTO*/
	
	public static final String INSERT_DOCUMENTO="insert into documento("+
												"doc_ano,"+
												"des_id,"+
												"desc_id,"+
												"doc_materia,"+
												"doc_fecha_ingreso,"+
												"doc_numero,"+
												"tpo_id,"+
												"doc_resumen,"+
												"doc_abogado,"+
												"doc_nombre_archivo,"+
												"doc_id_alfresco,"+
												"doc_usr_insert,"+
												"doc_usr_mod,"+
												"doc_fecha_mod,"+
												"doc_estado) "+
												" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String VALIDAR_DOCUMENTO="select doc_id from documento where doc_nombre_archivo=?";
	public static final String FECHA_INGRESO="select doc_fecha_ingreso from documento where doc_id=?";

	
	public static final String UPDATE_DOCUMENTO="update documento "+
			"set doc_ano=?,"+
			" des_id=?,"+
			" desc_id=?,"+
			" doc_materia=?,"+
			" doc_fecha_ingreso=?,"+
			" doc_numero=?,"+
			" tpo_id=?,"+
			" doc_resumen=?,"+
			" doc_abogado=?,"+
			" doc_nombre_archivo=?,"+
			" doc_id_alfresco=?,"+
			" doc_usr_insert=?,"+
			" doc_usr_mod=?,"+
			" doc_fecha_mod=?,"+
			" doc_estado=? "+
			" where doc_id=?";
	
	public static final String DELETE_DOCUMENTO="update documento "+
			"set doc_estado=0 "+
			" where doc_id=?";
	
	public static final String PUBLICAR_DOCUMENTO="update documento "+
			"set doc_estado=1 "+
			" where doc_id=?";
	
	
	public static final String SELECT_DOCUMENTO_USR="select "+
			"documento.doc_id,"+
			"documento.doc_ano,"+
			"documento.des_id,"+
			"documento.desc_id,"+
			"documento.doc_materia,"+
			"documento.doc_fecha_ingreso,"+
			"documento.doc_numero,"+
			"documento.tpo_id,"+
			"documento.doc_resumen,"+
			"documento.doc_abogado,"+
			"documento.doc_nombre_archivo,"+
			"documento.doc_id_alfresco,"+
			"documento.doc_usr_insert,"+
			"documento.doc_usr_mod,"+
			"documento.doc_fecha_mod,"+
			"documento.doc_estado, "+
			"destinatario.des_nombre_small, "+
			"descriptor.desc_nombre_small, "+
			"tipo_documento.tpo_nombre_small "+
			" from documento,destinatario,descriptor,tipo_documento "+
			" where doc_estado=1 and documento.des_id=destinatario.des_id and documento.desc_id=descriptor.desc_id and documento.tpo_id=tipo_documento.tpo_id";
	
	public static final String SELECT_DOCUMENTO_ADMIN="select "+
			"documento.doc_id,"+
			"documento.doc_ano,"+
			"documento.des_id,"+
			"documento.desc_id,"+
			"documento.doc_materia,"+
			"documento.doc_fecha_ingreso,"+
			"documento.doc_numero,"+
			"documento.tpo_id,"+
			"documento.doc_resumen,"+
			"documento.doc_abogado,"+
			"documento.doc_nombre_archivo,"+
			"documento.doc_id_alfresco,"+
			"documento.doc_usr_insert,"+
			"documento.doc_usr_mod,"+
			"documento.doc_fecha_mod,"+
			"documento.doc_estado, "+
			"destinatario.des_nombre_small, "+
			"descriptor.desc_nombre_small, "+
			"tipo_documento.tpo_nombre_small "+
			" from documento,destinatario,descriptor,tipo_documento "+
			" where documento.des_id=destinatario.des_id and documento.desc_id=descriptor.desc_id and documento.tpo_id=tipo_documento.tpo_id";
	
	
	/*
	 * Constantes para la tabla rol
	 */


	/** The Constant ROL_ID. */
	public static final String ROL_ID = "rol_id";

	/** The Constant ROL_NOMBRE. */
	public static final String ROL_NOMBRE = "rol_nombre";

	/** The Constant ROL_PRIORIDAD. */
	public static final String ROL_PRIORIDAD = "rol_prioridad";

	/** The Constant ROL_ADMIN. */
	public static final String ROL_ADMIN = "rol_admin";

	/** The Constant ROL_SUPER_ADMIN. */
	public static final String ROL_SUPER_ADMIN = "rol_super_admin";
	public static final String ROL_ESTADO = "rol_estado";
	
	
	/*
	 * Constantes para la tabla tipo documento
	 */
	
	public static String TPO_ID="tpo_id";
	public static String TPO_NOMBRE_SMALL="tpo_nombre_small";
	public static String TPO_NOMBRE_LARGE="tpo_nombre_large";
	public static String TPO_ESTADO="tpo_estado";
	
	
	/*
	 * Constantes para la tabla descriptor
	 */
	
	public static String DESC_ID="desc_id";
	public static String DESC_NOMBRE_SMALL="desc_nombre_small";
	public static String DESC_NOMBRE_LARGE="desc_nombre_large";
	public static String DESC_ESTADO="desc_estado";
	
	
	
	/*
	 * Constantes para la tabla destinatario
	 */
	
	public static String DES_ID="des_id";
	public static String DES_NOMBRE_SMALL="des_nombre_small";
	public static String DES_NOMBRE_LARGE="des_nombre_large";
	public static String DES_ESTADO="des_estado";

	
	
	/*
	 * Constantes para la tabla documento
	 */
	public static final String DOC_ID="documento.doc_id";
	public static final String DOC_ANO="documento.doc_ano";
	public static final String DOC_DES_ID="destinatario.des_id";
	public static final String DOC_DESC_ID="descriptor.desc_id";
	public static final String DOC_MATERIA="documento.doc_materia";
	public static final String DOC_FECHA_INGRESO="documento.doc_fecha_ingreso";
	public static final String DOC_NUMERO="documento.doc_numero";
	public static final String DOC_TPO_ID="tipo_documento.tpo_id";
	public static final String DOC_RESUMEN="documento.doc_resumen";
	public static final String DOC_ABOGADO="documento.doc_abogado";
	public static final String DOC_NOMBRE_ARCHIVO="documento.doc_nombre_archivo";
	public static final String DOC_ID_ALFRESCO="documento.doc_id_alfresco";
	public static final String DOC_USR_INSERT="documento.doc_usr_insert";
	public static final String DOC_USR_MOD="documento.doc_usr_mod";
	public static final String DOC_FECHA_MOD="documento.doc_fecha_mod";
	public static final String DOC_ESTADO="documento.doc_estado";
	
	public static final String QUERYDINAMICADOCUNO = " SELECT 	documento.doc_id, 	documento.doc_ano, 	documento.des_id, 	documento.desc_id, 	documento.doc_materia, 	documento.doc_fecha_ingreso, 	documento.doc_numero, 	documento.tpo_id, 	documento.doc_resumen, 	documento.doc_abogado, 	documento.doc_nombre_archivo, 	documento.doc_id_alfresco, 	documento.doc_usr_insert, 	documento.doc_usr_mod, 	documento.doc_fecha_mod, 	documento.doc_estado , destinatario.des_nombre_small , descriptor.desc_nombre_small, tipo_documento.tpo_nombre_small FROM 	documento,destinatario,descriptor,tipo_documento ";
	public static final String QUERYDINAMICADOCDOS =" documento.des_id=destinatario.des_id and documento.desc_id=descriptor.desc_id and documento.tpo_id=tipo_documento.tpo_id ";
	public static final String QUERYDINAMICADOCESTADO=" AND documento.doc_estado=1 ";
	public static final String QUERYDINAMICADOCWHERE = " where ";
	public static  final String QUERYDINAMICADOCAND = " and ";
	public static final String QUERYDINAMICODOCLIKE = " like ";
	public static final String QUERYDINAMICODONOMTABL ="documento";
	public static final String CONTADOR = "contador";

}


