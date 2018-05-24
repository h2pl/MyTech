package com.Java并发.JUC.原子操作类;

import com.javase.JavaIO.包二.A;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.*;

/**
 * Created by 周杰伦 on 2018/5/23.
 */
public class 原子类 {
    static volatile int num = 0;
    static class A{

    }
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        AtomicLong atomicLong = new AtomicLong(1l);
        A a = new A();
        AtomicReference atomicReference = new AtomicReference(a);
        atomicReference.compareAndSet(a, new A());
        A[] aa = null;
        AtomicReferenceArray atomicReferenceArray = new AtomicReferenceArray(aa);

        System.out.println(atomicInteger.intValue());
        CyclicBarrier cyclicBarrier = new CyclicBarrier(100);

        for (int i = 0 ;i < 100;i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
//                    num++;
//                    System.out.println(num);
                    //保证原子性操作
                    System.out.println(atomicInteger.incrementAndGet());
                }
            }).start();
        }


    }
}
