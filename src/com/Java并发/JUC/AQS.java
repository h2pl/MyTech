package com.Java并发.JUC;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 周杰伦 on 2018/5/18.
 */
public class AQS {
    //Lock的实现，就是引入内部抽象类类Sync继承AQS，lock操作通过两个实现类来完成
    class myLock implements Lock{
        final Sync sync;

        myLock() {
            sync = new FairSync();
        }

        abstract class Sync extends AbstractQueuedSynchronizer {

        }

        class FairSync extends Sync {

        }

        class UnFairSync extends Sync {

        }

        @Override
        public void lock() {
            sync.acquire(1);
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
            lock();
        }

        @Override
        public boolean tryLock() {
            try {
                sync.tryAcquireNanos(1000, 1l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void unlock() {
            sync.release(1);
        }

        @Override
        public Condition newCondition() {
            return this.newCondition();
        }
    }
}
