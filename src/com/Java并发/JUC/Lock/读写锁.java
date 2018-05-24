package com.Java并发.JUC.Lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by 周杰伦 on 2018/5/20.
 */
public class 读写锁 {
    public static void main(String[] args) {

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        List list = new ArrayList();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                writeLock.lock();
                while (i < 100) {
                    list.add(i ++);
//                    writeLock.unlock();
                }
                writeLock.unlock();
            }
        }).start();
        new Thread(new Runnable() {
            int i = 0;
            @Override
            public void run() {
                writeLock.lock();

                while (i < 200) {
                    list.add((i++) * 2);
                }
                writeLock.unlock();
            }
        }).start();

        new Thread(new Runnable() {
            int i = 0;
            @Override
            public void run() {
                readLock.lock();
                while (i < list.size()) {
                    System.out.println(list.get(i ++));
                }
                readLock.unlock();
            }
        }).start();

        new Thread(new Runnable() {
            int i = 100;
            @Override
            public void run() {
                readLock.lock();
                while (i > 0) {
                    System.out.println(list.get(list.size() - 1));
                    i --;
                }
                readLock.unlock();
            }
        }).start();
    }
}
