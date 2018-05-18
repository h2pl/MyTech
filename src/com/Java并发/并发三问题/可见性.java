package com.Java并发.并发三问题;

/**
 * Created by 周杰伦 on 2018/5/17.
 */
public class 可见性 {
    volatile static boolean flag = false;
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                flag = true;
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(flag);
            }
        }).start();
    }
}
