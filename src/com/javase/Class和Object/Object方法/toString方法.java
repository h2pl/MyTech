package com.javase.Class和Object.Object方法;

import com.javase.Class和Object.Object方法.用到的类.User;

/**
 * Created by 周杰伦 on 2018/4/30.
 */
public class toString方法 {
    public static void main(String[] args) {
        User user = new User();
        User user2 = new User();
        System.out.println(user);
        //打印默认就是打印tostring的结果。
        System.out.println(user.toString());
        System.out.println(user2.toString());
//        com.javase.Class和Object.Object方法.用到的类.User@4dc63996
//        com.javase.Class和Object.Object方法.用到的类.User@4dc63996
//        com.javase.Class和Object.Object方法.用到的类.User@d716361
    }
}
