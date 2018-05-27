package com.设计模式.创建型模式;

import lombok.Builder;

/**
 * Created by 周杰伦 on 2018/5/25.
 */

class User {
    // 下面是“一堆”的属性
    private String name;
    private String password;
    private String nickName;
    private int age;

    //单纯链式调用，不使用建造者模式写法
    public static void main(String[] args) {
        User d = User.builder()
                .name("foo")
                .password("pAss12345")
                .age(25)
                .build();
    }
    // 构造方法私有化，不然客户端就会直接调用构造方法了
    private User(String name, String password, String nickName, int age) {
        this.name = name;
        this.password = password;
        this.nickName = nickName;
        this.age = age;
    }
    // 静态方法，用于生成一个 Builder，这个不一定要有，不过写这个方法是一个很好的习惯，
    // 有些代码要求别人写 new User.UserBuilder().a()...build() 看上去就没那么好
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        // 下面是和 User 一模一样的一堆属性
        private String  name;
        private String password;
        private String nickName;
        private int age;

        private UserBuilder() {
        }

        // 链式调用设置各个属性值，返回 this，即 UserBuilder
        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        // build() 方法负责将 UserBuilder 中设置好的属性“复制”到 User 中。
        // 当然，可以在 “复制” 之前做点检验
        public User build() {
            if (name == null || password == null) {
                throw new RuntimeException("用户名和密码必填");
            }
            if (age <= 0 || age >= 150) {
                throw new RuntimeException("年龄不合法");
            }
            // 还可以做赋予”默认值“的功能
            if (nickName == null) {
                nickName = name;
            }
            return new User(name, password, nickName, age);
        }
    }
}
//题外话，强烈建议读者使用 lombok，用了 lombok 以后，上面的一大堆代码会变成如下这样:

//@Builder
//class user {
//    private String  name;
//    private String password;
//    private String nickName;
//    private int age;
//}

class UserBean {
    private String  name;
    private String password;
    private String nickName;
    private int age;

    public String getName() {
        return name;
    }

    public UserBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserBean setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public UserBean setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserBean setAge(int age) {
        this.age = age;
        return this;
    }
}
public class 建造者模式 {
    public static void main(String[] args) {
        //使用静态内部类Builder完成对象创建
        User d = User.builder()
                .name("foo")
                .password("pAss12345")
                .age(25)
                .build();
        //使用注解的建造者模式
//        user u = user.builder()
//                .age(11)
//                .name("aa")
//                .nickName("da")
//                .password("782")
//                .build();
        //不使用建造者模式只用链式写法
        UserBean userBean = new UserBean()
                .setAge(11)
                .setName("ds")
                .setPassword("dasdas");
    }
}