import com.sun.xml.internal.stream.EventFilterSupport;
import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.core.*;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.util.LifecycleMBeanBase;
import org.apache.catalina.valves.ValveBase;
import org.apache.coyote.http11.Http11NioProtocol;
import sun.plugin.viewer.LifeCycleManager;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by 周杰伦 on 2018/5/29.
 */
public class TomcatTest {


    public static void main(String[] args) {
        //配置连接器和容器
        Connector connector = new Connector();
        connector.setPort(1234);
        Engine engine = new StandardEngine();
        Host host = new StandardHost();
        Context context = new StandardContext();
        Wrapper wrapper  = new StandardWrapper();

        //确定父子容器关系
        context.addChild(wrapper);
        host.addChild(context);
        engine.addChild(host);

        //注册监听器
        engine.findChildren()[0].addLifecycleListener(new LifecycleListener() {
            public void lifecycleEvent(LifecycleEvent event) {

            }
        });
        wrapper.addLifecycleListener(new LifecycleListener() {
            public void lifecycleEvent(LifecycleEvent event) {
            }
        });
        wrapper.addContainerListener(new ContainerListener() {
            public void containerEvent(ContainerEvent event) {

            }
        });

        //管理pipeline和valve
        wrapper.getPipeline().addValve(new Valve() {
            public Valve getNext() {
                return null;
            }

            public void setNext(Valve valve) {

            }

            public void backgroundProcess() {

            }

            public void invoke(Request request, Response response) throws IOException, ServletException {

            }

            public boolean isAsyncSupported() {
                return false;
            }
        });

        host.getPipeline().addValve(new ValveBase() {
            public void invoke(Request request, Response response) throws IOException, ServletException {

            }
        });

        //server管理service
        Service service = new StandardService();
        service.addConnector(connector);
        service.setContainer(engine);

        Server server = new StandardServer();
        server.addService(service);

        //启动服务器
        Tomcat tomcat = new Tomcat();
//        tomcat.setConnector(connector);
//        tomcat.setHost(host);
        try {
            //connector没问题
            tomcat.setConnector(connector);
            //但是串联成服务以后有问题，具体的问题就是start方法以后的事了，比较复杂。
//            tomcat.getServer().addService(service);
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }

}
