package com.gahon.pac4j.cas.filter;


import com.gahon.pac4j.cas.util.ContextHolder;
import org.pac4j.core.context.JEEContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.profile.BasicUserProfile;
import org.pac4j.core.profile.ProfileManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author han
 */
public class CustomContextThreadLocalFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            WebContext context = new JEEContext(request, response);
            ProfileManager manager = new ProfileManager(context);
            Optional pcs = manager.get(true);
            if (pcs.isPresent()) {
                BasicUserProfile p = (BasicUserProfile) pcs.get();
                ContextHolder.setProfile(p);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            ContextHolder.clear();
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
