package com.javase.面向对象.多态;

import java.util.Arrays;

/**
 * Created by 周杰伦 on 2018/4/21.
 */
public class 方法重载优先级匹配 {
    public static void main(String[] args) {
        方法重载优先级匹配 a = new 方法重载优先级匹配();
        //普通的重载一般就是同名方法不同参数。
        //这里我们来讨论当同名方法只有一个参数时的情况。
        //此时会调用char参数的方法。
        //当没有char参数的方法。会调用int类型的方法，如果没有int就调用long
        //即存在一个调用顺序char -> int -> long ->double -> ..。
        //当没有基本类型对应的方法时，先自动装箱，调用包装类方法。
        //如果没有包装类方法，则调用包装类实现的接口的方法。
        //最后再调用持有多个参数的char...方法。
        a.eat('a');
        a.eat('a','c','b');
    }
    public void eat(short i) {
        System.out.println("short");
    }
    public void eat(int i) {
        System.out.println("int");
    }
    public void eat(double i) {
        System.out.println("double");
    }
    public void eat(long i) {
        System.out.println("long");
    }
    public void eat(Character c) {
        System.out.println("Character");
    }
    public void eat(Comparable c) {
        System.out.println("Comparable");
    }
    public void eat(char ... c) {
        System.out.println(Arrays.toString(c));
        System.out.println("...");
    }

//    public void eat(char i) {
//        System.out.println("char");
//    }

}
