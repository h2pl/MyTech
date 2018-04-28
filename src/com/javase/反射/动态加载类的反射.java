package com.javase.反射;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by 周杰伦 on 2018/4/28.
 */
public class 动态加载类的反射 {
    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("com.javase.反射.UserBean");
            for (Field field : clazz.getDeclaredFields()) {
//                field.setAccessible(true);
                System.out.println(field);
            }
            //getDeclaredMethod*()获取的是类自身声明的所有方法，包含public、protected和private方法。
            System.out.println("------共有方法------");
//        getDeclaredMethod*()获取的是类自身声明的所有方法，包含public、protected和private方法。
//            getMethod*()获取的是类的所有共有方法，这就包括自身的所有public方法，和从基类继承的、从接口实现的所有public方法。
            for (Method method : clazz.getMethods()) {
                String name = method.getName();
                System.out.println(name);
                //打印出了UserBean.java的所有方法以及父类的方法
            }
            System.out.println("------独占方法------");

            for (Method method : clazz.getDeclaredMethods()) {
                String name = method.getName();
                System.out.println(name);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
