package com.javase.异常;

import com.sun.jmx.snmp.tasks.ThreadService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by 周杰伦 on 2018/4/27.
 */

public class 多线程的异常 {
    @Test
    public void test() {
        go();
    }
    public void go () {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0;i <= 2;i ++) {
            int finalI = i;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("start thread" + finalI);
                        throw new Exception();
                    }catch (Exception e) {
                        System.out.println("thread" + finalI + " go wrong");
                    }
                }
            });
        }
//        结果：
//        start thread0
//        thread0 go wrong
//        start thread1
//        thread1 go wrong
//        start thread2
//        thread2 go wrong
    }
}