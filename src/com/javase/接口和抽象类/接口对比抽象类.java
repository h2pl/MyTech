package com.javase.接口和抽象类;

import org.junit.jupiter.api.Test;

/**
 * Created by 周杰伦 on 2018/4/23.
 */

class A {

}
interface M extends N,L{

}
interface N{

}
interface L{

}
interface 接口 {
    public final int i = 1;//变量默认都为public final修饰
    final A a = null;//基本数据类型和引用都一样
    //protected void a();//报错
    //private //报错
    public abstract void a();// 方法都是public abstract修饰的。

    //void b(){} 报错，接口里的方法不能有方法体，也不能有{}，只能有()；

    // final void b();
    // 注意，抽象方法不能加final。因为final方法不能被重写。
    //但如果抽象方法不被重写那就没有意义了，因为他根本没有代码体。

}
abstract class 抽象类 {
    public final int i = 1;//变量并没有被pulic和final修饰，只是一般的成员变量
    public final A a = null;

    private void A(){}//抽象类可以有具体方法
    abstract void AA();//抽象方法没有方法体

    //private abstract void B();//报错，组合非法
    // 因为private修饰的方法无法被子类重写，所以和final一样，使抽象方法无法被实现。

}

//抽象类也可以被实例化，举例说明
abstract class B{
    B() {
        System.out.println("b init");
    }
}

class C extends B{
    C(){
        super();
        System.out.println("c init");
    }
}

public class 接口对比抽象类 {
    @Test
    public void test() {
        C c = new C();
        //结果先实例化B，再实例化C。
        //因为会调用到父类的构造方法。
    }
}



