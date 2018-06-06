package Servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by 周杰伦 on 2018/6/6.
 */
public class ServletConfig和ServletContext {

    public static void main(String[] args) {
        Servlet servlet = new Servlet() {
            @Override
            public void init(ServletConfig servletConfig) throws ServletException {

            }

            @Override
            public ServletConfig getServletConfig() {
                return null;
            }

            @Override
            public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

            }

            @Override
            public String getServletInfo() {
                return null;
            }

            @Override
            public void destroy() {

            }
        };

        //读取配置文件和servlet信息
        ServletConfig servletConfig = servlet.getServletConfig();
        servletConfig.getInitParameter("a");
        servletConfig.getServletName();

        //读取整个容器上下文。即context容器的上下文，可以添加监听器和过滤器，以及servlet，通过 ServletContext 可以拿到 Context 容器中一些必要信息，比如应用的工作路径，容器支持的 Servlet 最小版本等。
        ServletContext context = servlet.getServletConfig().getServletContext();
        context.addFilter("a","b");
        context.addListener(new ServletRequestListener() {

            @Override
            public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
                servletRequestEvent.getServletContext();
            }

            @Override
            public void requestInitialized(ServletRequestEvent servletRequestEvent) {
                servletRequestEvent.getSource();
            }
        });
        context.addServlet("a","b");
    }
//    public static void main(String[] args) {
//        Tomcat tomcat = getTomcatInstance();
//        File appDir = new File(getBuildDirectory(), "webapps/examples");
//        tomcat.addWebapp(null, "/examples", appDir.getAbsolutePath());
//        tomcat.start();
//        ByteChunk res = getUrl("http://localhost:" + getPort() +
//                "/examples/servlets/servlet/HelloWorldExample");
//        assertTrue(res.toString().indexOf("<h1>Hello World!</h1>") > 0);


}
