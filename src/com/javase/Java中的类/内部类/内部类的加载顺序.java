package com.javase.Java中的类.内部类;

import org.junit.jupiter.api.Test;

/**
 * Created by 周杰伦 on 2018/4/25.
 */
public class 内部类的加载顺序 {
    @Test
    public void test () {
        Outer outer = new Outer();
        //只实例化外部类
        Outer.A a = outer.new A();
        Outer.A aa = outer.new A();

        //A实例化
        //A实例化
        System.out.println(B.s);

        B b = new B();
        B bb = new B();

    }

}

class Outer {
    public void go () {

    }
    Outer (){
        System.out.println("外部类实例化");
    }
    @Test
    public void test () {
        A a = new A();
        B b = new B();

    }
    class A {
        int i = 1;
        int j = 2;
        public void test () {
            System.out.println("A使用");
        }
        A () {
            System.out.println(2);
            System.out.println("A实例化");
        }
    }
    static class B {
        static int i = 1;
        static int j = 2;

        public void test () {
            System.out.println("B使用");
        }
        B () {
            System.out.println("B实例化");
            System.out.println(j);
        }
    }
}
