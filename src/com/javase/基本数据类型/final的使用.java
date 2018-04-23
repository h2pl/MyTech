package com.javase.基本数据类型;

import org.junit.jupiter.api.Test;

/**
 * Created by 周杰伦 on 2018/4/22.
 */
public class final的使用 {
    final class Fi {
        int a;
        final int b = 0;
        Integer s;

    }
    class Si{
        //一般情况下final修饰的变量一定要被初始化。
        //只有下面这种情况例外，要求该变量必须在构造方法中被初始化。
        //并且不能有空参数的构造方法。
        //这样就可以让每个实例都有一个不同的变量，并且这个变量在每个实例中只会被初始化一次
        //于是这个变量在单个实例里就是常量了。
        final int s ;
        Si(int s) {
            this.s = s;
        }
    }
    class Bi {
        final int a = 1;
        final void go() {
            //final修饰方法无法被继承
        }
    }
    class Ci extends Bi {
        final int a = 1;
//        void go() {
//            //final修饰方法无法被继承
//        }
    }
    final char[]a = {'a'};
    final int[]b = {1};
    @Test
    public void final修饰类() {
        //引用没有被final修饰，所以是可变的。
        //final只修饰了Fi类型，即Fi实例化的对象在堆中内存地址是不可变的。
        //虽然内存地址不可变，但是可以对内部的数据做改变。
        Fi f = new Fi();
        f.a = 1;
        System.out.println(f);
        f.a = 2;
        System.out.println(f);
        //改变实例中的值并不改变内存地址。

        Fi ff = f;
        //让引用指向新的Fi对象，原来的f对象由新的引用ff持有。
        //引用的指向改变也不会改变原来对象的地址
        f = new Fi();
        System.out.println(f);
        System.out.println(ff);
    }


    @Test
    public void final修饰方法() {
        Bi bi = new Bi();
        bi.go();//该方法无法被子类Ci重写

    }
    @Test
    public void final修饰基本类型变量和引用() {
        final int a = 1;
        final int[] b = {1};
        final int[] c = {1};
//        b = c;报错
        b[0] = 1;
        final String aa = "a";
        final Fi f = new Fi();
        //aa = "b";报错
        // f = null;//报错
        f.a = 1;
    }
}
