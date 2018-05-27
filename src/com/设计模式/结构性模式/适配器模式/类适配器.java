package com.设计模式.结构性模式.适配器模式;

/**
 * Created by 周杰伦 on 2018/5/25.
 */
public class 类适配器 {
}

interface Target {
    void f1();
    void f2();
    void f3();
}

class SomeClass {
    public void f1() {

    }
    public void f2(){

    }
}
//把SomeClass适配成Target，仅仅通过继承就可以实现
class SomeAdaptor extends SomeClass implements Target {

    @Override
    public void f3() {
        System.out.println("真正实现的方法");
    }

}
