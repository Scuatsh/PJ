/*
 * 
 */
package org.dgac.common.dto;

import java.io.Serializable;
import java.sql.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class LogDTO.
 */
public class LogDTO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The log_id. */
	private long log_id;
	
	/** The log usr. */
	private String logUsr;
	
	/** The log date. */
	private Date logDate;
	
	/** The log location. */
	private String logLocation;
	
	/** The log level. */
	private String logLevel;
	
	/** The log message. */
	private String logMessage;
	
	/**
	 * Instantiates a new log dto.
	 *
	 * @param logUsr the log usr
	 * @param logLocation the log location
	 * @param logLevel the log level
	 * @param logMessage the log message
	 */
	public LogDTO(String logUsr,String logLocation, String logLevel, String logMessage) {
		super();
		this.logUsr = logUsr;
		this.logLocation = logLocation;
		this.logLevel = logLevel;
		this.logMessage = logMessage;
	}
	
	/**
	 * Gets the log_id.
	 *
	 * @return the log_id
	 */
	public long getLog_id() {
		return log_id;
	}
	
	/**
	 * Sets the log_id.
	 *
	 * @param log_id the new log_id
	 */
	public void setLog_id(long log_id) {
		this.log_id = log_id;
	}
	
	/**
	 * Gets the log usr id.
	 *
	 * @return the log usr id
	 */
	public String getLogUsrId() {
		return logUsr;
	}
	
	/**
	 * Sets the log usr id.
	 *
	 * @param logUsrId the new log usr id
	 */
	public void setLogUsrId(String logUsrId) {
		this.logUsr = logUsrId;
	}
	
	/**
	 * Gets the log date.
	 *
	 * @return the log date
	 */
	public Date getLogDate() {
		return logDate;
	}
	
	/**
	 * Sets the log date.
	 *
	 * @param logDate the new log date
	 */
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	
	/**
	 * Gets the log location.
	 *
	 * @return the log location
	 */
	public String getLogLocation() {
		return logLocation;
	}
	
	/**
	 * Sets the log location.
	 *
	 * @param logLocation the new log location
	 */
	public void setLogLocation(String logLocation) {
		this.logLocation = logLocation;
	}
	
	/**
	 * Gets the log level.
	 *
	 * @return the log level
	 */
	public String getLogLevel() {
		return logLevel;
	}
	
	/**
	 * Sets the log level.
	 *
	 * @param logLevel the new log level
	 */
	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
	
	/**
	 * Gets the log message.
	 *
	 * @return the log message
	 */
	public String getLogMessage() {
		return logMessage;
	}
	
	/**
	 * Sets the log message.
	 *
	 * @param logMessage the new log message
	 */
	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}
	
}
