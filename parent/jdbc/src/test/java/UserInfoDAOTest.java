/**
 * Created by 周杰伦 on 2018/6/7.
 */
import dao.UserInfoDAO;
import entity.UserInfoBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import javax.annotation.Resource;
import static org.junit.Assert.*;

/**
 * 作用:
 * User: duqi
 * Date: 2017/6/24
 * Time: 09:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-h2-applicationContext.xml")
public class UserInfoDAOTest {

    @Resource
//    private UserInfoDAO userInfoDAO;

    @Test
    public void saveUserInfoBean() throws Exception {
//        UserInfoBean userInfoBean = new UserInfoBean();
//        userInfoBean.setUserId(1003L);
//        userInfoBean.setNickname("wangwu");
//        userInfoBean.setMobile("18890987675");
//        userInfoBean.setSex(1);
//        userInfoBean.setUpdateTime(new Date());
//        userInfoBean.setCreateTime(new Date());

//        int rows = userInfoDAO.saveUserInfoBean(userInfoBean);
//
//        assertEquals(1, rows);
    }

    @Test
    public void updateUserInfoBean() throws Exception {
    }

    @Test
    public void getUserInfoBeanByUserId() throws Exception {
    }

    @Test
    public void getUserInfoBeanByMobile() throws Exception {
    }

    @Test
    public void listUserInfoBeanByUserIds() throws Exception {
    }

    @Test
    public void removeUserInfoBeanByUserId() throws Exception {
    }

}