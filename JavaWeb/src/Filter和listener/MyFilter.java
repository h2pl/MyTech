package Filter和listener;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by 周杰伦 on 2018/6/6.
 */
public class MyFilter implements Filter {

    private String param;

    //初始化方法，在容器启动时调用
    public void init(FilterConfig filterConfig) throws ServletException {
        //做一些初始化操作
        param = filterConfig.getInitParameter("myParam");
        System.out.println("filter:"+param);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //处理请求
        System.out.println("do filter");
        chain.doFilter(request,response); //调用下一个过滤器
        //处理响应
    }

    @Override
    public void destroy() {
        //在servlet销毁后销毁
        //做一些销毁后的善后工作
    }
}
