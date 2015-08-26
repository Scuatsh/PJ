package org.dgac.app.web.security;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dgac.app.web.bean.BeanGenerico;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean extends BeanGenerico implements Serializable {

	private static final Log LOGGER = LogFactory.getLog(LoginBean.class);
	private static final long serialVersionUID = 1L;
	private String urlPage = null;	
	private String toViewId;
	private String toViewMenu;

	@PostConstruct
	public void init() {	

	}


	@PreDestroy
	public void destroy() {
	}

	public void logout() {
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if(session!=null){
			session.invalidate();
		}
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");	
		} catch (IOException e) {

		}
	}

	public void navigateTo(String toViewId) {

		this.setToViewId(toViewId);
		Object object = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("breadcrumbs");
		if (object != null) {
			@SuppressWarnings("unchecked")
			List<String> breadCrumbs = (ArrayList<String>)object;
			breadCrumbs.add(toViewId);
		} else {
			navigateFirst(toViewId);
		}
	}

	/**
	 * 
	 * @param toViewId
	 */
	public void navigateFirst(String toViewId) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("breadcrumbs", new ArrayList<String>());
		this.navigateTo(toViewId);
	}

	/**
	 * 
	 * 
	 */
	public void navigateFirst() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("breadcrumbs", new ArrayList<String>());
		this.navigateTo(urlPage);
	}

	/**
	 * 
	 */
	public void backNavigate() {
		Object object = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("breadcrumbs");
		if (object != null) {
			@SuppressWarnings("unchecked")
			List<String> breadCrumbs = (ArrayList<String>)object;
			if (breadCrumbs.size() > 0)
				breadCrumbs.remove(breadCrumbs.size()-1);
			if (breadCrumbs.size() > 0)
				navigateTo(breadCrumbs.get(breadCrumbs.size()-1));
			else
				navigateTo("welcome.xhtml");
		}

	}

	/**
	 * 
	 * @param beanName
	 */
	public static void removeViewScopedBean(String beanName) {
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(beanName);
	}

	/**
	 * 
	 */
	public final void removeViewScope() {
		Map<String, Object> maps = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
		Iterator<Entry<String, Object>> it = maps.entrySet().iterator();
		while (it.hasNext())
		{
			it.next();
			it.remove();
		}
	}
	

	public String getToViewId() {

		return toViewId;
	}

	public void setToViewId(String toViewId) {
		this.toViewId = toViewId;
	}


	public String getToViewMenu() {
		return toViewMenu;
	}


	public void setToViewMenu(String toViewMenu) {
		this.toViewMenu = toViewMenu;
	}
}
