package com.javaweb.filter;

/**
 * Created by 周杰伦 on 2018/3/31.
 */
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于的使 Browser 不缓存页面的过滤器
 */
public class ForceNoCacheFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException
    {
        ((HttpServletResponse) response).setHeader("Cache-Control","no-cache");
        ((HttpServletResponse) response).setHeader("Pragma","no-cache");
        ((HttpServletResponse) response).setDateHeader ("Expires", -1);
        filterChain.doFilter(request, response);
    }

    public void destroy()
    {
    }

    public void init(FilterConfig filterConfig) throws ServletException
    {
    }
}
