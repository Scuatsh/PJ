package org.dgac.app.web.bean;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


// TODO: Auto-generated Javadoc
/**
 * The Class BeanGenerico.
 */
public class BeanGenerico implements Serializable {

	/** The Constant LOGGER. */
	private static final Log LOGGER = LogFactory.getLog( BeanGenerico.class );

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


	
	/** The bundle. */
	protected ResourceBundle bundle = FacesContext.getCurrentInstance()
			.getApplication()
			.getResourceBundle(FacesContext.getCurrentInstance(), "messages");

}
