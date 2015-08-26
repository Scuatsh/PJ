package org.dgac.app.web.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {    
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        LoginBean loginBean = (LoginBean) req.getSession().getAttribute("loginBean");
//        if (loginBean != null && loginBean.isLoggedIn()) {
            // User is logged in, so just continue request.
            if (!"/common.jsf".equals(req.getServletPath())) {
                res.sendRedirect(req.getContextPath() + "/sinAcceso.jsf");
            }
            res.addHeader("X-UA-Compatible", "IE=EmulateIE8");
            chain.doFilter(request, response);
//        } else {
//            // User is not logged in, so redirect to index.
//            res.sendRedirect(req.getContextPath() + "/login.jsf");
//        }
        	
    }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}