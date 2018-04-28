package com.javase.异常;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by 周杰伦 on 2018/4/27.
 */
public class 异常处理方式 {

    @Test
    public void main() {
        try{
            //try块中放可能发生异常的代码。
            InputStream inputStream = new FileInputStream("a.txt");

            //如果执行完try且不发生异常，则接着去执行finally块和finally后面的代码（如果有的话）。
            int i = 1/0;
            //如果发生异常，则尝试去匹配catch块。
            throw new SQLException();
            //使用1.8jdk同时捕获多个异常，runtimeexception也可以捕获。只是捕获后虚拟机也无法处理，所以不建议捕获。
        }catch(SQLException | IOException | ArrayIndexOutOfBoundsException exception){
            System.out.println(exception.getMessage());
            //每一个catch块用于捕获并处理一个特定的异常，或者这异常类型的子类。Java7中可以将多个异常声明在一个catch中。

            //catch后面的括号定义了异常类型和异常参数。如果异常与之匹配且是最先匹配到的，则虚拟机将使用这个catch块来处理异常。

            //在catch块中可以使用这个块的异常参数来获取异常的相关信息。异常参数是这个catch块中的局部变量，其它块不能访问。

            //如果当前try块中发生的异常在后续的所有catch中都没捕获到，则先去执行finally，然后到这个函数的外部caller中去匹配异常处理器。

            //如果try中没有发生异常，则所有的catch块将被忽略。

        }catch(Exception exception){
            System.out.println(exception.getMessage());
            //...
        }finally{
            //finally块通常是可选的。
            //无论异常是否发生，异常是否匹配被处理，finally都会执行。

            //finally主要做一些清理工作，如流的关闭，数据库连接的关闭等。
        }


        try {
            int i = 1;
        }finally {
            //一个try至少要有一个catch块，否则， 至少要有1个finally块。但是finally不是用来处理异常的，finally不会捕获异常。
        }
    }

    @Test
    public void test() {
        try {
            throwE();
            System.out.println("我前面抛出异常了");
            System.out.println("我不会执行了");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(e.getCause());
        }catch (Exception ex) {
            try {
                throw new Exception();
            }catch (Exception ee) {

            }finally {
                System.out.println("我所在的catch块没有执行，我也不会执行的");
            }
        }
    }
    //在方法声明中抛出的异常必须由调用方法处理或者继续往上抛，
    // 当抛到jre时由于无法处理终止程序
    public void throwE (){
//        Socket socket = new Socket("127.0.0.1", 80);

        //手动抛出异常时，不会报错，但是调用该方法的方法需要处理这个异常，否则会出错。
//        java.lang.StringIndexOutOfBoundsException
//        at com.javase.异常.异常处理方式.throwE(异常处理方式.java:75)
//        at com.javase.异常.异常处理方式.test(异常处理方式.java:62)
        throw new StringIndexOutOfBoundsException();
    }
}
