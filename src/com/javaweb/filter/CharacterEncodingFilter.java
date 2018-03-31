package com.javaweb.filter;

/**
 * Created by 周杰伦 on 2018/3/31.
 */
import javax.servlet.*;
import java.io.IOException;

/**
 * 用于设置 HTTP 请求字符编码的过滤器，通过过滤器参数encoding指明使用何种字符编码,用于处理Html Form请求参数的中文问题
 */
public class CharacterEncodingFilter
        implements Filter
{
    protected FilterConfig filterConfig = null;
    protected String encoding = "";

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        if(encoding != null)
            servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy()
    {
        filterConfig = null;
        encoding = null;
    }

    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");

    }
}
