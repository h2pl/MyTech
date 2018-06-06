package 门面设计模式;

import org.apache.catalina.connector.*;
import org.apache.catalina.core.*;

/**
 * Created by 周杰伦 on 2018/6/6.
 */
public class exm {
    public static void main(String[] args) {
        StandardWrapperFacade facade = new StandardWrapperFacade(new StandardWrapper());
        ApplicationContextFacade applicationContextFacade = new ApplicationContextFacade(new ApplicationContext(new StandardContext()));
        RequestFacade requestFacade = new RequestFacade(new Request(new Connector()));
        ResponseFacade responseFacade = new ResponseFacade(new Response());
    }
}
