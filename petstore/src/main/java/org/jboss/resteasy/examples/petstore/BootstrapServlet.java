package org.jboss.resteasy.examples.petstore;

import io.swagger.jaxrs.config.BeanConfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class BootstrapServlet extends HttpServlet {
    
    private static final long serialVersionUID = -4207947168502472976L;
    
    static final String BASEURL = "/petstore/rest";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("Jaxrs Examples Petstore");
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setBasePath(BASEURL);
        beanConfig.setResourcePackage("org.jboss.resteasy.examples.petstore");
        beanConfig.setScan(true);
    }
    
    

}
