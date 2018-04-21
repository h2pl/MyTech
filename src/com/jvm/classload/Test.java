package com.jvm.classload;

/**
 * Created by 周杰伦 on 2018/4/1.
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        String className = "classloader.NetworkClass";
        NetworkClassLoader networkClassLoader = new NetworkClassLoader();
        Class<?> clazz  = networkClassLoader.loadClass(className);
    }
}
