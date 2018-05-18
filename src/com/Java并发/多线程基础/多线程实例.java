package com.Java并发.多线程基础;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by 周杰伦 on 2018/5/3.
 */
public class 多线程实例 {

    //继承thread
    @Test
    public void test1() {
        class A extends Thread {
            @Override
            public void run() {
                System.out.println("A run");
            }
        }
        A a = new A();
        a.start();
    }

    //实现Runnable
    @Test
    public void test2() {
        class B implements Runnable {

            @Override
            public void run() {
                System.out.println("B run");
            }
        }
        B b = new B();
        //Runable实现类需要由Thread类包装后才能执行
        new Thread(b).start();
    }

    //有返回值的线程
    @Test
    public void test3() {
        Callable callable = new Callable() {
            int sum = 0;
            @Override
            public Object call() throws Exception {
                for (int i = 0;i < 5;i ++) {
                    sum += i;
                }
                return sum;
            }
        };
        //这里要用FutureTask，否则不能加入Thread构造方法
        FutureTask futureTask = new FutureTask(callable);
        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    //线程池实现
    @Test
    public void test4() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //execute直接执行线程
        executorService.execute(new Thread());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable");
            }
        });
        //submit提交有返回结果的任务，运行完后返回结果。
        Future future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "a";
            }
        });
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ArrayList<String> list = new ArrayList<>();
        //有返回值的线程组将返回值存进集合
        for (int i = 0;i < 5;i ++ ) {
            int finalI = i;
            Future future1 = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "res" + finalI;
                }
            });
            try {
                list.add((String) future1.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        for (String s : list) {
            System.out.println(s);
        }
    }
}
