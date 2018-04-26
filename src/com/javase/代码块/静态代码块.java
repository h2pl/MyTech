package com.javase.代码块;

import org.junit.jupiter.api.Test;

/**
 * Created by 周杰伦 on 2018/4/24.
 */
public class 静态代码块 {

    @Test
    public void test() {
        C c1 = new C();
        C c2 = new C();
        //结果,静态代码块只会调用一次，类的所有对象共享该代码块
        //一般用于类的全局信息初始化
        //静态代码块调用
        //代码块调用
        //构造方法调用
        //代码块调用
        //构造方法调用
    }

}
class C{
    C(){
        System.out.println("构造方法调用");
    }
    {
        System.out.println("代码块调用");
    }
    static {
        System.out.println("静态代码块调用");
    }
}
