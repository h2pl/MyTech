package com.Java并发.JUC.并发容器;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by 周杰伦 on 2018/5/23.
 */
public class 写时复制容器 {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        for(int i = 0;i < 10;i ++){
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    list.add(finalI);
                }
            }).start();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    int i = list.get(list.size() - 1);
                    System.out.println(i);
                }
            }
        }).start();
    }
}
