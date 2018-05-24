package com.Java并发.JUC.线程池;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by 周杰伦 on 2018/5/23.
 */
public class future和callable {
    public static void main(String[] args) {
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return 3;
            }
        };
        Future future = new Future() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Object get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };


        //callable和future的组合需要搭配线程池才能使用，否则没有人调用call方法。
        Callable callable1  = new Callable() {
            @Override
            public Object call() throws Exception {
                return 2;
            }
        };
        try {
            //callable可以自己调用call获取结果。这和runnable自己调用run一样，不会开启新线程
            System.out.println(callable1.call());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //futuretask则可以自己使用，因为他即是任务又是结果
        FutureTask futureTask = new FutureTask(callable);
        try {
            //这也是调用内部方法，不会开启新线程
            futureTask.run();
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



        Future f = new FutureTask(callable);
        try {
            //这样调用f仍然获取不到值。而future本身不提供启动方法，扑街。
            callable.call();
            System.out.println(f.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future f1 = executorService.submit(callable);
        try {
            System.out.println(f1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }

    public void test() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0;i < 10;i ++) {
            Callable callable1 = new Callable() {
                @Override
                public Object call() throws Exception {
                    return new Random().nextInt(10);
                }
            };
            Future futureTask = executorService.submit(callable1);
        }
    }

}
