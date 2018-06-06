package Filter和listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by 周杰伦 on 2018/6/6.
 */
@WebListener
public class ContentListener implements ServletContextListener {


    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("listener 容器初始化开始");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("listener 容器销毁");
    }
}

