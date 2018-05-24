package com.Java并发.JUC.线程池;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by 周杰伦 on 2018/5/23.
 */
public class 线程池测试 {
    public static void main(String[] args) {
        //有些线程池方法不要在test方法中跑，会出错
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            for (int i = 0;i < 10;i ++) {
                int finalI = i;
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //先等待两秒，5个核心线程一起做完，其余五个任务加入等待队列，再等待两秒，五个线程把5个任务做完
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(finalI);
                    }
                });
            }

            ExecutorService executorService1 = Executors.newCachedThreadPool();
            for (int i = 0;i < 10;i ++) {
                int finalI = i;
                executorService1.submit(new Runnable() {
                    @Override
                    public void run() {

                        System.out.println(finalI);
                        try {
                            //开启十个线程，一起做完
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            ExecutorService executorService2 = Executors.newSingleThreadExecutor();
            for (int i = 0;i < 10;i ++) {
                int finalI = i;
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {

                        System.out.println(finalI);
                        try {
                            //开启十个线程，一起做完
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        for (int i = 0;i < 10;i ++) {
            int finalI = i;
            executor.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI);
                }
            },finalI, TimeUnit.SECONDS);
        }
        executor.shutdown();


    }
    @Test
    public void test1() throws InterruptedException {
        //只需开启一个线程
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0;i < 10;i ++) {
            int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI);
                }
            });
            Thread.sleep(2000);
        }
    }

    @Test
    public void test2() throws InterruptedException {
        //只需开启一个线程
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0;i < 10;i ++) {
            int finalI = i;
            Runnable a = new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI);
                }
            };
            executorService.submit(a);
            Thread.sleep(1000);
        }
    }

    @Test
    public void test3() throws InterruptedException {
        //只需开启一个线程
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0;i < 10;i ++) {
            int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI);
                }
            });
            Thread.sleep(1000);
        }
    }

    @Test
    public void test4() throws InterruptedException {

    }
}
