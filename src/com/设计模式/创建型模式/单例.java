package com.设计模式.创建型模式;

/**
 * Created by 周杰伦 on 2018/5/25.
 */

//饿汉
class Singleton0{
    private static Singleton0 instance = new Singleton0();

    public static Singleton0 getInstance() {
        return instance;
    }
}
//非线程安全的饱汉
class Singleton1{
    private static Singleton1 instance = null;

    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}
//双重检查的饱汉
class Singleton2{
    private volatile static Singleton2 instance = null;

    public static Singleton2 getInstance() {
        if (instance == null) {
            synchronized (Singleton2.class) {
                if (instance == null) {
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }
}
//静态内部类
class Singleton3 {
    public static void main(String[] args) {
        System.out.println(Holder.instance);
    }
    private Singleton3() {}
    // 主要是使用了 嵌套类可以访问外部类的静态属性和静态方法 的特性
    //静态内部类要私有化，不然会被别人改了
    private static class Holder {
        private static Singleton3 instance = new Singleton3();
    }
    //暴露外部方法，这个不会修改到单例
    public static Singleton3 getInstance() {
        //同一个类里的私有变量也可以随便访问
        return Holder.instance;
    }
}

//枚举类
enum Singleton4 {
    INSTANCE
}
//枚举类实现单例
public class 单例 {
    public static void main(String[] args) {
        //JVM保证该实例唯一
        Singleton4 instance = Singleton4.INSTANCE;
    }
}
