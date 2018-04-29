package com.javase.泛型;

import org.junit.jupiter.api.Test;

/**
 * Created by 周杰伦 on 2018/4/28.
 */
public class 泛型方法 {
    //注意泛型类先写类名再写泛型，泛型方法先写泛型再写方法名
    //类中声明的泛型在成员和方法中可用
    class A <T, E>{
        {
            T t1 ;
        }
        A (T t){
            this.t = t;
        }
        T t;

        public void test1() {
            System.out.println(this.t);
        }

        public void test2(T t,E e) {
            System.out.println(t);
            System.out.println(e);
        }
    }
    @Test
    public void run () {
        A <Integer,String > a = new A<>(1);
        a.test1();
        a.test2(2,"ds");
//        1
//        2
//        ds
    }

    static class B <T>{
        T t;
        public void go () {
            System.out.println(t);
        }
    }
    @Test
    public void test() {
        test1();
        test2(new Integer(2));
        test3(new int[3],new Object());

        //打印结果
//        null
//        2
//        [I@3d8c7aca
//        java.lang.Object@5ebec15
    }
    //该方法使用泛型T
    public <T> void test1() {
        T t = null;
        System.out.println(t);
    }
    //该方法使用泛型T
    //并且参数和返回值都是T类型
    public <T> T test2(T t) {
        System.out.println(t);
        return t;
    }

    //该方法使用泛型T,E
    //参数包括T,E
    public <T, E> void test3(T t, E e) {
        System.out.println(t);
        System.out.println(e);
    }

    /**
     * 如果在类中定义使用泛型的静态方法，需要添加额外的泛型声明（将这个方法定义成泛型方法）
     * 即使静态方法要使用泛型类中已经声明过的泛型也不可以。
     * 如：public static void show(T t){..},此时编译器会提示错误信息：
     "StaticGenerator cannot be refrenced from static context"
     */
    public static <T> void show(T t){

    }
}
