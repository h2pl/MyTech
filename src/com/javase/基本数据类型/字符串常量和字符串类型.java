package com.javase.基本数据类型;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created by 周杰伦 on 2018/4/22.
 */
public class 字符串常量和字符串类型 {
    @Test
    public void finalString () {
        //3变量加final即可变为常量
        final String f1 = "a";
        final String f2 = "a" + f1;

        //String是final修饰的类，但是只是他不能被继承而已，没有其他限制，引用依然可变。
        String a = new String("a");
        a = new String("b");
        a = "s";

        //string底层是final数组，不能改变引用地址，但是可以改变数组里的内容。
        final char[] arr = "abc".toCharArray();
        arr[0] = 'd';
        //arr = "add".toCharArray();报错。

        System.out.println(Arrays.toString(arr));
        //注意，此处必须f1和f2都是常量才可以，否则又涉及变量相加
        System.out.println(f2 == "a" + "a"); //t
    }
    @Test
    public void contact () {
        //1连接方式
        String s1 = "a";
        String s2 = "a";
        String s3 = "a" + s2;
        String s4 = "a" + "a";
        String s5 = s1 + s2;
        //表达式只有常量时，编译期完成计算
        //表达式有变量时，运行期才计算，所以地址不一样
        System.out.println(s3 == s4); //f
        System.out.println(s3 == s5); //f
        System.out.println(s4 == "aa"); //t

    }
    public void intern () {
        //2：string的intern使用
        //s1是基本类型，比较值。s2是string实例，比较实例地址
        //字符串类型用equals方法比较时只会比较值
        String s1 = "a";
        String s2 = new String("a");
        //调用intern时,如果s2中的字符不在常量池，则加入常量池并返回常量的引用
        String s3 = s2.intern();
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
    }
    //字符串的equals方法
//    public boolean equals(Object anObject) {
//            if (this == anObject) {
//                return true;
//            }
//            if (anObject instanceof String) {
//                String anotherString = (String)anObject;
//                int n = value.length;
//                if (n == anotherString.value.length) {
//                    char v1[] = value;
//                    char v2[] = anotherString.value;
//                    int i = 0;
//                    while (n-- != 0) {
//                        if (v1[i] != v2[i])
//                            return false;
//                        i++;
//                    }
//                    return true;
//                }
//            }
//            return false;
//        }

}
