package com.Java并发.JUC.Lock;

import sun.misc.Unsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 周杰伦 on 2018/5/19.
 */
public class ConditionTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition notEmpty = lock.newCondition();
        Condition notFull = lock.newCondition();
        List<Integer> list = new ArrayList<>();
        for (int i = 1;i <= 5;i ++) {
            list.add(i);
        }
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();
                        if (list.size() == 10) {
                            try {
                                notFull.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        list.add(new Random().nextInt(10));
                        notEmpty.signal();
                    } finally {
                        lock.unlock();
                    }
                }

            }
        });
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();
                        if (list.size() == 0) {
                            try {
                                notEmpty.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(list.remove(list.size() - 1));
                        notFull.signal();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });
        a.start();
        b.start();
    }
}
