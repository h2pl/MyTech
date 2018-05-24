package com.Java并发.JUC.Lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by 周杰伦 on 2018/5/19.
 */
public class AQS {
    public static void main(String[] args) {
        AbstractQueuedSynchronizer aqs = new AbstractQueuedSynchronizer() {
            @Override
            protected boolean tryAcquire(int arg) {
                return super.tryAcquire(arg);
            }
        };


    }
}
