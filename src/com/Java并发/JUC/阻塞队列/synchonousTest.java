package com.Java并发.JUC.阻塞队列;

import java.util.concurrent.SynchronousQueue;

/**
 * Created by 周杰伦 on 2018/5/23.
 */
public class synchonousTest {
    public static void main(String[] args) {
        SynchronousQueue<Integer> queue = new SynchronousQueue();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        queue.put(finalI);
                        System.out.println("Thread " + finalI + " return");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 5;i ++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int k = queue.take();
                        System.out.println("thread " + finalI + " get " + k);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        //如果还有线程阻塞，则程序继续运行
    }
}
