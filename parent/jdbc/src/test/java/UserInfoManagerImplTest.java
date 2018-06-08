/**
 * Created by 周杰伦 on 2018/6/7.
 */
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

/**
 * 作用:
 * User: duqi
 * Date: 2017/6/24
 * Time: 09:55
 */
@RunWith(MockitoJUnitRunner.class)
public class UserInfoManagerImplTest {

//    @Mock //用于定义被Mock的组件
//    private UserInfoDAO userInfoDAO;
//
//    @InjectMocks //用于定义待测试的组件
//    private UserInfoManager userInfoManager = new UserInfoManagerImpl();
//
//    private UserInfo userInfoToSave;

//    @Before
//    public void setUp() throws Exception {
//        //用于初始化@Mock注解修饰的组件
//        MockitoAnnotations.initMocks(this);
//
//        userInfoToSave = new UserInfo();
//        userInfoToSave.setMobile("18978760099");
//        userInfoToSave.setUserId(7777L);
//        userInfoToSave.setSex(1);
//    }
//
//    @Test
//    public void saveUserInfo_case1() throws Exception {
//        //step1 准备数据和动作
//        doReturn(1).when(userInfoDAO).saveUserInfoBean(any(UserInfoBean.class));
//
//        //step2 运行待测试模块
//        Boolean res = userInfoManager.saveUserInfo(userInfoToSave);
//
//        //step3 验证测试结果
//        assertTrue(res);
//    }
//
//    @Test
//    public void saveUserInfo_case2() throws Exception {
//        //step1 准备数据和动作
//        doReturn(0).when(userInfoDAO).saveUserInfoBean(any(UserInfoBean.class));
//
//        //step2 运行待测试模块
//        Boolean res = userInfoManager.saveUserInfo(userInfoToSave);
//
//        //step3 验证测试结果
//        assertFalse(res);
//    }

    @Test
    public void updateUserInfo() throws Exception {
    }

    @Test
    public void getUserInfoByUserId() throws Exception {
    }

    @Test
    public void getUserInfoByMobile() throws Exception {
    }

    @Test
    public void listUserInfoByUserIds() throws Exception {
    }

    @Test
    public void removeUserInfoByUserId() throws Exception {
    }

}