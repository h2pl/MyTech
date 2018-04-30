package com.javase.Class和Object.Object方法.用到的类;

/**
 * Created by 周杰伦 on 2018/4/30.
 */
public class User implements Cloneable{
    public int id;
    public String name;
    public UserInfo userInfo;

    public static void main(String[] args) {
        User user = new User();
        UserInfo userInfo = new UserInfo();
        user.userInfo = userInfo;
        System.out.println(user);
        System.out.println(user.userInfo);
        try {
            User copy = (User) user.clone();
            System.out.println(copy);
            System.out.println(copy.userInfo);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
    //拷贝的User实例与原来不一样，是两个对象。
//    com.javase.Class和Object.Object方法.用到的类.User@4dc63996
//    com.javase.Class和Object.Object方法.用到的类.UserInfo@d716361
        //而拷贝后对象的userinfo引用对象是同一个。
    //所以这是浅拷贝
//    com.javase.Class和Object.Object方法.用到的类.User@6ff3c5b5
//    com.javase.Class和Object.Object方法.用到的类.UserInfo@d716361
}
