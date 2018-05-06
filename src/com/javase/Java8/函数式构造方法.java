package com.javase.Java8;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by 周杰伦 on 2018/5/6.
 */
public class 函数式构造方法 {
    public static void main(String[] args) {
        //无参构造函数
        Supplier<SomeClass> c1 = SomeClass::new;
        SomeClass s1 = c1.get();

        //无参构造函数
        Supplier<SomeClass> c2 = () -> new SomeClass();
        SomeClass s2 = c2.get();

        //有参构造函数，只能有一个参数
        Function<Integer, SomeClass> c3 = SomeClass::new;
        SomeClass s3 = c3.apply(100);

//等价于
        //有参构造函数。只能有一个参数
        Function<Integer, SomeClass> c4 = (i) -> new SomeClass(i);
        SomeClass s4 = c4.apply(100);
    }
}
class SomeClass {
    public SomeClass() {

    }
    public SomeClass(int i) {

    }
    public SomeClass(int i,int j) {

    }

    public SomeClass(Integer i, Object j) {

    }
}