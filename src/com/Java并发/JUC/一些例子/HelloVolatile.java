package com.Java并发.JUC.一些例子;

/**
 * Created by Jay on 3/22/17.
 */
public class HelloVolatile {
    public static void main(String[] args) {
//        testVolatile();
//        printIncNumberByThreeThreads();
//        testWaitNotify();
        testSingleton();
        //        testSynchronizedObject();
    }

    private static void testSingleton() {
        for (int i=0; i<100; i++) {
            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Want to fetch singleton object in thread: " + index);
                    getSingleton();
                }
            }).start();
        }
    }

    static Object singleton;
    private synchronized static Object getSingleton() {
        if (singleton == null) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("create singleTon, this method must only occur once");
            singleton = new Object();
        }
        return singleton;
    }

    private static void testSynchronizedObject() {
        final Object lock = new Object();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("thread1 running, start sleep");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread1 finish sleep");
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread2 running");
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("thread3 running, it will wait for thread1 ending");
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static void testWaitNotify() {
        final Object lock = new Object();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("thread1 running, start waiting lock");
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread1 finish wait");
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("thread2 running, ready notify lock");
                    lock.notify();
                    System.out.println("thread2 running, notified lock");
                }
            }
        });

        thread1.start();
        thread2.start();
    }

    static int count = 1;
    public static void printIncNumberByThreeThreads() {
        HelloVolatile helloVolatile = new HelloVolatile();
        for (int i=1; i<=3; i++) {
            new PrintThread(i, helloVolatile).start();
        }
    }

    private static class PrintThread extends Thread {
        // use wait/notify for better performance
        int id;
        final HelloVolatile helloVolatile;

        PrintThread(int id, HelloVolatile helloVolatile) {
            this.id = id;
            this.helloVolatile = helloVolatile;
        }

        @Override
        public void run() {
            super.run();
            synchronized (helloVolatile) {
                while (count <= 750) {
                    System.out.println("Thread is working, " + id);
                    if (((count-1) / 5) % 3 == (id-1)) {
                        System.out.println("Thread " + id + " : " + count++);
                        helloVolatile.notifyAll();
                    } else {
                        try {
                            helloVolatile.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }

    private static class SimplePrintThread extends Thread {
        // don't use wait/notify, it will do more useless work in the while(){...}
        int id;
        HelloVolatile helloVolatile;

        SimplePrintThread(int id, HelloVolatile helloVolatile) {
            this.id = id;
            this.helloVolatile = helloVolatile;
        }

        @Override
        public void run() {
            super.run();
            while (count <= 75) {
                System.out.println("Thread is working, " + id);
                if (((count-1) / 5) % 3 == (id-1)) {
                    System.out.println("Thread " + id + " : " + count++);
                }
            }
        }
    }

    public static void testVolatile() {
        for (int i=0; i<2000; i++) {
            new Thread(String.valueOf(i)) {
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {

                    }
                    System.out.println("prepare to add : " + count + " in thread: " + Thread.currentThread().getName());
                    count++;
                }
            }.start();
        }

        try {
            Thread.sleep(100);
        } catch (Exception e) {}
        System.out.println("count: " + count);
    }

}