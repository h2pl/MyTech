package com.javase.回调和委托机制.回调;


import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by 周杰伦 on 2018/4/26.
 */
public class 多线程中的回调 {
    //这里简单地使用future和callable实现了线程执行完后
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("call");
                TimeUnit.SECONDS.sleep(1);
                return "str";
            }
        });
        //手动阻塞调用get通过call方法获得返回值。
        System.out.println(future.get());
        //需要手动关闭，不然线程池的线程会继续执行。
        executor.shutdown();

        //使用futuretask同时作为线程执行单元和数据请求单元。
        FutureTask<Integer> futureTask = new FutureTask(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("dasds");
                return new Random().nextInt();
            }
        });
        new Thread(futureTask).start();
        //阻塞获取返回值
        System.out.println(futureTask.get());
    }
    @Test
    public void test () {
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        };
        FutureTask futureTask = new FutureTask(callable);

    }
}
