package com.jvm.四种引用;

import java.lang.ref.SoftReference;

/**
 * Created by 周杰伦 on 2018/6/13.
 */
public class 软引用 {

}
class SoftReferenceTest {

    public static int M = 1024*1024;

    public static void printlnMemory(String tag){
        Runtime runtime = Runtime.getRuntime();
        int M = StrongReferenceTest.M;
        System.out.println("\n"+tag+":");
        System.out.println(runtime.freeMemory()/M+"M(free)/" + runtime.totalMemory()/M+"M(total)");
    }

    public static void main(String[] args){
        SoftReferenceTest.printlnMemory("1.原可用内存和总内存");

        //建立软引用
        SoftReference<Object> softRerference = new SoftReference<Object>(new byte[10*SoftReferenceTest.M]);
        SoftReferenceTest.printlnMemory("2.实例化10M的数组,并建立软引用");
        System.out.println("softRerference.get() : "+softRerference.get());

        System.gc();
        SoftReferenceTest.printlnMemory("3.内存可用容量充足，GC后");
        System.out.println("softRerference.get() : "+softRerference.get());

        //实例化一个4M的数组,使内存不够用,并建立软引用
        //free=10M=4M+10M-4M,证明内存可用量不足时，GC后byte[10*m]被回收
        SoftReference<Object> softRerference2 = new SoftReference<Object>(new byte[4*SoftReferenceTest.M]);
        SoftReferenceTest.printlnMemory("4.实例化一个4M的数组后");
        System.out.println("softRerference.get() : "+softRerference.get());
        System.out.println("softRerference2.get() : "+softRerference2.get());
    }
}
