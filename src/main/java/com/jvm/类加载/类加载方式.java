package com.jvm.类加载;

/**
 * Created by 周杰伦 on 2018/6/8.
 */
public class 类加载方式 {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader loader = 类加载方式.class.getClassLoader();
        System.out.println(loader);
        //使用ClassLoader.loadClass()来加载类，不会执行初始化块
        loader.loadClass("com.jvm.类加载.Test2");
        //使用Class.forName()来加载类，默认会执行初始化块
                Class.forName("com.jvm.类加载.Test2");
        //使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
//                Class.forName("Test2", false, loader);
    }
}
