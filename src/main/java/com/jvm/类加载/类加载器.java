package com.jvm.类加载;

/**
 * Created by 周杰伦 on 2018/6/8.
 */
public class 类加载器 {
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());

    }
}
