package com.javase.泛型;

import org.junit.jupiter.api.Test;

/**
 * Created by 周杰伦 on 2018/4/29.
 */
public class 泛型和可变参数 {
    @Test
    public void test () {
        printMsg("dasd",1,"dasd",2.0,false);
        print("dasdas","dasdas", "aa");
    }
    //普通可变参数只能适配一种类型
    public void print(String ... args) {
        for(String t : args){
            System.out.println(t);
        }
    }
    //泛型的可变参数可以匹配所有类型的参数。。有点无敌

    public <T> void printMsg( T... args){
        for(T t : args){
            System.out.println(t);
        }
    }
    //打印结果：
    //dasd
    //1
    //dasd
    //2.0
    //false

}
