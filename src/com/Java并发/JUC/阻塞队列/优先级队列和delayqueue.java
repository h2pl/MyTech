package com.Java并发.JUC.阻塞队列;

import java.lang.reflect.Parameter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by 周杰伦 on 2018/5/24.
 */
public class 优先级队列和delayqueue {
    public static void main(String[] args) {
//        DelayQueue<Delayed> queue = new DelayQueue();
//        for (int i = 0;i < 5;i ++) {
//            int finalI = i;
//            queue.put(new Delayed() {
//                @Override
//                public long getDelay(TimeUnit unit) {
//                    return finalI;
//                }
//
//                @Override
//                public int compareTo(Delayed o) {
//                    return (int) (this.getDelay(TimeUnit.SECONDS) - o.getDelay(TimeUnit.SECONDS));
//                }
//            });
//        }
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        System.out.println(queue.take());
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();

        PriorityBlockingQueue<Integer> queue1 = new PriorityBlockingQueue();
        for (int i = 10;i >= 1;i --) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    queue1.put(finalI);
                }
            }).start();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        int i = queue1.take();
                        System.out.println(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
