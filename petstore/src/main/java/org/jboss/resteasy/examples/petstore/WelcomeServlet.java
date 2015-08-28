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
        String path = req.getContextPath();
        String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/";
        String base = path + "/api.html";
        String swagger = "rest/swagger.json";
        String param = "/url=" + basePath + swagger;
        String location = base + "?" + param;
        resp.sendRedirect(location);
    }

}
