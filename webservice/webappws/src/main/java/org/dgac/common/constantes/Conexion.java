package org.dgac.common.constantes;

import java.io.Serializable;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Conexion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection conexion = null;
	private DataSource dataSource = null;
	
	
	public Connection conectar_sigerh() throws Exception
	{
			Context context = new InitialContext();
	    	dataSource = (DataSource)context.lookup("java:jboss/ds_ora_sigerh");
	    	conexion = dataSource.getConnection();
		return conexion;
	}	
	
	public Connection conectar_sigerhP() throws Exception
	{
			Context context = new InitialContext();
	    	dataSource = (DataSource)context.lookup("java:jboss/ds_ora_sigerh");
	    	conexion = dataSource.getConnection();
		return conexion;
	}	
	
	public Connection conectar_redoex() throws Exception
	{
			Context context = new InitialContext();
	    	dataSource = (DataSource)context.lookup("java:jboss/ds_pgs_redoex");
	    	conexion = dataSource.getConnection();
		return conexion;
	}	
	
	public Connection conectar_certificados() throws Exception
	{
			Context context = new InitialContext();
	    	dataSource = (DataSource)context.lookup("java:jboss/ds_pgs_certificados");
	    	conexion = dataSource.getConnection();
		return conexion;
	}	
	
	public boolean cerrarConexion() throws Exception
	{
		boolean res = false;
		try
		{
			conexion.close();		
			res = true;
		}
		catch(Exception EX)
		{
			System.out.println(EX);
		}
		return res;
	}
	
}
