package com.JavaWeb.Servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * Created by 周杰伦 on 2018/6/6.
 */
public class Servlet实践 extends HttpServlet{
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        res.getWriter().write("abc");
        res.getWriter().flush();
    }
}
