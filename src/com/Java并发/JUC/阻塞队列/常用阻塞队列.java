package com.Java并发.JUC.阻塞队列;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by 周杰伦 on 2018/5/23.
 */
public class 常用阻塞队列 {
    public static void main(String[] args) {
//        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0;i < 10;i ++) {
//                    arrayBlockingQueue.offer(i);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    if (!arrayBlockingQueue.isEmpty()) {
//                        System.out.println(arrayBlockingQueue.poll());
//                    }
//                }
//            }
//        }).start();
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        new Thread(producer).start();
        new Thread(consumer).start();
    }
    static LinkedBlockingDeque<Integer> linkedBlockingDeque = new LinkedBlockingDeque(10);

    static class Producer implements Runnable{
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    linkedBlockingDeque.put(new Random().nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static class Consumer implements Runnable{
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println(linkedBlockingDeque.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
