package com.javase.Class和Object.Object方法;

/**
 * Created by 周杰伦 on 2018/4/30.
 */
public class wait和notify {
    //volatile保证线程可见性
    volatile static int flag = 1;
    //object作为锁对象，用于线程使用wait和notify方法
    volatile static Object o = new Object();
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //wait和notify只能在同步代码块内使用
                synchronized (o) {
                    while (true) {
                        if (flag == 0) {
                            try {
                                Thread.sleep(2000);
                                System.out.println("thread1 wait");
                                //释放锁，线程挂起进入object的等待队列，后续代码运行
                                o.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("thread1 run");
                        System.out.println("notify t2");
                        flag = 0;
                        //通知等待队列的一个线程获取锁
                        o.notify();
                    }
                }
            }
        }).start();
        //解释同上
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (o) {
                        if (flag == 1) {
                            try {
                                Thread.sleep(2000);
                                System.out.println("thread2 wait");
                                o.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("thread2 run");
                        System.out.println("notify t1");
                        flag = 1;
                        o.notify();
                    }
                }
            }
        }).start();
    }

    //输出结果是
//    thread1 run
//    notify t2
//    thread1 wait
//    thread2 run
//    notify t1
//    thread2 wait
//    thread1 run
//    notify t2
//不断循环
}
