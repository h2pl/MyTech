package test;

/**
 * Created by 周杰伦 on 2018/6/7.
 */
import entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class TestJDBCtemplate {



    public class TestJDBCTemplate {

        @Test
        public void test2() {

            ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
//            UserService userService = (UserService) ctx.getBean("userService");

            User user = new User();
            user.setPassword("111");
            user.setUsername("小王");

            JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        }

    }
}
