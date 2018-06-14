package com.jvm.内存泄漏与内存溢出;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 周杰伦 on 2018/6/13.
 */
public class 内存溢出 {
    //方法区内存溢出
    public static void main(String[] args) {
        int i = 0;
//        //注意设置栈方法区最大值，不然压根不溢出
//        while (true) {
//            Delegate delegate = new Delegate();
//            class Handler implements InvocationHandler {
//                Object object;
//
//                public Handler(Object o) {
//                    this.object = o;
//                }
//
//                @Override
//                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//
//                    method.invoke(object, args);
//                    return null;
//                }
//            }
//            User delegate1 = (User) Proxy.newProxyInstance(delegate.getClass().getClassLoader(), delegate.getClass().getInterfaces(), new Handler(delegate));
//            delegate1.run();
//            System.out.println(++i);
//
//        }
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Delegate.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    System.out.println("before");
                    return methodProxy.invokeSuper(o, objects);
                }
            });
            Delegate delegate = (Delegate) enhancer.create();
            delegate.run();
            System.out.println(++i);
        }
    }

    //1,6和以前会显示pergem space，
    //1.7
    //Caused by: java.lang.OutOfMemoryError: Metaspace
//    at java.lang.ClassLoader.defineClass1(Native Method)
//    at java.lang.ClassLoader.defineClass(ClassLoader.java:763)
//            ... 20 more

    //栈内存
    @Test
    public void test1() {
        while (true) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println("run");
                    }
                }
            }).start();
        }
    }

    //栈溢出 9723
//9724
//        9725
//    java.lang.StackOverflowError
//    at sun.nio.cs.UTF_8$Encoder.encodeLoop(UTF_8.java:691)

    int a = 0;
    @Test
    public void test11() {
        System.out.println(++a);
        test11();
    }

    //堆内存
    @Test
    public void test2() {
        List list = new ArrayList();
        while (true) {
            list.add(new Object());
        }
    }
//    Exception in thread "main" java.lang.OutOfMemoryError: Java heap space

    int i = 0;
    //常量池内存溢出，1.8常量池跑到元空间里了
    @Test
    public void test3() {

        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
        //Error occurred during initialization of VM
//        OutOfMemoryError: Metaspace


    }

    interface User{
        void run();
    }
    static class Delegate implements User{

        @Override
        public void run() {
            System.out.println("run");
        }
    }
}
