package com.设计模式.创建型模式;

/**
 * Created by 周杰伦 on 2018/5/25.
 */
public class 原型模式 {
    public static void main(String[] args) {
        Prototype prototype = new Prototype();
        try {
            Prototype p1 = (Prototype) prototype.clone();
            Prototype p2 = (Prototype) prototype.clone();
            System.out.println(p1);
            System.out.println(p2);
            System.out.println(prototype);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
//这是我要说的创建型模式的最后一个设计模式了。
//
//        原型模式很简单：有一个原型实例，基于这个原型实例产生新的实例，也就是“克隆”了。
//
//        Object 类中有一个 clone() 方法，它用于生成一个新的对象，当然，如果我们要调用这个方法，java 要求我们的类必须先实现 Cloneable 接口，此接口没有定义任何方法，但是不这么做的话，在 clone() 的时候，会抛出 CloneNotSupportedException 异常。
class Prototype implements Cloneable{
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}