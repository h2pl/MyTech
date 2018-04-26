---
title: java基础4：深入理解final关键字
date: 2018-04-23 11:41:32
tags:
	- Java基础
categories:
	- 后端
	- Java
---
本文主要介绍了final关键字的使用方法及原理

具体代码在我的GitHub中可以找到
> https://github.com/h2pl/MyTech

文章首发于我的个人博客：
> https://h2pl.github.io/2018/04/23/javase4

更多关于Java后端学习的内容请到我的CSDN博客上查看：

https://blog.csdn.net/a724888
<!-- more -->

> final关键字可以修饰类、方法和引用。
> 
> 修饰类，该类不能被继承。并且这个类的对象在堆中分配内存后地址不可变。
> 
> 修饰方法，方法不能被子类重写。
> 
> 修饰引用，引用无法改变，对于基本类型，无法修改值，对于引用，虽然不能修改地址值，但是可以对指向对象的内部进行修改。

比如char[0] = 'a'。不改变对象内存地址，只改变了值。

具体看一下下面的栗子：


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

## final修饰类

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

## final修饰方法

    @Test
    public void final修饰方法() {
        Bi bi = new Bi();
        bi.go();//该方法无法被子类Ci重写

    }
    
## final修饰基本数据类型变量和引用

    @Test
    public void final修饰基本类型变量和引用() {
        final int a = 1;
        final int[] b = {1};
        final int[] c = {1};
    //  b = c;报错
        b[0] = 1;
        final String aa = "a";
        final Fi f = new Fi();
        //aa = "b";报错
        // f = null;//报错
        f.a = 1;
    }
关于字符串的内容可以在上一节查看：

https://blog.csdn.net/a724888/article/details/80042298