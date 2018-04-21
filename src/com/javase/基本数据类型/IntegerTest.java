package com.javase.基本数据类型;

/**
 * Created by 周杰伦 on 2018/4/21.
 */
public class IntegerTest {
    public static void main(String[] args) {
        //int的自动拆箱和装箱只在-128到127范围中进行，超过该范围的两个integer的 == 判断是会返回false的。
        Integer a1 = 128;
        Integer a2 = -128;
        Integer a3 = -128;
        Integer a4 = 128;
        System.out.println(a1 == a4);
        System.out.println(a2 == a3);
    }
}
