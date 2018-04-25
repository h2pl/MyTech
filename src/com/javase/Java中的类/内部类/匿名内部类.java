package com.javase.Java中的类.内部类;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Created by 周杰伦 on 2018/4/25.
 */
public class 匿名内部类 {

}
interface D{
    void run ();
}
abstract class E{
    E (){

    }
    abstract void work();
}
class A {

        @Test
        public void test (int k) {
            //利用接口写出一个实现该接口的类的实例。
            //有且仅有一个实例，这个类无法重用。
            new Runnable() {
                int j = k;
                @Override
                public void run() {
//                    k = 1;报错，当外部方法中的局部变量在内部类使用中必须改为final类型。
                    //因为方外部法中即使改变了这个变量也不会反映到内部类中。
                    //所以对于内部类来讲这只是一个常量。
                    System.out.println(100);
                    System.out.println(k);
                }
            };
            new D(){
                //实现接口的匿名类
                int i =1;
                @Override
                public void run() {
                    System.out.println("run");
                    System.out.println(i);
                    System.out.println(k);
                }
            }.run();
            new E(){
                //继承抽象类的匿名类
                int i = 1;
                void run (int j) {
                    j = 1;
                }

                @Override
                void work() {

                }
            };
        }

}

