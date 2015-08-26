package org.dgac.common.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceLocator.
 */
public class ServiceLocator {

	/** The Constant LOGGER. */
	private static final Log LOGGER = LogFactory.getLog( ServiceLocator.class );

	/** The inital context. */
	private Context initalContext;
	
	/** The cache. */
	private Map<String, Object> cache;

	/** The _instance. */
	private static ServiceLocator _instance = null;

	/**
	 * Gets the single instance of ServiceLocator.
	 *
	 * @return single instance of ServiceLocator
	 */
	public static ServiceLocator getInstance() {
		if (_instance == null)
			_instance = new ServiceLocator();
		return _instance;
	}

	/**
	 * Instantiates a new service locator.
	 */
	private ServiceLocator() {
		try {
			this.initalContext = new InitialContext();
			this.cache = Collections
					.synchronizedMap(new HashMap<String, Object>());
		} catch (NamingException ex) {
			LOGGER.error("NamingException en ServiceLocator ", ex);
		}
	}

	/**
	 * Gets the ejb.
	 *
	 * @param clase the clase
	 * @return the ejb
	 */
	@SuppressWarnings("rawtypes")
	public Object getEjb(Class clase) {
		String jndiName=clase.getCanonicalName();
		jndiName = jndiName.replace("Local","").replace(jndiName.substring(0,jndiName.lastIndexOf("."))+".", "")+"!"+clase.getCanonicalName();
		jndiName="ejb:/app_web/"+jndiName;
		Object ejb = null;
		try {
			if (this.cache.containsKey(jndiName))
				ejb = this.cache.get(jndiName);
			else {
				ejb = initalContext.lookup(jndiName);
				this.cache.put(jndiName, ejb);
			}
		}
		catch (NamingException ex)
		{
			LOGGER.error("NamingException en ServiceLocator ", ex);
		}
		return ejb;
	}
}
