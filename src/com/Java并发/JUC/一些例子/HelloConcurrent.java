package com.Java并发.JUC.一些例子;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Concurrent library learning
 */
public class HelloConcurrent {

    public static void main(String[] args) {
//        runDAfterABC();

        runABCWhenAllReady();

//        doTaskWithResultInWorker();
    }

    private static void doTaskWithResultInWorker() {
        //创建线程池
        ExecutorService executor = Executors.newCachedThreadPool();

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Task starts");
                Thread.sleep(1000);
                int result = 0;
                for (int i=0; i<=100; i++) {
                    result += i;
                }
                System.out.println("Task finished and return result");
                return result;
            }
        };

        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable) {
            @Override
            protected void done() {
                System.out.println("Before futureTask.get(). ThreadName: " + Thread.currentThread().getName());
                try {
                    System.out.println("Result: " + get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println("After futureTask.get()");
            }
        };
        executor.submit(futureTask);
        executor.shutdown();
        System.out.println("End of main thread. ThreadName: " + Thread.currentThread().getName());
    }


    /**
     * A B C starts running when all three are ready.
     */
    private static void runABCWhenAllReady() {
        int runner = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(runner);

        final Random random = new Random();
        for (char runnerName='A'; runnerName <= 'C'; runnerName++) {
            final String rN = String.valueOf(runnerName);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long prepareTime = random.nextInt(10000) + 100;
                    System.out.println(rN + " is preparing for time: " + prepareTime);
                    try {
                        Thread.sleep(prepareTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        System.out.println(rN + " is prepared, waiting for others");
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                    System.out.println(rN + " starts running");
                }
            }).start();
        }
    }

    /**
     * Only if A, B, C are all finished, D starts working
     */
    private static void runDAfterABC() {
        int worker = 3;
        CountDownLatch countDownLatch = new CountDownLatch(worker);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("D is waiting for other three threads");
                try {
                    countDownLatch.await();
                    System.out.println("All done, D starts working");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        for (char threadName='A'; threadName <= 'C'; threadName++) {
            final String tN = String.valueOf(threadName);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(tN + " is working");
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    System.out.println(tN + " finished");
                    countDownLatch.countDown();
                }
            }).start();
        }
    }

}