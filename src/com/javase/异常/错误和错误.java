package com.javase.异常;

/**
 * Created by 周杰伦 on 2018/4/27.
 */
//错误即error一般指jvm无法处理的错误
public class 错误和错误 {
    Error error = new Error();

    public static void main(String[] args) {
        throw new Error();
    }

    //下面这四个异常或者错误有着不同的处理方法
    public void error1 (){
        //编译期要求必须处理，因为这个异常是最顶层异常，包括了检查异常，必须要处理
        try {
            throw new Throwable();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
    //Exception也必须处理。否则报错，因为检查异常都继承自exception，所以默认需要捕捉。
    public void error2 (){
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //error可以不处理，编译不报错,原因是虚拟机根本无法处理，所以啥都不用做
    public void error3 (){
        throw new Error();
    }

    //runtimeexception众所周知编译不会报错
    public void error4 (){
        throw new RuntimeException();
    }
//    Exception in thread "main" java.lang.Error
//    at com.javase.异常.错误.main(错误.java:11)

}

