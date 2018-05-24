package com.Java并发.JUC.Lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 周杰伦 on 2018/5/19.
 */
public class FairSync和UnfairSync {
    public static void main(String[] args) {
        Lock fairLock = new ReentrantLock(true);
        Lock unfairLock = new ReentrantLock(false);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
//        for (int i = 0;i < 10;i ++) {
//            int finalI = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
////                        cyclicBarrier.await();
//                        fairLock.lock();
//                        System.out.println(finalI);
//                        fairLock.unlock();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }

        for (int i = 0;i < 10;i ++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                        unfairLock.lock();
                        System.out.println(finalI);
                        unfairLock.unlock();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
