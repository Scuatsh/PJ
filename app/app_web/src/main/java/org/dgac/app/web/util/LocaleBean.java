package org.dgac.app.web.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@ManagedBean
@SessionScoped
public class LocaleBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Locale locale;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale localeParam) {
        this.locale = localeParam;
    }

    public SelectItem[] getLocales() {
        List<SelectItem> items = new ArrayList<SelectItem>();
        Iterator<Locale> supportedLocales = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
        while (supportedLocales.hasNext()) {
            Locale supportedLocale = supportedLocales.next();
            items.add(new SelectItem(supportedLocale.toString(), supportedLocale.getDisplayName()));
        }
        return items.toArray(new SelectItem[] {});
    }

    public String getSelectedLocale() {
        return getLocale().toString();
    }

    public void setSelectedLocale() {
        setSelectedLocale(FacesContext.getCurrentInstance()
        		.getExternalContext().getRequestParameterMap().get("locale"));
    }
    
   public void setSelectedLocale(String localeString) {
        Iterator<Locale> supportedLocales = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
        while (supportedLocales.hasNext()) {
            Locale supportedLocale = supportedLocales.next();
            if (supportedLocale.toString().equals(localeString)) {
                this.locale = supportedLocale;
                break;
            }
        }
    }
}