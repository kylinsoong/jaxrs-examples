package org.teiid.jboss.rest;

import java.io.IOException;

import io.swagger.jaxrs.config.BeanConfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BootstrapServlet extends HttpServlet {

    private static final long serialVersionUID = 5704762873796188429L;
    
    @Override
    public void init(ServletConfig paramServletConfig) throws ServletException {
      super.init(paramServletConfig);
      BeanConfig localBeanConfig = new BeanConfig();
      localBeanConfig.setTitle("sample");
      localBeanConfig.setDescription("sample");
      localBeanConfig.setVersion("1.0");
      localBeanConfig.setSchemes(new String[] { "http", "https"});
      localBeanConfig.setBasePath("/customer");
      localBeanConfig.setResourcePackage("org.teiid.jboss.rest");
      localBeanConfig.setScan(true);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)throws ServletException, IOException {
        String str1 = paramHttpServletRequest.getContextPath();
        String str2 = paramHttpServletRequest.getScheme() + "://" + paramHttpServletRequest.getServerName() + ":" + paramHttpServletRequest.getServerPort() + str1 + "/";
        String str3 = str1 + "/api.html";
        String str4 = "rest/swagger.json";
        String str5 = "/url=" + str2 + str4;
        String str6 = str3 + "?" + str5;
        paramHttpServletResponse.sendRedirect(str6);
    }
    

}
