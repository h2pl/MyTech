package com.Java集合;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by 周杰伦 on 2018/5/8.
 */
public class ArrayList和Vector和Stack {
    public static void main(String[] args) {
        //一般讨论集合类无非就是。这里的两种数组类型更是如此
        // 1底层数据结构
        // 2增删改查方式
        // 3线程安全与否
        // 4初始容量，扩容方式，扩容时机。
        // 5是否允许空，是否允许重复，是否有序

        ArrayList list = new ArrayList();
        Vector vector = new Vector();
        Stack stack = new Stack();
    }

    @Test
    public void testArr() {
        ArrayList<Object> list = new ArrayList<>();
        Object obj = new Object();
        list.add(obj);
        list.add(0, new Object());
        System.out.println(list.size());
        //remove可以针对下标或者对象
        list.remove(obj);
        //set必须设置存在的下标
        list.set(0, obj);
        System.out.println(list.size());
        System.out.println(list.get(0));

//        2
//        1
//        java.lang.Object@3d8c7aca

        //modcount
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    list.add(0, obj);
                }
            }
        }).start();
        while (true) {
            for (Object o : list) {
                System.out.println(o);
            }
        }
//          抛出异常，使用迭代器时如果有其他线程进行了结构性修改，则抛出该异常
//        java.util.ConcurrentModificationException
//        at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901)
//        at java.util.ArrayList$Itr.next(ArrayList.java:851)

    }

    @Test
    public void testArr2() {
        ArrayList list = new ArrayList(Integer.MAX_VALUE);
        //这里只会显示实际元素
        System.out.println(list.size());
        //堆内存不够，理论上容量大小可以到最大整数
//        Exception in thread "main" java.lang.OutOfMemoryError: Requested array size exceeds VM limit
//        at java.util.ArrayList.<init>(ArrayList.java:152)

        //扩容方式是1.5被扩容，线程不安全。
    }

    @Test
    public void testVector() {
        Vector vector = new Vector();
        System.out.println(vector.capacity());
        System.out.println(vector.size());
        //10
        //1
        for (int i = 0;i < 10;i ++) {
            vector.add(1);
        }
        System.out.println(vector.capacity());
        System.out.println(vector.size());
        //10
        //10
        vector.add(1);
        System.out.println(vector.capacity());
        System.out.println(vector.size());
        //两倍扩容。capcity为原来的两倍
//        20
//        11

        //线程安全

    }

    @Test
    public void testStack() {
        Stack stack = new Stack();
        stack.push(1);
        System.out.println(stack.size());
        System.out.println(stack.capacity());
        //stack继承vector，所以方法也是类似
        stack.peek();
        stack.pop();
        for (int i = 0;i <= 10;i ++) {
            stack.add(i);
        }
        System.out.println(stack.size());
        System.out.println(stack.capacity());
    }
    //11
    //20
}
