package com.Java并发.并发基础与线程安全;

/**
 * Created by 周杰伦 on 2018/5/18.
 */
public class 线程安全 {
    public void func(int x) {
        //局部变量没有线程安全问题
        System.out.println(x);
    }

    //逃逸分析后不会溢出的变量也不会有线程安全问题。

    //栈上分配的对象不会有线程安全问题。

}
