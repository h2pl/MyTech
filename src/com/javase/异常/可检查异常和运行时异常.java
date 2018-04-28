package com.javase.异常;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by 周杰伦 on 2018/4/27.
 */
public class 可检查异常和运行时异常 {
    public static void main (String [] args )
    {
        System . out. println( "----欢迎使用命令行除法计算器----" ) ;
        CMDCalculate ();
    }
    public static void CMDCalculate ()
    {
        Scanner scan = new Scanner ( System. in );
        int num1 = scan .nextInt () ;
        int num2 = scan .nextInt () ;
        int result = devide (num1 , num2 ) ;
        System . out. println( "result:" + result) ;
        scan .close () ;
    }
    public static int devide (int num1, int num2 ){
        return num1 / num2 ;
    }

//    ----欢迎使用命令行除法计算器----
//            1
//            0
//    Exception in thread "main" java.lang.ArithmeticException: / by zero
//    at com.javase.异常.异常.devide(异常.java:24)
//    at com.javase.异常.异常.CMDCalculate(异常.java:19)
//    at com.javase.异常.异常.main(异常.java:12)


//  ----欢迎使用命令行除法计算器----
//    r
//    Exception in thread "main" java.util.InputMismatchException
//    at java.util.Scanner.throwFor(Scanner.java:864)
//    at java.util.Scanner.next(Scanner.java:1485)
//    at java.util.Scanner.nextInt(Scanner.java:2117)
//    at java.util.Scanner.nextInt(Scanner.java:2076)
//    at com.javase.异常.异常.CMDCalculate(异常.java:17)
//    at com.javase.异常.异常.main(异常.java:12)
@Test
public void testException() throws IOException
{
    //FileInputStream的构造函数会抛出FileNotFoundException
    FileInputStream fileIn = new FileInputStream("E:\\a.txt");

    int word;
    //read方法会抛出IOException
    while((word =  fileIn.read())!=-1)
    {
        System.out.print((char)word);
    }
    //close方法会抛出IOException
    fileIn.close();
}
}
