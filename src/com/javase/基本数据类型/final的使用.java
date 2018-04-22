package com.javase.基本数据类型;

import com.sun.xml.internal.ws.api.server.SDDocument;
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
        final int s ;
        Si(int s) {
            this.s = s;
        }
    }
    class Bi {
        final int a = 1;
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
    public void final初始化() {
        Fi fi = new Fi();

    }

    @Test
    public void final修饰引用() {
        final String a = "a";
        final
        //a = "b";报错
        String b = new String("b");
//        b = "a";
    }
    @Test
    public void final修饰基本类型变量和数组() {

    }
}
