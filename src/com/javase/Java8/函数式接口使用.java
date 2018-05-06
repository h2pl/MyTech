package com.javase.Java8;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Function;

/**
 * Created by 周杰伦 on 2018/5/6.
 */
public class 函数式接口使用 {

    @FunctionalInterface
    interface A {
        void say();
        default void talk() {

        }
    }
    @Test
    public void test1() {
        A a = () -> System.out.println("hello");
        a.say();
    }

    @FunctionalInterface
    interface B {
        void say(String i);
    }
    public void test2() {
        //下面两个是等价的，都是通过B接口来引用一个方法，而方法可以直接使用::来作为方法引用
        B b = System.out::println;
        B b1 = a -> Integer.parseInt("s");//这里的a其实换成别的也行，只是将方法传给接口作为其方法实现
        B b2 = Integer::valueOf;//i与方法传入参数的变量类型一直时，可以直接替换
        B b3 = String::valueOf;
        //B b4 = Integer::parseInt;类型不符，无法使用

    }
    @FunctionalInterface
    interface C {
        int say(String i);
    }
    public void test3() {
        C c = Integer::parseInt;//方法参数和接口方法的参数一样，可以替换。
        int i = c.say("1");
        //当我把C接口的int替换为void时就会报错，因为返回类型不一致。
        System.out.println(i);
        //综上所述，lambda表达式提供了一种简便的表达方式，可以将一个方法传到接口中。
        //函数式接口是只提供一个抽象方法的接口，其方法由lambda表达式注入，不需要写实现类，
        //也不需要写匿名内部类，可以省去很多代码，比如实现runnable接口。
        //函数式编程就是指把方法当做一个参数或引用来进行操作。除了普通方法以外，静态方法，构造方法也是可以这样操作的。
    }
    @Test
    public void test4() {
        //Function前面是传入参数类型，后面是返回参数类型
        Function<String, Integer> stringToInteger = (String s) -> Integer.parseInt(s);
        Function<Object, Integer> objectToInteger = (dashkdas) -> Integer.parseInt("a");
        //如果方法有返回值，（）里要用变量名。Function<Double, Object> voidToObject = () -> Integer.parseInt("1");//

    }

    static void add(double a,String b) {
        System.out.println(a + b);
    }
    @Test
    public void test5() {
        D d = (a,b) -> add(a,b);
//        interface D {
//            void get(int i,String j);
//        }
        //这里要求，add的两个参数和get的两个参数吻合并且返回类型也要相等，否则报错
//        static void add(double a,String b) {
//            System.out.println(a + b);
//        }
    }

    @FunctionalInterface
    interface D {
        void get(int i,String j);
    }
    @Test
    public void test() {
        //方法中有参数时，需要用e，不能用(),当然用(e)也可以
        Arrays.asList( "a", "b", "d" ).forEach(e -> System.out.println( e ) );
        //请注意参数e的类型是由编译器推测出来的。同时，你也可以通过把参数类型与参数包括在括号中的形式直接给出参数的类型：
        //但是你如果写了错误的参数类型，比如Integer，系统会编译错误，
        Arrays.asList( "a", "b", "d" ).forEach( ( String e ) -> System.out.println( e ) );
        //在某些情况下lambda的函数体会更加复杂，这时可以把函数体放到在一对花括号中，就像在Java中定义普通函数一样。例如：
        //e的参数类型要和foreach传入类型一致。
        Arrays.asList( "a", "b", "d" ).forEach( e -> {
            System.out.print( e );
            System.out.print( e );
        } );
//        Lambda可以引用类的成员变量与局部变量（如果这些变量不是final的话，它们会被隐含的转为final，这样效率更高）。例如，下面两个代码片段是等价的：
        String separator = ",";
        Arrays.asList( "a", "b", "d" ).forEach(
                (e ) -> System.out.print( e + separator ) );

//        Lambda可能会返回一个值。返回值的类型也是由编译器推测出来的。如果lambda的函数体只有一行的话，那么没有必要显式使用return语句。下面两个代码片段是等价的：
        //参数类型要么都写要么都不写，否则报错
        Arrays.asList( "a", "b", "d" ).sort( ( String e1, String e2 ) -> e1.compareTo( e2 ) );
        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {
        int result = e1.compareTo( e2 );
        return result;
    } );
    }


}
