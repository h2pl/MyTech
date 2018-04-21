package com.javase.面向对象.多态;

/**
 * Created by 周杰伦 on 2018/4/21.
 */
public class 静态分派 {
    public static void main(String[] args) {
        Father father  = new Son();
        静态分派 a= new 静态分派();

        //编译期确定引用类型为Father。
        //所以调用的是第一个方法。
        a.play(father);
        //向下转型后，引用类型为Son，此时调用第二个方法。
        //所以，编译期只确定了引用，运行期再进行实例化。
        a.play((Son)father);
        //当没有Son引用类型的方法时，会自动向上转型调用第一个方法。
        a.smoke(father);
        //
    }
    public void smoke(Father father) {
        System.out.println("father smoke");
    }
    public void play (Father father) {
        System.out.println("father");
        //father.drive();
    }
    public void play (Son son) {
        System.out.println("son");
        //son.drive();
    }

}
