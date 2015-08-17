package org.jboss.resteasy.examples.petstore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeServlet extends HttpServlet {

    private static final long serialVersionUID = -4957997421431841280L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String location = buildLocation(req, resp);
        resp.sendRedirect(location);
    }

    private String buildLocation(HttpServletRequest request, HttpServletResponse resp) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
//        System.out.println(path);
//        System.out.println(basePath);
        String base = path + "/teiid.html";
        String restPrefix = "rest";
        String swagger = "/swagger.json";
        String param = "/url=" + basePath + restPrefix + swagger;
        return base + "?" + param;
    }

}
