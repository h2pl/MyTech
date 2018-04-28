package com.javase.异常;

import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Created by 周杰伦 on 2018/4/27.
 */
public class 异常链 {
    @Test
    public void test() {
        C();
    }
    public void A () throws Exception {
        try {
            int i = 1;
            i = i / 0;
            //当我注释掉这行代码并使用B方法抛出一个error时，运行结果如下
//            四月 27, 2018 10:12:30 下午 org.junit.platform.launcher.core.ServiceLoaderTestEngineRegistry loadTestEngines
//            信息: Discovered TestEngines with IDs: [junit-jupiter]
//            java.lang.Error: B也犯了个错误
//            at com.javase.异常.异常链.B(异常链.java:33)
//            at com.javase.异常.异常链.C(异常链.java:38)
//            at com.javase.异常.异常链.test(异常链.java:13)
//            at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
//            Caused by: java.lang.Error
//            at com.javase.异常.异常链.B(异常链.java:29)

        }catch (ArithmeticException e) {
            //这里通过throwable类的构造方法将最底层的异常重新包装并抛出，此时注入了A方法的信息。
            //如果直接抛出，栈信息打印结果只能看到上层方法的错误信息，不能看到其实是A发生了错误。
            //所以需要包装并抛出
            throw new Exception("A方法计算错误", e);
        }

    }
    public void B () throws Exception,Error {
        try {
            //接收到A的异常，
            A();
            throw new Error();
        }catch (Exception e) {
            throw e;
        }catch (Error error) {
            throw new Error("B也犯了个错误", error);
        }
    }
    public void C () {
        try {
            B();
        }catch (Exception | Error e) {
            e.printStackTrace();
        }

    }

    //最后结果
//    java.lang.Exception: A方法计算错误
//    at com.javase.异常.异常链.A(异常链.java:18)
//    at com.javase.异常.异常链.B(异常链.java:24)
//    at com.javase.异常.异常链.C(异常链.java:31)
//    at com.javase.异常.异常链.test(异常链.java:11)
//    省略
//    Caused by: java.lang.ArithmeticException: / by zero
//    at com.javase.异常.异常链.A(异常链.java:16)
//            ... 31 more
}
