package com.javase.Class和Object.Object方法;

import com.javase.Class和Object.Object方法.用到的类.User;

/**
 * Created by 周杰伦 on 2018/4/30.
 */
public class getClass方法 {
    public static void main(String[] args) {
        User user = new User();
        //getclass方法是native方法，可以取到堆区唯一的Class<User>对象
        Class<?> aClass = user.getClass();
        Class bClass = User.class;
        try {
            Class cClass = Class.forName("com.javase.Class和Object.Object方法.用到的类.User");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(aClass);
        System.out.println(bClass);
//        class com.javase.Class和Object.Object方法.用到的类.User
//        class com.javase.Class和Object.Object方法.用到的类.User
        try {
            User a = (User) aClass.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
