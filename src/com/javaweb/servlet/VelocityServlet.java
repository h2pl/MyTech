package com.javaweb.servlet;

/**
 * Created by 周杰伦 on 2018/3/31.
 */
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;


@WebServlet(name = "VelocityServlet", urlPatterns = {"/hello"})
public class VelocityServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Properties properties=new Properties();
        properties.setProperty("resource.loader", "webapp");
        properties.setProperty("webapp.resource.loader.class", "org.apache.velocity.tools.view.servlet.WebappLoader");
        properties.setProperty("webapp.resource.loader.path", "/WEB-INF/template");
        properties.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
        properties.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        VelocityEngine velocityEngine = new VelocityEngine(properties);
        velocityEngine.setApplicationAttribute("javax.servlet.ServletContext", request.getServletContext());

        VelocityContext context=new VelocityContext();
        context.put("name", "user01");
        StringWriter sw = new StringWriter();
        velocityEngine.mergeTemplate("hello.vm", "utf-8", context, sw);
//        velocityEngine.mergeTemplate("hello.vm", "utf-8", context, sw);      //如果这行不注释，hello.vm的内容会出现两次
        out.println(sw.toString());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
