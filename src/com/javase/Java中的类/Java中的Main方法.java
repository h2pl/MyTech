package com.javase.Java中的类;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created by 周杰伦 on 2018/4/24.
 */
public class Java中的Main方法 {
    public static void main(String[] args) {
        main(1);
        System.out.println(args);
        System.out.println(Arrays.toString(args));
        //打印出数组地址[Ljava.lang.String;@4dc63996
    }
    //可以有同名方法重载函数，但是调用入口一定是第一个方法，这是jvm规定的。
    public static void main(int x) {
        System.out.println(x);
    }
}
class MainDemo1
{
    //运行时需要指定main方法是哪一个，并不一定是public类的main方法
    public static void main(String[] args)
    {
        System.out.println(args.length);
        //System.out.println(args[1]);//这样是错误的

        System.out.println("MainDemo----1");
    }
}