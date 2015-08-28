package org.jboss.resteasy.examples.petstore;

import io.swagger.jaxrs.config.BeanConfig;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BootServlet extends HttpServlet {

    private static final long serialVersionUID = 203132504196289454L;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("Jaxrs Examples Petstore");
        beanConfig.setDescription("desc");
        beanConfig.setVersion("1.0.0");
        
        beanConfig.setSchemes(new String[]{"http", "https", "ajp"});
        
        beanConfig.setBasePath("/baseURL");
        beanConfig.setResourcePackage("org.jboss.resteasy.examples.petstore");
        beanConfig.setScan(true);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        
        String path = req.getContextPath();
        String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/";
        String base = path + "/api.html";
        String swagger = "/swagger.json";
        String param = "/url=" + basePath + swagger;
        String location = base + "?" + param;
        resp.sendRedirect(location);
    }

}
