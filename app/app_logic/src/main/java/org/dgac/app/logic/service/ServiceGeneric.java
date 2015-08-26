package org.dgac.app.logic.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceGeneric.
 */
public class ServiceGeneric {

	/** The Constant LOGGER. */
	private static final Log LOGGER = LogFactory.getLog(ServiceGeneric.class);	

	private DataSource ds;

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 * @throws SQLException the SQL exception
	 */
	public Connection getConnection () throws SQLException {
		try
		{
			Context ctx = new InitialContext();
		    ds = (DataSource)ctx.lookup("java:jboss/ds/portal");
		}
		catch(Exception e)
		{
			LOGGER.error("Error al recuperar el DataSource",e);
		}
		return ds.getConnection(); 
	}

	/**
	 * Close connection.
	 *
	 * @param conn the conn
	 */
	public void closeConnection (Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			LOGGER.error("Ocurrio un error al cerrar la Connection ", e);
		}
	}
	
	/**
	 * Close callable statement.
	 *
	 * @param cableStatement the cable statement
	 */
	public void closeCallableStatement (CallableStatement cableStatement) {
		try {
			if (cableStatement != null)
				cableStatement.close();
		} catch (SQLException e) {
			LOGGER.error("Ocurrio un error al cerrar el Cable Statement ", e);
		}
	}
	
	
	/**
	 * Close  statement.
	 *
	 * @param cableStatement the cable statement
	 */
	public void closeStatement (Statement statement) {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			LOGGER.error("Ocurrio un error al cerrar el Statement ", e);
		}
	}
	
	/**
	 * Close  Preparedstatement.
	 *
	 * @param cableStatement the cable statement
	 */
	public void closePreparedStatement (PreparedStatement statement) {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			LOGGER.error("Ocurrio un error al cerrar el PreparedStatement ", e);
		}
	}

	/**
	 * Close result set.
	 *
	 * @param resultSet the result set
	 */
	public void closeResultSet (ResultSet resultSet) {
		try {
			if (resultSet != null)
				resultSet.close();
		} catch (SQLException e) {
			LOGGER.error("Ocurrio un error al cerrar el ResultSet ", e);
		}
	}
	
}
