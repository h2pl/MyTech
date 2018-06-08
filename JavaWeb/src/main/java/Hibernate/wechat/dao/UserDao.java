package Hibernate.wechat.dao;

/**
 * Created by 周杰伦 on 2018/6/7.
 */


import java.util.List;

import Hibernate.wechat.entity.po.User;

public interface UserDao {
    // 得到所有用户
    public List<User> getAllUser();

    // 检测用户名是否存在
    public boolean isExists(String username);

}