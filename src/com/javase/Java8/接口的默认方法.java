package com.javase.Java8;

import org.junit.jupiter.api.Test;

/**
 * Created by 周杰伦 on 2018/5/6.
 */
public class 接口的默认方法 {
    class B implements A {
//        void a(){}实现类方法不能重名
    }
    interface A {
        //java8接口可以声明静态方法了、
        static void put(){
            System.out.println("dasdas");
        }
        //可以有多个默认方法
        public default void a(){
            System.out.println("a");
        }
        public default void b(){
            System.out.println("b");
        }
        //报错static和default不能同时使用
//        public static default void c(){
//            System.out.println("c");
//        }
    }
    @Test
    public void test() {
        B b = new B();
        b.a();
        A.put();

    }
}
