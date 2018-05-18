package com.Java并发.多线程基础;


/**
 * Created by 周杰伦 on 2018/5/3.
 */
public class 线程的状态转换 {
    //一开始线程是init状态，结束时是terminated状态
    class t implements Runnable {
        private String name;
        public t(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println(name + "run");
        }
    }

    //测试join，父线程在子线程运行时进入waiting状态
//    @Test
    public void test1() throws InterruptedException {
        Thread dad = new Thread(new Runnable() {
            Thread son = new Thread(new t("son"));
            @Override
            public void run() {
                System.out.println("dad init");
                son.start();
                try {
                    //保证子线程运行完再运行父线程
                    son.join();
                    System.out.println("dad run");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //调用start，线程进入runnable状态，等待系统调度
        dad.start();
        //在父线程中对子线程实例使用join，保证子线程在父线程之前执行完

    }

    //测试sleep
//    @Test
    public void test2(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 run");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //主线程休眠。进入time waiting状态
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();

    }

    //线程2进入blocked状态。
    public static void main(String[] args) {
        test4();
        Thread.yield();//进入runnable状态
    }

    //测试blocked状态
    public static void test4() {
        class A {
            //线程1获得实例锁以后线程2无法获得实例锁，所以进入blocked状态
            synchronized void run() {
                while (true) {
                    System.out.println("run");
                }
            }
        }
        A a = new A();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 get lock");
                a.run();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 get lock");
                a.run();
            }
        }).start();

    }

    //volatile保证线程可见性
    volatile static int flag = 1;
    //object作为锁对象，用于线程使用wait和notify方法
    volatile static Object o = new Object();
    //测试wait和notify
    //wait后进入waiting状态，被notify进入blocked（阻塞等待锁释放）或者runnable状态（获取到锁）
    public void test5() {
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
