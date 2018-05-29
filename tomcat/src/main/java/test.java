import com.sun.deploy.net.protocol.ProtocolType;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

/**
 * Created by 周杰伦 on 2018/5/27.
 */
public class test {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        Connector connector1 = new Connector("HTTP/1.1");
        connector1.setPort(8080);
        Connector connector2 = new Connector("HTTP/1.1");
        connector2.setPort(8087);
        tomcat.setConnector(connector2);

        tomcat.start();
        tomcat.getServer().await();
    }
}
