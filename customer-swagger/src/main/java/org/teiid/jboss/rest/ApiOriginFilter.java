package org.teiid.jboss.rest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class ApiOriginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse,FilterChain paramFilterChain) throws IOException, ServletException {

        HttpServletResponse localHttpServletResponse = (HttpServletResponse)paramServletResponse;
        localHttpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        localHttpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        localHttpServletResponse.addHeader("Access-Control-Allow-Headers", "Origin, X-Atmosphere-tracking-id, X-Atmosphere-Framework, X-Cache-Date, Content-Type, X-Atmosphere-Transport, *");
        paramFilterChain.doFilter(paramServletRequest, paramServletResponse);
    }

    @Override
    public void destroy() {

    }

}
