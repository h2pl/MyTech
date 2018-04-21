package com.javase.面向对象.继承;

/**
 * Created by 周杰伦 on 2018/4/5.
 */
public class Son extends Father{
    public void go () {
        System.out.println("son go");
    }
    public void eat () {
        System.out.println("son eat");
    }
    public void sleep() {
        System.out.println("zzzzzz");
    }
    public void cook() {
        //匿名内部类实现的多继承
        new Mother().cook();
        //内部类继承第二个父类来实现多继承
        Mom mom = new Mom();
        mom.cook();
    }
    private class Mom extends Mother {
        @Override
        public void cook() {
            System.out.println("mom cook");
        }
    }
}
