package com.Java并发.JUC.并发工具类;

import java.util.concurrent.*;

/**
 * Created by 周杰伦 on 2018/5/23.
 */
public class countdownlatch测试 {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        CountDownLatch countDownLatch = new CountDownLatch(10);
//        final int[] sum = {0};
//        for (int i = 0;i < 10;i ++) {
//            int finalI = i;
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    sum[0] += finalI;
//                    System.out.println("thread " + finalI + " add sum to " + sum[0]);
//                    countDownLatch.countDown();
//                }
//            });
//        }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    countDownLatch.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("finally the sum = " + sum[0]);
//            }
//        }).start();
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
//        for (int i = 0;i < 10;i ++) {
//            int finalI = i;
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        //等待起跑
//                        cyclicBarrier.await();
//                        //全部起跑
//                        System.out.println(finalI + " run");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (BrokenBarrierException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }

//        Semaphore semaphore = new Semaphore(5);
//        for (int i = 0;i < 10;i ++) {
//            int finalI = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        semaphore.acquire();
//                        System.out.println(finalI);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }finally {
////                        semaphore.release();
//                    }
//                }
//            }).start();
//        }

        Exchanger<Integer> exchanger = new Exchanger<>();
        for (int i = 0;i < 5;i ++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("num of thread" + finalI + "  is " + finalI);
                        int i = exchanger.exchange(finalI);
                        System.out.println("get of thread " + finalI + " is " + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
