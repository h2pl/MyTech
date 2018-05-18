package com.Java并发.并发三问题;

import java.util.concurrent.CountDownLatch;

/**
 * Created by 周杰伦 on 2018/5/17.
 */
public class 有序性 {

}
class Test {

    private static int x = 0, y = 0;
    private static int a = 0, b =0;

    //多线程中如果不加同步机制，会产生交叉操作，不符合有序性。
    //JMM模型下即使加锁时也允许同步块代码重排序，而volatile只是不允许volatile之前或者之后的代码进行重排序。
    //实际上都不保证严格的有序性
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for(;;) {
            i++;
            x = 0; y = 0;
            a = 0; b = 0;
            CountDownLatch latch = new CountDownLatch(1);

            Thread one = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                }
                a = 1;
                x = b;
            });

            Thread other = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                }
                b = 1;
                y = a;
            });
            one.start();other.start();
            latch.countDown();
            one.join();other.join();

            String result = "第" + i + "次 (" + x + "," + y + "）";
            if(x == 0 && y == 0) {
                System.err.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }
}
