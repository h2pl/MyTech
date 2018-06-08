package Mybatis;

import Hibernate.wechat.entity.po.User;

/**
 * Created by 周杰伦 on 2018/6/7.
 */
public class FooServiceImpl implements FooService {

    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User doSomeBusinessStuff(String userId) {
        return this.userMapper.getUser(userId);
    }
}
