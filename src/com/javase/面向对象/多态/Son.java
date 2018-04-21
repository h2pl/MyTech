package com.javase.面向对象.多态;

/**
 * Created by 周杰伦 on 2018/4/21.
 */
public class Son extends Father{
    public void drive() {
        System.out.println("son ride bike");
    }
    public void play () {
        System.out.println("son play ball");
    }
    public void smoke() {
        System.out.println("son somke ");
    }
    public void smoke(int i) {
        System.out.println("son somke ");
    }
}
