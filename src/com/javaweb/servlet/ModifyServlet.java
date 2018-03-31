package com.javaweb.servlet;

/**
 * Created by 周杰伦 on 2018/3/31.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;
import java.io.StringWriter;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.VelocityContext;


@WebServlet(name = "ModifyServlet", urlPatterns = {"/modify"})
public class ModifyServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Properties properties=new Properties();
        properties.load(getServletContext().getResourceAsStream("/WEB-INF/velocity.properties"));

        VelocityEngine velocityEngine = new VelocityEngine(properties);
        velocityEngine.setApplicationAttribute("javax.servlet.ServletContext", request.getServletContext());

        VelocityContext context=new VelocityContext();
        context.put("name", "user01");
        StringWriter sw = new StringWriter();
        velocityEngine.mergeTemplate("hello.vm", "utf-8", context, sw);

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