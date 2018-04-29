package com.javase.泛型;

/**
 * Created by 周杰伦 on 2018/4/28.
 */
public class Generic<T>{
    //在类中声明的泛型整个类里面都可以用，除了静态部分，因为泛型是实例化时声明的。
    //静态区域的代码在编译时就已经确定，只与类相关

    public void showKeyValue(Generic<Number> obj){
        System.out.println(obj);
    }

    public void test () {
        Generic<Integer> gInteger = new Generic<Integer>(123);
        Generic<Number> gNumber = new Generic<Number>(456);
//        showKeyValue(gInteger);该方法会报错
//        showKeyValue(gInteger);
    }

    public void showKeyValue1(Generic<?> obj) {
        System.out.println(obj);
    }
    // showKeyValue这个方法编译器会为我们报错：Generic<java.lang.Integer>
    // cannot be applied to Generic<java.lang.Number>
    // showKeyValue(gInteger);

    class A <E>{
        T t;
    }
    //类里面的方法或类中再次声明同名泛型是允许的，并且该泛型会覆盖掉父类的同名泛型T
    class B <T>{
        T t;
    }
    //静态内部类也可以使用泛型，实例化时赋予泛型实际类型
    static class C <T> {
        T t;
    }
    public static void main(String[] args) {
        //报错，不能使用T泛型，因为泛型T属于实例不属于类
//        T t = null;
    }

    //key这个成员变量的类型为T,T的类型由外部指定
    private T key;

    public Generic(T key) { //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    public T getKey(){ //泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }
}
